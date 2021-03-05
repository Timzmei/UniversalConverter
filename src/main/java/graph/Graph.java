package graph;

import java.util.HashMap;
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

        System.out.println(fromNodeName + " " + toNodeName + " " + weight);
//        System.out.println((weight));

        if(!(toNode.listEdges.containsKey(fromNode))){
            addEdge(toNode, fromNode, (1 / weight));

        }
        if(!(fromNode.listEdges.containsKey(toNode))) {
            addEdge(fromNode, toNode, weight);
        }
    }

    public Node getNode(final String name) {
        return nodeNameToNode.get(name);
    }

//    public Node getNodeOrThrow(final String name) {
//        final Node node = nodeNameToNode.get(name);
//        if (node == null) {
//            throw new NodeNotFoundException(name);
//        }
//        return node;
//    }

    public void addEdge(Node fromNode, Node toNode, Double weight) {

        fromNode.addEdge(toNode, weight);


        fromNode.listEdges.forEach((k, v) -> {
            if(!(k.listEdges.containsKey(toNode)) || !(k.name.equals(toNode.name))) {
                k.addEdge(toNode, (weight / v));
                createEdge(k.name, toNode.name, (weight / v));
            }
        });

    }

    public Map<String, Node> getMapNode() {
        return nodeNameToNode;
    }






}