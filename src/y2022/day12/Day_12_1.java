package y2022.day12;

import common.IPoint;
import common.NeighbourComputer;
import common.Point;
import common.astar.Graph;
import common.astar.Node;
import common.astar.PathFinder;
import common.astar.Scorer;
import common.parsing.ParsingUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;

public class Day_12_1 {

    public static void main(String[] args) {
        final String[] lines = Example.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect, lines);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect, String[] lines) {
        final IPoint[] coord = new Point[2];

        Map<IPoint, WeightedPoint> points = ParsingUtils.toPointMapXColumnsYLines(collect,
                "",
                (p, s) -> {
                    char charAt = s.charAt(0);
                    int weight = charAt;
                    if (charAt == 'S'){
                        coord[0] = p;
                        weight = 'a';
                    }
                    if (charAt == 'E'){
                        coord[1] = p;
                        weight = 'z';
                    }
                    return new WeightedPoint(p.x(), p.y(), weight);
                });

        //points.values().stream().mapToInt(WeightedPoint::weight).max().ifPresent(System.out::println);
        IPoint start = coord[0];//p(0, 20);
        IPoint end = coord[1];//p(158, 20);

        Collection<WeightedPoint> values = points.values();
        NeighbourComputer neighbourComputer = new NeighbourComputer().withOnlyOrthogonal(true)
                                                                     .withXMin(0)
                                                                     .withYMin(0)
                                                                     .withXMax(lines[0].length() - 1)
                                                                     .withYMax(lines.length - 1);
        Map<WeightedPoint, Set<WeightedPoint>> connections = new HashMap<>();
        for (WeightedPoint p : values) {
            Set<Point> neighbours = neighbourComputer.compute(p);
            Set<WeightedPoint> links = neighbours.stream()
                                                 .map(points::get)
                                                 .filter(n -> n.weight <= p.weight + 1)
                                                 .collect(Collectors.toSet());
            connections.put(p, links);
        }

        final Map<WeightedPoint, WeightedPoint> nodes =
                points.values().stream()
                      .collect(Collectors.toMap(identity(), identity()));

        WeightedPoint from = points.get(start);

        final Graph<WeightedPoint, WeightedPoint> graph = new Graph<>(nodes, connections);
        final PathFinder<WeightedPoint, WeightedPoint> routeFinder = new PathFinder<>(graph, new WeightScorer(),
                new WeightScorer());
        final List<WeightedPoint> route = routeFinder.findRoute(from, points.get(end));


        return route.size() - 1;
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
