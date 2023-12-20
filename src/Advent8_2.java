import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Advent8_2 {
    private final String leftRight = "LRLRRLLRRLRLRRLLRRLRRLLRRLRRRLRRLRRLRRRLRRLRLRLRLLLRRRLRLLRRLRLRRRLRRLLRRLRRRLRRLRLRLRRRLRLRRRLLRLLRRLRRRLLRRLRLLLRRLRLRLLRRLRLRRRLLRLLRRRLRLLRRRLRRLRRLRRRLRRRLLRLLRRRLRRRLRRLRRRLLRRRLRLRRLLRRLRLRLRRLRRLRLLRRRLRRRLLLRLRLRRRLLRRLRRRLRLRRLLRRLRRLLRLRLRRRLRLRLRLRRRLRLLRRRLRRRLRRLLLRRRR";
    // private final String leftRight = "LR";

    private HashMap<String, ArrayList<String>> directions = new HashMap<String, ArrayList<String>>();

    private ArrayList<String> nodes = new ArrayList<>();
    private int[] cycles;


    public void execute(){
        System.out.println("\n= Advent 8_2 Executing");

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

            findStartNodes();
            System.out.println("Stored: " + this.nodes.size() + " Start Nodes");

            this.cycles = new int[this.nodes.size()];

            findCycles();
            long lcmCycleLengths = LeastCommonMultiple.LCM(this.cycles, this.cycles.length);
            System.out.println("** Least Common Multiple of Cycle Lengths = " + lcmCycleLengths );

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    private void findCycles(){
        Integer pos = 0; // This keeps track of the position in the left-Right instructions list
        Long numSteps = 0L; // This is the total number of steps

        for(int i = 0; i < this.nodes.size(); i++ ){
            String startNode = this.nodes.get(i);
            int cycle = findRoute(startNode);
            System.out.println("Start Node: " + startNode + " :Cycle Length: " + cycle);
            this.cycles[i] = cycle;
        }
    }

    private int findRoute(String startNode){
        Integer pos = 0; // This keeps track of the position in the left-Right instructions list
        Integer numSteps = 0; // This is the total number of steps
        Boolean isZNode = Boolean.FALSE; // becomes true if it hits ZZZ

        // Find the record containing a key "AAA"
        ArrayList<String> dirRec;
        dirRec = this.directions.get(startNode);
        // System.out.println("Found Start:" + dirRec.get(0) + "," + dirRec.get(1) );

        while(!isZNode){

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

            if(isEnd(nextNode)){
                isZNode = Boolean.TRUE;
            }
        }
        // System.out.println("Pos:" + pos + " NTEPS:" + numSteps);
        return numSteps.intValue();
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


    private void findStartNodes(){
        for(String node: this.directions.keySet()){
            if(isStart(node)){
                // System.out.println("Start Node:" + node);
                this.nodes.add(node);
            }
        }
    }

    private Boolean isStart(String nodeStr){
        if(nodeStr.charAt(2) == 'A'){
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

    private Boolean isEnd(String nodeStr){
        if(nodeStr.charAt(2) == 'Z'){
            return Boolean.TRUE;
        } else return Boolean.FALSE;
    }

}
