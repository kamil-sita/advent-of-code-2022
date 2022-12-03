package place.sita.aoc2022.day3;

import com.google.common.math.LongMath;
import place.sita.aoc2022.utils.Utils;

import java.math.RoundingMode;

public class Day3_1 {

    public static void main(String[] args) {

        int sum = Utils.readFile("day3.txt")
            .stream()
            .map(string -> {
                String firstHalf = string.substring(0, string.length()/2);
                String secondHalf = string.substring(string.length()/2);

                long firstBits = itemPrioritiesBit(firstHalf);
                long secondBits = itemPrioritiesBit(secondHalf);

                long commonBits = firstBits & secondBits;
                int priority = priorityBitToPriority(commonBits);

                return priority;
            })
            .reduce(Integer::sum)
                .get();

        System.out.println(sum);
    }

    private static int itemPriority(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else {
            return c - 'A' + 27;
        }
    }

    private static long itemPriorityBit(int itemPriority) {
        return 1L << (itemPriority-1);
    }

    private static int priorityBitToPriority(long bit) {
        return LongMath.log2(bit, RoundingMode.UNNECESSARY) + 1;
    }

    private static long itemPrioritiesBit(String subString) {
        long outputLong = itemPriorityBit(itemPriority(subString.charAt(0)));
        for (int i = 1; i < subString.length(); i++) {
            outputLong |= itemPriorityBit(itemPriority(subString.charAt(i)));
        }
        return outputLong;
    }

}
