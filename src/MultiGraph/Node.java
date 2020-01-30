package MultiGraph;

import java.util.ArrayList;

public class Node implements INode{
    public ArrayList<IEdge> edges = new ArrayList<>();

    public final void addEdge(IEdge edge) {
        edges.add(edge);
    }

    public void removeEdge(IEdge edge) {
        edges.remove(edge);
    }
}
