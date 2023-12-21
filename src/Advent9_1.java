import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Advent9_1 {

    // Whole dataset
    private ArrayList<ArrayList<Integer>> dataset = new ArrayList<>();

    public void execute(){
        System.out.println("\n= Advent 9_1 Executing");

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv9.txt");

            Scanner sc = new Scanner(inputStream);
            Integer rowCounter = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                // System.out.println("Row: "+ rowCounter + " : " + str);
                addRowToArray(str, rowCounter);
                rowCounter++;
            }

            seeDataset();
            Integer totalSum = processSeries();
            System.out.println("** Total Sum =" + totalSum);
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    private Integer processSeries(){
        Integer totalSum = 0;

        for(ArrayList<Integer> series : this.dataset){
            // System.out.print("\nProcessing Row : " + this.dataset.indexOf(series) + ": ");
            Integer nextvalue = getNextvalue(series);
            System.out.println("* Next Value:" + nextvalue);
            totalSum += nextvalue;
        }
        return totalSum;
    }

    private Integer getNextvalue(ArrayList<Integer> series){

        ArrayList<Integer> nexts = new ArrayList<>();
        ArrayList<Integer> lasts = new ArrayList<>();

        lasts.add(series.get(series.size()-1));

        ArrayList<Integer> diffSeries = getDifferences(series);
        lasts.add(diffSeries.get(diffSeries.size()-1));
        System.out.println("\nInitial Series:" + seeRow(series));
        System.out.println("First Diff Series:" + seeRow(diffSeries));

        while(!isZero(diffSeries)){
            diffSeries = getDifferences(diffSeries);
            System.out.println("Next Diff Series:" + seeRow(diffSeries));
            lasts.add(diffSeries.get(diffSeries.size()-1));
        }

        System.out.println("End Values:" + seeRow(lasts));

        nexts.add(0);

        for(int i = lasts.size()-2; i >=0; i--){
            nexts.add(lasts.get(i) + nexts.get(nexts.size()-1));
        }
        System.out.println("Next Values:" + seeRow(nexts));

        return nexts.get(nexts.size()-1);
    }

    private ArrayList<Integer> getDifferences(ArrayList<Integer> series){
        ArrayList<Integer> differences = new ArrayList<>();

        for(int i = 1; i < series.size(); i++ ){
            differences.add(series.get(i) - series.get(i-1));
        }
        return differences;
    }

    void addRowToArray(String str, Integer row){
        List<String> strValues = Arrays.asList(str.split(" "));
        ArrayList<Integer> series = new ArrayList<>();

        for(String strval: strValues ){
            try{
                Integer val = Integer.valueOf(strval);
                series.add(val);
            } catch(NumberFormatException nfe){
                System.out.println(nfe.getMessage());
            }
        }
        this.dataset.add(series);
    }

    private void seeDataset(){
        for(ArrayList<Integer> series : this.dataset){
            System.out.print("\nRow : " + this.dataset.indexOf(series) + ": " + seeRow(series));
        }
    }

    private String seeRow(ArrayList<Integer> series){
        StringBuilder builder = new StringBuilder();
        for(Integer val: series){
            builder.append(val+",");
        }
        return builder.toString();
    }

    private Boolean isZero(ArrayList<Integer> series){

        for(Integer val: series){
            if(!val.equals(0)){
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
