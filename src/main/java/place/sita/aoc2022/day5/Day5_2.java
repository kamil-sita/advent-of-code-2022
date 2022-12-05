package place.sita.aoc2022.day5;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.CollectionUtils;
import place.sita.aoc2022.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day5_2 {

    public static void main(String[] args) {

        List<String> lines = Utils.readFile("day5.txt");
        List<String> buildingLines = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.isBlank()) {
                j = i;
                break;
            }

            buildingLines.add(line);
        }

        List<Stack<Character>> stacks = new ArrayList<>();
        int queueCount = (CollectionUtils.lastValue(buildingLines).length() - 2) / 4 + 1;
        for (int i = 0; i < queueCount; i++) {
            stacks.add(new Stack<>());
        }
        for (int i = j - 2; i >= 0; i--) {
            int queueId = 0;
            String line = buildingLines.get(i);
            for (int x = 1; x < line.length(); x += 4) {
                char c = line.charAt(x);
                if (c != ' ') {
                    stacks.get(queueId).add(c);
                }
                queueId++;
            }
        }

        for (int i = j + 1; i < lines.size(); i++) {
            String line = lines.get(i);
            MoveInstr moveInstr = QuickParse.parseToObject(
                "move $(int){count}$ from $(int){id1}$ to $(int){id2}$",
                line,
                new MoveInstr(),
                MoveInstr.class
            );

            List<Character> charsToMove = new ArrayList<>();
            for (int x = 0; x < moveInstr.count; x++) {
                char c = stacks.get(moveInstr.id1 - 1).pop();
                charsToMove.add(c);
            }

            for (int id = CollectionUtils.lastIndex(charsToMove); id >= 0; id--) {
                stacks.get(moveInstr.id2 - 1).add(charsToMove.get(id));
            }

        }

        for (Stack<Character> stack : stacks) {
            System.out.println(stack.peek());
        }
    }

    private static class MoveInstr {
        int count;
        int id1;
        int id2;

    }

}
