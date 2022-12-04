package place.sita.aoc2022.day4;

import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.Utils;

public class Day4_2 {

    public static void main(String[] args) {

        long sum = Utils.readFile("day4.txt")
            .stream()
            .filter(str -> {
                Ranges ranges = QuickParse.parseToObject("$(int){range01}$-$(int){range02}$,$(int){range11}$-$(int){range12}$", str, new Ranges(), Ranges.class);
                if (ranges.range01 <= ranges.range11) {
                    return ranges.range02 >= ranges.range11;
                } else {
                    return ranges.range12 >= ranges.range01;
                }
            })
                .count();

        System.out.println(sum);
    }

    private static class Ranges {
        int range01;
        int range02;
        int range11;
        int range12;

    }

}
