import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Advent2_1 {

    final static Integer MAX_RED = 12;
    final static Integer MAX_GREEN = 13;
    final static Integer MAX_BLUE = 14;

    public void execute(){
        System.out.println("= Advent 2_1 Executing");

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv2.txt");

            Scanner sc = new Scanner(inputStream);

            Integer gameCounter = 1;
            Integer sumOfPossibleGames = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                System.out.println("Read: "+ str);
                sumOfPossibleGames += parseGame(str, gameCounter);
                gameCounter++;
            }
            System.out.println("Sum of Possible Game IDS =" + sumOfPossibleGames);
        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }
    }

    private Integer parseGame(String str, Integer gameCounter){
        Boolean isPossibleGame = true;
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

                if(isGamePossible(colour, counter) == false){
                    isPossibleGame = false;
                }
            }
        }
        if(isPossibleGame == true){
            return gameCounter;
        } else{
            return 0;
        }
    }

    private boolean isGamePossible(String colour, Integer counter){
        boolean result = true;
        if(colour.equals("red")){
            if(counter > MAX_RED){
                return false;
            }
        }

        if(colour.equals("green")){
            if(counter > MAX_GREEN){
                return false;
            }
        }

        if(colour.equals("blue")){
            if(counter > MAX_BLUE){
                return false;
            }
        }
        return result;
    }

}
