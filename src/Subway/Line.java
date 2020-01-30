package Subway;

import MultiGraph.Edge;

public class Line extends Edge {
    public String lineName;
    public Integer startId;
    public Integer endId;

    public Line(String lineName, Integer startId, Integer endId) {
        this.lineName = lineName;
        this.startId = startId;
        this. endId = endId;
    }

    public Line() {}
}
