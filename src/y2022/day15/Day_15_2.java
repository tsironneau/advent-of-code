package y2022.day15;

import common.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static common.Point.p;

public class Day_15_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        long result = puzzle(collect);

        System.out.println(result);
    }

    private static long puzzle(List<String> collect) {
        List<String[]> entries = collect.stream()
                                        .map(s -> s.replace("Sensor at ", ""))
                                        .map(s -> s.replace(" closest beacon is at ", ""))
                                        .map(s -> s.split(":"))
                                        .toList();

        Set<Sensor> sensors = new HashSet<>();
        Set<Point> beacons = new HashSet<>();

        for (String[] entry : entries) {
            String[] sPos = entry[0]
                    .replace("x=", "")
                    .replace("y=", "")
                    .split(", ");
            String[] bPos = entry[1]
                    .replace("x=", "")
                    .replace("y=", "")
                    .split(", ");
            Sensor sensor = new Sensor(p(sPos[0], sPos[1]), p(bPos[0], bPos[1]));
            sensors.add(sensor);
            beacons.add(p(sensor.nearestBeacon.x(), sensor.nearestBeacon.y()));
        }

        Set<Point> result = new HashSet<>();
        System.out.println(sensors);

        Point res = new Point(0, 0);

        for (Sensor sensor : sensors) {
            int d = (int) sensor.d();
            for (int dx = 0; dx <= d + 1; dx++) {
                int dy = d + 1 - dx;

                res = p(sensor.x() + dx, sensor.y() + dy);
                if (!isInRange(res)) continue;
                if (!isCovered(res, sensors)){
                    long l = res.x() * 4000000L + res.y();
                    System.out.println(res);
                    System.out.println(l);
                    return l;
                }

                res = p(sensor.x() - dx, sensor.y() + dy);
                if (!isInRange(res)) continue;
                if (!isCovered(res, sensors)){
                    long l = res.x() * 4000000L + res.y();
                    System.out.println(res);
                    System.out.println(l);
                    return l;
                }

                res = p(sensor.x() + dx, sensor.y() - dy);
                if (!isInRange(res)) continue;
                if (!isCovered(res, sensors)){
                    long l = res.x() * 4000000L + res.y();
                    System.out.println(res);
                    System.out.println(l);
                    return l;
                }

                res = p(sensor.x() - dx, sensor.y() - dy);
                if (!isInRange(res)) continue;
                if (!isCovered(res, sensors)){
                    long l = res.x() * 4000000L + res.y();
                    System.out.println(res);
                    System.out.println(l);
                    return l;
                }

            }
        }
        long l = res.x() * 4000000L + res.y();
        return l;
    }

    private static boolean isInRange(Point neighbour) {
        int nX = neighbour.x();
        int nY = neighbour.y();
        if (nY < 0 || nY > 4_000_000)
            return false;
        if (nX < 0 || nX > 4_000_000)
            return false;
        return true;
    }

    private static boolean isCovered(Point neighbour, Set<Sensor> sensors) {
        for (Sensor sensor : sensors) {
            long distanceTo = neighbour.distanceTo(sensor.sensorPos);
            long d = sensor.d();
            if (distanceTo <= d)
                return true;
        }
        return false;
    }

    record Sensor(Point sensorPos, Point nearestBeacon) {

        long d() {
            return sensorPos.distanceTo(nearestBeacon);
        }

        public int x() {
            return sensorPos.x();
        }

        public int y() {
            return sensorPos.y();
        }
    }
}
