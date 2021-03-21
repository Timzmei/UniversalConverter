package graph;

import java.util.List;

public class Unit extends Graph{

    public Unit(List<String[]> rules) {
        rules.forEach(r -> {
            createNode(r[0]);
            createNode(r[1]);
            addWeight(r[0], r[1], Double.valueOf(r[2]));
        });
    }

    private void addWeight(String source, String target, Double weight) {
        createEdge(source, target, weight);
    }


}
