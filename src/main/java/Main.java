import Request.RequestBody;
import com.google.gson.Gson;
import graph.Unit;
import java.text.DecimalFormat;

import static spark.Spark.*;

public class Main {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8080;

    private static final Gson GSON = new Gson();
    private static final int STATUS_CODE_404 = 404;
    private static final int STATUS_CODE_400 = 400;
    private static final int STATUS_CODE_200 = 200;


    public static void main(String[] args) throws Exception {

        String path = args[0];

        Unit unit = readCSV(path);

        ipAddress(SERVER_HOST);
        port(SERVER_PORT);

        post("/convert", (req, res) -> {
            RequestBody requestBody = GSON.fromJson(req.body(), RequestBody.class);
            String answer = readUnit(requestBody.getFrom().trim(), requestBody.getTo().trim(), unit);

            if(answer.equals("404")){
                res.status(STATUS_CODE_404);
                return "Невозможно осуществить такое преобразование";
            }

            if(answer.equals("400")){
                res.status(STATUS_CODE_400);
                return "В выражениях используются неизвестные единицы измерения";
            }

            res.status(STATUS_CODE_200);
            return answer;
        });

    }

    public static Unit readCSV(String path) throws Exception {
        ReadCSV readCSV = new ReadCSV(path);

        return new Unit(readCSV.getList());

    }

    public static String gettingFraction(String fromUnit, String toUnit){
        String fraction = " / ";

        String[] fromUnitSplit = fromUnit.split("/");
        String[] toUnitSplit = toUnit.split("/");
        fraction = (toUnitSplit.length == 2) ? fromUnitSplit[0].trim() + " * " + toUnitSplit[1].trim() + fraction : fromUnitSplit[0].trim() + fraction;
        fraction = (fromUnitSplit.length == 2) ? fraction + fromUnitSplit[1].trim() + " * " + toUnitSplit[0].trim() : fraction + toUnitSplit[0].trim();
        return fraction;
    }

    public static String readUnit(String fromUnit, String toUnit, Unit unit) {
        String[] fraction = new String[2];
        if(fromUnit.equals("") && toUnit.equals("")){
            return "404";
        }
        else if(fromUnit.equals("") && !toUnit.contains("/") || toUnit.equals("") && !fromUnit.contains("/")){
            fraction[0] = (fromUnit.equals("")) ? toUnit : fromUnit;
            fraction[1] = "";
        }
        else if (toUnit.equals("") && fromUnit.contains("/") || fromUnit.equals("") && toUnit.contains("/")){
            fraction = (toUnit.equals("")) ? fromUnit.split("/") : toUnit.split("/");
        }
        else if (!toUnit.equals("") && !fromUnit.equals("")){
            fraction = gettingFraction(fromUnit, toUnit).split("/");
        }
        return calculation(fraction[0].trim(), fraction[1].trim(), unit);
    }

    public static String calculation(String numerator, String denominator, Unit unit) {

        Calculate calculate = new Calculate(numerator, denominator, unit.getMapNode());
        calculate.run();
        if(calculate.getAnswer().equals("400")){
            return "400";
        }
        else if (calculate.getNUMERATOR_ELEMENTS() == 0 || calculate.getDENOMINATOR_ELEMENTS() == 0) {
            return doubleToString(calculate.getK());
        }
        else {
            return "404";
        }
    }

    public static String doubleToString(double k){
        return new DecimalFormat("#0.000000000000000").format(k);
    }


}



