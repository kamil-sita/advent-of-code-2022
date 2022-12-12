package place.sita.aoc2022.day12;

import place.sita.aoc2022.utils.Coords;
import place.sita.aoc2022.utils.Holder;
import place.sita.aoc2022.utils.Map2d;
import place.sita.aoc2022.utils.Utils;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class Day12_2 {

    public static void main(String[] args) {
        new Day12_2().run();
    }

    public void run() {
        // hardcoding solution this time, not going to write a parser for that
        //List<String> strings = Utils.readFile("day11.txt");
        List<String> strings = Utils.readFile("day12.txt");
        Holder<Coords> start = new Holder<>();
        Holder<Coords> end = new Holder<>();
        Map2d<Integer> map = Map2d.fromStrings(strings, (character, coords, applyFunc) -> {
            char c = character;
            if (c == 'S') {
                applyFunc.accept(coords, (int) (0));
            } else if (c == 'E') {
                start.set(coords);
                applyFunc.accept(coords, (int) ('z' - 'a'));
            } else {
                applyFunc.accept(coords, (int) (c - 'a'));
            }
        });
        Map2d<Integer> visited = new Map2d<>();

        Queue<Step> steps = new ArrayDeque<>();
        steps.add(new Step(start.get(), 0));

        Step current = null;
        while ((current = steps.poll()) != null) {
            Coords currentCoords = current.coords;

            if (map.get(currentCoords).get() == 0) {
                System.out.println(current.steps);
                return;
            }

            int currentHeight = map.get(currentCoords).get();

            Step finalCurrent = current;
            currentCoords
                .around(false)
                .forEach(coord -> {
                   if (visited.coordExists(coord)) {
                       return;
                   }
                   map.get(coord)
                       .ifPresent(height -> {
                           if (height == currentHeight - 1 || height >= currentHeight) {
                               visited.put(coord, finalCurrent.steps);
                               steps.add(new Step(coord, finalCurrent.steps + 1));
                           }
                       });
                });
        }

        visited.print(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.format("%03d", integer) + " ";
            }
        }, "___ ");
        throw new RuntimeException("No solution");

    }

    public static class Step {
        private final Coords coords;
        private final int steps;

        public Step(Coords coords, int steps) {
            this.coords = coords;
            this.steps = steps;
        }

        public Coords getCoords() {
            return coords;
        }

        public int getSteps() {
            return steps;
        }
    }

}
