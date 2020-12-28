package day6.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private static Set<Character> alphabetSet() {
        Set<Character> alphabetSet = new HashSet<>();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < alphabet.length(); i++) {
            alphabetSet.add(alphabet.charAt(i));
        }

        return alphabetSet;
    }

    public static void main(String[] args) {
        List<String> inputData = readFileInList("src\\day6\\input\\input.txt");

        Set<Character> curPersonAnswered = new HashSet<>();
        Set<Character> everyoneAnswered;
        everyoneAnswered = alphabetSet();
        int sum = 0;

        for (String line : inputData) {
            if (line.isEmpty()) {
                sum += everyoneAnswered.size();
                curPersonAnswered.clear();
                everyoneAnswered = alphabetSet();
            }

            else {
                for (int i = 0; i < line.length(); i++) {
                    curPersonAnswered.add(line.charAt(i));
                }

                everyoneAnswered.retainAll(curPersonAnswered);
                curPersonAnswered.clear();
            }
        }
        sum += everyoneAnswered.size();

        System.out.println(sum);
    }
}
