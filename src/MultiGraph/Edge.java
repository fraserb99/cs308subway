package MultiGraph;

public class Edge implements IEdge {
    public INode start;
    public INode end;

    public final void connect(INode from, INode to) {
        start = from;
        end = to;
    }
}
