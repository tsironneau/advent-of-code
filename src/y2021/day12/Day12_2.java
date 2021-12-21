package y2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class Day12_2 extends Day12_1 {

    public static final String START = "start";
    public static final String END = "end";

    public static void main(String[] args) {

        new Day12_2().execute();
    }

    boolean invalid(final String node, final List<String> currentPath) {
        if (!smalls.contains(node)) {
            return false;
        }

        if (START.equals(node) || END.equals(node)) {
            return currentPath.contains(node);
        }

        if (!currentPath.contains(node)) {
            return false;
        }

        final ArrayList<String> copyPath = new ArrayList<>(currentPath);
        copyPath.add(node);

        final Map<String, List<String>> collect = copyPath.stream()
                .filter(s -> smalls.contains(s))
                .collect(Collectors.groupingBy(Function.identity()));
        final long smallMoreThanOnceCount = collect
                .values()
                .stream()
                .filter(g -> g.size() > 1)
                .count();


        return smallMoreThanOnceCount > 1 || collect.get(node).size() > 2;
    }
}
