package day7.solutions;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Part2 {
    private static HashMap<String, HashMap<String, Integer>> rules;

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

    private static boolean hasShinyGold(HashMap<String, HashMap<String, Integer>> rules, HashMap<String, Integer> insideBagRules) {
        if (insideBagRules == null) {
            return false;
        }
        else if (insideBagRules.containsKey("shiny gold")) {
            return true;
        }
        else {
            for (String insideBag : insideBagRules.keySet()) {
                HashMap<String, Integer> curBagRules = rules.get(insideBag);

                if (hasShinyGold(rules, curBagRules)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static HashMap<String, HashMap<String, Integer>> loadInput() {
        List<String> inputData = readFileInList("src\\day7\\input\\input.txt");

        HashMap<String, HashMap<String, Integer>> rules = new HashMap<String, HashMap<String, Integer>>();

        for (String line : inputData) {
            int idxBagsContain = line.indexOf("bags contain");
            int idxNoOtherBags = line.indexOf("no other bags");

            String mainBag = line.substring(0, idxBagsContain - 1);
            HashMap<String, Integer> insideBagRules = null;

            if (idxNoOtherBags == -1) {
                String rulesForMainBag = line.substring(idxBagsContain + 13, line.indexOf("."));

                String[] insideBags = rulesForMainBag.split(",");
                insideBagRules = new HashMap<String, Integer>();

                for (String bag : insideBags) {
                    bag = bag.trim();
                    int numBags = Integer.parseInt(bag.substring(0, 1));
                    String insideBag = bag.substring(2, bag.indexOf("bag") - 1);

                    insideBagRules.put(insideBag, numBags);
                }
            }

            rules.put(mainBag, insideBagRules);
        }
        return rules;
    }

    private static int numBagsInside(HashMap<String, Integer> shinyGoldRules) {
        int curBags = 0;

        if (shinyGoldRules != null) {
            for (String insideBag : shinyGoldRules.keySet()) {
                System.out.println("LB" + curBags);
                curBags += shinyGoldRules.get(insideBag) + (shinyGoldRules.get(insideBag) * numBagsInside(rules.get(insideBag)));
                System.out.println("LA" + curBags);
            }

        }

        System.out.println("R" + curBags);
        return curBags;
    }

    public static void main(String[] args) {
        rules = loadInput();
        HashMap<String, Integer> shinyGoldRules = rules.get("shiny gold");

        System.out.println(numBagsInside(shinyGoldRules));
    }
}
