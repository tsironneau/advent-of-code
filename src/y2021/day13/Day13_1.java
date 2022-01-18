package y2021.day13;

import common.Point;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13_1 {

    public static void main(String[] args) {

        final String[] split = Input.INPUT.split("\n\n");
        final String array = split[0];
        final String instructions = split[1];

        System.out.println(array);
        System.out.println(instructions);

        final int max = array.lines()
                .flatMap(l -> Arrays.stream(l.split("\n")))
                .flatMap(l -> Arrays.stream(l.split(",")))
                .mapToInt(Integer::parseInt)
                .max().getAsInt();

        System.out.println(max);
        Set<Point> dots = array.lines()
                .flatMap(l -> Arrays.stream(l.split("\n")))
                .map(l -> l.split(","))
                .map(c -> new Point(Integer.parseInt(c[0]), Integer.parseInt(c[1])))
                .collect(Collectors.toSet());

        int maxX = dots.stream().mapToInt(Point::x).max().getAsInt();
        int maxY = dots.stream().mapToInt(Point::y).max().getAsInt();

        final List<String[]> instructionsList = instructions.lines()
                .map(l -> l.replace("fold along ", "").split("="))
                .toList();

        instructionsList.forEach(i -> System.out.println(Arrays.toString(i)));

        for (String[] inst : instructionsList) {
            final String axe = inst[0];
            dots = fold(dots, axe, Integer.parseInt(inst[1]));

            switch (axe) {
                case "x" -> maxX = maxX / 2;
                case "y" -> maxY = maxY / 2;
                default -> throw new IllegalArgumentException();
            }
        }

        System.out.println(print(dots));

        System.out.println(dots.stream().sorted(Comparator.comparingInt(Point::x)).toList());
        System.out.println(dots.size());
    }

    private static String print(final Set<Point> dots) {
        int maxX = dots.stream().mapToInt(Point::x).max().getAsInt();
        int maxY = dots.stream().mapToInt(Point::y).max().getAsInt();

        char[][] array = new char[maxY +1][maxX +1];
        for (int i = 0; i <= maxY; i++) {
            for (int j = 0; j <= maxX; j++) {
                array[i][j] = ' ';
            }
        }
        for (Point dot : dots) {
            array[dot.y()][dot.x()] = '#';
        }

        for (char[] chars : array) {
            System.out.println(Arrays.toString(chars));
        }
        return "";
    }

    private static Set<Point> fold(final Set<Point> dots, final String axe, final int coord) {
        final Set<Point> result = new HashSet<>();

        for (final Point dot : dots) {
            result.add(computeOpposite(dot, axe, coord));
        }
        return result;
    }

    private static Point computeOpposite(final Point dot, final String axe, final int coord) {
        switch (axe) {
            case "x" -> {
                if (dot.x() < coord) {
                    return dot;
                } else {
                    return new Point(2 * coord - dot.x(), dot.y());
                }
            }
            case "y" -> {
                if (dot.y() < coord) {
                    return dot;
                } else {
                    return new Point(dot.x(), 2 * coord - dot.y());
                }
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
