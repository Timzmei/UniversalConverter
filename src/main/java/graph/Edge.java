package graph;
public class Edge {
    public final Node target;
    public final String weight;

    public Edge(final Node target, String weight) {
        this.target = target;
        this.weight = weight;
    }
}