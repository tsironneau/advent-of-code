package common.astar;

public class PathNode<T extends Node<?>> implements Comparable<PathNode<T>> {

    private final T current;
    private T previous;
    private double pathScore;
    private double estimatedScore;

    PathNode(T current) {
        this(current, null, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    PathNode(T current, T previous, double pathScore, double estimatedScore) {
        this.current = current;
        this.previous = previous;
        this.pathScore = pathScore;
        this.estimatedScore = estimatedScore;
    }

    @Override
    public int compareTo(PathNode other) {
        return Double.compare(this.estimatedScore, other.estimatedScore);
    }

    public T getCurrent() {
        return current;
    }

    public T getPrevious() {
        return previous;
    }

    public double getPathScore() {
        return pathScore;
    }

    public double getEstimatedScore() {
        return estimatedScore;
    }

    public void setPrevious(final T previous) {
        this.previous = previous;
    }

    public void setPathScore(final double pathScore) {
        this.pathScore = pathScore;
    }

    public void setEstimatedScore(final double estimatedScore) {
        this.estimatedScore = estimatedScore;
    }
}
