package y2021.day11;

import common.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static common.PointUtils.computeNeighbours;

public class Day11_1 {

    public static void main(String[] args) {

        final List<List<Integer>> lines = Input.INPUT.lines()
                .map(l -> Arrays.stream(l.split("")).map(Integer::parseInt).collect(Collectors.toList()))
                .toList();
        System.out.println(lines.stream().map(Object::toString).collect(Collectors.joining("\n")));

        Set<Point> flashes = new HashSet<>();

        int step = 100;

        long totalNumberOfFlashes = 0;
        for (int i = 0; i < step; i++) {
            flashes.clear();

            computeNextStep(lines);
            computeFlashes(lines, flashes);
            resetFlashed(lines, flashes);
            totalNumberOfFlashes += flashes.size();
        }

        System.out.println(totalNumberOfFlashes);
    }

    static void resetFlashed(final List<List<Integer>> lines, final Set<Point> flashes) {
        for (Point flash : flashes) {
            lines.get(flash.x()).set(flash.y(), 0);
        }
    }

    static void computeNextStep(final List<List<Integer>> lines) {

        for (final List<Integer> line : lines) {
            for (int j = 0; j < line.size(); j++) {
                Integer power = line.get(j);

                power += 1;
                line.set(j, power);
            }
        }
    }

    static void computeFlashes(final List<List<Integer>> lines, final Set<Point> flashes) {

        final int currentFlashesSize = flashes.size();
        for (int i = 0; i < lines.size(); i++) {
            final List<Integer> line = lines.get(i);
            for (int j = 0; j < line.size(); j++) {
                Integer power = line.get(j);

                final Point point = new Point(i, j);
                if (power > 9 && !flashes.contains(point)) {
                    flashes.add(point);
                    increaseNeighbours(i, j, flashes, lines);
                }
            }
        }
        final int newFlashesSize = flashes.size();
        if (newFlashesSize != currentFlashesSize) {
            computeFlashes(lines, flashes);
        }
    }

    private static void increaseNeighbours(final int i, final int j, final Set<Point> flashes,
                                           final List<List<Integer>> lines) {

        Set<Point> neighbours = computeNeighbours(i, j, lines.size() - 1, lines.get(0).size() - 1);
        for (Point neighbour : neighbours) {
            final int x = neighbour.x();
            final int y = neighbour.y();
            final Point point = new Point(x, y);
            if (!flashes.contains(point)) {
                final Integer energy = lines.get(x).get(y);
                lines.get(x).set(y, energy + 1);
            }
        }
    }
}
