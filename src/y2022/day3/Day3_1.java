package y2022.day3;

import java.util.Arrays;
import java.util.List;

public class Day3_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        for (String s : collect) {
            String first = s.substring(0, s.length() / 2);
            String second = s.substring(s.length() / 2);

            char commonChar = getCommonChar(first, second);
            result += getCharScore(commonChar);
        }

        return result;
    }

    private static int getCharScore(char commonChar) {
        if (Character.isUpperCase(commonChar)) {
            return (int) commonChar - 48 + 10;
        }
        if (Character.isLowerCase(commonChar)) {
            return (int) commonChar - 96;
        }
        throw new IllegalArgumentException();
    }

    private static char getCommonChar(String first, String second) {
        return Arrays.stream(first.split(""))
                .dropWhile(s -> !second.contains(s))
                .findFirst()
                .map(s -> s.charAt(0))
                .orElseThrow();
    }

}
