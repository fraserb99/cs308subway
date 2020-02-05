package Subway;

import MultiGraph.MultiGraphADT;

import java.io.IOException;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        MetroMapParser parser = new MetroMapParser();
        MultiGraphADT graph = null;
        try {
            graph = parser.generateGraphFromFile("bostonmetro.txt");
        } catch (IOException | BadFileException e) {
            System.out.println(e);
            System.exit(1);
        }

        Scanner input = new Scanner(System.in);
        String userInput = "";
        while (userInput != "exit") {
            System.out.println("Enter a station id");
            userInput = input.nextLine();
            Integer stationId;
            try {
                stationId = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number");
                continue;
            }
            Station station = (Station)graph.findNode(x -> x.getId() == stationId);
            if (station == null) {
                System.out.println("A station with this ID does not exist");
                continue;
            }
            System.out.println(station.stationName);
        }
    }
}
