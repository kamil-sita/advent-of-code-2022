package place.sita.aoc2022.day9;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.Coords;
import place.sita.aoc2022.utils.Map2d;
import place.sita.aoc2022.utils.Utils;

import java.util.List;
import java.util.Queue;
import java.util.function.Function;

public class Day9_1 {

    public static void main(String[] args) {
        List<String> strings = Utils.readFile("day9.txt");
        Map2d<Boolean> visited = new Map2d<>();

        final Coords[] headPos = {new Coords(0, 0)};
        final Coords[] tailPos = {new Coords(0, 0)};

        strings.forEach(string -> {
            Instruction ins = QuickParse.parseToObject("${dir}$ $(int){dist}$", string, new Instruction(), Instruction.class);

            for (int i = 0; i < ins.dist; i++) {
                // update head
                switch (ins.dir) {
                    case "R":
                        headPos[0] = headPos[0].withXDiff(1);
                        break;
                    case "L":
                        headPos[0] = headPos[0].withXDiff(-1);
                        break;
                    case "D":
                        headPos[0] = headPos[0].withYDiff(1);
                        break;
                    case "U":
                        headPos[0] = headPos[0].withYDiff(-1);
                        break;
                }

                // update tail
                int x0 = headPos[0].getX();
                int x1 = tailPos[0].getX();
                int y0 = headPos[0].getY();
                int y1 = tailPos[0].getY();

                if (Math.abs(x0 - x1) > 1 && Math.abs(y0 - y1) == 0) {
                    if (x0 > x1) {
                        tailPos[0] = tailPos[0].withXDiff(1);
                    }
                    if (x0 < x1) {
                        tailPos[0] = tailPos[0].withXDiff(-1);
                    }
                } else if (Math.abs(y0 - y1) > 1 && Math.abs(x0 - x1) == 0) {
                    if (y0 > y1) {
                        tailPos[0] = tailPos[0].withYDiff(1);
                    }
                    if (y0 < y1) {
                        tailPos[0] = tailPos[0].withYDiff(-1);
                    }
                } else if (Math.abs(y0 - y1) > 1 || Math.abs(x0 - x1) > 1) {
                    int xDiff = x0 > x1 ? 1 : -1;
                    int yDiff = y0 > y1 ? 1 : -1;
                    tailPos[0] = tailPos[0].withDiff(xDiff, yDiff);
                }

                visited.put(tailPos[0], true);
                //visited.print(aBoolean -> "#", ".");
            }


        });


        System.out.println(visited.valueCount());
    }

    public static class Instruction {
        private String dir;
        private long dist;
    }


}
