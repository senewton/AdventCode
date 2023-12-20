import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Advent8_1 {

    private final String leftRight = "LRLRRLLRRLRLRRLLRRLRRLLRRLRRRLRRLRRLRRRLRRLRLRLRLLLRRRLRLLRRLRLRRRLRRLLRRLRRRLRRLRLRLRRRLRLRRRLLRLLRRLRRRLLRRLRLLLRRLRLRLLRRLRLRRRLLRLLRRRLRLLRRRLRRLRRLRRRLRRRLLRLLRRRLRRRLRRLRRRLLRRRLRLRRLLRRLRLRLRRLRRLRLLRRRLRRRLLLRLRLRRRLLRRLRRRLRLRRLLRRLRRLLRLRLRRRLRLRLRLRRRLRLLRRRLRRRLRRLLLRRRR";


    private HashMap<String, ArrayList<String>> directions = new HashMap<String, ArrayList<String>>();

    public void execute(){
        System.out.println("\n= Advent 8_1 Executing");

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv8.txt");

            Scanner sc = new Scanner(inputStream);
            Integer rowCounter = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                // System.out.println("Row"+ rowCounter + " : " + str);
                storeDirections(str);
                rowCounter++;
            }
            System.out.println("Stored: " + this.directions.size() + " Sets of Directions");

            findRoute();

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }


    private void storeDirections(String str){
        // LCP = (FQJ, JGH)

        String keyString = str.substring(0,3);
        String leftDirection = str.substring(str.indexOf("(")+1, str.indexOf(",")).trim();
        String rightDirection = str.substring(str.indexOf(",")+2, str.indexOf(")")).trim();

        // System.out.println("Parsed: Key:_" + keyString + "_Left:" + leftDirection + "_Right:" + rightDirection + "_");

        ArrayList<String> directionRec = new ArrayList<>(Arrays.asList(leftDirection, rightDirection));
        this.directions.put(keyString, directionRec);
    }


    private void findRoute(){
        Integer pos = 0; // This keeps track of the position in the left-Right instructions list
        Integer numSteps = 0; // This is the total number of steps
        Boolean isZZZ = Boolean.FALSE; // becomes true if it hits ZZZ

        // Find the record containing a key "AAA"
        ArrayList<String> dirRec;
        dirRec = this.directions.get("AAA");
        System.out.println("Found Start:" + dirRec.get(0) + "," + dirRec.get(1) );

        while(!isZZZ){

            if(pos >= this.leftRight.length()){
                pos = 0; // Loop around to beginning if you go past the end
            }

            Character nextInstruction = this.leftRight.charAt(pos);
            String nextNode;
            if(nextInstruction.equals('L')){
                nextNode = dirRec.get(0); // The left node
            } else{
                nextNode = dirRec.get(1); // The right node
            }
            dirRec = this.directions.get(nextNode); // Update based on the instruction at the previous node

            pos++;
            numSteps++;

            if(nextNode.equals("ZZZ")){
                isZZZ = Boolean.TRUE;
            }
        }
        System.out.println("Pos:" + pos + " NTEPS:" + numSteps);

    }
}
