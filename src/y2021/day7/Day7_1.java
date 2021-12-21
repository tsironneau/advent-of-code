package y2021.day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day7_1 {

    public static void main(String[] args) {
        new Day7_1().execute();
    }

    void execute() {
        final List<Integer> integers = Arrays.stream(Input.INPUT.split(","))
                .map(Integer::parseInt)
                .toList();

        final int max = integers.stream().mapToInt(i -> i).max().orElseThrow();
        final int min = integers.stream().mapToInt(i -> i).min().orElseThrow();

        System.out.println(min);
        System.out.println(max);
        int minDiff = Integer.MAX_VALUE;
        int minPos = 0;
        for (int i = min; i < max; i++) {
            final int diff = i;
            final IntStream diffs = integers.stream().mapToInt(pos -> computeCost(pos, diff));
            final int diffSum = diffs.sum();
            if(diffSum < minDiff) {
                minDiff = diffSum;
                minPos = i;
            }
        }

        final int targetPos = minPos;
        final int cost = integers.stream().mapToInt(pos -> computeCost(pos, targetPos)).sum();

        System.out.println(cost);
    }

    int computeCost(final Integer pos, final int minPos) {
        return Math.abs(pos - minPos);
    }
}
