package y2022.day15;

import common.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static common.Point.p;

public class Day_15_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
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

        for (Sensor sensor : sensors) {
            long d = sensor.d();
            Point sensorPos = sensor.sensorPos;
            for (long i = -d; i <= d; i++) {
                Point testedPos = p(sensor.sensorPos.x() + (int) i, 2_000_000);
                //Point testedPos = p(sensor.sensorPos.x() + (int) i, 10);
                long l = sensorPos.distanceTo(testedPos);
                if (l <= d)
                    result.add(testedPos);
            }
        }

        result.removeAll(beacons);
        result.removeAll(sensors.stream().map(Sensor::sensorPos).collect(Collectors.toSet()));
        return result.size();
    }

    record Sensor(Point sensorPos, Point nearestBeacon) {

        long d() {
            return sensorPos.distanceTo(nearestBeacon);
        }
    }
}
