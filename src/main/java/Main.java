import Request.RequestBody;
import com.google.gson.Gson;
import graph.Node;
import graph.Unit;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static spark.Spark.*;

public class Main {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    private static Gson gson = new Gson();


    public static void main(String[] args) throws Exception {


        Unit unit = readCSV();

        ipAddress(SERVER_HOST);
        port(SERVER_PORT);


        post("/convert", (req, res) -> {
            RequestBody requestBody = gson.fromJson(req.body(), RequestBody.class);
            String answer = readUnit(requestBody, unit);

            if(answer.equals("404")){
                res.status(404);
                return "Невозможно осуществить такое преобразование";
            }

            if(answer.equals("400")){
                res.status(400);
                return "В выражениях используются неизвестные единицы измерения";
            }

            res.status(200);
            return answer;
        });

    }

    public static Unit readCSV() throws Exception {
        ReadCSV readCSV = new ReadCSV();

        return new Unit(readCSV.getList());

    }

    public static String readUnit(RequestBody requestBody, Unit unit) {
        String fromUnit = requestBody.getFrom();
        String toUnit = requestBody.getTo();
        String numerator = fromUnit;
        String denominator = toUnit;

        if(fromUnit == null){
            return "400";
        }
        else if (toUnit == null){
            if(fromUnit.contains("/")) {
                String[] fromUnitSplit = fromUnit.split("/");
                numerator = fromUnitSplit[0].trim();
                denominator = fromUnitSplit[1].trim();
                return calculation(numerator, denominator, unit);
            }
            else{
                return "400";
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

    public static String calculation(String numerator, String denominator, Unit unit) {

        List<String> numeratorElements = new ArrayList<>(Arrays.asList(numerator.split("\\*")));
        List<String> denominatorElements = new ArrayList<>(Arrays.asList(denominator.split("\\*")));
        Map<String, Node> unitMap = unit.getMapNode();
        double k = 1.0;

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
                    return "400";
                }
            }
        }

        if (numeratorElements.size() == 0 || denominatorElements.size() == 0) {

            return doubleToString(k);
        }else {
            return "404";
        }
    }

    public static String doubleToString(double k){
        String[] splitter = String.valueOf(k).split("\\.");
        int i = splitter[1].length();
        String formattedDouble;
        if (i > 15){
            formattedDouble = new DecimalFormat("#0.000000000000000").format(k);
        }
        else {
            formattedDouble = Double.toString(k);
        }
        return formattedDouble;
    }


}



