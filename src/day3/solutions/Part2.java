package day3.solutions;

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

    public static int treesHitWithSlope(List<String> inputData, int right, int down) {
        int treesHit = 0;
        int idx = 0;
        int columns = inputData.get(0).length();

        for (int i = 0; i < inputData.size(); i+= down) {
            if (inputData.get(i).charAt(idx) == '#') {
                treesHit++;
            }
            idx = (idx + right) % columns;
        }

        return treesHit;
    }


    public static void main(String[] args)
    {
        List<String> inputData = readFileInList("src\\day3\\input\\input.txt");

        long r1d1 = treesHitWithSlope(inputData, 1, 1);
        long r3d1 = treesHitWithSlope(inputData, 3, 1);
        long r5d1 = treesHitWithSlope(inputData, 5, 1);
        long r7d1 = treesHitWithSlope(inputData, 7, 1);
        long r1d2 = treesHitWithSlope(inputData, 1, 2);

        long productOfTreesHit = r1d1 * r3d1 * r5d1 * r7d1 * r1d2;
        System.out.println(productOfTreesHit);
    }
}
