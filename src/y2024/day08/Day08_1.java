package y2024.day08;

import common.Point;

import java.util.*;
import java.util.stream.Collectors;

import static common.Point.p;

public class Day08_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        Map<String, List<Point>> radars = new HashMap<>();
        for (int i = 0; i < collect.size(); i++) {
            String s = collect.get(i);
            final var split = s.split("");
            for (int j = 0; j < split.length; j++) {
                String string = split[j];
                if (string.equals("."))
                    continue;
                radars.putIfAbsent(string, new ArrayList<>());
                radars.get(string).add(new Point(i, j));
            }
        }

        Set<Point> antinodes = new HashSet<>();
        radars.values().forEach(points -> {
            antinodes.addAll(computeAntinodes(points, collect.size(), collect.get(0).length()));
        });

        return antinodes.size();
    }

    private static Collection<Point> computeAntinodes(List<Point> points, int iMax, int jMax) {
        final var queue = new LinkedList<>(points);
        Collection<Point> antinodes = new HashSet<>();
        while (!queue.isEmpty()) {
            final var current = queue.pop();
            for (Point other : queue) {
                antinodes.addAll(computeAntinodes(current, other, iMax, jMax));
            }
        }
        return antinodes;
    }

    private static Collection<Point> computeAntinodes(Point current, Point other, int iMax, int jMax) {
        Collection<Point> antinode = new HashSet<>();

        final var iDiff = current.x() - other.x();
        final var jDiff = current.y() - other.y();

        antinode.add(current.add(p(iDiff, jDiff)));
        antinode.add(other.add(p(-iDiff, -jDiff)));

        return antinode.stream().filter(
                p -> p.x() >= 0 && p.x() < iMax && p.y() >= 0 && p.y() < jMax
        ).toList();
    }
}
