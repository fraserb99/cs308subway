package MultiGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public interface IGraph<TNode extends Node, TEdge extends Edge> {
    public boolean isEmpty();
    public Integer nodeCount();
    public Integer edgeCount();
    public boolean hasEdge(TNode start, TNode end);
    public void addNode(TNode Node);
    public void addEdge(TNode start, TNode end, TEdge edge);
    public Iterable<TNode> getNodes();
    public Iterable<TEdge> getEdges();
    public void removeNode(TNode node);
    public void removeEdge(TEdge edge);
    public TNode findNode(Predicate<? super TNode> predicate);
}
