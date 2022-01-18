package y2021.day15;

import common.IPoint;
import common.Point;
import common.PointUtils;
import common.astar.Graph;
import common.astar.Node;
import common.astar.PathFinder;
import common.astar.PointNode;
import common.astar.Scorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static y2021.day15.Input.INPUT;

public class Day15_2 {

    public static void main(String[] args) {

        final List<List<Integer>> map = INPUT.lines().map(l -> l.split(""))
                .map(l -> Arrays.stream(l).map(Integer::parseInt).toList())
                .toList();
        final int factor = 5;

        final int baseXMax = map.size();
        final int baseYMax = map.get(0).size();

        Set<WeightedPoint> points = new HashSet<>();
        List<List<WeightedPoint>> pointsMap = new ArrayList<>();
        for (int i = 0; i < baseXMax * factor; i++) {
            pointsMap.add(new ArrayList<>());
        }

        for (int i = 0; i < map.size(); i++) {
            final List<WeightedPoint> current = pointsMap.get(i);
            List<Integer> line = map.get(i);
            for (int j = 0; j < line.size(); j++) {
                Integer weight = line.get(j);
                final WeightedPoint p = new WeightedPoint(i, j, weight);
                points.add(p);
                current.add(p);
            }
        }

        for (int i = baseXMax; i < baseXMax * factor; i++) {
            final List<WeightedPoint> current = pointsMap.get(i);
            for (int j = 0; j < baseYMax; j++) {
                final Integer baseWeight = pointsMap.get(i - baseXMax).get(j).weight();
                int newWeight = baseWeight + 1 <= 9 ? baseWeight + 1 : 1;
                final WeightedPoint p = new WeightedPoint(i, j, newWeight);
                points.add(p);
                current.add(p);
            }
        }

        for (int i = 0; i < pointsMap.size(); i++) {
            final List<WeightedPoint> weightedPoints = pointsMap.get(i);
            for (int j = baseYMax; j < baseYMax * 5; j++) {
                final Integer baseWeight = pointsMap.get(i).get(j - baseYMax).weight();
                int newWeight = baseWeight + 1 <= 9 ? baseWeight + 1 : 1;
                final WeightedPoint p = new WeightedPoint(i, j, newWeight);
                weightedPoints.add(p);
                points.add(p);
            }
        }

        final int endX = map.size() * factor - 1;
        final int endY = map.get(0).size() * factor - 1;

        final WeightedPoint start = pointsMap.get(0).get(0);
        final WeightedPoint end = pointsMap.get(endX).get(endY);

        System.out.println("start " + start);
        System.out.println("end " + end);

        Map<WeightedPoint, Set<WeightedPoint>> connections = new HashMap<>();
        for (WeightedPoint point : points) {

            final Set<Point> neighbours = PointUtils.computeOrthogonalNeighbours(new Point(point.x(), point.y()),
                                                                                  pointsMap.size() - 1,
                                                                                  pointsMap.get(0).size() - 1);

            final Set<WeightedPoint> collect = neighbours.stream()
                    .map(n -> new WeightedPoint(n.x(), n.y(), pointsMap.get(n.x()).get(n.y()).weight()))
                    .collect(Collectors.toSet());

            connections.put(point, collect);
        }

        final Map<WeightedPoint, WeightedPoint> nodes = points.stream()
                .collect(Collectors.toMap(Function.identity(), Function.identity()));
        final Graph<WeightedPoint, WeightedPoint> graph = new Graph<>(nodes, connections);
        final PathFinder<WeightedPoint, WeightedPoint> routeFinder = new PathFinder<>(graph, new WeightScorer(),
                                                                         new WeightScorer());
        final List<WeightedPoint> route = routeFinder.findRoute(start, end);

        final int sum = route.stream().mapToInt(WeightedPoint::weight).sum();
        System.out.println(sum - start.weight);
    }

    record WeightedPoint(int x, int y, int weight) implements Node<WeightedPoint>, IPoint {

        @Override
        public WeightedPoint getId() {
            return this;
        }
    }

    public static class WeightScorer implements Scorer<WeightedPoint> {

        @Override
        public double computeScore(WeightedPoint from, WeightedPoint to) {

            return to.weight;
        }
    }
}
