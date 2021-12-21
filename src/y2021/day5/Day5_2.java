package y2021.day5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static y2021.day5.Input.INPUT;

public class Day5_2 {

    public static void main(String[] args) {

        List<Coordinate> coordinates = INPUT.lines().map(l -> l.split(" -> "))
                .map(l -> new Coordinate(Point.from(l[0].split(",")), Point.from(l[1].split(","))))
                .toList();

        List<Coordinate> alignedCoordinates = coordinates.stream()
                .filter(c -> c.start.x == c.end.x || c.start.y == c.end.y)
                .toList();

        List<Coordinate> diagonalCoordinates = coordinates.stream().filter(c -> !alignedCoordinates.contains(c)).toList();
        Map<Point, Integer> counters = fillAlignedCounters(alignedCoordinates);

        fillDiagonalCounters(diagonalCoordinates, counters);

        List<Integer> integerStream = counters.values().stream().filter(v -> v >= 2).toList();
        long count = integerStream.size();
        System.out.println(count);
    }

    private static void fillDiagonalCounters(List<Coordinate> coordinates, Map<Point, Integer> result) {
        for (Coordinate coordinate : coordinates) {
            List<Point> counters = computeDiagonalPoints(coordinate);
            counters.forEach((point) ->
                    result.compute(point, (k, v) -> v == null ? 1 : v + 1)
            );
        }
    }

    private static Map<Point, Integer> fillAlignedCounters(List<Coordinate> coordinates) {
        Map<Point, Integer> result = new HashMap<>();
        for (Coordinate coordinate : coordinates) {
            Map<Point, Integer> counters = fillAlignedCounters(coordinate);
            counters.forEach((k, v) ->
                    result.compute(k, (p, c) -> c == null ? 1 : c + 1)
            );
        }
        return result;
    }

    private static List<Point> computeDiagonalPoints(Coordinate coordinate) {
        List<Point> result = new ArrayList<>();

        Point start = coordinate.start;
        Point end = coordinate.end;

        int startX = start.x;
        int endX = end.x;
        int startY = start.y;
        int endY = end.y;

        int incX = endX - startX > 0 ? 1 : -1;
        int incY = endY - startY > 0 ? 1 : -1;

        int diff = Math.abs(endX - startX);
        for (int i = 0; i <= diff; i++) {
            result.add(new Point(startX + (i * incX), startY + (i * incY)));
        }

        return result;
    }

    private static Map<Point, Integer> fillAlignedCounters(Coordinate coordinate) {
        Map<Point, Integer> result = new HashMap<>();

        Point start = coordinate.start;
        Point end = coordinate.end;

        result.put(start, 1);
        result.put(end, 1);

        if (start.x < end.x) {
            for (int i = start.x + 1; i < end.x; i++) {
                result.put(new Point(i, end.y), 1);
            }
        } else {
            for (int i = end.x + 1; i < start.x; i++) {
                result.put(new Point(i, end.y), 1);
            }
        }

        if (start.y < end.y) {
            for (int i = start.y + 1; i < end.y; i++) {
                result.put(new Point(start.x, i), 1);
            }
        } else {
            for (int i = end.y + 1; i < start.y; i++) {
                result.put(new Point(start.x, i), 1);
            }
        }

        return result;
    }


    record Point(int x, int y) {
        public static Point from(String[] strings) {
            int x = Integer.parseInt(strings[0]);
            int y = Integer.parseInt(strings[1]);
            return new Point(x, y);
        }
    }

    record Coordinate(Point start, Point end) {

    }
}
