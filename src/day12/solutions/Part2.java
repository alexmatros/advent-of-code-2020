package day12.solutions;

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

    private static int[] rotateWaypoint(char direction, int value, int waypointX, int waypointY) {
        int[] coords = new int[2];
        int newX = 0;
        int newY = 0;
        int tempValue = (value / 90) % 4;

        switch (tempValue) {
            case 1 -> { // rotate 90 deg
                if (direction == 'R') {
                    newX = waypointY;
                    newY = -waypointX;
                }
                else {
                    newX = -waypointY;
                    newY = waypointX;
                }
            }
            case 2 -> { // rotate 180 deg
                newX = -waypointX;
                newY = -waypointY;
            }

            case 3 -> { // rotate 270 deg
                if (direction == 'R') {
                    newX = -waypointY;
                    newY = waypointX;
                }
                else {
                    newX = waypointY;
                    newY = -waypointX;
                }
            }
        }

        coords[0] = newX;
        coords[1] = newY;

        return coords;
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day12\\input\\input.txt");
        int boatX = 0;
        int boatY = 0;
        int waypointX = 10;
        int waypointY = 1;

        for (String line : inputData) {
            char action = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (action == 'F') {
                boatX += (waypointX * value);
                boatY += (waypointY * value);
            }

            else {
                switch (action) {
                    case 'N' -> waypointY += value;
                    case 'S' -> waypointY -= value;
                    case 'E' -> waypointX += value;
                    case 'W' -> waypointX -= value;
                    case 'R', 'L' -> {
                        int[] wayPointCoords = rotateWaypoint(action, value, waypointX, waypointY);
                        waypointX = wayPointCoords[0];
                        waypointY = wayPointCoords[1];
                    }
                }
            }
        }

        System.out.println(Math.abs(boatX) + Math.abs(boatY));
    }
}
