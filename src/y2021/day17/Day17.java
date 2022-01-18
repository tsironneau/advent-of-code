package y2021.day17;

import common.Point;

public class Day17 {

    public static void main(String[] args) {

        final String[] rawCoords = Input.INPUT.split(": ")[1].split(", ");

        final Coord x = computeCoord(rawCoords[0]);
        final Coord y = computeCoord(rawCoords[1]);

        System.out.println(x);
        System.out.println(y);

        Point currentPos = new Point(0, 0);
        int xMinimumVelocity = findXMinimumVelocity(x.min());
        int xMaximumVelocity = x.max();

        int maxYVelocity = Integer.MIN_VALUE;
        int validInitialVelocityCount = 0;
        for (int i = xMinimumVelocity; i <= xMaximumVelocity; i++) {
            for (int j = y.min(); j < -y.min(); j++) {
                if (reachTarget(x, y, i, j, currentPos)) {
                    if (j > maxYVelocity) {
                        maxYVelocity = j;
                    }
                    validInitialVelocityCount++;
                }
            }
        }

        final int highestY = sumTo(maxYVelocity);
        System.out.println("firstPart answer : " + highestY);
        System.out.println("secondPart answer : " + validInitialVelocityCount);
    }

    private static boolean reachTarget(final Coord x, final Coord y, int xVelocity, int yVelocity,
                                       final Point startingPos) {

        Point currentPosition = startingPos;
        while (currentPosition.y() >= y.min) {
            if (isInTarget(currentPosition, x, y)) {
                return true;
            }

            currentPosition = updatePosition(currentPosition, xVelocity, yVelocity);
            xVelocity = xVelocity == 0 ? 0 : xVelocity > 0 ? xVelocity - 1 : xVelocity + 1;
            yVelocity -= 1;
        }
        return false;
    }

    private static boolean isInTarget(final Point currentPosition, final Coord x, final Coord y) {
        final int x1 = currentPosition.x();
        final int y1 = currentPosition.y();
        return x.min() <= x1 && x.max() >= x1 && y.min() <= y1 && y.max() >= y1;
    }

    private static int findXMinimumVelocity(final int min) {
        int res = 0;
        while (sumTo(res) < min) {
            res++;
        }
        return res;
    }

    private static int sumTo(int x) {
        return x * (x + 1) / 2;
    }

    private static Point updatePosition(final Point currentPos, final int xVelocity, final int yVelocity) {
        return new Point(currentPos.x() + xVelocity, currentPos.y() + yVelocity);
    }

    record Coord(int min, int max) {
    }


    private static Coord computeCoord(final String rawCoords) {
        final String[] xCoords = rawCoords.split("=")[1].split("\\.\\.");
        return new Coord(Integer.parseInt(xCoords[0]), Integer.parseInt(xCoords[1]));
    }
}
