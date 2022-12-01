package y2022.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day1_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        List<Integer> sums = new ArrayList<>();
        int lastIndex = 0;
        for (int i = 0; i < collect.size(); i++) {
            String s = collect.get(i);

            if (s.isBlank()) {
                int sum = sum(collect, lastIndex, i);
                sums.add(sum);
                lastIndex = i + 1;
            }
        }

        return topThreeSum(sums);
    }

    private static int topThreeSum(List<Integer> sums) {
        Collections.sort(sums);
        Collections.reverse(sums);
        List<Integer> topThree = sums.subList(0, 3);
        return topThree.stream().mapToInt(i -> i).sum();
    }

    private static int sum(List<String> collect, int lastIndex, int i) {
        return collect.subList(lastIndex, i).stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
