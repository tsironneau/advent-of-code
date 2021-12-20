package y2021.day1;

import java.util.Arrays;
import java.util.List;

public class Day1_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<Integer> collect = Arrays.stream(lines)
                .map(Integer::parseInt)
                .toList();

        int result = nbOfIncrease(collect);

        System.out.println(result);
    }

    static int nbOfIncrease(final List<Integer> collect) {
        int result = 0;
        Integer current = null;
        for (Integer integer : collect) {
            if (current != null && integer > current)
                result++;

            current = integer;
        }
        return result;
    }
}
