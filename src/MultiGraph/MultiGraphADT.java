package MultiGraph;

import java.util.ArrayList;
import java.util.function.Predicate;

public class MultiGraphADT<TNode extends Node, TEdge extends Edge> extends GraphADT<TNode, TEdge> {

    public boolean hasEdge(TNode start, TNode end) {
        return edges.stream().anyMatch(x -> x.start.equals(start) && x.end.equals(end));
    }

    public void addEdge(TNode start, TNode end, TEdge edge) {
        edge.connect(start, end);
        edges.add(edge);
        start.addEdge(edge);
    }

    public void removeNode(TNode node) {
        edges.removeIf(x -> x.end.equals(node) || x.start.equals(node));
        nodes.remove(node);
    }

    public void removeEdge(TEdge edge) {
        edge.start.removeEdge(edge);
        edges.remove(edge);
    }
}