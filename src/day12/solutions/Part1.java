package day12.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day12\\input\\input.txt");
        int x = 0;
        int y = 0;
        int rotation = 0;
        char facing = 'E';

        for (String line : inputData) {
            char action = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            if (action == 'F') {
                action = facing;
            }

            switch (action) {
                case 'N' -> x += value;
                case 'S' -> x -= value;
                case 'E' -> y -= value;
                case 'W' -> y += value;
                case 'R' -> rotation += value;
                case 'L' -> rotation -= value;
            }

            if (action == 'R' || action == 'L') {
                int tempValue = (rotation / 90) % 4;

                switch (tempValue) {
                    case 0 -> facing = 'E';
                    case 1, -3 -> facing = 'S';
                    case 2, -2 -> facing = 'W';
                    case -1, 3 -> facing = 'N';
                }
            }
        }

        System.out.println(Math.abs(x) + Math.abs(y));
    }
}