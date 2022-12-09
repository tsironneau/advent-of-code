package common;

import java.util.HashSet;
import java.util.Set;

public class PointUtils {

    public static Set<Point> computeNeighbours(final int x, final int y, final int xMax, final int yMax) {
        final int iMin = x - 1;
        final int iMax = Math.min(xMax, x + 1);
        final int jMin = y - 1;
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

    public static Set<Point> computeNeighbours(final int x, final int y) {
        return computeNeighbours(x,y, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static Set<Point> computeNeighbours(final IPoint p, int xMax, int yMax) {
        return computeNeighbours(p.x(), p.y(), xMax, yMax);
    }

    public static Set<Point> computeOrthogonalNeighbours(final IPoint point, final int xMax, final int yMax) {
        final Set<Point> result = new HashSet<>();

        final int x = point.x();
        final int y = point.y();
        if (x > 0) {
            result.add(new Point(x - 1, y));
        }
        if (x < xMax) {
            result.add(new Point(x + 1, y));
        }
        if (y > 0) {
            result.add(new Point(x, y - 1));
        }
        if (y < yMax) {
            result.add(new Point(x, y + 1));
        }

        return result;
    }
    public static Set<Point> computeOrthogonalNeighbours(final IPoint point) {
        return computeOrthogonalNeighbours(point, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
