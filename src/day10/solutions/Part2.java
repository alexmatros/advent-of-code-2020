package day10.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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

    private static List<Integer> toIntList(List<String> inputData) {
        List<Integer> convertedList = new ArrayList<>();

        for (String line : inputData) {
            int lineAsInt = Integer.parseInt(line);
            convertedList.add(lineAsInt);
        }

        return convertedList;
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day10\\input\\input.txt");
        List<Integer> inputIntData = toIntList(inputData);
        inputIntData.add(0);
        Collections.sort(inputIntData);
        int maxValue = inputIntData.get(inputIntData.size() - 1);

        TreeMap<Integer, Long> masterMap = new TreeMap<>();
        masterMap.put(0, (long) 1);

        ArrayList<Integer> newLists = new ArrayList<>();
        TreeMap<Integer, Long> newMap = new TreeMap<>();

        for (int i = 1; i < inputIntData.size(); i++) {

            for (int key : masterMap.keySet()) {
                if (inputIntData.get(i) - key <= 3) {
                    int newKey = inputIntData.get(i);
                    newMap.put(newKey, (newMap.get(newKey)==null?0:newMap.get(newKey)) + masterMap.get(key));
                }
            }

            masterMap.putAll(newMap);
            newMap.clear();

            TreeMap<Integer, Long> tempMap = new TreeMap<>();

            for (int key : masterMap.keySet()) {
                if (inputIntData.get(i) - key < 3) {
                    tempMap.put(key, masterMap.get(key));
                }
            }

            masterMap = tempMap;

            System.out.println("i: " + i + " Map: " + masterMap);
        }

        System.out.println(masterMap.get(maxValue));
    }
}
