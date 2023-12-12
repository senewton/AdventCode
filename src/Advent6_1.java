
public class Advent6_1 {

    /* Tests */
    /* private Integer[] times = new Integer[]{ 7, 15,  30};
    private Integer[] dists = new Integer[]{ 9, 40, 200}; */

    private Integer[] times = new Integer[]{44, 82, 69, 81};
    private Integer[] dists = new Integer[]{202, 1076, 1138, 1458};

    public void execute(){
        System.out.println("= Advent 6_1 Executing");
        Integer runningTotal = 1;
        for(int i = 0; i < times.length; i++){
            runningTotal *= countWaysOfWinning(times[i], dists[i]);
        }
        System.out.println("Total Ways:" + runningTotal);
    }

    private Integer countWaysOfWinning(Integer raceTime, Integer distRecord){
        Integer numWaysWinning = 0;

        for(Integer buttonMs = 0 ; buttonMs < raceTime; buttonMs++){
            Integer distCovered = getDistanceCovered(raceTime, buttonMs);

            if(distCovered > distRecord){
                numWaysWinning++;
            }
        }
        System.out.println("RaceTime:" + raceTime + " Dist Record:" + distRecord + " Wins:" + numWaysWinning);
        return numWaysWinning;
    }

    private Integer getDistanceCovered(Integer raceTime, Integer buttonMs){
        Integer distCovered = 0;

        Integer runningSpeed = buttonMs;
        Integer runningTime = raceTime-buttonMs;

        distCovered = runningTime * runningSpeed;
        // System.out.println("RaceTime:" + raceTime + " ButtonMs" + buttonMs + " DistCovered:" + distCovered);

        return distCovered;
    }
}
