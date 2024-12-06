package y2024.day05;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

public class Day05_02 {

    private static Map<Integer, Set<Integer>> rules;

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");
        rules = Arrays.stream(Input.RULES.split("\n"))
                      .map(s -> s.split("[|]"))
                      .collect(Collectors.groupingBy(rule -> Integer.parseInt(rule[0]), mapping(rule -> Integer.parseInt(rule[1]), toSet())));

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        final var lines = collect.stream()
                                 .map(line -> Arrays.stream(line.split(","))
                                                    .map(Integer::parseInt)
                                                    .toList())
                                 .toList();
        for (List<Integer> line : lines) {
            final var sorted = sortLine(line);
            if (!line.equals(sorted)) {
                result += sorted.get(sorted.size() / 2);
            }
        }

        return result;
    }

    private static ArrayList<Integer> sortLine(List<Integer> line) {
        Comparator<Integer> comparator = (o1, o2) -> {
            final var rules1 = rules.get(o1);
            if (rules1 != null && rules1.contains(o2))
                return -1;
            final var rules2 = rules.get(o2);
            if (rules2 != null && rules2.contains(o1))
                return 1;
            return 0;
        };
        final var copy = new ArrayList<>(line);
        copy.sort(comparator);
        return copy;
    }
}
