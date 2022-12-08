package y2022.day8;

import common.IPoint;
import common.Point;

import java.util.*;

public class Day8_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;

        Set<Tree> allTrees = new HashSet<>();
        Map<Point, Tree> treeMap = new HashMap<>();

        for (int i = 0; i < collect.size(); i++) {
            String s = collect.get(i);
            String[] split = s.split("");
            for (int j = 0; j < split.length; j++) {
                String s1 = split[j];
                int size = Integer.parseInt(s1);

                Tree tree = new Tree(new Point(i, j), size);
                allTrees.add(tree);
                treeMap.put(tree.pos, tree);
            }
        }

        int height = collect.size();
        int length = collect.get(0).length();

        int max = 0;
        for (Tree tree : allTrees) {
            result = scenicScore(tree, treeMap, height, length);
            if (result > max)
                max = result;
        }

        return max;
    }

    private static int scenicScore(Tree tree, Map<Point, Tree> allTrees, int height, int length) {
        int res = 1;

        Point pos = tree.pos;
        int count = 0;
        for (int i = pos.x() + 1; i < height; i++) {
            Tree otherTree = allTrees.get(new Point(i, tree.y()));
            if (tree.size > otherTree.size)
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;

        count = 0;
        for (int i = pos.x() - 1; i >= 0; i--) {
            Tree otherTree = allTrees.get(new Point(i, tree.y()));
            if (tree.size > otherTree.size)
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;

        count = 0;
        for (int i = pos.y() + 1; i < length; i++) {
            Tree otherTree = allTrees.get(new Point(tree.x(), i));
            if (tree.size > otherTree.size)
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;

        count = 0;
        for (int i = pos.y() - 1; i >= 0; i--) {
            Tree otherTree = allTrees.get(new Point(tree.x(), i));
            if (tree.size > otherTree.size)
                count++;
            else {
                count++;
                break;
            }
        }
        res *= count;
        return res;
    }

    record Tree(Point pos, int size) implements IPoint {

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
