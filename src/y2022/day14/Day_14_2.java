package y2022.day14;

import common.Point;

import java.util.*;

import static common.Point.p;
import static y2022.day14.Day_14_1.dropSand;
import static y2022.day14.Day_14_1.initialState;

public class Day_14_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        Set<Point> occupiedPositions = initialState(collect);

        int yMax = occupiedPositions.stream()
                                  .mapToInt(Point::y)
                                  .max()
                                  .orElseThrow();

        int floor = yMax + 2;

        for (int i = 0; i < 1000; i++) {
            occupiedPositions.add(p(i, floor));
        }

        Point sandPosition = dropSand(occupiedPositions);
        int i = 1;
        while (sandPosition != null && !sandPosition.equals(p(500,0))) {
            occupiedPositions.add(sandPosition);
            sandPosition = dropSand(occupiedPositions);
            i++;
        }

        return i;
    }

}
