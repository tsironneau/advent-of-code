package common.tuple;

public final class Tuples {

    private Tuples() {
    }

    public static <T> Tuple3<T> of(final T _1, final T _2, final T _3) {
        return new Tuple3<>(_1, _2, _3);
    }

    public static <A, B> Tuple2<A, B> of(final A _1, final B _2) {
        return new Tuple2<>(_1, _2);
    }
}
