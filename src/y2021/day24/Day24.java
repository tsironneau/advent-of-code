package y2021.day24;

import common.tuple.Tuple2;
import common.tuple.Tuple3;
import common.tuple.Tuples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day24 {

    public static final List<Tuple3<Integer>> STEPS = List.of(
            Tuples.of(1, 13, 8),
            Tuples.of(1, 12, 16),
            Tuples.of(1, 10, 4),
            Tuples.of(26, -11, 1),
            Tuples.of(1, 14, 13),
            Tuples.of(1, 13, 5),
            Tuples.of(1, 12, 0),
            Tuples.of(26, -5, 10),
            Tuples.of(1, 10, 7),
            Tuples.of(26, 0, 2),
            Tuples.of(26, -11, 13),
            Tuples.of(26, -13, 15),
            Tuples.of(26, -13, 14),
            Tuples.of(26, -11, 9)
    );

    public static void main(String[] args) {

        final List<Tuple2<Integer, Integer>> stack = new ArrayList<>();

        final int[] mins = new int[14];
        final int[] maxs = new int[14];
        int i = 0;
        for (Tuple3<Integer> step : STEPS) {
            if (step._1() == 1) {
                stack.add(0, Tuples.of(i, (step._3())));
            } else{
                final Tuple2<Integer, Integer> left = stack.remove(0);
                final int d = - left._2() - (step._2());
                maxs[left._1()] = Math.min(9 + d, 9);
                maxs[i] = Math.min(9 - d, 9);
                mins[left._1()] = Math.max(1 + d, 1);
                mins[i] = Math.max(1 - d, 1);
            }
            i++;
        }

        System.out.println(Arrays.stream(mins).mapToObj(Integer::toString).collect(Collectors.joining("")));
        System.out.println(Arrays.stream(maxs).mapToObj(Integer::toString).collect(Collectors.joining("")));
    }
}
