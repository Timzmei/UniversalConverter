package graph;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class Node {

    public final String name;
    public final ConcurrentHashMap<Node, Double> listEdges;


    public Node(String name) {
        this.name = name;

        this.listEdges = new ConcurrentHashMap<>();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    void addEdge(Node n, Double weight) {
        listEdges.put(n, weight);
    }

    public ConcurrentHashMap<Node, Double> getListEdges() {
        return listEdges;
    }
}