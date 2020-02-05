package MultiGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class MultiGraphADT<TNode extends INode, TEdge extends IEdge> implements IGraph<TNode, TEdge> {
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
        return edges.stream().anyMatch(x -> x.getStart().equals(start) && x.getEnd().equals(end));
    }

    public void addNode(TNode node) {
        nodes.add(node);
    }

    public void addEdge(TNode start, TNode end, TEdge edge) {
        edge.connect(start, end);
        edges.add(edge);
        start.addEdge(edge);
    }

    public Collection<TNode> getNodes() {
        return nodes;
    }

    public Collection<TEdge> getEdges() {
        return edges;
    }

    public void removeNode(TNode node) {
        var nodeEdges = node.getEdges();
        edges.removeAll(nodeEdges);
        nodes.remove(node);
    }

    public void removeEdge(TEdge edge) {
        edge.getStart().removeEdge(edge);
        edges.remove(edge);
    }

    public TNode findNode(Predicate<? super TNode> predicate) {
        var optional = nodes.stream().filter(predicate).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
}