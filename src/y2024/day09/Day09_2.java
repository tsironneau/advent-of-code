package y2024.day09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day09_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        long result = puzzle(collect);

        System.out.println(result);
    }

    private static long puzzle(List<String> collect) {
        long result = 0;

        final var line = collect.getFirst();

        List<File> system = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            system.add(new File(
                    i / 2,
                    Integer.parseInt(line.charAt(i) + ""),
                    i % 2 == 1));
        }

        for (int i = system.size() - 1; i >= 0; i--) {
            File file = system.get(i);
            if(file.isSpace)
                continue;
            int firstSpace = findSpace(system, file.size, i);
            if (firstSpace > 0) {
                final var space = system.get(firstSpace);
                int remainingSpace = space.size - file.size;
                system.set(i, new File(file.index, file.size, true));
                if (remainingSpace > 0){
                    system.set(firstSpace, new File(0, remainingSpace, true));
                    system.add(firstSpace, file.copy());
                }else{
                    system.set(firstSpace, file.copy());
                }
            }
        }

        long currentIndex = 0;
        for (File file : system) {
            if (file.isSpace) {
                currentIndex += file.size;
                continue;
            }
            for (int i = 0; i < file.size; i++) {
                result += currentIndex * file.index;
                currentIndex++;
            }
        }
        return result;
    }

    private static int findSpace(List<File> system, int size, int untilIndex) {
        for (int i = 0; i < untilIndex; i++) {
            File file = system.get(i);
            if (file.isSpace && file.size >= size)
                return i;
        }
        return -1;
    }

    record File(long index, int size, boolean isSpace){
        public File copy() {
            return new File(index, size, isSpace);
        }
    }
}
