package day3.solutions;

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

    public static void main(String[] args)
    {
        List<String> inputData = readFileInList("src\\day3\\input\\input.txt");

        int treesHit = 0;
        int idx = 0;
        for (String line : inputData) {
            if (line.charAt(idx % line.length()) == '#') {
                treesHit++;
            }
            idx += 3;
        }

        System.out.println(treesHit);
    }
}
