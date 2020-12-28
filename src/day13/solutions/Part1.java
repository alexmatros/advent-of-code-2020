package day13.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part1 {
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

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day13\\input\\input.txt");
        int earliestTimestamp = Integer.parseInt(inputData.get(0));
        String[] busIDs = inputData.get(1).split(",");
        Map<Integer, Integer> busTimestamps = new HashMap<>();

        for (String bus : busIDs) {
            if (!bus.equals("x")) {
                int busID = Integer.parseInt(bus);
                int busTimestamp = 0;

                while (busTimestamp < earliestTimestamp) {
                    busTimestamp += busID;
                }

                busTimestamps.put(busID, busTimestamp);
            }
        }

        int earliestBusID = 0;
        int earliestBusTimestamp = earliestTimestamp * 2;

        for (int busID : busTimestamps.keySet()) {
            if (busTimestamps.get(busID) < earliestBusTimestamp) {
                earliestBusID = busID;
                earliestBusTimestamp = busTimestamps.get(busID);
            }
        }

        System.out.println(earliestBusID * (earliestBusTimestamp - earliestTimestamp));
    }
}
