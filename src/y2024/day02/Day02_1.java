package y2024.day02;

import java.util.Arrays;
import java.util.List;

public class Day02_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        for (String line : collect) {
            if (isSafe(line))
                result++;
        }
        return result;
    }

    private static boolean isSafe(String line) {
        final var space = " ";
        final var split = Arrays.stream(line.split(space))
                                .map(Integer::parseInt)
                                .toList();

        Integer sign = null;
        for (int i = 0; i < split.size() - 1; i++) {
            Integer current = split.get(i);
            Integer next = split.get(i + 1);
            final var diff = current - next;
            if (diff == 0)
                return false;
            final var abs = Math.abs(diff);
            if (abs > 3)
                return false;
            final var newSign = diff / abs;
            if (sign == null) {
                sign = newSign;
                continue;
            }
            if (newSign != sign)
                return false;
        }

        return true;
    }
}
