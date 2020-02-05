package MultiGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class MultiGraphADT implements IGraph {
    private ArrayList<INode> nodes = new ArrayList<>();
    private ArrayList<IEdge> edges = new ArrayList<>();

    public boolean isEmpty() {
        return nodes.isEmpty() && edges.isEmpty();
    }

    public Integer nodeCount() {
        return nodes.size();
    }

    public Integer edgeCount() {
        return edges.size();
    }

    public boolean hasEdge(INode start, INode end) {
        return edges.stream().anyMatch(x -> x.getStart().equals(start) && x.getEnd().equals(end));
    }

    public void addNode(INode node) {
        nodes.add(node);
    }

    public void addEdge(INode start, INode end, IEdge edge) {
        edge.connect(start, end);
        edges.add(edge);
        start.addEdge(edge);
    }

    public Collection<INode> getNodes() {
        return nodes;
    }

    public Collection<IEdge> getEdges() {
        return edges;
    }

    public void removeNode(INode node) {
        var nodeEdges = node.getEdges();
        edges.removeAll(nodeEdges);
        nodes.remove(node);
    }

    public void removeEdge(IEdge edge) {
        edge.getStart().removeEdge(edge);
        edges.remove(edge);
    }

    public INode findNode(Predicate<? super INode> predicate) {
        var optional = nodes.stream().filter(predicate).findFirst();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }
}