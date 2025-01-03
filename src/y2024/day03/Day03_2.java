package y2024.day03;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Day03_2 {

    public static final String REGEX = "(mul)[(]([\\d]+),([\\d]+)[)]|(do\\(\\))|(don\\'t\\(\\))";

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        final var pattern = Pattern.compile(REGEX);
        boolean active = true;
        for (String s : collect) {
            final var matcher = pattern.matcher(s);
            while (matcher.find()) {
                if (matcher.group().equals("do()"))
                    active = true;
                else if (matcher.group().equals("don't()"))
                    active = false;
                else if (active) {
                    final var left = Integer.parseInt(matcher.group(2));
                    final var right = Integer.parseInt(matcher.group(3));
                    result += left * right;
                }
            }
        }

        return result;
    }
}
