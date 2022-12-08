package place.sita.aoc2022.day8;

import place.sita.aoc2022.utils.Coords;
import place.sita.aoc2022.utils.Map2d;
import place.sita.aoc2022.utils.Utils;

import java.util.List;
import java.util.Optional;

public class Day8_2 {

    public static void main(String[] args) {
        List<String> strings = Utils.readFile("day8.txt");

        Map2d<Integer> map2D = Map2d.fromStrings(strings);

        long max = 0;

        for (int x = 0; x < map2D.getWidth(); x++) {
            for (int y = 0; y < map2D.getHeight(); y++) {

                Coords current = new Coords(x, y);

                long scenicScore = 1;

                scenicScore *= runFor(map2D, current, 1, 0);
                scenicScore *= runFor(map2D, current, -1, 0);
                scenicScore *= runFor(map2D, current, 0, 1);
                scenicScore *= runFor(map2D, current, 0, -1);

                if (scenicScore > max) {
                    max = scenicScore;
                }

                System.out.println("scenic score for " + x + ", " + y + ": " + scenicScore);
            }
        }

        System.out.println(max);

    }

    private static long runFor(Map2d<Integer> map2D, Coords current, int xDiff, int yDiff) {

        long count = 0;

        int maxHeight = map2D.get(current).get();

        while (true) {
            current = current.withDiff(xDiff, yDiff);
            Optional<Integer> optHeight = map2D.get(current);
            if (optHeight.isEmpty()) {
                return count;
            }
            int currentHeight = optHeight.get();
            if (currentHeight >= maxHeight) {
                return count + 1;
            }
            count++;
        }
    }


}
