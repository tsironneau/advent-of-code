package y2021.day19;

import common.Point3D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import static y2021.day19.Input.INPUT;

public class Day19_2 {

    private static final Map<Integer, List<Point3D>> SCANNERS_MAP = new HashMap<>();
    private static final Map<Integer, Set<Point3D>> ALL_SCANNERS_MAP = new HashMap<>();
    private static final List<Overlapping> OVERLAPPINGS = new ArrayList<>();
    public static final Set<Point3D> BEACONS = new HashSet<>();

    public static final List<Point3D> TRANSFORMATIONS = List.of(
            Point3D.of(1, 1, 1),
            Point3D.of(1, 1, -1),
            Point3D.of(1, -1, 1),
            Point3D.of(1, -1, -1),
            Point3D.of(-1, 1, 1),
            Point3D.of(-1, 1, -1),
            Point3D.of(-1, -1, 1),
            Point3D.of(-1, -1, -1)
    );

    public static final List<AxeSwitch> AXES_INVERSIONS = List.of(
            new AxeSwitch(p -> Point3D.of(p.x(), p.y(), p.z()), p -> Point3D.of(p.x(), p.y(), p.z())),
            new AxeSwitch(p -> Point3D.of(p.x(), p.z(), p.y()), p -> Point3D.of(p.x(), p.z(), p.y())),
            new AxeSwitch(p -> Point3D.of(p.y(), p.x(), p.z()), p -> Point3D.of(p.y(), p.x(), p.z())),
            new AxeSwitch(p -> Point3D.of(p.y(), p.z(), p.x()), p -> Point3D.of(p.z(), p.x(), p.y())),
            new AxeSwitch(p -> Point3D.of(p.z(), p.y(), p.x()), p -> Point3D.of(p.z(), p.y(), p.x())),
            new AxeSwitch(p -> Point3D.of(p.z(), p.x(), p.y()), p -> Point3D.of(p.y(), p.z(), p.x()))
    );

    public static void main(String[] args) {

        final List<String> lines = INPUT.lines().toList();
        buildScannersMap(lines);

        System.out.println(SCANNERS_MAP);

        final List<Integer> scanners = List.copyOf(SCANNERS_MAP.keySet());
        for (int i = 0; i < scanners.size(); i++) {
            Integer scanner_1 = scanners.get(i);
            for (int j = 0; j < scanners.size(); j++) {
                Integer scanner_2 = scanners.get(j);
                final AxeModification axeModification = overlap(SCANNERS_MAP.get(scanner_1),
                                                                SCANNERS_MAP.get(scanner_2));
                if (axeModification != null) {
                    final Overlapping overlap = new Overlapping(scanner_1, scanner_2, axeModification);
                    OVERLAPPINGS.add(overlap);
                }
            }
        }

        ALL_SCANNERS_MAP.replaceAll((k,v) -> new HashSet<>());
        for (int i = 0; i < ALL_SCANNERS_MAP.size(); i++) {
            for (Overlapping overlapping : OVERLAPPINGS) {
                final Integer from = overlapping.from;
                final Integer to = overlapping.to;

                final Set<Point3D> toComplete = ALL_SCANNERS_MAP.get(from);
                toComplete.add(overlapping.axeModification.relativePosition);
                final List<Point3D> transformed = ALL_SCANNERS_MAP.get(to).stream()
                        .map(overlapping.axeModification.inversion.apply)
                        .map(overlapping.axeModification.rotation::mult)
                        .map(overlapping.axeModification.relativePosition::plus)
                        .toList();
                toComplete.addAll(transformed);
            }
        }

        long max = 0;
        final Set<Point3D> set = ALL_SCANNERS_MAP.get(0);
        for (Point3D p1 : set) {
            for (Point3D p2 : set) {
                if (p1.distanceTo(p2) >= max) {
                    max = Math.max(max, p1.distanceTo(p2));
                    System.out.printf("p1 : %s, p2, %s\n", p1, p2);
                }
            }
        }

        System.out.println(max);
    }

    private static List<Point3D> transformBeacons(final List<Point3D> beacons, final Point3D referential) {
        return beacons.stream().map(b -> b.minus(referential)).toList();
    }

    private static List<Point3D> rotate(final List<Point3D> beacons, final Point3D rotation) {
        return beacons.stream().map(b -> b.mult(rotation)).toList();
    }

    private static List<Point3D> inverse(final List<Point3D> beacons, final Inversion inversion) {
        return beacons.stream().map(inversion).toList();
    }

    private static AxeModification overlap(final List<Point3D> beacons_1, final List<Point3D> beacons_2) {
        for (Point3D ref_1 : beacons_1) {
            final List<Point3D> newBeacons_1 = transformBeacons(beacons_1, ref_1);
            for (AxeSwitch inversion : AXES_INVERSIONS) {
                final List<Point3D> inverse_2 = inverse(beacons_2, inversion.apply);
                for (Point3D transformation : TRANSFORMATIONS) {
                    final List<Point3D> rotated_2 = rotate(inverse_2, transformation);
                    for (Point3D ref_2 : rotated_2) {
                        final List<Point3D> newBeacons_2 = transformBeacons(rotated_2, ref_2);
                        final List<Point3D> commonPoints = newBeacons_2.stream().filter(newBeacons_1::contains)
                                .toList();

                        final Point3D commonPoint = commonPoints.get(0);
                        final Point3D commonPoint_1 = commonPoint.plus(ref_1);
                        final Point3D commonPoint_2 = commonPoint.plus(ref_2);

                        final Point3D relativePosition = commonPoint_1.minus(commonPoint_2);
                        if (commonPoints.size() >= 12) {
                            return new AxeModification(transformation, inversion, relativePosition, ref_2);
                        }
                    }
                }
            }
        }
        return null;
    }

    record Overlapping(Integer from, Integer to, AxeModification axeModification) {
    }

    record AxeModification(Point3D rotation, AxeSwitch inversion, Point3D relativePosition, Point3D ref_2) {
    }

    interface Inversion extends Function<Point3D, Point3D> {
    }

    record AxeSwitch(Inversion apply, Inversion rollback) {

    }


    private static void buildScannersMap(final List<String> lines) {
        List<Point3D> current = null;
        Set<Point3D> forAll = null;
        for (String line : lines) {
            if (line.contains("scanner")) {
                current = new ArrayList<>();
                forAll = new HashSet<>();
                final Integer scannerId = Integer.valueOf(line.split(" ")[2]);
                SCANNERS_MAP.put(scannerId, current);
                ALL_SCANNERS_MAP.put(scannerId, forAll);
            } else if (line.isEmpty()) {
                current = null;
                forAll = null;
            } else if (line.contains(",")) {
                final String[] array = line.split(",");
                final int x = Integer.parseInt(array[0]);
                final int y = Integer.parseInt(array[1]);
                final int z = Integer.parseInt(array[2]);
                current.add(new Point3D(x, y, z));
                forAll.add(new Point3D(x, y, z));
            }
        }
    }
}
