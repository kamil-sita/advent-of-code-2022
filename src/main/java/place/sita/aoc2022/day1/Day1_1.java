package place.sita.aoc2022.day1;

import place.sita.aoc2022.utils.CollectionUtils;
import place.sita.aoc2022.utils.Utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1_1 {

    public static void main(String[] args) {
        List<Long> values = new ArrayList<>();
        values.add(0L);

        Utils.readFile("day1_1.txt")
            .forEach(string -> {
                if (string.isBlank()) {
                    values.add(0L);
                } else {
                    long addedValue = Long.parseLong(string);
                    int lastIndex = CollectionUtils.lastIndex(values);
                    values.set(lastIndex, addedValue + values.get(lastIndex));
                }
            });

        values.stream()
            .sorted((o1, o2) -> Long.compare(o2, o1))
            .limit(1)
            .forEach(System.out::println);
    }

}
