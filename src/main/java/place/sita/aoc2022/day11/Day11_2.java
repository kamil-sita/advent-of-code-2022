package place.sita.aoc2022.day11;

import place.sita.aoc2022.utils.CollectionUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day11_2 {

    public static void main(String[] args) {
        new Day11_2().run();
    }

    private List<Monkey> monkeys = new ArrayList<>();

    public void run() {
        // hardcoding solution this time, not going to write a parser for that
        //List<String> strings = Utils.readFile("day11.txt");

        defineMonkeys();

        int rounds = 10000;
        long divisor = monkeys.stream()
            .map(Monkey::getDivisor)
            .map(Long::valueOf)
            .reduce(1L, (aLong, aLong2) -> aLong * aLong2);

        for (int round = 1; round <= rounds; round++) {

            for (int monkeyId = 0; monkeyId < monkeys.size(); monkeyId++) {
                Monkey monkey = monkeys.get(monkeyId);

                while (monkey.hasNext()) {
                    long item = monkey.getNext();
                    item = monkey.inspect(item);
                    item %= divisor;
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

        System.out.println(((long) inspectionCount.get(lastIndex)) * ((long) inspectionCount.get(lastIndex - 1)));
    }

    private void defineMonkeys() {
        monkeys.add(
            new Monkey(0, List.of(61), 7, 4, 5) {
                @Override
                public long inspect(long val) {
                    return val * 11;
                }
            }
        );
        monkeys.add(
            new Monkey(1, List.of(76, 92, 53, 93, 79, 86, 81), 2, 6, 2) {
                @Override
                public long inspect(long val) {
                    return val + 4;
                }
            }
        );
        monkeys.add(
            new Monkey(2, List.of(91, 99), 5,  0, 13) {
                @Override
                public long inspect(long val) {
                    return val * 19;
                }
            }
        );
        monkeys.add(
            new Monkey(3, List.of(58, 67, 66), 6, 1, 7) {
                @Override
                public long inspect(long val) {
                    return val * val;
                }
            }
        );
        monkeys.add(
            new Monkey(4, List.of(94, 54, 62, 73), 3, 7, 19) {
                @Override
                public long inspect(long val) {
                    return val + 1;
                }
            }
        );
        monkeys.add(
            new Monkey(5, List.of(59, 95, 51, 58, 58), 0, 4, 11) {
                @Override
                public long inspect(long val) {
                    return val + 3;
                }
            }
        );
        monkeys.add(
            new Monkey(6, List.of(87, 69, 92, 56, 91, 93, 88, 73), 5, 2, 3) {
                @Override
                public long inspect(long val) {
                    return val + 8;
                }
            }
        );
        monkeys.add(
            new Monkey(7, List.of(71, 57, 86, 67, 96, 95), 3, 1, 17) {
                @Override
                public long inspect(long val) {
                    return val + 7;
                }
            }
        );
    }

    public abstract static class Monkey {

        private final int id;
        private Queue<Long> current = new ArrayDeque<>();
        private int inspections = 0;
        private final int throwToIfTrue;
        private final int throwToIfFalse;
        private final int divisibilityTest;


        protected Monkey(int id, List<Integer> initialNext, int throwToIfTrue, int throwToIfFalse, int divisibilityTest) {
            this.id = id;
            this.throwToIfTrue = throwToIfTrue;
            this.throwToIfFalse = throwToIfFalse;
            this.divisibilityTest = divisibilityTest;
            initialNext.forEach(i -> {
                current.add(Long.valueOf(i));
            });
        }

        public void add(long l) {
            current.add(l);
        }

        public boolean hasNext() {
            return !current.isEmpty();
        }

        public long getNext() {
            inspections++;
            return current.remove();
        }

        public abstract long inspect(long val);

        public boolean test(long val) {
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

        public int getDivisor() {
            return divisibilityTest;
        }
    }

}
