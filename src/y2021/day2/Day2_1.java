package y2021.day2;

import java.util.Arrays;
import java.util.List;

public class Day2_1 {

    public static void main(String[] args) {

        final String[] lines = Input.INPUT.split("\n");

        final List<String[]> commands = Arrays.stream(lines)
                .map(l -> l.split(" "))
                .toList();

        int horizontal = 0;
        int depth = 0;

        for (String[] command : commands) {
            final int value = Integer.parseInt(command[1]);
            switch (command[0]) {
                case "forward" -> horizontal += value;
                case "up" -> depth -= value;
                case "down" -> depth += value;
            }
        }

        System.out.println(horizontal);
        System.out.println(depth);
        System.out.println(horizontal * depth);
    }
}
