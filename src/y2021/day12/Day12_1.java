package y2021.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Day12_1 {

    private static final String START = "start";
    private static final String END = "end";
    private Set<Link> links;
    private List<List<String>> paths;
    Set<String> smalls;

    public static void main(String[] args) {

        new Day12_1().execute();
    }

    void execute() {
        links = Input.INPUT.lines()
                .map(l -> l.split("-"))
                .map(l -> new Link(l[0], l[1]))
                .collect(Collectors.toSet());

        smalls = Input.INPUT.lines()
                .flatMap(l -> Arrays.stream(l.split("-")))
                .filter(l -> l.toLowerCase().equals(l))
                .collect(Collectors.toSet());

        paths = new ArrayList<>();

        computePath(START, new ArrayList<>());

        System.out.println(paths.size());
    }

    private Set<Link> linksWith(final String node) {
        return links.stream().filter(l -> l.contains(node)).collect(Collectors.toSet());
    }

    void computePath(String node, List<String> path) {

        final List<String> copyPath = new ArrayList<>(path);

        if (END.equals(node)) {
            copyPath.add(node);
            paths.add(copyPath);
            return;//path end, ending point
        }

        if (invalid(node, path)) {
            return;//path invalid small already in
        }

        copyPath.add(node);
        final Set<Link> links = linksWith(node);
        for (Link link : links) {
            final String other = link.getOther(node);

            computePath(other, copyPath);
        }
    }

    boolean invalid(final String node, final List<String> copyPath) {
        return smalls.contains(node) && copyPath.contains(node);
    }

    record Link(String first, String second) {

        boolean contains(String point) {
            return first.equals(point) || second.equals(point);
        }

        String getOther(String point) {
            if (first.equals(point)) {
                return second;
            }
            if (second.equals(point)) {
                return first;
            }

            throw new IllegalArgumentException();
        }
    }
}
