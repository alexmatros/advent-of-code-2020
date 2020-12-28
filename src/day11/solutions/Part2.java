package day11.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

    private static int numSeatsAdjacent(List<List<Character>> floorPlan, int row, int col) {
        int numSeats = 0;
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};

        for (String direction : directions) {
            numSeats += lookForSeat(floorPlan, direction, row, col);
        }

        return numSeats;
    }

    private static int lookForSeat(List<List<Character>> floorPlan, String direction, int row, int col) { //
        int curRow = row;
        int curCol = col;
        int colLen = floorPlan.get(0).size();
        int rowLen = floorPlan.size();

        while (true) {
            switch (direction) {
                case "N" -> curRow++;
                case "NE" -> {
                    curRow++;
                    curCol++;
                }
                case "E" -> curCol++;
                case "SE" -> {
                    curRow--;
                    curCol++;
                }
                case "S" -> curRow--;
                case "SW" -> {
                    curRow--;
                    curCol--;
                }
                case "W" -> curCol--;
                case "NW" -> {
                    curRow++;
                    curCol--;
                }
            }

            if ((curRow == -1) || (curRow == rowLen) || (curCol == -1) || (curCol == colLen)) {
                return 0;
            }
            else {
                if (floorPlan.get(curRow).get(curCol) == '#') {
                    return 1;
                }
                else if (floorPlan.get(curRow).get(curCol) == 'L') {
                    return 0;
                }
            }
        }
    }

    private static void convertToCharList(List<String> inputData, List<List<Character>> floorPlan) {
        for (String line : inputData) {
            List<Character> curRow = new ArrayList<>();

            for (int i = 0; i < line.length(); i++) {
                curRow.add(line.charAt(i));
            }

            floorPlan.add(curRow);
        }
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day11\\input\\input.txt");
        List<List<Character>> floorPlan = new ArrayList<List<Character>>();
        boolean changed = true;
        long totalOccupied = 0;

        convertToCharList(inputData, floorPlan);

        while (changed) {
            changed = false;
            List<List<Character>> updatedFloorPlan = new ArrayList<List<Character>>();

            for (int row = 0; row < floorPlan.size(); row++) {
                List<Character> line = floorPlan.get(row);
                List<Character> updatedLine = new ArrayList<>();

                for (int col = 0; col < line.size(); col++) {
                    char curSeat = line.get(col);

                    if (curSeat == '.') {
                        updatedLine.add('.');
                    }

                    else {
                        if (curSeat == '#') {
                            if (numSeatsAdjacent(floorPlan, row, col) >= 5) {
                                updatedLine.add('L');
                                changed = true;
                            }
                            else {
                                updatedLine.add('#');
                            }
                        }
                        else {
                            if (numSeatsAdjacent(floorPlan, row, col) == 0) {
                                updatedLine.add('#');
                                changed = true;
                            }
                            else {
                                updatedLine.add('L');
                            }
                        }
                    }
                }

                updatedFloorPlan.add(updatedLine);
            }

            for (List<Character> row : floorPlan){
                System.out.println(row);
            }
            System.out.println("----------------------------------------------------");

            floorPlan = new ArrayList<>(updatedFloorPlan);
        }

        for (List<Character> row : floorPlan) {
            for (Character seat : row) {
                if (seat == '#') {
                    totalOccupied++;
                }
            }
        }

        System.out.println(totalOccupied);
    }
}
