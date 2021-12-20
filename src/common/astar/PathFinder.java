package common.astar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class PathFinder<I, T extends Node<I>> {

    private final Graph<I, T> graph;
    private final Scorer<T> nextNodeScorer;
    private final Scorer<T> targetScorer;

    public PathFinder(final Graph<I, T> graph, final Scorer<T> nextNodeScorer,
                      final Scorer<T> targetScorer) {
        this.graph = graph;
        this.nextNodeScorer = nextNodeScorer;
        this.targetScorer = targetScorer;
    }

    public List<T> findRoute(T from, T to) {

        Queue<PathNode<T>> openSet = new PriorityQueue<>();
        Map<T, PathNode<T>> allNodes = new HashMap<>();

        PathNode<T> start = new PathNode<>(from, null, 0d, targetScorer.computeScore(from, to));
        openSet.add(start);
        allNodes.put(from, start);

        while (!openSet.isEmpty()) {
            PathNode<T> next = openSet.poll();
            if (next.getCurrent().equals(to)) {
                List<T> route = new ArrayList<>();
                PathNode<T> current = next;
                do {
                    route.add(0, current.getCurrent());
                    current = allNodes.get(current.getPrevious());
                } while (current != null);
                return route;
            }
            graph.getLinks(next.getCurrent()).forEach(connection -> {
                PathNode<T> nextNode = allNodes.getOrDefault(connection, new PathNode<>(connection));
                allNodes.put(connection, nextNode);

                double newScore = next.getPathScore() + nextNodeScorer.computeScore(next.getCurrent(), connection);
                if (nextNode.getPathScore() <= newScore) {
                    return;
                }
                nextNode.setPrevious(next.getCurrent());
                nextNode.setPathScore(newScore);
                nextNode.setEstimatedScore(newScore + targetScorer.computeScore(connection, to));
                openSet.add(nextNode);
            });
        }
        throw new IllegalStateException("No path found");
    }
}
