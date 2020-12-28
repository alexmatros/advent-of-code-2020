package day6.solutions;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

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
        List<String> inputData = readFileInList("src\\day6\\input\\input.txt");
        Set<Character> correctQuestions = new HashSet<>();
        int sum = 0;

        for (String line : inputData) {
            if (line.isEmpty()) {
                sum += correctQuestions.size();
                correctQuestions.clear();
            }

            for (int i = 0; i < line.length(); i++) {
                correctQuestions.add(line.charAt(i));
            }
        }

        sum += correctQuestions.size();

        System.out.println(sum);
    }
}
