package place.sita.aoc2022.da10;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.Utils;

import java.util.List;

public class Day10_1 {

    public static void main(String[] args) {
        new Day10_1().run();
    }

    private int xValue = 1;

    public void run() {
        List<String> strings = Utils.readFile("day10.txt");

        int currentCycle = 1;
        int id = 0;
        int nextEvaluationMoment = 20;
        int evaluationLimit = 220;
        int currentInstructionEndsEvaluationAt = -1;
        Ins currentInstruction = null;
        int value = 0;

        while (true) {
            // start
            if (currentInstruction == null) {
                String s = strings.get(id);
                if (s.startsWith("addx")) {
                    InsInput input = QuickParse.parseToObject("${instruction}$ $(int){value}$", s, new InsInput(), InsInput.class);
                    currentInstruction = new AddValIns(input.value);
                } else {
                    currentInstruction = new NoopIns();
                }
                currentInstructionEndsEvaluationAt = currentCycle + currentInstruction.insLength() - 1;
                id++;
            }

            if (currentCycle == nextEvaluationMoment) {
                value += currentCycle * xValue;
                nextEvaluationMoment += 40;
            }

            // end
            if (currentInstructionEndsEvaluationAt == currentCycle) {
                currentInstruction.evaluate(this);
                currentInstruction = null;
            }

            currentCycle++;
            if (currentCycle > evaluationLimit) {
                break;
            }
        }

        System.out.println(value);

    }

    public interface Ins {
        void evaluate(Day10_1 day);
        int insLength();
    }

    public static class NoopIns implements Ins {
        @Override
        public void evaluate(Day10_1 day) {

        }

        @Override
        public int insLength() {
            return 1;
        }
    }

    public static class AddValIns implements Ins {
        private final int adj;

        public AddValIns(int adj) {
            this.adj = adj;
        }


        @Override
        public void evaluate(Day10_1 day) {
            day.xValue += adj;
        }

        @Override
        public int insLength() {
            return 2;
        }
    }

    public static class InsInput {
        private String instruction;

        private int value;
    }

}
