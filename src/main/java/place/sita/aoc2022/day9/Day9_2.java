package place.sita.aoc2022.day9;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.CollectionUtils;
import place.sita.aoc2022.utils.Coords;
import place.sita.aoc2022.utils.Map2d;
import place.sita.aoc2022.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class Day9_2 {

    public static void main(String[] args) {
        List<String> strings = Utils.readFile("day9.txt");
        Map2d<Boolean> visited = new Map2d<>();

        int knotCount = 9;

        final CoordWrapper headPos = new CoordWrapper(new Coords(0, 0));
        List<CoordWrapper> tails = new ArrayList<>();
        for (int i = 0; i < knotCount; i++) {
            tails.add(new CoordWrapper(new Coords(0, 0)));
        }

        strings.forEach(string -> {
            Instruction ins = QuickParse.parseToObject("${dir}$ $(int){dist}$", string, new Instruction(), Instruction.class);

            for (int i = 0; i < ins.dist; i++) {
                // update head
                switch (ins.dir) {
                    case "R":
                        headPos.setCoords(headPos.getCoords().withXDiff(1));
                        break;
                    case "L":
                        headPos.setCoords(headPos.getCoords().withXDiff(-1));
                        break;
                    case "D":
                        headPos.setCoords(headPos.getCoords().withYDiff(1));
                        break;
                    case "U":
                        headPos.setCoords(headPos.getCoords().withYDiff(-1));
                        break;
                }

                // update tail
                updateTail(headPos, tails.get(0));
                for (int ki = 0; ki < knotCount - 1; ki++) {
                    updateTail(tails.get(ki), tails.get(ki + 1));
                }

                visited.put(CollectionUtils.lastValue(tails).getCoords(), true);
                //visited.print(aBoolean -> "#", ".");
            }


        });


        System.out.println(visited.valueCount());
    }

    private static void updateTail(CoordWrapper headPos, CoordWrapper tailPos) {
        int x0 = headPos.getCoords().getX();
        int x1 = tailPos.getCoords().getX();
        int y0 = headPos.getCoords().getY();
        int y1 = tailPos.getCoords().getY();

        if (Math.abs(x0 - x1) > 1 && Math.abs(y0 - y1) == 0) {
            if (x0 > x1) {
                tailPos.setCoords(tailPos.getCoords().withXDiff(1));
            }
            if (x0 < x1) {
                tailPos.setCoords(tailPos.getCoords().withXDiff(-1));
            }
        } else if (Math.abs(y0 - y1) > 1 && Math.abs(x0 - x1) == 0) {
            if (y0 > y1) {
                tailPos.setCoords(tailPos.getCoords().withYDiff(1));
            }
            if (y0 < y1) {
                tailPos.setCoords(tailPos.getCoords().withYDiff(-1));
            }
        } else if (Math.abs(y0 - y1) > 1 || Math.abs(x0 - x1) > 1) {
            int xDiff = x0 > x1 ? 1 : -1;
            int yDiff = y0 > y1 ? 1 : -1;
            tailPos.setCoords(tailPos.getCoords().withDiff(xDiff, yDiff));
        }
    }

    public static class CoordWrapper {
        private Coords coords;

        public CoordWrapper(Coords coords) {
            this.coords = coords;
        }

        public Coords getCoords() {
            return coords;
        }

        public void setCoords(Coords coords) {
            this.coords = coords;
        }
    }

    public static class Instruction {
        private String dir;
        private long dist;
    }


}
