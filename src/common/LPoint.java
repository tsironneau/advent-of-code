package common;

public record LPoint(long x, long y) {

    public static LPoint p(long x, long  y) {
        return new LPoint(x, y);
    }

    public static LPoint p(String x, String y) {
        return new LPoint(Long.parseLong(x), Long.parseLong(y));
    }


    public long distanceTo(LPoint p) {
        return Math.abs(p.x - x) + Math.abs(p.y - y);
    }

    public LPoint add(LPoint p) {
        return p(x + p.x, y + p.y);
    }

    public LPoint add(long  x, long  y) {
        return p(this.x + x, this.y + y);
    }


}
