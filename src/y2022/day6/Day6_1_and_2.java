package y2022.day6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6_1_and_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        String line = collect.get(0);

        char[] chars = line.toCharArray();
        int packetSize = 14;//4 for part 1
        for (int i = packetSize; i < chars.length; i++) {
            String substring = line.substring(i - packetSize, i);
            String[] split = substring.split("");
            Set<String> toSet = new HashSet<>(Arrays.asList(split));
            if (toSet.size() == packetSize)
                return i;
        }

        throw new IllegalArgumentException();
    }

}
