package Subway;

import MultiGraph.Node;

public class Station extends Node {
    public Integer stationId;
    public String stationName;

    public Station(Integer stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }
}
