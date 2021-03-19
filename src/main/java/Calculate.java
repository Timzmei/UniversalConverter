import graph.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Calculate {

    private final List<String> NEMERATOR_ELEMENTS;
    private final List<String> DENOMINATOR_ELEMENTS;
    private final Map<String, Node> UNIT_MAP;
    private double k;
    private String answer;



    public Calculate(String numerator, String denominator, Map<String, Node> unitMap) {
        this.NEMERATOR_ELEMENTS = new ArrayList<>(Arrays.asList(numerator.split("\\*")));
        this.DENOMINATOR_ELEMENTS = new ArrayList<>(Arrays.asList(denominator.split("\\*")));
        this.UNIT_MAP = unitMap;
        this.k = 1;
        this.answer = "200";
    }

    public int getNEMERATOR_ELEMENTS() {
        return NEMERATOR_ELEMENTS.size();
    }

    public int getDENOMINATOR_ELEMENTS() {
        return DENOMINATOR_ELEMENTS.size();
    }

    public double getK() {
        return k;
    }

    public String getAnswer() {
        return answer;
    }

    public void run(){
        for (int n = NEMERATOR_ELEMENTS.size() - 1; n >= 0; n--){
            for (int d = DENOMINATOR_ELEMENTS.size() - 1; d >= 0; d--){

                if (UNIT_MAP.containsKey(NEMERATOR_ELEMENTS.get(n).trim()) && UNIT_MAP.containsKey(DENOMINATOR_ELEMENTS.get(d).trim())){
                    ConcurrentHashMap<Node, Double> nodeMapNmtr = UNIT_MAP.get(NEMERATOR_ELEMENTS.get(n).trim()).listEdges;
                    if (nodeMapNmtr.containsKey(UNIT_MAP.get(DENOMINATOR_ELEMENTS.get(d).trim()))){
                        this.k = this.k * nodeMapNmtr.get(UNIT_MAP.get(DENOMINATOR_ELEMENTS.get(d).trim()));
                        this.NEMERATOR_ELEMENTS.remove(n);
                        this.DENOMINATOR_ELEMENTS.remove(d);
                        break;
                    }
                }
                else {
                    this.answer = "400";
                }
            }
        }
    }
}
