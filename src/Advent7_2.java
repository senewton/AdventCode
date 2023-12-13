import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

public class Advent7_2 {

    private final ArrayList<CardHand> rankedList = new ArrayList<>();
    private String testStrings[] = new String[] {"32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483", "JJ4QK 99", "JJJJJ 100"};

    public void execute(){
        System.out.println("= Advent 7_2 Executing");

        /* for(int i = 0; i < testStrings.length; i++){
            String str = testStrings[i];
            rankCard(str);
        } */

        try {
            InputStream inputStream = new FileInputStream("c:/home/code/Java/AdventCode/data/adv7.txt");

            Scanner sc = new Scanner(inputStream);
            Integer rowCounter = 0;
            while (sc.hasNext()) {
                String str = sc.nextLine();
                System.out.println("Row"+ rowCounter + " : " + str);
                rankCard(str);
                rowCounter++;
            }

        } catch(FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        }

        Collections.sort(this.rankedList);

        Integer totalScore = 0;
        Integer rank = 1;
        for(CardHand ch : this.rankedList){
            System.out.println(ch.contents + ", " + ch.transformed + ", " + ch.type.name() + ", " + rank + ", " + ch.bidValue );
            totalScore += (rank* ch.bidValue);
            rank++;
        }
        System.out.println("Total Score: " + totalScore);
    }

    private void rankCard(String input){
        List<String> inStrings = Arrays.asList(input.split(" "));
        String card = inStrings.get(0);
        Integer bidAmount = Integer.parseInt(inStrings.get(1));
        card = replaceJokers(card); // Change J to * and then something that be scored with jokers

        ScoreCard scoreCard = findCardScore(card);
        card = inStrings.get(0); // reset back to original for ordering
        CardHand cardhand = new CardHand(card, bidAmount, scoreCard );
        this.rankedList.add(cardhand);
    }

    private ScoreCard findCardScore(String str){

        HashMap<Character, Integer> charMap = new HashMap<>();

        for(int i = 0; i < str.length(); i++){
            Character c = str.charAt(i);

            if(charMap.containsKey(c)){
                Integer counter = charMap.get(c);
                counter ++;
                charMap.put(c, counter);
            } else{
                charMap.put(c, 1);
            }
        }

        Integer numCharacters = 0;
        Integer maxCount = 0;
        for (Character c : charMap.keySet()) {
            numCharacters++;
            Integer cscore = charMap.get(c);
            if(cscore.compareTo(maxCount) >0){
                maxCount = cscore;
            }
        }

        if( numCharacters.equals(1)){
            return ScoreCard.FIVE_KIND;
        }

        if( numCharacters.equals(2)){
            if(maxCount == 4) {
                return ScoreCard.FOUR_KIND;
            }
            if(maxCount.equals(3)) {
                return ScoreCard.FULL_HOUSE;
            }
        }

        if(numCharacters.equals(3)){
            if(maxCount.equals(3)){
                return ScoreCard.THREE_KIND;
            }
            if(maxCount.equals(2)){
                return ScoreCard.TWO_PAIR;
            }
        }

        if(numCharacters.equals(4)){
            if(maxCount.equals(2)){
                return ScoreCard.ONE_PAIR;
            }
        }

        if(numCharacters.equals(5)){
            return ScoreCard.HIGH_CARD;
        }

        System.out.println("Error: " + str);
        return null;
    }

    private String replaceJokers(String str){
        Character[] validChar = new Character[]{'A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
        str = str.replace("J", "*");

        HashMap<Character, Integer> charMap = new HashMap<>();

        for(int i = 0; i < str.length(); i++){
            Character c = str.charAt(i);

            if(charMap.containsKey(c)){
                Integer counter = charMap.get(c);
                counter ++;
                charMap.put(c, counter);
            } else{
                charMap.put(c, 1);
            }
        }

        Integer maxCount = 0;
        for (Character c : charMap.keySet()) {
            Integer cscore = charMap.get(c);
            if(!c.equals('*') && cscore.compareTo(maxCount) >0){
                maxCount = cscore;
            }
        }

        Integer countJokers = 0;
        if(charMap.containsKey('*')) {
            countJokers = charMap.get('*');
        }

        Character hc = 'A';
        if(countJokers > 0) {
            for (int i = 0; i < validChar.length; i++) {
                Character c = validChar[i];
                if (charMap.containsKey(c)) {
                    Integer cscore = charMap.get(c);
                    if(cscore.equals(maxCount)){
                        cscore += countJokers;
                        charMap.put(c, cscore);
                        maxCount += countJokers;
                        hc = Character.valueOf(c);
                    }
                }
            }
        }
        str = str.replace("*", hc.toString());

        return str;
    }
}
