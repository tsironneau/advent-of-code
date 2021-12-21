package y2021.day6;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static y2021.day6.Input.INPUT;

public class Day6_2 {

    public static void main(String[] args) {
        String allFishes = INPUT.lines().toList().get(0);
        Map<Integer, List<Integer>> fishes = Arrays.stream(allFishes.split(",")).map(Integer::parseInt)
                .collect(Collectors.groupingBy(f -> f));

        final Map<Integer, Long> finalCounters = new HashMap<>();
        fishes.forEach((k, v) -> finalCounters.put(k, (long) v.size()));
        Map<Integer, Long> counters = finalCounters;
        Map<Integer, Long> newCounters = new HashMap<>();

        for (int i = 0; i < 256; i++) {
            for (int j = 8; j >= 0; j--) {
                Long count = counters.get(j);
                if (count != null) {
                    if (j == 0) {
                        newCounters.put(8, count);
                        newCounters.compute(6, (k, v) -> v == null ? count : v + count);
                    } else {
                        newCounters.put(j - 1, count);
                    }
                }
            }

            counters = newCounters;
            newCounters = new HashMap<>();
        }

        System.out.println(counters.values().stream().mapToLong(i -> i).sum());
    }
}
