package common;

public record Point(int x, int y) implements IPoint {

    public static Point p(int x, int y){
        return new Point(x,y);
    }

    public long distanceTo(Point p){
        return Math.abs(p.x - x) + Math.abs(p.y - y);
    }


}
