package y2021.day2;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;

import static y2021.day2.Input.INPUT;

public class Day2_2 {

    public static void main(String[] args) {

        final String[] lines = INPUT.split("\n");

        final long[] result = new long[3];

        Map<String, Consumer<Integer>> actions = Map.of(
                "forward", value -> {
                    result[0] = result[0] + value;
                    result[1] = result[1] + (result[2] * value);
                },
                "up", value -> result[2] = result[2] - value,
                "down", value -> result[2] = result[2] + value
        );
        Arrays.stream(lines)
                .map(l -> l.split(" "))
                .forEach(command -> actions.get(command[0]).accept(Integer.parseInt(command[1])));

        System.out.println(result[0] * result[1]);
    }
}
