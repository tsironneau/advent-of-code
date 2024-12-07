package y2024.day06;

import common.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static common.Point.p;

public class Day_06_1 {

    public static final List<Point> DIRECTIONS = List.of(
            p(-1, 0),
            p(0, 1),
            p(1, 0),
            p(0, -1)
    );

    public static final Function<Integer, Integer> NEXT_DIRECTION = current ->
            (current + 1) % DIRECTIONS.size();
    public static final Set<Point> VISITED_CELLS = new HashSet<>();
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
        position = findStart(lines);
        VISITED_CELLS.add(position);
        while (advance(lines)) {
            System.out.println(position);
            VISITED_CELLS.add(position);
        }

        return VISITED_CELLS.size();
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
