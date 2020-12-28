package day10.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Collections.sort(inputIntData);
        int totalDiffOf1 = 1;
        int totalDiffOf3 = 1;

        for (int i = 1; i < inputIntData.size(); i++) {
            int difference = inputIntData.get(i) - inputIntData.get(i - 1);

            if (difference == 1) {
                totalDiffOf1++;
            }
            else if (difference == 3) {
                totalDiffOf3++;
            }
        }

        System.out.println(totalDiffOf1 * totalDiffOf3);
    }
}
