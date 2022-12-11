package y2022.day10;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Day_10_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        int cycle = 0;
        int x = 1;
        Set<Integer> cycles = Set.of(20, 60, 100, 140, 180, 220);
        for (String s : collect) {
            if (s.equals("noop")) {

                cycle++;
                if (cycles.contains(cycle))
                    result = result + (cycle * x);
            } else {
                cycle++;
                if (cycles.contains(cycle))
                    result = result + (cycle * x);
                cycle++;
                if (cycles.contains(cycle))
                    result = result + (cycle * x);
                int diff = Integer.parseInt(s.split(" ")[1]);
                x += diff;
            }
        }
        return result;
    }
}
