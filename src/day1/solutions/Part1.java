package day1.solutions;

import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;

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

    public static void main(String[] args)
    {
        List<String> inputData = readFileInList("src\\day1\\input\\input.txt");

        for (int i = 0; i < inputData.size(); i++) {
            for (int j = i; j < inputData.size(); j++) {
                int firstNum = Integer.parseInt(inputData.get(i));
                int secondNum = Integer.parseInt(inputData.get(j));
                if (firstNum + secondNum == 2020) {
                    System.out.println(firstNum * secondNum);
                }
            }
        }
    }
} 