import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Advent3_1 {
    private final int MAX_ROWS = 140;
    private final int MAX_CHARS = 141;
    private final Boolean[][] symbArray = new Boolean[MAX_ROWS][MAX_CHARS];

    public void execute(){
        System.out.println("= Advent 3_1 Executing");
        clearSymbols();

        // First work out where the symbols are and store in symbArray
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

        // This holds the result
        Integer totalSum = 0;

        // Then look for the numbers
        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv3.txt");

            Scanner sc = new Scanner(inputStream);
            Integer rowCounter = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                System.out.println("Read: "+ str);
                totalSum += findNumbers(str, rowCounter);
                rowCounter++;
            }
            // this.showSymbols();

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        System.out.println("Result =" + totalSum);
    }


    private Integer findNumbers(String str, Integer rowCounter){
        StringBuffer digits = new StringBuffer();

        // Eliminate everything except digits
        for(int i = 0 ; i < str.length(); i++ ){
            if(Character.isDigit(str.charAt(i))){
                digits.append(str.charAt(i));
            } else {
                digits.append(' ');
            }
        }

        Integer totalValue = 0;

        Integer i = 0;
        Integer begInd = 0;
        Integer endInd = 0;
        Boolean buildingNumber = false;
        StringBuffer numBuff = new StringBuffer();

        while(i < digits.length()){
            Character c = digits.charAt(i);

            if(i == digits.length()-1 && Character.isDigit(c)){
                if(buildingNumber){
                    numBuff.append(c);
                    endInd = i;
                    buildingNumber = false;
                    // Check touching and aggreagte
                    System.out.println("Check End:" + numBuff.toString() + " From:" + begInd + " to " + endInd );
                    if( touchingSymbol(rowCounter, begInd, endInd) == true){
                        // System.out.println("Valid Value:" + n );
                        Integer number = Integer.parseInt(numBuff.toString());
                        totalValue += number;
                    }
                    i++;
                    break;
                }
            }

            if(c.equals(' ')){
                if(buildingNumber){
                    endInd = i-1;
                    buildingNumber = false;
                    // Check touching and aggreagte
                    System.out.println("Check:" + numBuff.toString() + " From:" + begInd + " to " + endInd );

                    if( touchingSymbol(rowCounter, begInd, endInd) == true){
                        // System.out.println("Valid Value:" + n );
                        Integer number = Integer.parseInt(numBuff.toString());
                        totalValue += number;
                    }

                    i++;
                } else{
                    i++;
                }
            } else{
                if(!buildingNumber){
                    begInd = i;
                    numBuff.delete(0,numBuff.length());
                    numBuff.append(c);
                    buildingNumber = true;
                    i++;
                } else {
                    numBuff.append(c);
                    i++;
                }
            }
        }

        System.out.println("Row Value:" + totalValue);
        return totalValue;
    }


    private Boolean touchingSymbol(Integer rowCounter, Integer begIndex, Integer endIndex){
        Boolean result = false;

        // Current Row
        if(begIndex > 0){
            //System.out.println("Beg:" + begIndex + " Symb:" + this.symbArray[rowCounter][begIndex-1] );
            if(this.symbArray[rowCounter][begIndex-1]){
                result = true;
            }
        }
        if(endIndex < this.MAX_CHARS -1 ){
            //System.out.println("Ebd:" + endIndex + " Symb:" + this.symbArray[rowCounter][endIndex+1] );
            if(this.symbArray[rowCounter][endIndex+1]){
                result = true;
            }
        }

        // Row Before
        if(rowCounter > 0){
            Integer be = Integer.max(0, begIndex-1);
            Integer ee = Integer.min(endIndex+1, this.MAX_CHARS);
            for(int i = be ; i <= ee ; i++ ){
                if(this.symbArray[rowCounter-1][i]){
                    result = true;
                }
            }
        }

        // Row After
        if(rowCounter < this.MAX_ROWS-1){
            Integer be = Integer.max(0, begIndex-1);
            Integer ee = Integer.min(endIndex+1, this.MAX_CHARS);
            for(int i = be ; i <= ee ; i++ ){
                if(this.symbArray[rowCounter+1][i]){
                    result = true;
                }
            }
        }

        return result;
    }

    private void findSymbols(String str, Integer rowCounter){
        // System.out.println("Input: " + str);
        for(int i = 0; i < str.length(); i++){
            this.symbArray[rowCounter][i] = isSpecialCharacter(str.charAt(i));
        }
        // this.showSymbols();
    }


    private Boolean isSpecialCharacter(Character c){
        if(!Character.isLetter(c) && !Character.isDigit(c) && !c.equals('.')) {
            return true;
        } else {
            return false;
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

/*

private Integer findNumbers(String str, Integer rowCounter){
        StringBuffer digits = new StringBuffer();

        for(int i = 0 ; i < str.length(); i++ ){
            if(Character.isDigit(str.charAt(i))){
                digits.append(str.charAt(i));
            } else {
                digits.append(' ');
            }
        }

        ArrayList<String> numberList = new ArrayList<>(Arrays.asList(digits.toString().split(" ")));
        numberList.removeAll(Arrays.asList(null,""));

        Integer totalValue = 0;
        Integer lastPos = 0;

        for( String n : numberList){
            Integer begIndex = str.indexOf(n);
            Integer endIndex = begIndex + n.length() - 1;

            if(begIndex < lastPos){
                System.out.println("Error: " + str);
            }
            //System.out.println("Row:" + rowCounter);
            //System.out.println("Value:" + n );
            if( touchingSymbol(rowCounter, begIndex, endIndex) == true){
                // System.out.println("Valid Value:" + n );
                Integer number = Integer.parseInt(n);
                totalValue += number;
            }
            lastPos = endIndex;
        }
        System.out.println("Row Value:" + totalValue);
        return totalValue;
    }
 */