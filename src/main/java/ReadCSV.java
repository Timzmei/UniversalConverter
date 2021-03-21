import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.List;

public class ReadCSV {

    private final List<String[]> list;

    public ReadCSV(String path) throws Exception {
        list = readAll(path);
    }

    public List<String[]> readAll(String path) throws Exception {
        CSVReader csvReader = new CSVReader(new FileReader(path));
        List<String[]> list;
        list = csvReader.readAll();
        csvReader.close();
        return list;
    }

    public List<String[]> getList() {
        return list;
    }


}
