package day11.solutions;

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

    private static int numSeatsAdjacent(List<List<Character>> floorPlan, int row, int col) {
        int takenSeats = 0;

        if (row >= 1) {
            List<Character> rowAbove = floorPlan.get(row - 1);

            if (col >= 1) {
                if (rowAbove.get(col - 1) == '#') { // top left
                    takenSeats++;
                }
            }

            if (rowAbove.get(col) == '#') { // top centre
                takenSeats++;
            }

            if (col < rowAbove.size() - 1) {
                if (rowAbove.get(col + 1) == '#') { // top right
                    takenSeats++;
                }
            }
        }

        if (row < floorPlan.size() - 1) {
            List<Character> rowBelow = floorPlan.get(row + 1);

            if (col >= 1) {
                if (rowBelow.get(col - 1) == '#') { // bottom left
                    takenSeats++;
                }
            }

            if (rowBelow.get(col) == '#') { // bottom centre
                takenSeats++;
            }

            if (col < rowBelow.size() - 1) {
                if (rowBelow.get(col + 1) == '#') { // bottom right
                    takenSeats++;
                }
            }
        }

        List<Character> curRow = floorPlan.get(row);

        if (col >= 1) {
            if (curRow.get(col - 1) == '#') {
                takenSeats++;
            }
        }

        if (col < curRow.size() - 1) {
            if (curRow.get(col + 1) == '#') {
                takenSeats++;
            }
        }

        return takenSeats;
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
                            if (numSeatsAdjacent(floorPlan, row, col) >= 4) {
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

            floorPlan = new ArrayList<>(updatedFloorPlan);
        }

        for (List<Character> row : floorPlan) {
            for (Character seat : row) {
                if (seat == '#') {
                    totalOccupied++;
                }
            }
        }

        for (List<Character> row : floorPlan){
            System.out.println(row);
        }

        System.out.println(totalOccupied);
    }
}
