package y2022.day13;

import common.parsing.ParsingUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Day_13_2 {

    public static final String _2 = "[[2]]";
    public static final String _6 = "[[6]]";

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = new ArrayList<>(
                        Arrays.stream(lines)
                            .filter(s -> !s.isBlank())
                          .toList()
        );
        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        collect.add(_2);
        collect.add(_6);
        List<String> collect1 = collect.stream()
                                       .map(ParsingUtils::readAsIntegerList)
                                       .sorted(Day_13_1::compare)
                                       .map(Object::toString)
                                       .collect(Collectors.toList());

        Collections.reverse(collect1);

        int twoIndex = collect1.indexOf(_2) + 1;
        int sixIndex = collect1.indexOf(_6) + 1;

        return twoIndex * sixIndex;
    }

}
