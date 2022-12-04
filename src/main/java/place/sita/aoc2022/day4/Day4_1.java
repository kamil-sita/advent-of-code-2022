package place.sita.aoc2022.day4;

import com.google.common.math.LongMath;
import pl.ksitarski.quickparse.QuickParse;
import place.sita.aoc2022.utils.Utils;

import java.math.RoundingMode;

public class Day4_1 {

    public static void main(String[] args) {

        long sum = Utils.readFile("day4.txt")
            .stream()
            .filter(str -> {
                Ranges ranges = QuickParse.parseToObject("$(int){range01}$-$(int){range02}$,$(int){range11}$-$(int){range12}$", str, new Ranges(), Ranges.class);
                if (ranges.range01 >= ranges.range11 && ranges.range02 <= ranges.range12) {
                    return true;
                }
                if (ranges.range11 >= ranges.range01 && ranges.range12 <= ranges.range02) {
                    return true;
                }
                return false;
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
