package common.astar;

import common.Point;

public record PointNode(Point point) implements Node<Point> {

    @Override
    public Point getId() {
        return null;
    }
}
