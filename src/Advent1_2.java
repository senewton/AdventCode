import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Advent1_2 {

    public void execute(){
        System.out.println("= Advent1_2 Executing");

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv1.txt");

            Scanner sc = new Scanner(inputStream);
            Integer sumval = 0;
            Integer val = 0;

            while (sc.hasNext()) {
                String str = sc.nextLine();
                System.out.println("Read: "+ str);
                str = replaceTextDigits(str);
                System.out.println("Updated: "+ str);
                val = getDigits(str);
                sumval += val;
            }
            System.out.println("Sum = " + sumval);

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }


    private String replaceTextDigits(String str){
        // str = str.replaceAll("zero", "0");
        str = str.replaceAll("oneight", "18");
        str = str.replaceAll("twone", "21");
        str = str.replaceAll("threeight", "38");
        str = str.replaceAll("fiveight", "58");
        str = str.replaceAll("eightwo", "82");
        str = str.replaceAll("eighthree", "83");
        str = str.replaceAll("nineight", "98");


        str = str.replaceAll("one", "1");
        str = str.replaceAll("two", "2");
        str = str.replaceAll("three", "3");
        str = str.replaceAll("four", "4");
        str = str.replaceAll("five", "5");
        str = str.replaceAll("six", "6");
        str = str.replaceAll("seven", "7");
        str = str.replaceAll("eight", "8");
        str = str.replaceAll("nine", "9");
        return str;
    }
    private Integer getDigits(String str){
        Integer result = 0 ;
        StringBuffer strDigits = new StringBuffer();
        StringBuffer strResult = new StringBuffer();

        for(int i = 0; i < str.length(); i++ ){
            if ( Character.isDigit(str.charAt(i)) == true ){
                strDigits.append(str.charAt(i));
            }
        }

        strResult.append(strDigits.charAt(0));
        strResult.append(strDigits.charAt(strDigits.length()-1));

        result = Integer.parseInt(strResult.toString());
        System.out.println(strDigits + " -> " + strResult + " -> " + result);
        return result;
    }
}


