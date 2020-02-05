package Subway;

import MultiGraph.IEdge;
import MultiGraph.INode;
import MultiGraph.MultiGraphADT;

import java.io.IOException;
import java.util.*;

public class Driver {
    private MultiGraphADT<Station, Line> graph;

    private List<Station> dijkstraSearchRestricted(Station start, Station end, String lineName) {
        List<Station> unvisited = new ArrayList<>(graph.getNodes());
        List<Station> visited = new ArrayList<>();

        HashMap<Integer, Integer> pathLengths = new HashMap<>();
        HashMap<Integer, Station> previousStations = new HashMap<>();

        for (Station station : unvisited) {
            pathLengths.put(station.getId(), Integer.MAX_VALUE);
            previousStations.put(station.getId(), null);
        }

        pathLengths.put(start.getId(), 0);

        while (!unvisited.isEmpty()) {
            var currentStation = unvisited.stream().min(Comparator.comparing(x -> pathLengths.get(x.getId()))).get();
            unvisited.remove(currentStation);
            visited.add(currentStation);

            var currentLength = pathLengths.get(currentStation.getId());

            for (IEdge edge : currentStation.getEdges()) {
                Line line = (Line) edge;
                if (!line.lineName.equals(lineName)) continue;
                Station endStation = (Station) line.getEnd();
                if (!unvisited.contains(endStation)) {
                    continue;
                }

                Integer newLength = !currentLength.equals(Integer.MAX_VALUE) ? currentLength + line.getWeight()
                        : Integer.MAX_VALUE;
                if (newLength < pathLengths.get(endStation.getId())) {
                    pathLengths.put(endStation.getId(), newLength);
                    previousStations.put(endStation.getId(), currentStation);
                }
            }
        }

        Station cur = end;
        List<Station> path = new ArrayList<>();
        path.add(cur);
        while (!cur.equals(start)) {
            cur = previousStations.get(cur.getId());
            if (cur == null) return new ArrayList<>();
            path.add(cur);
        }

        return path;
    }

    private void dijkstraSearch(Station start, Station end) {
        List<List<Station>> paths = new ArrayList<>();
        for (IEdge edge : start.getEdges()) {
            Line line = (Line) edge;
            List<Station> path = dijkstraSearchRestricted(start, end, line.lineName);
            if (!path.isEmpty()) paths.add(path);
        }

        System.out.println(paths);
    }

    private Station getStationInput(Scanner scan) throws NoSuchStationException {
        String userInput = scan.nextLine();
        Integer stationId;
        try {
            stationId = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            if (!userInput.equals("exit")) {
                System.out.println("Please enter a number");
            }
            throw e;
        }
        Station station = (Station) graph.findNode(x -> x.getId() == stationId);
        if (station == null) {
            System.out.println("A station with this ID does not exist");
            throw new NoSuchStationException();
        }
        System.out.println(station.stationName);

        return station;
    }

    public static void main(String[] args) {
        MetroMapParser parser = new MetroMapParser();
        Driver routeFinder = new Driver();
        try {
            routeFinder.graph = parser.generateGraphFromFile("bostonmetro.txt");
        } catch (IOException | BadFileException e) {
            System.out.println(e);
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("exit")) {
            System.out.println("Please enter your starting station");
            Station start = null;
            try {
                start = routeFinder.getStationInput(input);
            } catch (NumberFormatException | NoSuchStationException e) {
                continue;
            }

            System.out.println("Please enter your final destination");
            Station end = null;
            try {
                end = routeFinder.getStationInput(input);
            } catch (NumberFormatException | NoSuchStationException e) {
                continue;
            }

            System.out.println("Start station: " + start.stationName);
            System.out.println("End station: " + end.stationName);

            routeFinder.dijkstraSearch(start, end);

            System.out.println("Press enter to plan another route or type 'exit' to finish");
            userInput = input.nextLine();
        }
    }
}
