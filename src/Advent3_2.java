import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Advent3_2 {
    private final int MAX_ROWS = 140;
    private final int MAX_CHARS = 140;
    private final Boolean[][] symbArray = new Boolean[MAX_ROWS][MAX_CHARS];
    private final Character[][] charArray = new Character[MAX_ROWS][MAX_CHARS];

    public void execute(){
        System.out.println("= Advent 2_2 Executing");
        clearSymbols();

        // First work out where the star symbols are and store in symbArray
        // Also store numeric digits in character array
        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv3.txt");

            Scanner sc = new Scanner(inputStream);
            Integer rowCounter = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                //System.out.println("Read: "+ str);
                findSymbols(str, rowCounter);
                rowCounter++;
            }
            // this.showSymbols();

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        lookForGearRatios();
    }

    private void lookForGearRatios(){
        Integer totalSum = 0;
        for(int i = 0 ; i < this.MAX_ROWS; i++ ){
            for(int j= 0; j < this.MAX_CHARS; j++ ){
                if(this.symbArray[i][j]) {
                    System.out.println("Found Gear Symbol at : " + i + "," + j);
                    totalSum += lookForAttachedValues(i, j);
                }
            }
        }
        System.out.println("TotalSum:" + totalSum);
    }

    private Integer lookForAttachedValues(int r, int c){
        Integer ratio = 0;

        ArrayList<Integer> touchingValues = new ArrayList<>();

        Integer rowBeg = Integer.max(0, r-1);
        Integer rowEnd = Integer.min(r+1, this.MAX_ROWS-1);

        Integer colBeg = Integer.max(0, c-1);
        Integer colEnd = Integer.min(c+1, this.MAX_CHARS-1);

        for(int i = rowBeg; i <= rowEnd ; i++ ){
            StringBuffer numBuff = new StringBuffer();
            Boolean isInNumber = false;
            Integer numBeg = 0;
            Integer numEnd = 0;

            for(int j =0; j < this.MAX_CHARS; j++){
                Character ch = this.charArray[i][j];
                // System.out.println("Pos:" + j + " Char:" + ch);

                if(j == this.MAX_CHARS-1 && Character.isDigit(this.charArray[i][j])){
                    numEnd = j;
                    isInNumber = false;
                    numBuff.append(this.charArray[i][j]);
                    Integer number = Integer.parseInt(numBuff.toString());

                    // System.out.println("Found End Number: Row:" + i + " value:" + number + " NumBeg:" + numBeg + " NumEnd:" + numEnd);
                    if(isTouching( i, numBeg, numEnd, rowBeg, rowEnd, colBeg, colEnd)){
                        touchingValues.add(number);
                    }
                    break;
                }

                if(Character.isDigit(this.charArray[i][j])){
                    if(!isInNumber){
                        numBeg = j;
                        numBuff.delete(0,numBuff.length());
                        numBuff.append(this.charArray[i][j]);
                        isInNumber = true;
                    } else{
                        numBuff.append(this.charArray[i][j]);
                        isInNumber = true;
                    }
                } else {
                    if(!isInNumber){
                        isInNumber = false;
                    } else{
                        numEnd = j-1;
                        isInNumber = false;
                        Integer number = Integer.parseInt(numBuff.toString());

                        // System.out.println("Found Number: Row:" + i + " value:" + number + " NumBeg:" + numBeg + " NumEnd:" + numEnd);
                        if(isTouching(i, numBeg, numEnd, rowBeg, rowEnd, colBeg, colEnd)){
                            touchingValues.add(number);
                        }
                    }
                }
            }

        }
        if(touchingValues.size() == 2){
            ratio = touchingValues.get(0) * touchingValues.get(1);
            System.out.println("Ratio:" + touchingValues.get(0) + " * " + touchingValues.get(1) + " = "  + ratio);
        }
        return ratio;
    }

    private Boolean isTouching(Integer rowNum, Integer numBeg, Integer numEnd, Integer rowBeg, Integer rowEnd, Integer colBeg, Integer colEnd){
        Boolean result = false;
        for(int rp = numBeg; rp <= numEnd; rp++){
            if(rowNum >= rowBeg && rowNum <= rowEnd) {
                if (rp >= colBeg && rp <= colEnd) {
                    return true;
                }
            }
        }
        return result;
    }


    private void findSymbols(String str, Integer rowCounter){
        // System.out.println("Input: " + str);
        for(int i = 0; i < str.length(); i++){
            this.symbArray[rowCounter][i] = isGearSymbol(str.charAt(i));
            if(Character.isDigit(str.charAt(i))){
                this.charArray[rowCounter][i] = str.charAt(i);
            } else {
                this.charArray[rowCounter][i] = ' ';
            }
        }
    }

    private Boolean isGearSymbol(Character c){
        if(!c.equals('*')) {
            return false;
        } else {
            return true;
        }
    }

    private void clearSymbols(){
        for(int i = 0 ; i < MAX_ROWS ; i++ ){
            for(int j = 0; j < MAX_CHARS; j++ ){
                this.symbArray[i][j] = false;
            }
        }
    }


    private void showSymbols(){
        for(int i = 0 ; i < MAX_ROWS ; i++ ){
            System.out.print("\nRow:" + i );
            for(int j = 0; j < MAX_CHARS; j++ ){
                if(this.symbArray[i][j]) {
                    System.out.print( "T");
                } else {
                    System.out.print( "F");
                }
            }
        }
    }

}