package y2021.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Day10_1 {

    public static final Map<String, String> PAIRS = Map.of(
            "(", ")",
            "[", "]",
            "{", "}",
            "<", ">"
    );

    public static void main(String[] args) {

        final List<String> lines = Input.INPUT.lines().toList();
        System.out.println(lines.size());

        List<String> corruptedChars = new ArrayList<>();

        for (String line : lines) {

            final String[] chars = line.split("");

            List<String> queue = new ArrayList<>();
            queue.add(chars[0]);
            for (String aChar : chars) {
                if (PAIRS.containsKey(aChar)) {
                    queue.add(0, aChar);
                } else if (!aChar.equals(PAIRS.get(queue.remove(0)))) {
                    corruptedChars.add(aChar);
                    break;
                }
            }
        }

        System.out.println(corruptedChars.size());
        final int sum = corruptedChars.stream().mapToInt(c -> switch (c) {
            case ")" -> 3;
            case "]" -> 57;
            case "}" -> 1197;
            case ">" -> 25137;
            default -> 0;
        }).sum();

        System.out.println(sum);
    }
}
