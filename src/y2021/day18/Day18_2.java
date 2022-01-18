package y2021.day18;

import common.Point;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static y2021.day18.Input.INPUT;

public class Day18_2 extends Day18_1{

    public static void main(String[] args) {

        final List<String> lines = INPUT.lines().toList();

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.size(); i++) {
            String s = lines.get(i);
            for (int j = 0; j < lines.size(); j++) {
                String s1 = lines.get(j);

                final int value = computeValue(reduce(add(s, s1)));
                if (value > max)
                    max = value;
            }
        }

        System.out.println(max);
    }

    private static int computeValue(final String current) {

        String globalRegex = "(.*\\[\\d+,\\d+\\].*)";
        final Pattern globalPattern = Pattern.compile(globalRegex);
        String regex = "(\\[\\d+,\\d+\\])";
        final Pattern pattern = Pattern.compile(regex);

        String reduced = current;
        Matcher globalMatcher = globalPattern.matcher(reduced);
        while (globalMatcher.matches()) {
            int lastIndex = 0;
            StringBuilder output = new StringBuilder();
            Matcher matcher = pattern.matcher(reduced);
            while (matcher.find()) {
                output.append(reduced, lastIndex, matcher.start())
                        .append(convert(matcher.group(1)));

                lastIndex = matcher.end();
            }
            if (lastIndex < reduced.length()) {
                output.append(reduced, lastIndex, reduced.length());
            }
            reduced = output.toString();
            globalMatcher = globalPattern.matcher(reduced);
            System.out.println(reduced);
        }
        return Integer.parseInt(reduced);
    }

    private static String convert(final String group) {

        final String explodingPair = group.substring(1, group.length() - 1);

        final int left = Integer.parseInt(explodingPair.split(",")[0]);
        final int right = Integer.parseInt(explodingPair.split(",")[1]);

        return String.valueOf(3 * left + 2 * right);
    }
}
