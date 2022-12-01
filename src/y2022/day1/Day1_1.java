package y2022.day1;

import java.util.Arrays;
import java.util.List;

public class Day1_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int max = 0;
        int lastIndex = 0;
        for (int i = 0; i < collect.size(); i++) {
            String s = collect.get(i);

            if (s.isBlank()) {
                int sum = sum(collect, lastIndex, i);
                if (sum > max)
                    max = sum;
                lastIndex = i + 1;
            }
        }
        return max;
    }

    private static int sum(List<String> collect, int lastIndex, int i) {
        return collect.subList(lastIndex, i).stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
