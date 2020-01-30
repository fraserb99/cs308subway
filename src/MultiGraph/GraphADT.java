package MultiGraph;

import java.util.ArrayList;
import java.util.function.Predicate;

public abstract class GraphADT<TNode extends Node, TEdge extends Edge> implements IGraph<TNode, TEdge> {
    protected ArrayList<TNode> nodes = new ArrayList<>();
    protected ArrayList<TEdge> edges = new ArrayList<>();

    public boolean isEmpty() {
        return nodes.isEmpty() && edges.isEmpty();
    }

    public Integer nodeCount() {
        return nodes.size();
    }

    public Integer edgeCount() {
        return edges.size();
    }

    public Iterable<TNode> getNodes() {
        return nodes;
    }

    public Iterable<TEdge> getEdges() {
        return edges;
    }

    public void addNode(TNode node) {
        nodes.add(node);
    }

    public TNode findNode(Predicate<? super TNode> predicate) {
        return nodes.stream().filter(predicate).findFirst().get();
    }
}
