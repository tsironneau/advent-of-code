package y2021.day3;

import java.util.ArrayList;
import java.util.List;

import static y2021.day3.Input.INPUT;

public class Day3_2 extends Day3_1 {

    public static void main(String[] args) {

        final List<char[]> lines = INPUT.lines().map(String::toCharArray).toList();

        final int length = lines.get(0).length;

        List<char[]> forOxygen = computeOxygen(lines, length, '1');

        List<char[]> forCO2 = new ArrayList<>(lines);
        int index2 = 0;
        while (forCO2.size() > 1 && index2 < length) {

            final char[] minorityChars = computeMinorityChars(
                    computeNumberOfChar(forCO2, '1'),
                    forCO2.size());

            final int index3 = index2;
            forCO2 = forCO2.stream().filter(chars -> chars[index3] == minorityChars[index3]).toList();

            index2++;
        }

        System.out.println(forOxygen.stream().map(String::copyValueOf).toList());
        System.out.println(forCO2.stream().map(String::copyValueOf).toList());

        final String gammaS = String.copyValueOf(forOxygen.get(0));
        final String epsilonS = String.copyValueOf(forCO2.get(0));
        final int gammaInt = Integer.parseInt(gammaS, 2);
        final int epsilonInt = Integer.parseInt(epsilonS, 2);

        System.out.println(gammaS);
        System.out.println(epsilonS);
        System.out.println(gammaInt);
        System.out.println(epsilonInt);
        System.out.println(gammaInt * epsilonInt);
    }

    private static List<char[]> computeOxygen(final List<char[]> lines, final int length, final char countedChar) {
        List<char[]> result = new ArrayList<>(lines);
        int index = 0;
        while (result.size() > 1 && index < length) {

            final char[] majorityChars = computeMajorityChars(
                    computeNumberOfChar(result, countedChar),
                    result.size());

            final int finalIndex = index;
            result = result.stream().filter(chars -> chars[finalIndex] == majorityChars[finalIndex]).toList();

            index++;
        }
        return result;
    }

    private static char[] computeMajorityChars(final int[] in, final int totalChars) {
        char[] majorityChar = new char[in.length];

        for (int i = 0; i < in.length; i++) {
            int i1 = in[i];
            if (totalChars - i1 > i1) {
                majorityChar[i] = '0';
            } else {
                majorityChar[i] = '1';
            }
        }
        return majorityChar;
    }

    private static char[] computeMinorityChars(final int[] in, final int totalSize) {
        char[] minorityChar = new char[in.length];

        for (int i = 0; i < in.length; i++) {
            int i1 = in[i];
            if (totalSize - i1 > i1) {
                minorityChar[i] = '1';
            } else {
                minorityChar[i] = '0';
            }
        }

        return minorityChar;
    }

}
