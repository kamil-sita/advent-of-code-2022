package place.sita.aoc2022.day6;

import place.sita.aoc2022.utils.Utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6_2 {

    public static void main(String[] args) {

        List<String> lines = Utils.readFile("day6.txt");
        String line = lines.get(0);

        int length = 14;

        for (int i = length - 1; i < line.length(); i++) {
            Set<Character> characters = new HashSet<>();
            for (int j = i - (length - 1); j <= i; j++) {
                characters.add(line.charAt(j));
            }
            if (characters.size() == length) {
                System.out.println(i + 1);
                return;
            }
            characters.clear();
        }

    }


}
