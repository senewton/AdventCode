public class Advent6_2 {

    /* private Double[] times = new Double[]{44.0, 82.0, 69.0, 81.0 };
    private Double[] dists = new Double[]{202.0, 1076.0, 1138.0, 1458.0}; */

    private Double[] times = new Double[]{44826981.0 };
    private Double[] dists = new Double[]{202107611381458.0};

    public void execute(){
        System.out.println("= Advent 6_2 Executing");
        Double runningTotal = 1.0;
        for(int i = 0; i < times.length; i++){
            runningTotal *= countWaysOfWinning(times[i], dists[i]);
        }
        System.out.println("Total Ways:" +  String.format("%.0f", runningTotal));
    }

    private Double countWaysOfWinning(Double raceTime, Double distRecord){
        Double numWaysWinning = 0.0;
        Integer lastProg = 0;

        for(Double buttonMs = 0.0 ; buttonMs < raceTime; buttonMs++){
            Double distCovered = getDistanceCovered(raceTime, buttonMs);
            Double dProgress = buttonMs*100.0/raceTime;
            Integer progress = dProgress.intValue();

            if(progress > lastProg){
                System.out.println("ButtonMs = " + buttonMs + "  Progress % = " + progress);
                lastProg = progress;
            }

            if(distCovered > distRecord){
                numWaysWinning++;
            }
        }
        System.out.println("RaceTime:" + String.format("%.0f", raceTime)
                + " Dist Record:" +  String.format("%.0f", distRecord)
                + " Wins:" +  String.format("%.0f", numWaysWinning));
        return numWaysWinning;
    }

    private Double getDistanceCovered(Double raceTime, Double buttonMs){
        Double distCovered = 0.0;

        Double runningSpeed = buttonMs;
        Double runningTime = raceTime-buttonMs;

        distCovered = runningTime * runningSpeed;
        // System.out.println("RaceTime:" + raceTime + " ButtonMs" + buttonMs + " DistCovered:" + distCovered);

        return distCovered;
    }
}
