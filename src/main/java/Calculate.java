import graph.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Calculate {

    private  List<String> numeratorElements;
    private List<String> denominatorElements;
    private final Map<String, Node> UNIT_MAP;
    private double k;
    private String answer;



    public Calculate(String numerator, String denominator, Map<String, Node> unitMap) {
        this.numeratorElements = new ArrayList<>(Arrays.asList(numerator.split("\\*")));
        this.denominatorElements = new ArrayList<>(Arrays.asList(denominator.split("\\*")));
        this.UNIT_MAP = unitMap;
        this.k = 1;
        this.answer = "200";
    }

    public int getNUMERATOR_ELEMENTS() {
        return numeratorElements.size();
    }

    public int getDENOMINATOR_ELEMENTS() {
        return denominatorElements.size();
    }

    public double getK() {
        return k;
    }

    public String getAnswer() {
        return answer;
    }

    public void run(){
        for (int n = numeratorElements.size() - 1; n >= 0; n--){
            if(numeratorElements.get(n).trim().equals("1")){
                this.numeratorElements.remove(n);
            } else {
                for (int d = denominatorElements.size() - 1; d >= 0; d--) {

                    if(denominatorElements.get(d).trim().equals("1")){
                        this.denominatorElements.remove(d);
                    } else {
                        if (UNIT_MAP.containsKey(numeratorElements.get(n).trim()) && UNIT_MAP.containsKey(denominatorElements.get(d).trim())) {
                            ConcurrentHashMap<Node, Double> nodeMapNmtr = UNIT_MAP.get(numeratorElements.get(n).trim()).listEdges;
                            if (nodeMapNmtr.containsKey(UNIT_MAP.get(denominatorElements.get(d).trim()))) {
                                this.k = this.k * nodeMapNmtr.get(UNIT_MAP.get(denominatorElements.get(d).trim()));
                                this.numeratorElements.remove(n);
                                this.denominatorElements.remove(d);
                                break;
                            }
                        } else {
                            this.answer = "400";
                        }
                    }
                }
            }
        }
    }
}
