import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class RangeMap {
    String filename = null; // Filename containing input data
    Integer firstRow = 0;   // Row in input file where the data for this class starts
    Integer lastRow = 0;    // Row to stop reading

    ArrayList<RangeRecord> rangeData;

    RangeMap(String filename, Integer firstRow, Integer lastRow){
        this.filename = filename;

        this.firstRow = firstRow;
        this.lastRow = lastRow;

        this.rangeData = new ArrayList<>();
    }

    public void ReadFile(){
        try {
            InputStream inputStream = new FileInputStream(this.filename);

            Scanner sc = new Scanner(inputStream);
            Integer rowCounter = 1; // Keep the row numbers same as Notepad
            while (sc.hasNext()) {
                String str = sc.nextLine();

                if(rowCounter >= this.firstRow && rowCounter <= this.lastRow){
                    addRowToRangeData(str);
                }

                rowCounter++;
            }

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
        System.out.println("Read:" + this.rangeData.size() + " records");
    }

    private void addRowToRangeData(String str){
        List<String> rawRange = Arrays.asList(str.split(" "));
        RangeRecord rangeRecord = new RangeRecord();
        rangeRecord.destStart = Long.parseLong(rawRange.get(0));
        rangeRecord.sourceStart = Long.parseLong(rawRange.get(1));
        rangeRecord.rangeLength = Long.parseLong(rawRange.get(2));
        this.rangeData.add(rangeRecord);
    }

    public Long getMap(Long longKey){
        // System.out.println("Looking for key:" + longKey );

        for(int i = 0; i < this.rangeData.size(); i++){
            RangeRecord rr = this.rangeData.get(i);
            // rr.seeRecord();
            if(longKey.compareTo(rr.sourceStart)>=0 && longKey.compareTo(rr.sourceStart+rr.rangeLength)<0){
                Long longIncr = rr.sourceStart+rr.rangeLength-longKey;
                Long longResult = rr.destStart+rr.rangeLength -longIncr;
                // System.out.println("Returning: " + longResult);
                return longResult;
            }
        }

        return longKey;
    }
}
