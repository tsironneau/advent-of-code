package y2022.day18;

import common.Point3D;
import common.tuple.Tuple2;
import common.tuple.Tuples;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day_18_2 {

    public static final List<Tuple2<Point3D, Point3D>> SIDES = List.of(
            Tuples.of(Point3D.of(0, 0, 0), Point3D.of(1, 1, 0)),
            Tuples.of(Point3D.of(0, 0, 1), Point3D.of(1, 1, 1)),
            Tuples.of(Point3D.of(0, 0, 0), Point3D.of(1, 0, 1)),
            Tuples.of(Point3D.of(0, 1, 0), Point3D.of(1, 1, 1)),
            Tuples.of(Point3D.of(0, 0, 0), Point3D.of(0, 1, 1)),
            Tuples.of(Point3D.of(1, 0, 0), Point3D.of(1, 1, 1))
    );
    private static int MAX_COORD;

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static final Set<Point3D> OUTSIDE_CUBES = new HashSet<>();
    private static final Set<Point3D> INSIDE_CUBES = new HashSet<>();

    private static int puzzle(List<String> collect) {
        int result = 0;


        final List<Point3D> cells = collect.stream().map(s -> s.split(","))
                                           .map(a -> Point3D.of(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2])))
                                           .toList();
        MAX_COORD = cells.stream().flatMapToInt(c -> IntStream.of(c.x(), c.y(), c.z()))
                         .max()
                         .orElseThrow() + 1;
        fillOutsideSpace(MAX_COORD);


        List<Side> allSides = new ArrayList<>();
        Set<Side> sidesInDouble = new HashSet<>();

        for (Point3D cell : cells) {
            Set<Side> sides = toSides(cell);

            for (Side side : sides) {
                if (allSides.contains(side))
                    sidesInDouble.add(side);
            }

            allSides.addAll(sides);
        }

        for (int i = 0; i < MAX_COORD; i++) {
            for (int j = 0; j < MAX_COORD; j++) {
                for (int k = 0; k < MAX_COORD; k++) {

                    Point3D cube = Point3D.of(i, j, k);
                    if (!cells.contains(cube))
                        checkInside(cube, Set.copyOf(cells));
                }
            }
        }

        Set<Side> insideSides = INSIDE_CUBES.stream().flatMap(c -> toSides(c).stream()).collect(Collectors.toSet());
        Set<Side> uniqueSides = new HashSet<>(allSides);
        uniqueSides.removeAll(sidesInDouble);
        uniqueSides.removeAll(insideSides);

        return uniqueSides.size();
    }

    private static void fillOutsideSpace(int spaceLength) {
        for (int i = 0; i < spaceLength; i++) {
            for (int j = 0; j < spaceLength; j++) {
                OUTSIDE_CUBES.add(Point3D.of(0, i, j));
                OUTSIDE_CUBES.add(Point3D.of(i, 0, j));
                OUTSIDE_CUBES.add(Point3D.of(i, j, 0));
                OUTSIDE_CUBES.add(Point3D.of(spaceLength, i, j));
                OUTSIDE_CUBES.add(Point3D.of(i, spaceLength, j));
                OUTSIDE_CUBES.add(Point3D.of(i, j, spaceLength));
            }
        }
    }

    private static void checkInside(Point3D cube, Set<Point3D> magmaCells) {

        Queue<Point3D> toCheck = new LinkedList<>();
        toCheck.add(cube);
        toCheck.addAll(adjacentCubes(cube));

        Set<Point3D> alreadyChecked = new HashSet<>();
        while (!toCheck.isEmpty()) {
            Point3D checkedCube = toCheck.poll();

            if (magmaCells.contains(checkedCube))
                continue;

            if (alreadyChecked.contains(checkedCube)) {
                continue;
            }

            alreadyChecked.add(checkedCube);

            if (OUTSIDE_CUBES.contains(checkedCube)) {
                OUTSIDE_CUBES.addAll(alreadyChecked);
                return;
            }
            if (INSIDE_CUBES.contains(checkedCube)) {
                INSIDE_CUBES.addAll(alreadyChecked);
                return;
            }

            toCheck.addAll(adjacentCubes(checkedCube));
        }

        INSIDE_CUBES.addAll(alreadyChecked);
    }

    private static Collection<Point3D> adjacentCubes(Point3D checkedCube) {
        return Stream.of(
                             checkedCube.add(1, 0, 0),
                             checkedCube.add(-1, 0, 0),
                             checkedCube.add(0, 1, 0),
                             checkedCube.add(0, -1, 0),
                             checkedCube.add(0, 0, 1),
                             checkedCube.add(0, 0, -1))
                     .filter(p -> p.x() < MAX_COORD && p.y() < MAX_COORD && p.z() < MAX_COORD && p.x() >= 0 && p.y() >= 0 && p.z() >= 0)
                     .toList();
    }

    private static Set<Side> toSides(Point3D cell) {
        return SIDES.stream().map(t2 -> new Side(Set.of(cell.add(t2._1()), cell.add(t2._2()))))
                    .collect(Collectors.toSet());
    }

    record Side(Set<Point3D> points) {
    }


}
