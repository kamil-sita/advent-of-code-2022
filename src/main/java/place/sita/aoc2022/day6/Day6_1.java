package place.sita.aoc2022.day6;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.CollectionUtils;
import place.sita.aoc2022.utils.Utils;

import java.util.*;

public class Day6_1 {

    public static void main(String[] args) {

        List<String> lines = Utils.readFile("day6.txt");
        String line = lines.get(0);

        for (int i = 3; i < line.length(); i++) {
            Set<Character> characters = new HashSet<>();
            for (int j = i - 3; j <= i; j++) {
                characters.add(line.charAt(j));
            }
            if (characters.size() == 4) {
                System.out.println(i + 1);
                return;
            }
            characters.clear();
        }

    }


}
