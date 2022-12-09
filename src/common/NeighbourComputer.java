package common;

import java.util.HashSet;
import java.util.Set;


public class NeighbourComputer {

    private int xMin = Integer.MIN_VALUE;
    private int xMax = Integer.MAX_VALUE;
    private int yMin = Integer.MIN_VALUE;
    private int yMax = Integer.MAX_VALUE;

    private boolean onlyOrthogonal;

    public Set<Point> compute(IPoint p){
        return compute(p.x(), p.y());
    }

    public Set<Point> compute(int x, int y) {
        if(onlyOrthogonal)
            return computeOrthogonalNeighbours(x, y);
        return computeNeighbours(x, y);
    }

    private Set<Point> computeNeighbours(final int x, final int y) {
        final int iMin = Math.max(xMin, x - 1);
        final int iMax = Math.min(xMax, x + 1);
        final int jMin = Math.max(yMin, y - 1);
        final int jMax = Math.min(yMax, y + 1);

        final Set<Point> result = new HashSet<>();
        for (int i = iMin; i <= iMax; i++) {
            for (int j = jMin; j <= jMax; j++) {
                if (i != x || j != y) {
                    result.add(new Point(i, j));
                }
            }
        }
        return result;
    }

    private Set<Point> computeOrthogonalNeighbours(int x, int y) {
        final Set<Point> result = new HashSet<>();
        if (x > xMin) {
            result.add(new Point(x - 1, y));
        }
        if (x < xMax) {
            result.add(new Point(x + 1, y));
        }
        if (y > yMin) {
            result.add(new Point(x, y - 1));
        }
        if (y < yMax) {
            result.add(new Point(x, y + 1));
        }

        return result;
    }

    public NeighbourComputer withXMin(int xMin) {
        this.xMin = xMin;
        return this;
    }

    public NeighbourComputer withXMax(int xMax) {
        this.xMax = xMax;
        return this;
    }

    public NeighbourComputer withYMin(int yMin) {
        this.yMin = yMin;
        return this;
    }

    public NeighbourComputer withYMax(int yMax) {
        this.yMax = yMax;
        return this;
    }

    public NeighbourComputer withOnlyOrthogonal(boolean onlyOrthogonal) {
        this.onlyOrthogonal = onlyOrthogonal;
        return this;
    }
}
