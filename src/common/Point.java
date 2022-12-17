package common;

public record Point(int x, int y) implements IPoint {

    public static Point p(int x, int y) {
        return new Point(x, y);
    }

    public static Point p(String x, String y) {
        return new Point(Integer.parseInt(x), Integer.parseInt(y));
    }


    public long distanceTo(Point p) {
        return Math.abs(p.x - x) + Math.abs(p.y - y);
    }

    public Point add(Point p) {
        return p(x + p.x, y + p.y);
    }

    public Point add(int x, int y) {
        return p(this.x + x, this.y + y);
    }


}
