import graph.Node;
import graph.Unit;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {

//        System.out.println(WritingCSV.csvWriter(WritingCSV.csvString(), "file.csv"));


        ReadCSV readCSV = new ReadCSV();

        Unit unit = new Unit(readCSV.getList());
        for (Map.Entry<String, Node> m :
             unit.getMapNode().entrySet()) {

            System.out.println(m.getKey() + " " + m.getValue().listEdges);
            
        }





    }

//    public static Double findFriendUnit(List<Unit> listUnit, String firstName, String secondName){
//        if(listUnit.contains())
//
//        return null;
//    }

}
