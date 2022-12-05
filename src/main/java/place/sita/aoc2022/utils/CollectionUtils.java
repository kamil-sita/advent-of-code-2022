package place.sita.aoc2022.utils;

import java.util.List;

public class CollectionUtils {
    public static int lastIndex(List<?> values) {
        return values.size() - 1;
    }

    public static <T> T lastValue(List<T> values) {
        return values.get(lastIndex(values));
    }
}
