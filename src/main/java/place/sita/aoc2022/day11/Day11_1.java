package place.sita.aoc2022.day11;

import place.sita.aoc2022.utils.CollectionUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day11_1 {

    public static void main(String[] args) {
        new Day11_1().run();
    }

    private List<Monkey> monkeys = new ArrayList<>();

    public void run() {
        // hardcoding solution this time, not going to write a parser for that
        //List<String> strings = Utils.readFile("day11.txt");

        defineMonkeys();

        int rounds = 20;

        for (int round = 1; round <= rounds; round++) {

            for (int monkeyId = 0; monkeyId < monkeys.size(); monkeyId++) {
                Monkey monkey = monkeys.get(monkeyId);

                while (monkey.hasNext()) {
                    int item = monkey.getNext();
                    item = monkey.inspect(item);
                    item /= 3;
                    if (monkey.test(item)) {
                        monkeys.get(monkey.throwToIfTrue()).add(item);
                    } else {
                        monkeys.get(monkey.throwToIfFalse()).add(item);
                    }

                }
            }

        }

        List<Integer> inspectionCount = monkeys.stream()
            .map(Monkey::getInspections)
            .collect(Collectors.toList());

        inspectionCount.sort(Integer::compareTo);

        int lastIndex = CollectionUtils.lastIndex(inspectionCount);

        System.out.println(inspectionCount.get(lastIndex) * inspectionCount.get(lastIndex - 1));
    }

    private void defineMonkeys() {
        monkeys.add(
            new Monkey(0, List.of(61), 7, 4, 5) {
                @Override
                public int inspect(int val) {
                    return val * 11;
                }
            }
        );
        monkeys.add(
            new Monkey(1, List.of(76, 92, 53, 93, 79, 86, 81), 2, 6, 2) {
                @Override
                public int inspect(int val) {
                    return val + 4;
                }
            }
        );
        monkeys.add(
            new Monkey(2, List.of(91, 99), 5,  0, 13) {
                @Override
                public int inspect(int val) {
                    return val * 19;
                }
            }
        );
        monkeys.add(
            new Monkey(3, List.of(58, 67, 66), 6, 1, 7) {
                @Override
                public int inspect(int val) {
                    return val * val;
                }
            }
        );
        monkeys.add(
            new Monkey(4, List.of(94, 54, 62, 73), 3, 7, 19) {
                @Override
                public int inspect(int val) {
                    return val + 1;
                }
            }
        );
        monkeys.add(
            new Monkey(5, List.of(59, 95, 51, 58, 58), 0, 4, 11) {
                @Override
                public int inspect(int val) {
                    return val + 3;
                }
            }
        );
        monkeys.add(
            new Monkey(6, List.of(87, 69, 92, 56, 91, 93, 88, 73), 5, 2, 3) {
                @Override
                public int inspect(int val) {
                    return val + 8;
                }
            }
        );
        monkeys.add(
            new Monkey(7, List.of(71, 57, 86, 67, 96, 95), 3, 1, 17) {
                @Override
                public int inspect(int val) {
                    return val + 7;
                }
            }
        );
    }

    public abstract static class Monkey {

        private final int id;
        private Queue<Integer> current = new ArrayDeque<>();
        private int inspections = 0;
        private final int throwToIfTrue;
        private final int throwToIfFalse;
        private final int divisibilityTest;


        protected Monkey(int id, List<Integer> initialNext, int throwToIfTrue, int throwToIfFalse, int divisibilityTest) {
            this.id = id;
            this.throwToIfTrue = throwToIfTrue;
            this.throwToIfFalse = throwToIfFalse;
            this.divisibilityTest = divisibilityTest;
            current.addAll(initialNext);
        }

        public void add(int integer) {
            current.add(integer);
        }

        public boolean hasNext() {
            return !current.isEmpty();
        }

        public int getNext() {
            inspections++;
            return current.remove();
        }

        public abstract int inspect(int val);

        public boolean test(int val) {
            return val % divisibilityTest == 0;
        }

        public int throwToIfTrue() {
            return throwToIfTrue;
        }

        public int throwToIfFalse() {
            return throwToIfFalse;
        }

        public int getInspections() {
            return inspections;
        }
    }

}
