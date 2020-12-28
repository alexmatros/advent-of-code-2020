package day9.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

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

    private static boolean isValid(List<String> inputData, int idx) {
        long value = Long.parseLong(inputData.get(idx));
        boolean isValid = false;

        for (int j = idx - 25; j < idx - 1; j++) {
            long firstNum = Long.parseLong(inputData.get(j));
            for (int k = j + 1; k < idx; k++) {
                long secondNum = Long.parseLong(inputData.get(k));

                if (firstNum + secondNum == value) {
                    isValid = true;
                    break;
                }
            }
        }

        return isValid;
    }

    private static long getListSum(List<String> inputList) {
        long sum = 0;

        for (String line : inputList) {
            sum += Long.parseLong(line);
        }

        return sum;
    }

    private static long findContiguousRange(List<String> inputData, int invalidIdx) {
        for (int i = 0; i < invalidIdx - 1; i++) {
            for (int j = i + 1; j < invalidIdx; j++) {
                List<String> subList = inputData.subList(i, j);
                long contiguousRangeSum = getListSum(subList);

                if (contiguousRangeSum == Long.parseLong(inputData.get(invalidIdx))) {
                    Collections.sort(subList);
                    long minValue = Long.parseLong(subList.get(0));
                    long maxValue = Long.parseLong(subList.get(subList.size() - 1));

                    return minValue + maxValue;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day9\\input\\input.txt");

        for (int i = 25; i < inputData.size(); i++) {
            if (!isValid(inputData, i)) {
                System.out.println(findContiguousRange(inputData, i));
            }
        }
    }
}
