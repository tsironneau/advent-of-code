package y2021.day9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static y2021.day9.Input.INPUT;

public class Day9_2 {

    //1056330
    public static void main(String[] args) {

        final List<String[]> lines = INPUT.lines().map(line -> line.split("")).toList();

        List<Point> lowests = findLowests(lines);
        List<Set<Point>> bassins = computeBassinsQueue(lines, lowests);

        final Integer result = bassins.stream()
                .map(Set::size)
                .sorted(Comparator.<Integer>naturalOrder().reversed())
                .limit(3)
                .reduce((a, b) -> a * b)
                .orElseThrow(IllegalArgumentException::new);

        System.out.println(result);
    }

    private static List<Set<Point>> computeBassinsRecursive(final List<String[]> lines, final List<Point> lowests) {
        final List<Set<Point>> result = new ArrayList<>();
        for (Point lowest : lowests) {
            final Set<Point> bassin = computeBassin(lowest, lines);
            if (bassin.size() > 1) {
                result.add(bassin);
            }
        }
        return result;
    }

    private static List<Set<Point>> computeBassinsQueue(final List<String[]> lines, final List<Point> lowests) {
        final List<Set<Point>> result = new ArrayList<>();
        final Queue<Point> toCheck = new LinkedList<>();
        for (Point lowest : lowests) {
            final Set<Point> bassin = new HashSet<>();
            toCheck.add(lowest);
            while (!toCheck.isEmpty()) {
                final Point head = toCheck.poll();
                final List<Point> neighbours = getNeighbours(head, lines);
                neighbours.removeAll(bassin);
                if (head.height < 9 && isLowestOrEquals(head, neighbours)) {
                    bassin.add(head);
                    toCheck.addAll(neighbours);
                }
            }
            result.add(bassin);
        }
        return result;
    }

    private static boolean isLowestOrEquals(final Point point, final List<Point> neighbours) {
        return neighbours.stream().allMatch(n -> point.height <= n.height);
    }

    private static Set<Point> computeBassin(final Point lowest, final List<String[]> lines) {

        final Set<Point> bassin = new HashSet<>();
        bassin.add(lowest);
        List<Point> neighbours = getNeighbours(lowest, lines);

        for (Point neighbour : neighbours) {
            addToBassin(neighbour, lines, bassin);
        }

        return bassin;
    }

    private static void addToBassin(final Point point, final List<String[]> lines, final Set<Point> bassin) {
        if (point.height == 9) {
            return;
        }
        bassin.add(point);
        final List<Point> neighbours = getNeighbours(point, lines);
        neighbours.removeAll(bassin);
        for (Point neighbour : neighbours) {
            if (neighbour.height >= point.height) {
                addToBassin(neighbour, lines, bassin);
            }
        }
    }

    private static List<Point> getNeighbours(final Point point, final List<String[]> lines) {
        final List<Point> result = new ArrayList<>();

        final int i = point.x;
        final int j = point.y;
        if (i > 0) {
            result.add(new Point(i - 1, j, Integer.parseInt(lines.get(i - 1)[j])));
        }
        if (i < lines.size() - 1) {
            result.add(new Point(i + 1, j, Integer.parseInt(lines.get(i + 1)[j])));
        }
        if (j > 0) {
            result.add(new Point(i, j - 1, Integer.parseInt(lines.get(i)[j - 1])));
        }
        if (j < lines.get(i).length - 1) {
            result.add(new Point(i, j + 1, Integer.parseInt(lines.get(i)[j + 1])));
        }

        return result;
    }

    private static List<Point> findLowests(final List<String[]> lines) {
        List<Point> lowests = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            final String[] line = lines.get(i);

            for (int j = 0; j < line.length; j++) {
                final String height = line[j];

                if (isLowest(i, j, lines)) {
                    lowests.add(new Point(i, j, Integer.parseInt(height)));
                }
            }
        }
        return lowests;
    }

    record Point(int x, int y, int height) {}

    private static boolean isLowest(final int i, final int j, final List<String[]> lines) {
        final Integer value = Integer.valueOf(lines.get(i)[j]);

        final Integer south = i > 0 ? Integer.valueOf(lines.get(i - 1)[j]) : null;
        final Integer north = i < lines.size() - 1 ? Integer.valueOf(lines.get(i + 1)[j]) : null;
        final Integer west = j > 0 ? Integer.valueOf(lines.get(i)[j - 1]) : null;
        final Integer east = j < lines.get(i).length - 1 ? Integer.valueOf(lines.get(i)[j + 1]) : null;
        return isLowest(value, south, north, west, east);
    }

    private static boolean isLowest(final Integer value, final Integer south, final Integer north, final Integer west,
                                    final Integer east) {
        if (south != null) {
            if (south <= value) {
                return false;
            }
        }

        if (north != null) {
            if (north <= value) {
                return false;
            }
        }

        if (west != null) {
            if (west <= value) {
                return false;
            }
        }

        if (east != null) {
            return east > value;
        }

        return true;
    }
}
