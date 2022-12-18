package y2022.day18;

import common.Point3D;
import common.tuple.Tuple2;
import common.tuple.Tuples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day_18_1 {

    public static final List<Tuple2<Point3D, Point3D>> SIDES = List.of(
            Tuples.of(Point3D.of(0, 0, 0), Point3D.of(1, 1, 0)),
            Tuples.of(Point3D.of(0, 0, 1), Point3D.of(1, 1, 1)),
            Tuples.of(Point3D.of(0, 0, 0), Point3D.of(1, 0, 1)),
            Tuples.of(Point3D.of(0, 1, 0), Point3D.of(1, 1, 1)),
            Tuples.of(Point3D.of(0, 0, 0), Point3D.of(0, 1, 1)),
            Tuples.of(Point3D.of(1, 0, 0), Point3D.of(1, 1, 1))
    );

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;

        List<Point3D> cells = collect.stream().map(s -> s.split(","))
                                        .map(a -> Point3D.of(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2])))
                                        .toList();

        List<Side> allSides = new ArrayList<>();
        List<Side> sidesInDouble = new ArrayList<>();

        for (Point3D cell : cells) {
            Set<Side> sides = toSides(cell);

            for (Side side : sides) {
                if(allSides.contains(side))
                    sidesInDouble.add(side);
            }

            allSides.addAll(sides);
        }

        ArrayList<Side> uniqueSides = new ArrayList<>(allSides);
        uniqueSides.removeAll(sidesInDouble);

        return uniqueSides.size();
    }

    private static Set<Side> toSides(Point3D cell) {
        return SIDES.stream().map(t2 -> new Side(Set.of(cell.add(t2._1()),cell.add(t2._2()))))
                .collect(Collectors.toSet());
    }

    record Side(Set<Point3D> points) {
    }


}
