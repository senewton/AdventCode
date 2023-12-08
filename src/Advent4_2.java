import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Advent4_2 {
    public void execute() {
        System.out.println("= Advent 4_2 Executing");

        final Integer MAX_ROWS = 202;
        final Integer[] cardCount = new Integer[MAX_ROWS];

        for(int i = 0; i < MAX_ROWS; i++){
            cardCount[i] = 1;  // One of each card
        }

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv4.txt");

            Scanner sc = new Scanner(inputStream);
            Integer rowCounter = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                Integer noCardsWon = getWinningCards(str);
                Integer countOfThisCard = cardCount[rowCounter];

                for(int j = 0; j < noCardsWon; j++ ){
                    cardCount[rowCounter+1+j] += countOfThisCard;
                }
                rowCounter++;
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        Integer totalSum = 0;
        for(int i = 0; i < MAX_ROWS; i++){
            totalSum += cardCount[i] ;
        }
        System.out.println("Total Score:" + totalSum);
    }

    private int getWinningCards(String str){

        // Lose the prefix
        str = str.substring(str.indexOf(':')+1, str.length());
        String winStr  = str.substring(0, str.indexOf('|'));
        String yourStr = str.substring(str.indexOf('|')+1, str.length());
        System.out.println(winStr + ":" + yourStr);

        List<String> winScoreList = Arrays.asList(winStr.split(" "));
        List<String> yourScoreList = Arrays.asList(yourStr.split(" "));

        ArrayList<Integer> winScores = new ArrayList<>();
        ArrayList<Integer> yourScores = new ArrayList<>();

        for(String winScoreStr: winScoreList){
            if(!winScoreStr.isEmpty()){
                winScores.add(Integer.parseInt(winScoreStr));
            }
        }

        for(String yourScoreStr: yourScoreList){
            if(!yourScoreStr.isEmpty()){
                yourScores.add(Integer.parseInt(yourScoreStr));
            }
        }

        Integer winningNumbers = 0 ;

        for(Integer yourScore: yourScores){
            for(Integer winScore: winScores){
                if(yourScore.equals(winScore)){
                    winningNumbers++;
                }
            }
        }
        System.out.println("Number of winning scores:" + winningNumbers);

        return winningNumbers;
    }
}
