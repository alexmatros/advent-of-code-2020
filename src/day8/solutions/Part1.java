package day8.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part1 {
    private static int accumulator = 0;

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
        List<String> inputData = readFileInList("src\\day8\\input\\input.txt");
        List<Integer> instructionsVisited = new ArrayList<Integer>();
        boolean alreadyVisited = false;
        int instructionIdx = 0;

        while (!alreadyVisited) {
            if (instructionsVisited.contains(instructionIdx)) {
                alreadyVisited = true;
            }
            else {
                instructionsVisited.add(instructionIdx);

                String line = inputData.get(instructionIdx);
                String operation = line.substring(0, line.indexOf(" "));
                String argument = line.substring(line.indexOf(" ") + 1);

                if (operation.equals("nop")) {
                    instructionIdx++;
                }

                else if (operation.equals("jmp")) {
                    if (argument.startsWith("-")) {
                        instructionIdx -= Integer.parseInt(argument.substring(1));
                    }
                    else {
                        instructionIdx += Integer.parseInt(argument.substring(1));
                    }
                }

                else {
                    if (argument.startsWith("-")) {
                        accumulator -= Integer.parseInt(argument.substring(1));
                    }
                    else {
                        accumulator += Integer.parseInt(argument.substring(1));
                    }

                    instructionIdx++;
                }
            }
        }

        System.out.println(accumulator);
    }
}
