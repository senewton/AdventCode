public class CardHand implements Comparable<CardHand>{
    public String contents;
    public String transformed;
    public Integer bidValue;

    public ScoreCard type;

    CardHand(String input, Integer bidValue, ScoreCard type) {
        this.contents = input;
        this.bidValue = bidValue;
        this.type = type;

        transformed = contents;
        transform();
        transformed = type.ordinal() + "_" + transformed;

        System.out.println("Card:" + this.contents + " Transformed to:" + this.transformed);
    }

    public int compareTo(CardHand ch){
        return ch.toString().compareTo(transformed);
    }

    private void transform(){
        // A, K, Q, J, T, 9, 8, 7, 6, 5, 4, 3, 2

        transformed = transformed.replaceAll("A", "a");
        transformed = transformed.replaceAll("K", "b");
        transformed = transformed.replaceAll("Q", "c");
        transformed = transformed.replaceAll("J", "n"); // Changed from d for 7_2
        transformed = transformed.replaceAll("T", "e");
        transformed = transformed.replaceAll("9", "f");
        transformed = transformed.replaceAll("8", "g");
        transformed = transformed.replaceAll("7", "h");
        transformed = transformed.replaceAll("6", "i");
        transformed = transformed.replaceAll("5", "j");
        transformed = transformed.replaceAll("4", "k");
        transformed = transformed.replaceAll("3", "l");
        transformed = transformed.replaceAll("2", "m");
    }

    public String toString(){
        return transformed;
    }
}
