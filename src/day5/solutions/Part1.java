package day5.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

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

    private static int getId(int row, int column) {
        return (row * 8) + column;
    }

    private static int getRow(String boardingPass) {
        String firstSeven = boardingPass.substring(0, boardingPass.length() - 3);
        double min = 0;
        double max = 127;

        for (int i = 0; i < 7; i ++) {
            char letter = firstSeven.charAt(i);

            if (letter == 'B') {
                min = min + Math.ceil((max - min) / 2);
            }
            else {
                max = max - Math.ceil((max - min) / 2);
            }
        }

        return (int)max;
    }

    private static int getColumn(String boardingPass) {
        String lastThree = boardingPass.substring(boardingPass.length() - 3);
        double min = 0;
        double max = 7;

        for (int i = 0; i < 3; i ++) {
            char letter = lastThree.charAt(i);

            if (letter == 'R') {
                min = min + Math.ceil((max - min) / 2);
            }
            else {
                max = max - Math.ceil((max - min) / 2);
            }
        }

        return (int)max;
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day5\\input\\input.txt");
        TreeSet<Integer> idArray = new TreeSet<>();

        for (String boardingPass : inputData) {
            idArray.add(getId(getRow(boardingPass), getColumn(boardingPass)));
        }

        System.out.println(idArray.last());
    }
}
