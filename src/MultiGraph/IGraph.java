package MultiGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public interface IGraph {
    public boolean isEmpty();
    public Integer nodeCount();
    public Integer edgeCount();
    public boolean hasEdge(INode start, INode end);
    public void addNode(INode Node);
    public void addEdge(INode start, INode end, IEdge edge);
    public Collection<INode> getNodes();
    public Collection<IEdge> getEdges();
    public INode findNode(Predicate<? super INode> predicate);
}
