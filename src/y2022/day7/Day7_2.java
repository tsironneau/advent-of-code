package y2022.day7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day7_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        final List<Dir> dirs = new ArrayList<>();

        Dir root = new Dir("root", null);
        dirs.add(root);
        Dir currentDir = root;
        for (String s : collect) {
            //Command mode
            if (s.startsWith("$")) {
                //ls
                //cd ..
                if (s.startsWith("$ cd")) {
                    String arg = s.split(" ")[2];
                    if (arg.equals(".."))
                        currentDir = currentDir.parent;
                    else
                        currentDir = currentDir.findChild(arg);
                }

                continue;
            }

            if (s.startsWith("dir")) {
                String dirName = s.split(" ")[1];
                if (!currentDir.hasChild(dirName)) {
                    Dir dir = new Dir(dirName, currentDir);
                    currentDir.subDir.add(dir);
                    dirs.add(dir);
                }
                continue;
            }

            //Last possible case start with size
            String[] s1 = s.split(" ");
            int size = Integer.parseInt(s1[0]);
            String name = s1[1];

            currentDir.files.add(new File(name, size));
        }

        int size = root.size();
        int freeSpace = 70000000 - size;
        int neededSpace = 30000000 - freeSpace;

        return dirs.stream().filter(d -> d.size() >= neededSpace)
                .min(Comparator.comparingInt(Dir::size))
                .orElseThrow()
                .size();
    }

    private static class Dir {

        private final String name;
        private final Dir parent;
        private final List<Dir> subDir = new ArrayList<>();
        private final List<File> files = new ArrayList<>();

        public Dir(String name, Dir parent) {
            this.name = name;
            this.parent = parent;
        }

        private int size() {
            return files.stream().mapToInt(File::size).sum() + subDir.stream().mapToInt(Dir::size).sum();
        }

        public Dir findChild(String name) {
            return subDir.stream()
                    .filter(d -> d.name.equals(name))
                    .findFirst()
                    .orElseThrow();
        }

        public boolean hasChild(String name) {
            return subDir.stream()
                    .anyMatch(d -> d.name.equals(name));
        }

        @Override
        public String toString() {
            return "Dir{" +
                    "name='" + name + '\'' +
                    ", parent=" + parent +
                    '}';
        }
    }

    private record File(String name, int size) {
    }

}
