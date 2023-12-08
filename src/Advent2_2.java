import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Advent2_2 {

    public void execute(){
        System.out.println("= Advent 2_2 Executing");

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv2.txt");

            Scanner sc = new Scanner(inputStream);

            Integer gameCounter = 1;
            Integer sumOfCubes = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                System.out.println("Read: "+ str);
                sumOfCubes += parseGame(str, gameCounter);
                gameCounter++;
            }
            System.out.println("Sum of Cubes =" + sumOfCubes);
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    private Integer parseGame(String str, Integer gameCounter){
        Integer sumOfCubes = 0;
        Integer maxRed = 0;
        Integer maxGreen = 0;
        Integer maxBlue = 0;

        str = str.substring(str.indexOf(':')+1, str.length());
        List<String> gameList = Arrays.asList(str.split(";"));

        for(String game: gameList){
            System.out.println("Game: " + game);
            List<String> gameScoreList = Arrays.asList(game.split(","));

            for(String gameScore: gameScoreList){
                System.out.println("Game Score :" + gameScore);
                List<String> numberColourList = Arrays.asList(gameScore.trim().split(" "));
                Integer counter = Integer.parseInt(numberColourList.get(0));
                String colour = numberColourList.get(1);
                System.out.println("Colour=" + colour + "  Count=" + counter );

                if(colour.equals("red")){
                    if(counter > maxRed){
                        maxRed = counter;
                    }
                }

                if(colour.equals("green")){
                    if(counter > maxGreen){
                        maxGreen = counter;
                    }
                }

                if(colour.equals("blue")){
                    if(counter > maxBlue){
                        maxBlue = counter;
                    }
                }
            }
        }
        sumOfCubes = maxRed * maxBlue * maxGreen;
        return sumOfCubes;
    }



}
