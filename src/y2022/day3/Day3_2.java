package y2022.day3;

import java.util.Arrays;
import java.util.List;

public class Day3_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        for (int i = 0; i < collect.size(); i += 3) {
            String first = collect.get(i);
            String second = collect.get(i + 1);
            String third = collect.get(i + 2);

            char commonChar = getCommonChar(first, second, third);
            result += getCharScore(commonChar);
        }

        return result;
    }

    private static char getCommonChar(String first, String second, String third) {

        return Arrays.stream(first.split(""))
                .dropWhile(s -> !(second.contains(s) && third.contains(s)))
                .findFirst()
                .map(s -> s.charAt(0))
                .orElseThrow();
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

}
