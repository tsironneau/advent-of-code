package y2022.day17;

import common.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static common.Point.p;

public class Day_17_1 {

    public static final List<Rock> rocks = List.of(
            new Rock(Set.of(p(0, 0), p(1, 0), p(2, 0), p(3, 0)), 3, "_"),
            new Rock(Set.of(p(1, 1), p(1, 0), p(0, 1), p(1, 2), p(2, 1)), 2, "+"),
            new Rock(Set.of(p(0, 0), p(1, 0), p(2, 0), p(2, 1), p(2, 2)), 2, "J"),
            new Rock(Set.of(p(0, 0), p(0, 1), p(0, 2), p(0, 3)), 0, "I"),
            new Rock(Set.of(p(0, 0), p(0, 1), p(1, 1), p(1, 0)), 1, "O")
    );
    private static char[] moves;
    private static int moveIndex = 0;
    private static int rockIndex = 0;
    private static Set<Point> occupiedCells = new HashSet<>();

    public static void main(String[] args) {
        final String[] lines = Example.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
        printCells(occupiedCells);
    }

    private static void printCells(Set<Point> occupiedCells) {
        int max = occupiedCells.stream().mapToInt(Point::y).max().orElseThrow();
        for (int i = max; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                if(occupiedCells.contains(p(j,i)))
                    System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
    }


    private static int puzzle(List<String> collect) {
        int height = 0;
        moves = collect.get(0).toCharArray();
        occupiedCells.addAll(IntStream.range(0, 7).mapToObj(i -> new Point(i, 0)).toList());
        System.out.println(Arrays.toString(moves));

        for (int i = 0; i < 3; i++) {
            height = dropRock(height);
            printCells(occupiedCells);
        }

        return height;
    }

    private static int dropRock(int height) {
        Rock rock = rocks.get(rockIndex++ % rocks.size());
        System.out.println(rock);

        Point pos = p(2, height + 3);

        boolean rest = false;
        while (true) {
            pos = pushRock(pos, rock);

            if (!isBlocked(rock, pos)) {
                pos = fallRock(rock, pos);
            } else {
                final Point finalPos = pos.add(p(0,1));
                List<Point> toAdd = rock.pointSet.stream().map(p -> p.add(finalPos)).toList();
                occupiedCells.addAll(
                        toAdd
                );
                return occupiedCells.stream().mapToInt(Point::y).max().orElseThrow();
            }

        }
    }

    private static boolean canFall(Rock rock, Point pos) {
        return !isBlocked(rock, pos.add(0, -1));
    }

    private static Point fallRock(Rock rock, Point pos) {
        return pos.add(0, -1);
    }

    private static boolean isBlocked(Rock rock, Point pos) {
        for (Point point : rock.pointSet) {
            if (occupiedCells.contains(point.add(pos)))
                return true;
        }
        return false;
    }

    private static Point pushRock(Point pos, Rock rock) {
        char move = moves[moveIndex++ % moves.length];
        Point newPos;
        if (move == '<') {
            newPos = pos.add(-1, 0);
        } else if (move == '>') {
            newPos = pos.add(1, 0);
        } else throw new IllegalArgumentException();

        if (validPos(newPos, rock))
            return newPos;
        return pos;
    }

    private static boolean validPos(Point newPos, Rock rock) {
        if(occupiedCells.contains(newPos))
            return false;
        return newPos.x() >= 0 && newPos.x() + rock.maxX <= 6;
    }

    record Rock(Set<Point> pointSet, int maxX, String symbol) {

        @Override
        public String toString() {
            return symbol;
        }
    }

    ;
}
