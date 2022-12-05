package y2022.day4;

import common.Point;

import java.util.Arrays;
import java.util.List;

public class Day4_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        List<String[]> pairList = collect.stream()
                .map(s -> s.split(","))
                .toList();

        for (String[] pair : pairList) {
            String[] pair1 = pair[0].split("-");
            String[] pair2 = pair[1].split("-");

            Point p1 = new Point(Integer.parseInt(pair1[0]), Integer.parseInt(pair1[1]));
            Point p2 = new Point(Integer.parseInt(pair2[0]), Integer.parseInt(pair2[1]));

            if (p1.x() >= p2.x() && p1.y() <= p2.y()) {
                result += 1;
            } else if (p1.x() <= p2.x() && p1.y() >= p2.y()) {
                result += 1;
            }

        }


        return result;
    }

}
