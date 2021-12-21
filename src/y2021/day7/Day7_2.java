package y2021.day7;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static y2021.day7.Input.INPUT;

public class Day7_2 extends Day7_1{

    public  static void main(String[] args) {
        new Day7_2().execute();
    }

    int computeCost(final Integer pos, final int minPos) {
        return IntStream.range(0, Math.abs(pos - minPos) + 1).sum();
    }
}
