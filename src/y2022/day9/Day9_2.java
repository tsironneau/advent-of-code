package y2022.day9;

import common.IPoint;
import common.NeighbourComputer;
import common.Point;
import common.PointUtils;

import java.util.*;

public class Day9_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        //2604
        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {

        Set<IPoint> visitedCells = new HashSet<>();
        List<Point> tailPos = new ArrayList<>(List.of(
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0),
                new Point(0, 0)
        ));
        visitedCells.add(new Point(0, 0));

        for (String move : collect) {
            String[] split = move.split(" ");
            String dir = split[0];
            int step = Integer.parseInt(split[1]);

            for (int i = 0; i < step; i++) {
                tailPos.set(0, moveHead(tailPos.get(0), dir));
                for (int j = 1; j < tailPos.size(); j++) {
                    Point previous = tailPos.get(j - 1);
                    Point point = tailPos.get(j);

                    tailPos.set(j, updateTail(point, previous));
                }
                visitedCells.add(tailPos.get(tailPos.size() - 1));
            }
        }

        return visitedCells.size();
    }

    private static Point updateTail(Point tailPos, IPoint headPos) {
        Set<Point> neighbours = new NeighbourComputer().compute(headPos);
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

    private static Point moveHead(IPoint headPos, String dir) {
        return switch (dir) {
            case "U" -> new Point(headPos.x(), headPos.y() + 1);
            case "D" -> new Point(headPos.x(), headPos.y() - 1);
            case "R" -> new Point(headPos.x() + 1, headPos.y());
            case "L" -> new Point(headPos.x() - 1, headPos.y());
            default -> throw new IllegalStateException("Unexpected value: " + dir);
        };
    }

}
