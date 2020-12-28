package day13.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
    public static List<String> readFileInList(String fileName)
    {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

    private static boolean nextBusCanDepart(long timestamp, int busIdx, String[] busIDs) {
        while (busIDs[busIdx].equals("x")) {
            busIdx++;
            timestamp++;
        }
        if (busIDs[busIdx].equals("937")) {
            System.out.println("Timestamp: " + timestamp + " BusID: " + busIDs[busIdx]);
        }

        if (timestamp % Integer.parseInt(busIDs[busIdx]) == 0) {
            timestamp++;


            if (busIdx == busIDs.length - 1) {
                return true;
            }

            busIdx++;
            return nextBusCanDepart(timestamp, busIdx, busIDs);
        }

        return false;
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day13\\input\\input.txt");
        int earliestTimestamp = Integer.parseInt(inputData.get(0));
        String[] busIDs = inputData.get(1).split(",");
        long currentTimestamp = Long.parseLong("487905974205104");

        int i=205;
        while (true) {
            currentTimestamp += Integer.parseInt(busIDs[0]);
            // System.out.println("CurrentTimeStamp: " + currentTimestamp);

            if (nextBusCanDepart(currentTimestamp+1, 1, busIDs)) {
                break;
            }
        }

        System.out.println(currentTimestamp);
    }
}