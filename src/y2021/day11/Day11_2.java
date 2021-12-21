package y2021.day11;

import common.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static y2021.day11.Input.INPUT;

public class Day11_2 extends Day11_1{

    public static void main(String[] args) {

        final List<List<Integer>> lines = INPUT.lines()
                .map(l -> Arrays.stream(l.split("")).map(Integer::parseInt).collect(Collectors.toList()))
                .toList();

        Set<Point> flashes = new HashSet<>();
        final int totalSize = lines.size() * lines.get(0).size();

        int i = 0;
        while (flashes.size() < totalSize){
            i++;
            flashes.clear();

            computeNextStep(lines);
            computeFlashes(lines, flashes);
            resetFlashed(lines, flashes);
        }

        System.out.println(i);

    }
}
