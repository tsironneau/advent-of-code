package y2024.day06;

import common.Point;
import common.tuple.Tuple2;
import common.tuple.Tuples;

import java.util.*;
import java.util.function.Function;

import static common.Point.p;

public class Day_06_2 {

    public static final List<Point> DIRECTIONS = List.of(
            p(-1, 0),
            p(0, 1),
            p(1, 0),
            p(0, -1)
    );

    public static final Function<Integer, Integer> NEXT_DIRECTION = current ->
            (current + 1) % DIRECTIONS.size();
    public static final Set<Point> VISITED_CELLS = new HashSet<>();
    public static final Set<Tuple2<Integer, Point>> VISITED_PATHS = new HashSet<>();
    private static final Set<Point> LOOPING_CELLS = new HashSet<>();
    private static Integer currentDirection = 0;
    private static Point position;

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> lines) {
        final var start = findStart(lines);
        position = start;
        VISITED_CELLS.add(position);
        while (advance(lines)) {
            VISITED_CELLS.add(position);
        }

        for (Point visitedCell : VISITED_CELLS) {
            if (visitedCell.equals(start))
                continue;
            position = start;
            currentDirection = 0;
            VISITED_PATHS.clear();
            if (isLooping(visitedCell, lines))
                LOOPING_CELLS.add(visitedCell);
        }

        return LOOPING_CELLS.size();
    }

    private static boolean isLooping(Point cell, List<String> lines) {
        final var copy = new ArrayList<>(lines);
        final var lineChars = copy.get(cell.x()).toCharArray();
        lineChars[cell.y()] = '#';
        copy.set(cell.x(), new String(lineChars));

        while (advance(copy)) {
            final var visitedPath = Tuples.of(currentDirection, position);
            if (VISITED_PATHS.contains(visitedPath)) {
                return true;
            }
            VISITED_PATHS.add(visitedPath);
        }

        return false;
    }

    private static boolean advance(List<String> lines) {
        try {
            if (goToNext(lines)) return true;
            for (int i = 0; i < 3; i++) {
                currentDirection = NEXT_DIRECTION.apply(currentDirection);
                if (goToNext(lines))
                    return true;
            }
            throw new IllegalStateException();
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }

    private static boolean goToNext(List<String> lines) {
        Point nextPos = position.add(DIRECTIONS.get(currentDirection));
        char next = lines.get(nextPos.x()).charAt(nextPos.y());
        if (next == '.' || next == '^') {
            position = nextPos;
            return true;
        }
        return false;
    }

    private static Point findStart(List<String> collect) {
        for (int i = 0; i < collect.size(); i++) {
            char[] chars = collect.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                if (c == '^')
                    return new Point(i, j);
            }

        }
        throw new IllegalArgumentException();
    }
}
