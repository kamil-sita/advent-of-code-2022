package place.sita.aoc2022.day10;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.Utils;

import java.util.List;

public class Day10_2 {

    public static void main(String[] args) {
        new Day10_2().run();
    }

    private int xValue = 1;

    public void run() {
        List<String> strings = Utils.readFile("day10.txt");

        int currentCycle = 1;
        int id = 0;
        int evaluationLimit = 240;
        int currentInstructionEndsEvaluationAt = -1;
        Ins currentInstruction = null;
        final int initialPixelId = 0;
        final int length = 40;
        int pixelId = initialPixelId;

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

            // end
            if (pixelId == xValue || pixelId == xValue - 1 || pixelId == xValue + 1) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            if (currentInstructionEndsEvaluationAt == currentCycle) {
                currentInstruction.evaluate(this);
                currentInstruction = null;
            }

            currentCycle++;
            pixelId++;
            if (pixelId == length) {
                pixelId = initialPixelId;
                System.out.println();
            }

            if (currentCycle > evaluationLimit) {
                break;
            }
        }

    }

    public interface Ins {
        void evaluate(Day10_2 day);
        int insLength();
    }

    public static class NoopIns implements Ins {
        @Override
        public void evaluate(Day10_2 day) {

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
        public void evaluate(Day10_2 day) {
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
