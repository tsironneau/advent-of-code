package common;

public record Point(int x, int y) implements IPoint {

    public static Point p(int x, int y){
        return new Point(x,y);
    }

}
