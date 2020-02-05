package Subway;

import MultiGraph.INode;
import MultiGraph.MultiGraphADT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Driver {
    private MultiGraphADT<Station, Line> graph;

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

            System.out.println("Press enter to plan another route or type 'exit' to finish");
            userInput = input.nextLine();
        }
    }
}
