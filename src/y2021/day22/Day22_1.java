package y2021.day22;

import common.Point;
import common.Point3D;

import java.util.*;

public class Day22_1 {

    //            on x=-44..9,y=-9..44,z=-34..13
    // on x=-20..26,y=-36..17,z=-47..7
    public static void main(String[] args) {

        final List<String> lines = Input.INPUT.lines().toList();

        List<Map<String, Point>> coordsList = new ArrayList<>();
        Set<Point3D> on = new HashSet<>();
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
                coordMap.put(axe, new Point(
                        Integer.parseInt(pointRaw[0]),
                        Integer.parseInt(pointRaw[1])
                ));
            }

            final Point x = coordMap.get("x");
            final Point y = coordMap.get("y");
            final Point z = coordMap.get("z");

            if (x.x() > 50 && x.y() < -50)
                continue;

            if (y.x() > 50 && y.y() < -50)
                continue;

            if (z.x() > 50 && z.y() < -50)
                continue;

            for (int j = Math.max(x.x(), -50); j <= Math.min(x.y(), 50); j++) {
                for (int k = Math.max(y.x(), -50); k <= Math.min(y.y(), 50); k++) {
                    for (int l = Math.max(z.x(), -50); l <= Math.min(z.y(), 50); l++) {
                        if (inRange(j, -50, 50) &&
                                inRange(k, -50, 50) &&
                                inRange(l, -50, 50)) {

                            final Point3D e = new Point3D(j, k, l);
                            if (action.equals("on")) {
                                on.add(e);
                            } else {
                                on.remove(e);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(on.size());
    }

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, max), min);
    }

    public static boolean inRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
