package y2021.day1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static y2021.day1.Input.INPUT;

public class Day1_2 {

    public static void main(String[] args) {
        final String[] lines = INPUT.split("\n");

        final List<Integer> ints = Arrays.stream(lines)
                .map(Integer::parseInt)
                .toList();

        List<Integer> groupOfThree = new ArrayList<>();
        for (int i = 0; i < ints.size() - 2; i++) {
            groupOfThree.add(sum(ints, i, i + 2));
        }
        System.out.println(Day1_1.nbOfIncrease(groupOfThree));
    }

    private static int sum(final List<Integer> ints, final int from, final int to) {
        int sum = 0;
        for (int i = from; i <= to; i++) {
            sum += ints.get(i);
        }
        return sum;
    }
}
