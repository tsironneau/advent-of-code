package y2021.day3;

import java.util.List;

public class Day3_1 {

    public static void main(String[] args) {

        final List<char[]> lines = Input.INPUT.lines().map(String::toCharArray).toList();

        final int length = lines.get(0).length;

        int[] result = computeNumberOfChar(lines, '1');

        char[] gamma = new char[length];
        char[] epsilon = new char[length];

        for (int i = 0; i < result.length; i++) {
            int i1 = result[i];
            if (lines.size() - i1 > i1) {
                gamma[i] = '0';
                epsilon[i] = '1';
            } else {
                gamma[i] = '1';
                epsilon[i] = '0';
            }
        }

        final String gammaS = String.copyValueOf(gamma);
        final String epsilonS = String.copyValueOf(epsilon);
        final int gammaInt = Integer.parseInt(gammaS, 2);
        final int epsilonInt = Integer.parseInt(epsilonS, 2);

        System.out.println(gammaS);
        System.out.println(epsilonS);
        System.out.println(gammaInt);
        System.out.println(epsilonInt);
        System.out.println(gammaInt * epsilonInt);
    }

    static int[] computeNumberOfChar(final List<char[]> lines, final char countedChar) {
        final int length = lines.get(0).length;
        int[] result = new int[length];

        for (char[] line : lines) {
            int i = 0;
            for (Character c : line) {
                if (c.equals(countedChar)) {
                    result[i] = result[i] + 1;
                }
                i++;
            }
        }
        return result;
    }
}
