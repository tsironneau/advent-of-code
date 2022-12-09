package y2022.day9;

import common.IPoint;
import common.Point;
import common.PointUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day9_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {

        Set<IPoint> visitedCells = new HashSet<>();
        IPoint headPos = new Point(0, 0);
        Point tailPos = new Point(0, 0);
        visitedCells.add(headPos);

        for (String move : collect) {
            String[] split = move.split(" ");
            String dir = split[0];
            int step = Integer.parseInt(split[1]);

            for (int i = 0; i < step; i++) {
                headPos = moveHead(headPos, dir);
                tailPos = updateTail(tailPos, headPos);
                visitedCells.add(tailPos);
            }
        }

        return visitedCells.size();
    }

    private static Point updateTail(Point tailPos, IPoint headPos) {
        Set<Point> neighbours = PointUtils.computeNeighbours(headPos.x(), headPos.y());
        if (neighbours.contains(tailPos))
            return tailPos;

        int newX = tailPos.x();
        int newY = tailPos.y();
        if (tailPos.y() > headPos.y()) newY = tailPos.y() - 1;
        if (tailPos.y() < headPos.y())
            newY = tailPos.y() + 1;

        if (tailPos.x() > headPos.x())
            newX = tailPos.x() - 1;
        if (tailPos.x() < headPos.x())
            newX = tailPos.x() + 1;

        return new Point(newX, newY);
    }

    private static IPoint moveHead(IPoint headPos, String dir) {
        return switch (dir) {
            case "U" -> new Point(headPos.x(), headPos.y() + 1);
            case "D" -> new Point(headPos.x(), headPos.y() - 1);
            case "R" -> new Point(headPos.x() + 1, headPos.y());
            case "L" -> new Point(headPos.x() - 1, headPos.y());
            default -> throw new IllegalStateException("Unexpected value: " + dir);
        };
    }

}
