package MultiGraph;

import java.util.ArrayList;
import java.util.function.Predicate;

public class MultiGraphADT<TNode extends Node, TEdge extends Edge> implements IGraph<TNode, TEdge> {
    private ArrayList<TNode> nodes = new ArrayList<>();
    private ArrayList<TEdge> edges = new ArrayList<>();

    public boolean isEmpty() {
        return nodes.isEmpty() && edges.isEmpty();
    }

    public Integer nodeCount() {
        return nodes.size();
    }

    public Integer edgeCount() {
        return edges.size();
    }

    public boolean hasEdge(TNode start, TNode end) {
        return edges.stream().anyMatch(x -> x.start.equals(start) && x.end.equals(end));
    }

    public void addNode(TNode node) {
        nodes.add(node);
    }

    public void addEdge(TNode start, TNode end, TEdge edge) {
        edge.connect(start, end);
        edges.add(edge);
        start.addEdge(edge);
    }

    @Override
    public Iterable<TNode> getNodes() {
        return nodes;
    }

    @Override
    public Iterable<TEdge> getEdges() {
        return edges;
    }

    public void removeNode(TNode node) {
        edges.removeIf(x -> x.end.equals(node) || x.start.equals(node));
        nodes.remove(node);
    }

    public void removeEdge(TEdge edge) {
        edge.start.removeEdge(edge);
        edges.remove(edge);
    }

    public TNode findNode(Predicate<? super TNode> predicate) {
        return nodes.stream().filter(predicate).findFirst().get();
    }
}