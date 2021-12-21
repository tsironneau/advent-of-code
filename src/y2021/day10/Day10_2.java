package y2021.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static y2021.day10.Input.INPUT;

public class Day10_2 {

    public static final Map<String, String> PAIRS = Map.of(
            "(", ")",
            "[", "]",
            "{", "}",
            "<", ">"
    );

    public static void main(String[] args) {

        final List<String> lines = INPUT.lines().toList();

        List<String> corruptedLines = new ArrayList<>();
        List<List<String>> incomplete = new ArrayList<>();

        for (String line : lines) {

            final String[] chars = line.split("");

            List<String> queue = new ArrayList<>();
            for (String aChar : chars) {
                if (PAIRS.containsKey(aChar)) {
                    queue.add(0, aChar);
                } else if (!aChar.equals(PAIRS.get(queue.remove(0)))) {
                    corruptedLines.add(line);
                    break;//corrupted
                }
            }
        }

        final List<String> incompleteLines = lines.stream().filter(l -> !corruptedLines.contains(l)).toList();
        System.out.println("incomplete size : " +  incompleteLines.size());
        for (String line : incompleteLines) {

            final String[] chars = line.split("");

            List<String> queue = new ArrayList<>();
            for (String aChar : chars) {
                if (PAIRS.containsKey(aChar)) {
                    queue.add(0, aChar);
                } else if (!aChar.equals(PAIRS.get(queue.remove(0)))) {
                    break;//corrupted
                }
            }

            if (!queue.isEmpty()) {
                incomplete.add(queue);
            }
        }

        final List<List<String>> missingChars = incomplete.stream().map(
                l -> l.stream().map(PAIRS::get).toList()
        ).toList();

        List<Long> scores = missingChars.stream().map(Day10_2::computeScore)
                .sorted()
                .toList();

        System.out.println(scores.size());
        final int index = scores.size() / 2;
        System.out.println(index);
        final Long middleScode = scores.get(index);

        System.out.println(middleScode);
    }

    private static Long computeScore(final List<String> chars) {
        long score = 0L;

        for (String aChar : chars) {
            score *= 5;
            score += switch (aChar){
                case ")" -> 1;
                case "]" -> 2;
                case "}" -> 3;
                case ">" -> 4;
                default -> 0;
            };
        }

        return score;
    }
}
