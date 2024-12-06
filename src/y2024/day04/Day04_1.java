package y2024.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        long result = puzzle(collect);

        System.out.println(result);
    }

    private static long puzzle(List<String> collect) {
        long result = 0;
        final var array = collect.stream()
                                 .map(line -> Arrays.stream(line.split("")).toList())
                                 .toList();

        for (int i = 0; i < array.size(); i++) {
            List<String> line = array.get(i);
            for (int j = 0; j < line.size(); j++) {
                List<String> words = findWords(i, j, array);
                final var count = words.stream().filter(
                        word -> word.equals("XMAS") || word.equals("SAMX")
                ).count();
                result += count;
            }
        }

        return result;
    }

    private static List<String> findWords(int i, int j, List<List<String>> array) {
        String vertical = "";
        String horizontal = "";
        String downleft = "";
        String downright = "";
        for (int k = 0; k < 4; k++) {
            try {
                horizontal += array.get(i).get(j+k);
            } catch (Exception e) {
            }
            try {
                vertical += array.get(i + k).get(j);
            } catch (Exception e) {
            }
            try {
                downleft += array.get(i + k).get(j - k);
            } catch (Exception e) {
            }
            try {
                downright += array.get(i + k).get(j + k);
            } catch (Exception e) {
            }
        }
        return List.of(vertical, horizontal, downleft, downright);
    }
}