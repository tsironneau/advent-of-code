package y2022.day8;

import common.IPoint;
import common.Point;
import common.parsing.ParsingUtils;

import java.util.*;

public class Day8_2 {

    //479400
    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result;

        Map<IPoint, Tree> treeMap = ParsingUtils.toPointMapXLinesYColumns(collect, "", (p, s) -> new Tree(p, Integer.parseInt(s)));
        Set<Tree> allTrees = new HashSet<>(treeMap.values());

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

    private static int scenicScore(Tree tree, Map<IPoint, Tree> allTrees, int height, int length) {
        int res = 1;

        IPoint pos = tree.pos;
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
