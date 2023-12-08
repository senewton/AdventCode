import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class Advent1_1 {

    public void execute(){
        System.out.println("= Advent1_1 Executing");

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv1.txt");

            Scanner sc = new Scanner(inputStream);
            Integer sumval = 0;
            Integer val = 0;

            while (sc.hasNext()) {
                String str = sc.nextLine();
                System.out.println("Read: "+ str);
                val = getDigits(str);
                sumval += val;
            }
            System.out.println("Sum = " + sumval);

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
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
