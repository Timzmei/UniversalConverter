import com.opencsv.CSVWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class WritingCSV {

    public static String csvWriter(List<String[]> stringArray, String path) throws Exception {
        CSVWriter writer = new CSVWriter(new FileWriter(path));
        for (String[] array : stringArray) {
            writer.writeNext(array);
        }

        writer.close();
        return readFile(path);
    }

    public static String readFile(String path) {
        String response = "";
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String strLine;
            StringBuilder sb = new StringBuilder();
            while ((strLine = br.readLine()) != null) {
                sb.append(strLine);
            }
            response = sb.toString();
            System.out.println(response);
            fr.close();
            br.close();
        } catch (Exception ex) {
            err(ex);
        }
        return response;
    }

    public static void err(Exception ex) {
        System.out.println("EXCEPTION ENCOUNTERED: " + " " + ex);
    }


    public static List<String[]> csvString() {
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"м", "см", "100"});
        list.add(new String[]{"мм", "м", "0.001"});
        list.add(new String[]{"км", "м", "1000"});
        list.add(new String[]{"час", "мин", "60"});
        list.add(new String[]{"мин", "с", "60"});
        list.add(new String[]{"кг", "г", "1000"});
        list.add(new String[]{"т", "кг", "1000"});

        return list;
    }
}
