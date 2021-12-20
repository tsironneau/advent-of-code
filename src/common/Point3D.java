package common;

import java.util.function.Function;

public record Point3D(int x, int y, int z) {

    public static Point3D of(int x, int y, int z){
        return new Point3D(x, y, z);
    }

    public Point3D minus(Point3D p){
        return new Point3D(x - p.x, y - p.y, z - p.z);
    }

    public Point3D plus(Point3D p){
        return new Point3D(x + p.x, y + p.y, z + p.z);
    }

    public Point3D mult(Point3D p){
        return new Point3D(x * p.x, y * p.y, z * p.z);
    }

    public Point3D apply(Function<Point3D, Point3D> func){
        return func.apply(this);
    }

    public long distanceTo(final Point3D p2) {
        return Math.abs(x - p2.x) + Math.abs(y - p2.y) + Math.abs(z - p2.z);
    }
}
