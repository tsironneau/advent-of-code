package y2022.day14;

import common.Point;

import java.util.*;

import static common.Point.p;

public class Day_14_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        Set<Point> occupiedPositions = initialState(collect);

        Point sandPosition = dropSand(occupiedPositions);
        int i = 0;
        while (sandPosition != null) {
            occupiedPositions.add(sandPosition);
            sandPosition = dropSand(occupiedPositions);
            i++;
        }

        return i;
    }

    static Point dropSand(Set<Point> occupiedPositions) {
        Point initialPosition = p(500, 0);
        Point nextPos = findValidPos(occupiedPositions, initialPosition);
        if (nextPos == null)
            return initialPosition;
        for (int i = 0; i < 100000; i++) {
            Point validPos = findValidPos(occupiedPositions, nextPos);
            if (validPos == null)
                return nextPos;
            nextPos = validPos;
        }
        return null;
    }

    static Point findValidPos(Set<Point> occupiedPositions, Point currentPos) {
        Point down = p(currentPos.x(), currentPos.y() + 1);
        if (!occupiedPositions.contains(down))
            return down;
        Point downLeft = p(currentPos.x() - 1, currentPos.y() + 1);
        if (!occupiedPositions.contains(downLeft))
            return downLeft;
        Point downRight = p(currentPos.x() + 1, currentPos.y() + 1);
        if (!occupiedPositions.contains(downRight))
            return downRight;
        return null;
    }

    static Set<Point> initialState(List<String> collect) {
        Set<Point> res = new HashSet<>();
        for (String line : collect) {
            res.addAll(readLine(line));
        }

        return res;
    }

    static Collection<Point> readLine(String line) {
        Collection<Point> res = new HashSet<>();
        String[] steps = line.split(" -> ");
        for (int i = 0; i < steps.length - 1; i++) {
            String step = steps[i];
            String stepN = steps[i + 1];
            String[] from = step.split(",");
            String[] to = stepN.split(",");

            Point pFrom = readP(from);
            Point pTo = readP(to);

            int diffX = pFrom.x() - pTo.x();
            int diffY = pFrom.y() - pTo.y();
            res.add(pFrom);
            res.add(pTo);
            if (diffX < 0) {
                for (int j = 1; j < -diffX; j++) {
                    res.add(p(pFrom.x() + j, pFrom.y()));
                }
            } else if (diffX > 0) {
                for (int j = 1; j < diffX; j++) {
                    res.add(p(pFrom.x() - j, pFrom.y()));
                }
            } else if (diffY < 0) {
                for (int j = 1; j < -diffY; j++) {
                    res.add(p(pFrom.x(), pFrom.y() + j));
                }
            } else if (diffY > 0) {
                for (int j = 1; j < diffY; j++) {
                    res.add(p(pFrom.x(), pFrom.y() - j));
                }
            }


        }

        return res;
    }

    private static Point readP(String[] from) {
        int x = Integer.parseInt(from[0]);
        int y = Integer.parseInt(from[1]);
        return p(x, y);
    }
}
