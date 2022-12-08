package y2022.day8;

import common.IPoint;
import common.Point;
import common.parsing.ParsingUtils;

import java.util.*;

public class Day8_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {

        Set<Tree> visibleTrees = new HashSet<>();

        Map<IPoint, Tree> treeMap = ParsingUtils.toPointMapXLinesYColumns(collect, "", (p, s) -> new Tree(p, Integer.parseInt(s)));

        int height = collect.size();
        int length = collect.get(0).length();

        int currentMax;
        for (int i = 0; i < height; i++) {
            currentMax = -1;
            for (int j = 0; j < length; j++) {

                Tree tree = treeMap.get(new Point(i, j));
                if (tree.size > currentMax) {
                    currentMax = tree.size;
                    visibleTrees.add(tree);
                }
            }
        }

        for (int i = 0; i < height; i++) {
            currentMax = -1;
            for (int j = length - 1; j >= 0; j--) {

                Tree tree = treeMap.get(new Point(i, j));
                if (tree.size > currentMax) {
                    currentMax = tree.size;
                    visibleTrees.add(tree);
                }
            }
        }

        for (int j = 0; j < length; j++) {
            currentMax = -1;
            for (int i = 0; i < height; i++) {
                Tree tree = treeMap.get(new Point(i, j));
                if (tree.size > currentMax) {
                    currentMax = tree.size;
                    visibleTrees.add(tree);
                }
            }
        }

        for (int j = 0; j < length; j++) {
            currentMax = -1;
            for (int i = height - 1; i >= 0; i--) {
                Tree tree = treeMap.get(new Point(i, j));
                if (tree.size > currentMax) {
                    currentMax = tree.size;
                    visibleTrees.add(tree);
                }
            }
        }


        return visibleTrees.size();
    }

    record Tree(IPoint pos, int size) implements IPoint {

        @Override
        public int x() {
            return pos().x();
        }

        @Override
        public int y() {
            return pos().y();
        }
    }

}
