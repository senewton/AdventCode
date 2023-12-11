public class RangeRecord{
    public Long destStart;
    public Long sourceStart;
    public Long rangeLength;

    public void seeRecord(){
        System.out.println("Source Start: " + sourceStart + " Dest Start: " + destStart + " Length: " + rangeLength);
    }
}
