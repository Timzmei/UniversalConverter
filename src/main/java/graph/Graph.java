package graph;

import graph.exceptions.DuplicateNodeException;
import graph.exceptions.NodeNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private final Map<String, Node> nodeNameToNode = new HashMap<>();


    public Node createNode(String name) {
        final Node n;
        if (nodeNameToNode.containsKey(name)) {
            n = nodeNameToNode.get(name);
        } else{
            n = new Node(name);
            nodeNameToNode.put(name, n);

        }

        return n;
    }

    public void createEdge(String fromNodeName, String toNodeName, Double weight) {
        final Node fromNode = getNode(fromNodeName);
        final Node toNode = getNode(toNodeName);

        System.out.println(fromNodeName + " " + weight);
        System.out.println((weight));

        addEdge(toNode, fromNode, (weight));
        addEdge(fromNode, toNode, (1 / (weight)));
    }

    public Node getNode(final String name) {
        return nodeNameToNode.get(name);
    }

    public Node getNodeOrThrow(final String name) {
        final Node node = nodeNameToNode.get(name);
        if (node == null) {
            throw new NodeNotFoundException(name);
        }
        return node;
    }

    public void addEdge(Node fromNode, Node toNode, Double weight) {

//        System.out.println(fromNode + " " + fromNode.listEdges);

        if(fromNode.listEdges != null){
            for (Map.Entry<Node, Double> e:
                    fromNode.listEdges.entrySet()) {
                if(!e.getKey().listEdges.containsKey(toNode)) {
                    createEdge(e.getKey().name, toNode.name, (e.getValue()) / (weight));
                }

            }
        }
        fromNode.addEdge(toNode, weight);
    }

    public Map<String, Node> getMapNode() {
        return nodeNameToNode;
    }






}