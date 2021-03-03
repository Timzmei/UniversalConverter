package graph;

import java.util.*;
import java.util.stream.Stream;

public class Node extends GraphElement {

    public final String name;
    public final HashMap<Node, Double> listEdges;


    public Node(String name) {
        this.name = name;

        this.listEdges = new HashMap<>();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

//    @Override
//    public String toString() {
//        return "Node{" +
//                "name='" + this.name + '\'' +
//                ", listEdges=" + this.listEdges +
//                '}';
//    }


    @Override
    public String toString() {
        return super.toString();
    }

    void addEdge(Node n, Double weight) {
        listEdges.put(n, weight);
    }

    public HashMap<Node, Double> getListEdges() {
        return listEdges;
    }
}