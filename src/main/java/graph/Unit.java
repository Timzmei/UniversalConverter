package graph;

import java.util.List;
import java.util.Map;

public class Unit extends Graph{

    public Unit(List<String[]> rules) {
        rules.forEach(r -> {
            addUnitNode(r[0]);
            addUnitNode(r[1]);
            addWeight(r[0], r[1], Double.valueOf(r[2]));
        });
    }

    private void addUnitNode(String unitName) {

        final Node node = createNode(unitName);

    }

    private void addWeight(String source, String target, Double weight) {

        createEdge(source, target, weight);

    }


}
