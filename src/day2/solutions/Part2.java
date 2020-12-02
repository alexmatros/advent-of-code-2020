package day2.solutions;

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

    public static void main(String[] args)
    {
        List<String> inputData = readFileInList("src\\day2\\input\\input.txt");
        int validPasswords = 0;

        for (String line : inputData) {
            int dashLocation = line.indexOf("-");
            int firstSpaceLocation = line.indexOf(" ");
            int secondSpaceLocation = line.lastIndexOf(" ");

            int firstIndex = Integer.parseInt(line.substring(0, dashLocation));
            int secondIndex = Integer.parseInt(line.substring(dashLocation + 1, firstSpaceLocation));
            char reqChar = line.charAt(firstSpaceLocation + 1);
            String password = line.substring(secondSpaceLocation + 1);

            char charAtFirstIndex = password.charAt(firstIndex - 1);
            char charAtSecondIndex = password.charAt(secondIndex - 1);

            if ((charAtFirstIndex == reqChar) && !(charAtSecondIndex == reqChar)) {
                validPasswords++;
            }
            if (!(charAtFirstIndex == reqChar) && (charAtSecondIndex == reqChar)) {
                validPasswords++;
            }
        }

        System.out.println(validPasswords);
    }
}
