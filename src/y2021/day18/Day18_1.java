package y2021.day18;

import common.Point;

import java.util.List;
import java.util.Set;

public class Day18_1 {

    public static final Set<Character> NOT_NUMBERS = Set.of('[', ']', ',');
    public static final String PAIR_PATTERN = "[%s,%s]";

    public static void main(String[] args) {

        final List<String> lines = Input.INPUT.lines().toList();

        String current = lines.get(0);
        for (int i = 1; i < lines.size(); i++) {
            String s = lines.get(i);
            System.out.println("  " + current);
            System.out.println("+ " + s);
            current = reduce(add(current, s));
            System.out.println("= " + current + "\n");
        }
        System.out.println(current);

        int value =
                compute(
                        compute(
                                compute(
                                        compute(7, 7),
                                        compute(7, 0)
                                ),
                                compute(
                                        compute(7, 8),
                                        compute(8, 8)
                                )
                        ), compute(6, 9));

        System.out.println(value);
    }

    private static int compute(int a, int b) {
        return 3 * a + 2 * b;
    }

    static String reduce(final String input) {
        final int explodeIndex = computeExplodeIndex(input);
        if (explodeIndex > 0) {
            final String exploded = explode(input, explodeIndex);
            return reduce(exploded);
        }
        final Point point = computeSplitIndex(input);
        if (point != null) {
            final String sp = split(input, point);
            return reduce(sp);
        }
        return input;
    }

    static String add(final String left, final String right) {
        return String.format(PAIR_PATTERN, left, right);
    }

    private static void testSplit() {
        testSplit("[[[[0,7],4],[15,[0,13]]],[1,1]]");
        testSplit("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]");
    }

    private static void testSplit(final String testedSplit) {
        Point splitIndex = computeSplitIndex(testedSplit);

        if (splitIndex != null) {
            String split = split(testedSplit, splitIndex);
            System.out.println(testedSplit + " -> " + split);
        }
    }

    private static void testExplode() {
        testExplode("[[[[[9,8],1],2],3],4]");
        testExplode("[7,[6,[5,[4,[3,2]]]]]");
        testExplode("[[6,[5,[4,[3,2]]]],1]");
        testExplode("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
        testExplode("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
        testExplode("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]");
    }

    static String split(final String testedSplit, final Point splitIndex) {
        String output = testedSplit;

        int numberToSplit = Integer.parseInt(testedSplit.substring(splitIndex.x(), splitIndex.y() + 1));

        final String pair = String.format(PAIR_PATTERN, numberToSplit / 2, (numberToSplit / 2) + numberToSplit % 2);
        return output.substring(0, splitIndex.x()) + pair + output.substring(splitIndex.y() + 1);
    }

    static Point computeSplitIndex(final String s) {
        Integer start = null;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (!NOT_NUMBERS.contains(c)) {
                if (start == null) {
                    start = i;
                }
            } else {
                if (start != null && i > start + 1) {
                    return new Point(start, i - 1);
                } else {
                    start = null;
                }
            }
        }
        return null;
    }

    private static int testExplode(final String testedNumber) {
        int explodeIndex = computeExplodeIndex(testedNumber);
        if (explodeIndex > 0) {
            String explodedString = explode(testedNumber, explodeIndex);
            System.out.println(testedNumber + " -> " + explodedString);
        } else {
            System.out.println("Not exploding");
        }
        return explodeIndex;
    }

    static String explode(final String input, final int explodeIndex) {

        final String fromExplode = input.substring(explodeIndex);
        final int pairEndIndex = fromExplode.indexOf("]");
        final String explodingPair = input.substring(explodeIndex + 1, explodeIndex + pairEndIndex);

        final int left = Integer.parseInt(explodingPair.split(",")[0]);
        final int right = Integer.parseInt(explodingPair.split(",")[1]);

        final String regex = String.format("\\[%s,%s\\]", left, right);
        String output = input.substring(0, explodeIndex) + "0" + input.substring(explodeIndex + pairEndIndex + 1);

        output = replaceRight(explodeIndex, right, output);
        output = replaceLeft(explodeIndex, left, output);
        return output;
    }

    private static String replaceLeft(final int explodeIndex, final int left, String output) {
        Integer end = null;
        for (int i = explodeIndex - 1; i >= 0; i--) {
            final char c = output.charAt(i);
            if (!NOT_NUMBERS.contains(c)) {
                if (end == null) {
                    end = i;
                }
            } else {
                if (end != null) {
                    int value = Integer.parseInt(output.substring(i + 1, end + 1));
                    final int sum = left + value;
                    return output.substring(0, i + 1) + sum + output.substring(end + 1);
                }
            }
        }
        return output;
    }

    private static String replaceRight(final int explodeIndex, final int right, String output) {
        Integer start = null;
        for (int i = explodeIndex + 1; i < output.length(); i++) {
            final char c = output.charAt(i);
            if (!NOT_NUMBERS.contains(c)) {
                if (start == null) {
                    start = i;
                }
            } else {
                if (start != null) {
                    int value = Integer.parseInt(output.substring(start, i));
                    final int sum = right + value;
                    return output.substring(0, start) + sum + output.substring(i);
                }
            }
        }
        return output;
    }

    static int computeExplodeIndex(final String s) {
        final char[] chars = s.toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '[') {
                count++;
            } else if (aChar == ']') {
                count--;
            }
            if (count > 4) {
                return i;
            }
        }

        return -1;
    }
}
