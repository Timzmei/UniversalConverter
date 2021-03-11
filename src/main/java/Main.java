import Request.RequestBody;
import com.google.gson.Gson;
import graph.Graph;
import graph.Node;
import graph.Unit;

import java.util.List;
import java.util.Map;
import java.util.Set;
import static spark.Spark.*;

public class Main {
    public static final String JSON_MIME_TYPE = "application/json";

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 80;

    private static Gson gson = new Gson();


    public static void main(String[] args) throws Exception {


        readCSV();

        ipAddress(SERVER_HOST);
        port(SERVER_PORT);


        post("/convert", (req, res) -> {
            RequestBody requestBody = gson.fromJson(req.body(), RequestBody.class);

            return "Hello World";
        });





    }

    public static void readCSV() throws Exception {
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

    }

    public static void readUnit(RequestBody requestBody){
        String fromUnit = requestBody.getFrom();
        String toUnit = requestBody.getTo();

        if(toUnit == null && !fromUnit.contains("/")){
            System.out.println("404");
        }
        else if (toUnit == null && fromUnit.contains("/")){

            String[] fromUnitSplit = fromUnit.split("/");

            String numerator = fromUnitSplit[0].trim();
            String denominator = fromUnitSplit[1].trim();
            if()
        }


    }

    public static void calculation(String numerator, String denominator){

        String[] numeratorElements = numerator.split("\\*");
        String[] denominatorElements = denominator.split("\\*");

        numeratorElements[0].trim()

    }


}



