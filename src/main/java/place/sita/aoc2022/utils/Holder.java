package place.sita.aoc2022.utils;

public class Holder<T> {
    private T t;

    public T get() {
        return t;
    }

    public void set(T t) {
        this.t = t;
    }
}
