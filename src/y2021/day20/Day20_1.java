package y2021.day20;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day20_1 {

    public static void main(String[] args) {

        final String input = Input.INPUT;
        final String[] inputs = input.split("\n\n");
        final char[] mapping = inputs[0].toCharArray();
        List<char[]> map = inputs[1].lines().map(String::toCharArray).collect(Collectors.toList());

        System.out.println(computeLitCount(map));

        map = increaseMap(map, 4, '.');
        for (int i = 0; i < 50; i++) {
            map = transform(map, mapping, 2);
            if (i == 1)
                System.out.println("First part answer : " + computeLitCount(map));
            map = increaseMap(map, 3, i % 2 == 0 ? mapping[0] : '.');
        }

        System.out.println("Second part answer : " + computeLitCount(map));
    }

    private static void print(final List<char[]> map) {
        for (char[] chars : map) {
            System.out.println(Arrays.toString(chars));
        }
    }

    private static List<char[]> increaseMap(final List<char[]> map, int increment, final Character emptyChar) {
        final int length = map.get(0).length;
        final int growth = increment * 2;

        for (int i = 0; i < map.size(); i++) {
            char[] chars = map.get(i);
            map.set(i, (emptyChar.toString().repeat(increment) + new String(chars) + emptyChar.toString()
                    .repeat(increment)).toCharArray());
        }

        for (int i = 0; i < increment; i++) {
            map.add(0, emptyLine(length + growth, emptyChar));
            map.add(emptyLine(length + growth, emptyChar));
        }
        return map;
    }

    private static char[] emptyLine(final int newLength, final char emptyChar) {
        final char[] chars = new char[newLength];
        Arrays.fill(chars, emptyChar);
        return chars;
    }

    private static List<char[]> transform(final List<char[]> map, final char[] mapping, final int increment) {

        final List<char[]> result = copy(map, 0);
        for (int i = increment; i < map.size() - increment; i++) {
            char[] chars = map.get(i);
            for (int j = increment; j < chars.length - increment; j++) {
                final char[] upper = Arrays.copyOfRange(map.get(i - 1), j - 1, j + 2);
                final char[] middle = Arrays.copyOfRange(map.get(i), j - 1, j + 2);
                final char[] down = Arrays.copyOfRange(map.get(i + 1), j - 1, j + 2);

                final String rawIndex = new String(upper) + new String(middle) + new String(down);
                final String binaryIndex = rawIndex.replaceAll("\\.", "0").replaceAll("#", "1");

                final BigInteger index = new BigInteger(binaryIndex, 2);

                result.get(i)[j] = mapping[index.intValue()];
            }
        }

        return copy(result, increment);
    }

    private static List<char[]> copy(final List<char[]> map, final int cut) {
        final List<char[]> result = new ArrayList<>();
        for (int i = cut; i < map.size() - cut; i++) {
            char[] chars = map.get(i);
            result.add(Arrays.copyOfRange(chars, cut, chars.length - cut));
        }
        return result;
    }

    private static long computeLitCount(final List<char[]> map) {
        long count = 0;
        for (int i = 1; i < map.size() - 1; i++) {
            char[] chars = map.get(i);
            for (int j = 1; j < chars.length - 1; j++) {
                char aChar = chars[j];
                if (aChar == '#') {
                    count++;
                }
            }
        }
        return count;
    }
}
