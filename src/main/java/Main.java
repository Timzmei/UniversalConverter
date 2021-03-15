import Request.RequestBody;
import com.google.gson.Gson;
import graph.Graph;
import graph.Node;
import graph.Unit;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

public class Main {
    public static final String JSON_MIME_TYPE = "application/json";

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    private static Gson gson = new Gson();


    public static void main(String[] args) throws Exception {


        Unit unit = readCSV();

        ipAddress(SERVER_HOST);
        port(SERVER_PORT);


        post("/convert", (req, res) -> {
            RequestBody requestBody = gson.fromJson(req.body(), RequestBody.class);

            return readUnit(requestBody, unit);
        });





    }

    public static Unit readCSV() throws Exception {
        ReadCSV readCSV = new ReadCSV();

        Unit unit = new Unit(readCSV.getList());
//        for (Map.Entry<String, Node> m :
//                unit.getMapNode().entrySet()) {
//
//            int length = m.getValue().getListEdges().keySet().toArray().length;
//            Node[] node = m.getValue().getListEdges().keySet().toArray(new Node[length]);
//
//            System.out.printf("%s %s%n", m.getKey(), node.length);
//        }

        return  unit;

    }

    public static String readUnit(RequestBody requestBody, Unit unit){
        String fromUnit = requestBody.getFrom();
        String toUnit = requestBody.getTo();
        String numerator = fromUnit;
        String denominator = toUnit;

        if(fromUnit == null){
            return "Код 400 Bad Request, если в выражениях используются неизвестные единицы измерения (т.е. отсутствуют в предоставленном файле с правилами конвертации).";
        }
        else if (toUnit == null){
            if(fromUnit.contains("/")) {
                String[] fromUnitSplit = fromUnit.split("/");
                numerator = fromUnitSplit[0].trim();
                denominator = fromUnitSplit[1].trim();
                return calculation(numerator, denominator, unit);
            }
            else{
                return "Сдеся Код 400 Bad Request, если в выражениях используются неизвестные единицы измерения (т.е. отсутствуют в предоставленном файле с правилами конвертации).";
            }
        }
        else if (toUnit.contains("/") && fromUnit.contains("/")){
            String[] fromUnitSplit = fromUnit.split("/");
            String[] toUnitSplit = toUnit.split("/");
            numerator = fromUnitSplit[0].trim() + " * " + toUnitSplit[1].trim();
            denominator = fromUnitSplit[1].trim() + " * " + toUnitSplit[0].trim();
            return calculation(numerator, denominator, unit);

        }
        else if (!toUnit.contains("/") && !fromUnit.contains("/")){
            return calculation(numerator, denominator, unit);
        }
        return calculation(numerator, denominator, unit);


    }

    public static String calculation(String numerator, String denominator, Unit unit){

        List<String> numeratorElements = new ArrayList<>(Arrays.asList(numerator.split("\\*")));
        List<String> denominatorElements = new ArrayList<>(Arrays.asList(denominator.split("\\*")));
        Map<String, Node> unitMap = unit.getMapNode();
        Double k = 1.0;

        for (int n = numeratorElements.size() - 1; n >= 0; n--){
            for (int d = denominatorElements.size() - 1; d >= 0; d--){

                if (unitMap.containsKey(numeratorElements.get(n).trim()) || unitMap.containsKey(denominatorElements.get(d).trim())){
                    ConcurrentHashMap<Node, Double> nodeMapNmtr = unitMap.get(numeratorElements.get(n).trim()).listEdges;

                    if (nodeMapNmtr.containsKey(unitMap.get(denominatorElements.get(d).trim()))){
                        k = k * nodeMapNmtr.get(unitMap.get(denominatorElements.get(d).trim()));
                        numeratorElements.remove(n);
                        denominatorElements.remove(d);
                    }

                }
                else {
                    return "Тута Код 400 Bad Request, если в выражениях используются неизвестные единицы измерения (т.е. отсутствуют в предоставленном файле с правилами конвертации).";
                }
            }
        }

        if(numeratorElements.size() == 0 && denominatorElements.size() == 0){
            return Double.toString(k);
        }
        else {
            return "Код 404 Not Found, если невозможно осуществить такое преобразование (например, нельзя перевести метры в килограммы).";
        }


    }


}



