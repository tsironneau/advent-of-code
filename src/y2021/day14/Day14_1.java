package y2021.day14;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Day14_1 {

    public static void main(String[] args) {

        final String input = Input.INPUT;
        final String[] base = input.split("\n\n");

        final String start = base[0];
        final String opsString = base[1];

        final Map<String, String> ops = opsString.lines()
                .flatMap(l -> Arrays.stream(l.split("\n")))
                .map(l -> l.split(" -> "))
                .collect(Collectors.toMap(op -> op[0], op -> op[0].split("")[0] + op[1] + op[0].split("")[1]));;

        System.out.println(start);
        System.out.println(ops);

        final int count = 40;
        String polymere = start;
        for (int i = 0; i < count; i++) {
            polymere = mutate(polymere, ops);
        }

        final long max = counters(polymere).max().getAsLong();
        final long min = counters(polymere).min().getAsLong();
        System.out.println(max - min);
    }

    private static LongStream counters(final String polymere) {
        return Arrays.stream(polymere.split(""))
                .collect(Collectors.groupingBy(c -> c))
                .values()
                .stream().mapToLong(List::size);
    }

    private static String mutate(final String polymere, final Map<String, String> ops) {
        String current = polymere;
        int baseLength = current.length() - 1;

        for (int i = 0; i < baseLength; i++) {
            final String currentPair = current.substring(i, i + 2);

            final String replacement = ops.get(currentPair);

            if (replacement != null) {
                current = current.substring(0, i) + replacement + current.substring(i + 2);
                baseLength += 1;
                i += 1;
            }
        }
        return current;
    }
}
