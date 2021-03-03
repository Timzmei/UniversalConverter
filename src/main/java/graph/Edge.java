package graph;

public class Edge extends GraphElement {
    public final Node source;
    public final Node target;
    public final String weight;

    public Edge(final Node source, final Node target, String weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
    }
}