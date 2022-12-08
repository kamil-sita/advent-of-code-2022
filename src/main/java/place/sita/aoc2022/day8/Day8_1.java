package place.sita.aoc2022.day8;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.Coords;
import place.sita.aoc2022.utils.Map2d;
import place.sita.aoc2022.utils.Utils;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class Day8_1 {

    public static void main(String[] args) {
        List<String> strings = Utils.readFile("day8.txt");

        Map2d<Integer> map2D = Map2d.fromStrings(strings);
        Map2d<Boolean> visible = new Map2d<>();

        // down
        for (int x = 0; x < map2D.getWidth(); x++) {
            boolean first = true;
            int biggest = -1;
            for (int y = 0; y < map2D.getHeight(); y++) {

                if (first) {
                    biggest = map2D.get(new Coords(x, y)).get();
                    visible.put(new Coords(x, y), true);
                    first = false;
                }

                int height = map2D.get(new Coords(x, y)).get();
                if (height > biggest) {
                    biggest = height;
                    visible.put(new Coords(x, y), true);
                }

            }
        }

        // up
        for (int x = 0; x < map2D.getWidth(); x++) {
            boolean first = true;
            int biggest = -1;
            for (int y = map2D.getHeight() - 1; y >= 0; y--) {

                if (first) {
                    biggest = map2D.get(new Coords(x, y)).get();
                    visible.put(new Coords(x, y), true);
                    first = false;
                }

                int height = map2D.get(new Coords(x, y)).get();
                if (height > biggest) {
                    biggest = height;
                    visible.put(new Coords(x, y), true);
                }

            }
        }

        // right
        for (int y = 0; y < map2D.getHeight(); y++) {
            boolean first = true;
            int biggest = -1;
            for (int x = 0; x < map2D.getWidth(); x++) {

                if (first) {
                    biggest = map2D.get(new Coords(x, y)).get();
                    visible.put(new Coords(x, y), true);
                    first = false;
                }

                int height = map2D.get(new Coords(x, y)).get();
                if (height > biggest) {
                    biggest = height;
                    visible.put(new Coords(x, y), true);
                }

            }
        }

        // left
        for (int y = 0; y < map2D.getHeight(); y++) {
            boolean first = true;
            int biggest = -1;
            for (int x = map2D.getWidth() - 1; x >= 0; x--) {

                if (first) {
                    biggest = map2D.get(new Coords(x, y)).get();
                    visible.put(new Coords(x, y), true);
                    first = false;
                }

                int height = map2D.get(new Coords(x, y)).get();
                if (height > biggest) {
                    biggest = height;
                    visible.put(new Coords(x, y), true);
                }

            }
        }

        int visibleCount = 0;

        for (int x = 0; x < map2D.getWidth(); x++) {
            for (int y = 0; y < map2D.getHeight(); y++) {
                if (visible.getOrDefault(new Coords(x, y), false)) {
                    visibleCount++;
                }
            }
        }

        System.out.println(visibleCount);

    }


}
