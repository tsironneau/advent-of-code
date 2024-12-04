package y2024.day01;

import java.util.*;

public class Day01_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        final var left = new ArrayList<Integer>();
        final var right = new ArrayList<Integer>();

        collect.stream()
               .map(entry -> entry.split("   "))
               .map(array -> new Integer[]{Integer.parseInt(array[0]), Integer.parseInt(array[1])})
               .forEach(array -> {
                   left.add(array[0]);
                   right.add(array[1]);
               });

        final var sortedLeft = new HashSet<>(left);
        final var sortedRight = right.stream().sorted().toList();

        int result = 0;
        for (Integer i : sortedLeft) {
            result += (int) sortedRight.stream().filter(r -> Objects.equals(r, i)).count() * i;
        }
        return result;
    }

}
