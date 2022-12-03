package place.sita.aoc2022.day3;

import com.google.common.math.LongMath;
import place.sita.aoc2022.utils.Utils;

import java.math.RoundingMode;
import java.util.List;

public class Day3_2 {

    public static void main(String[] args) {
        List<String> items = Utils.readFile("day3.txt");

        int prioritySum = 0;

        for (int i = 0; i < items.size(); i += 3) {
            String first = items.get(i);
            String second = items.get(i + 1);
            String third = items.get(i + 2);

            long firstBits = itemPrioritiesBit(first);
            long secondBits = itemPrioritiesBit(second);
            long thirdBits = itemPrioritiesBit(third);

            long commonBits = firstBits & secondBits & thirdBits;
            int priority = priorityBitToPriority(commonBits);
            prioritySum += priority;
        }

        System.out.println(prioritySum);
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
