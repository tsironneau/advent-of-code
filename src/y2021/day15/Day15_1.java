package y2021.day15;

import common.Point;
import common.PointUtils;
import common.astar.Graph;
import common.astar.Node;
import common.astar.PathFinder;
import common.astar.Scorer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.function.Function.*;
import static y2021.day15.Input.INPUT;

public class Day15_1 {

    public static void main(String[] args) {

        final List<List<Integer>> map = INPUT.lines().map(l -> l.split(""))
                .map(l -> Arrays.stream(l).map(Integer::parseInt).toList())
                .toList();

        Set<WeightedPoint> points = new HashSet<>();
        for (int i = 0; i < map.size(); i++) {
            List<Integer> line = map.get(i);
            for (int j = 0; j < line.size(); j++) {
                Integer weight = line.get(j);
                points.add(new WeightedPoint(i, j, weight));
            }
        }

        System.out.println(points);

        final WeightedPoint start = new WeightedPoint(0, 0, map.get(0).get(0));
        final int endX = map.size() - 1;
        final int endY = map.get(0).size() - 1;
        final WeightedPoint end = new WeightedPoint(endX, endY, map.get(endX).get(endY));

        System.out.println("start " + start);
        System.out.println("end " + end);

        Map<WeightedPoint, Set<WeightedPoint>> connections = new HashMap<>();
        for (WeightedPoint point : points) {

            final Set<Point> neighbours = PointUtils.computeOrthogonalNeighbours(new Point(point.x(), point.y()),
                                                                                  map.size() - 1,
                                                                                  map.get(0).size() - 1);

            final Set<WeightedPoint> collect = neighbours.stream()
                    .map(n -> new WeightedPoint(n.x(), n.y(), map.get(n.x()).get(n.y())))
                    .collect(Collectors.toSet());

            connections.put(point, collect);
        }

        System.out.println(connections);

        final Map<WeightedPoint, WeightedPoint> nodes = points.stream()
                .collect(Collectors.toMap(identity(), identity()));
        final Graph<WeightedPoint, WeightedPoint> graph = new Graph<>(nodes, connections);
        final PathFinder<WeightedPoint, WeightedPoint> routeFinder = new PathFinder<>(graph, new WeightScorer(),
                                                                        new WeightScorer());
        final List<WeightedPoint> route = routeFinder.findRoute(start, end);

        final int sum = route.stream().mapToInt(WeightedPoint::weight).sum();
        System.out.println(sum - start.weight);
    }

    record WeightedPoint(int x, int y, int weight) implements Node<WeightedPoint> {

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
