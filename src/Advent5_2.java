public class Advent5_2 {
    /*private long[] seedArray = new long[]{
            3640772818L, 104094365,1236480411, 161072229, 376099792, 370219099, 1590268366,
            273715765, 3224333694L, 68979978, 2070154278, 189826014, 3855332650L, 230434913,
            3033760782L, 82305885, 837883389, 177854788, 2442602612L, 571881366 };*/

    private long[] seedStartArray = new long[]{
            3640772818L, 1236480411, 376099792, 1590268366,
            3224333694L, 2070154278, 3855332650L,
            3033760782L, 837883389, 2442602612L};

    private long[] seedLengthArray = new long[]{
            104094365, 161072229, 370219099,
            273715765, 68979978, 189826014, 230434913,
            82305885, 177854788, 571881366 };



    public void execute(){
        System.out.println("= Advent 5_2 Executing");

        /*for(int i = 0; i < seedStartArray.length; i++){
            System.out.println(seedStartArray[i] + ", " + seedLengthArray[i]);
        }*/

        RangeMap seedToSoilMap = new RangeMap("c:/home/code/Java/AdventCode/data/adv5.txt", 5, 7 );
        seedToSoilMap.ReadFile();

        RangeMap soilToFertilizerMap = new RangeMap("c:/home/code/Java/AdventCode/data/adv5.txt", 10, 38 );
        soilToFertilizerMap.ReadFile();

        RangeMap fertilizerToWaterMap = new RangeMap("c:/home/code/Java/AdventCode/data/adv5.txt", 41, 83 );
        fertilizerToWaterMap.ReadFile();

        RangeMap waterToLightMap = new RangeMap("c:/home/code/Java/AdventCode/data/adv5.txt", 86, 126 );
        waterToLightMap.ReadFile();

        RangeMap lightToTemperatureMap = new RangeMap("c:/home/code/Java/AdventCode/data/adv5.txt", 129, 159 );
        lightToTemperatureMap.ReadFile();

        RangeMap temperatureToHumidityMap = new RangeMap("c:/home/code/Java/AdventCode/data/adv5.txt", 162, 208 );
        temperatureToHumidityMap.ReadFile();

        RangeMap humidityToLocationMap = new RangeMap("c:/home/code/Java/AdventCode/data/adv5.txt", 211, 251 );
        humidityToLocationMap.ReadFile();

        Long longMinResult = Long.MAX_VALUE;

        for(int i = 0; i < seedStartArray.length; i++){
            System.out.println("Seed:" + seedStartArray[i]);
            for(Long longSeed = Long.valueOf(seedStartArray[i]);
                longSeed < Long.valueOf(seedStartArray[i]+seedLengthArray[i]); longSeed++) {
                // System.out.println("Seed:" + longSeed);
                Long soilKey = seedToSoilMap.getMap(longSeed);
                // System.out.println("returned: " + soilKey);
                Long fertKey = soilToFertilizerMap.getMap(soilKey);
                Long waterKey = fertilizerToWaterMap.getMap(fertKey);
                Long lightKey = waterToLightMap.getMap(waterKey);
                Long tempKey = lightToTemperatureMap.getMap(lightKey);
                Long humidKey = temperatureToHumidityMap.getMap(tempKey);
                Long locKey = humidityToLocationMap.getMap(humidKey);
                //System.out.println("Location:" + locKey);

                if (locKey.compareTo(longMinResult) < 0) {
                    longMinResult = locKey;
                }
            }
        }
        System.out.println("Min result:" + longMinResult);
    }
}
