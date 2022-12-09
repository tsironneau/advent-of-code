package y2021.day22;

import common.Point;

import java.util.*;

import static common.Point.p;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day22_2 {

    //            on x=-44..9,y=-9..44,z=-34..13
    // on x=-20..26,y=-36..17,z=-47..7
    public static void main(String[] args) {

        final List<String> lines = Input.INPUT.lines().toList();

        List<Cube> cubes = new ArrayList<>();
        for (String s : lines) {
            final String[] line = s.split(" ");
            final String action = line[0];
            Map<String, Point> coordMap = new HashMap<>();
            final String[] coords = line[1].split(",");
            for (int i = 0; i < coords.length; i++) {
                String coord = coords[i];
                final String[] split = coord.split("=");
                final String axe = split[0];
                final String[] pointRaw = split[1].split("\\.\\.");
                final int ux = Integer.parseInt(pointRaw[0]);
                final int uy = Integer.parseInt(pointRaw[1]);
                coordMap.put(axe, new Point(
                        min(ux, uy),
                        max(ux, uy)
                ));
            }

            final Point x = coordMap.get("x");
            final Point y = coordMap.get("y");
            final Point z = coordMap.get("z");

            final Cube newCube = new Cube(x, y, z);

            Set<Cube> toRemove = new HashSet<>();
            Set<Cube> toAdd = new HashSet<>();
            for (Cube cube : cubes) {
                if (!cube.overlaps(newCube))
                    continue;

                toRemove.add(cube);

                if(newCube.x.x() > cube.x.x())
                    toAdd.add(new Cube(p(cube.x.x(), newCube.x.x() - 1), cube.y, cube.z));
                if (newCube.x.y() < cube.x.y())
                    toAdd.add(new Cube(p(newCube.x.y() + 1, cube.x.y()), cube.y, cube.z));

                if(newCube.y.x() > cube.y.x())
                    toAdd.add(new Cube(p(max(cube.x.x(), newCube.x.x()), min(cube.x.y(), newCube.x.y())),
                                       p(cube.y.x(), newCube.y.x() - 1), cube.z));
                if(newCube.y.y() < cube.y.y())
                    toAdd.add(new Cube(p(max(cube.x.x(), newCube.x.x()), min(cube.x.y(), newCube.x.y())),
                                       p(newCube.y.y() + 1, cube.y.y()), cube.z));

                if(newCube.z.x() > cube.z.x())
                    toAdd.add(new Cube(p(max(cube.x.x(), newCube.x.x()), min(cube.x.y(), newCube.x.y())),
                                       p(max(cube.y.x(), newCube.y.x() ), min(cube.y.y(), newCube.y.y())),
                                       p(cube.z.x(), newCube.z.x() - 1)));
                if(newCube.z.y() < cube.z.y())
                    toAdd.add(new Cube(p(max(cube.x.x(), newCube.x.x()), min(cube.x.y(), newCube.x.y())),
                                       p(max(cube.y.x(), newCube.y.x() ), min(cube.y.y(), newCube.y.y())),
                                       p(newCube.z.y() + 1, cube.z.y())));
            }

            cubes.removeAll(toRemove);
            cubes.addAll(toAdd);

            if (action.equals("on"))
                cubes.add(newCube);
        }

        final long totalSize = cubes.stream().mapToLong(Cube::size).sum();

        System.out.println(totalSize);

    }



    public static int clamp(int value, int min, int max) {
        return min(max(value, max), min);
    }

    public static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    record Cube(Point x, Point y, Point z) {

        long size() {
            return (long) (x.y() - x.x() + 1) * (y.y() - y.x() + 1) * (z.y() - z.x() + 1);
        }

        public boolean overlaps(final Cube otherCube) {
            return !(otherCube.x.x() > this.x.y()
                    || otherCube.x.y() < this.x.x()
                    || otherCube.y.x() > this.y.y()
                    || otherCube.y.y() < this.y.x()
                    || otherCube.z.x() > this.z.y()
                    || otherCube.z.y() < this.z.x()
            );
        }
    }
}
