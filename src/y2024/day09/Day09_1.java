package y2024.day09;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Day09_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        long result = puzzle(collect);

        System.out.println(result);
    }

    private static long puzzle(List<String> collect) {
        long result = 0;

        final var line = collect.get(0);

        long currentIndex = 0;
        int indexFromEnd = line.length() - 1;
        Deque<Long> toMoveForward = new LinkedList<>();
        for (int i = 0; i < line.length(); i++) {
            boolean blank = i % 2 == 1;
            final var currentInt = Long.parseLong(line.charAt(i) + "");
            if (!blank) {
                for (int j = 0; j < currentInt; j++) {
                    result += currentIndex * i / 2;
                    currentIndex++;
                }
            } else {
                while (toMoveForward.size() < currentInt && indexFromEnd > i) {
                    final var intFromEnd = Long.parseLong(line.charAt(indexFromEnd) + "");
                    for (int j = 0; j < intFromEnd; j++) {
                        toMoveForward.add((long) indexFromEnd / 2);
                    }
                    indexFromEnd -= 2;
                }
                for (int j = 0; j < currentInt && !toMoveForward.isEmpty(); j++) {
                    final var pop = toMoveForward.pop();
                    result += currentIndex * pop;
                    currentIndex++;
                }
            }
            if (indexFromEnd <= i){
                while (!toMoveForward.isEmpty()){
                    final var pop = toMoveForward.pop();
                    result += currentIndex * pop;
                    currentIndex++;
                }
                break;
            }
        }

        return result;
    }
}
