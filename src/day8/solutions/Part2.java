package day8.solutions;

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

    private static ArrayList<String> cloneOriginalData(List<String> inputData) {
        return new ArrayList<>(inputData);
    }

    private static int getAccumulatorValue(List<String> inputData) {
        List<Integer> instructionsVisited = new ArrayList<Integer>();
        boolean reachedEnd = false;
        int accumulator = 0;
        int instructionIdx = 0;

        while (!reachedEnd) {
            if (instructionsVisited.contains(inputData.size() - 1)) {
                reachedEnd = true;
            }

            else if (instructionsVisited.contains(instructionIdx)) {
                return -1;
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

        return accumulator;
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day8\\input\\input.txt");
        int accumulatorValue = 0;

        for (String line : inputData) {
            String operation = line.substring(0, line.indexOf(" "));

            if (!operation.equals("acc")) {
                ArrayList<String> clonedList = cloneOriginalData(inputData);
                String swappedInstruction = "";

                if (operation.equals("jmp")) {
                    swappedInstruction = line.replace("jmp", "nop");
                }
                else if (operation.equals("nop")) {
                    swappedInstruction = line.replace("jmp", "nop");
                }

                clonedList.set(inputData.indexOf(line), swappedInstruction);
                accumulatorValue = getAccumulatorValue(clonedList);

                if (accumulatorValue != -1) {
                    break;
                }

            }
        }

        System.out.println(accumulatorValue);
    }

}
