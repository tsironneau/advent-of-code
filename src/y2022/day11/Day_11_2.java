package y2022.day11;

import java.util.*;

import static y2022.day11.Day_11_1.*;

public class Day_11_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        long result = puzzle(collect);

        System.out.println(result);
    }

    private static long puzzle(List<String> collect) {
        int primeGcd = 2 * 3 * 5 * 7 * 11 * 13 * 17 * 19;
        return resolve(getMonkeys(), 10000, l -> l % primeGcd);
    }



}
