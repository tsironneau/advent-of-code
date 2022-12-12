package y2022.day11;

import java.util.*;
import java.util.function.Function;

public class Day_11_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        long result = puzzle(collect);

        System.out.println(result);
    }

    private static long puzzle(List<String> collect) {
        return resolve(getMonkeys(), 20, l -> l / 3);
    }

    static Long resolve(List<Monkey> monkeys, int count, Function<Long, Long> worryAdjust) {
        for (int i = 0; i < count; i++) {
            for (Monkey monkey : monkeys) {
                Iterator<Long> it = monkey.items.iterator();
                while (it.hasNext()) {
                    Long next = it.next();

                    monkey.incInspections();
                    //updateLevel
                    Long worry = monkey.update.apply(next);
                    // /3 rounded down
                    worry = worryAdjust.apply(worry);
                    Integer newMonkey = monkey.evaluation.apply(worry);
                    monkeys.get(newMonkey).items.add(worry);

                    it.remove();
                }
            }
        }

        return monkeys.stream()
                      .map(m -> m.inspections)
                      .sorted(Comparator.reverseOrder())
                      .limit(2)
                      .reduce(1L, (a, b) -> a * b);
    }

    static List<Monkey> getMonkeys() {
        return List.of(
                new Monkey(new ArrayList<>(List.of(83, 62, 93)), x -> x * 17, x -> (x % 2 == 0) ? 1 : 6),
                new Monkey(new ArrayList<>(List.of(90, 55)), x -> x + 1, x -> (x % 17 == 0) ? 6 : 3),
                new Monkey(new ArrayList<>(List.of(91, 78, 80, 97, 79, 88)), x -> x + 3, x -> (x % 19 == 0) ? 7 : 5),
                new Monkey(new ArrayList<>(List.of(64, 80, 83, 89, 59)), x -> x + 5, x -> (x % 3 == 0) ? 7 : 2),
                new Monkey(new ArrayList<>(List.of(98, 92, 99, 51)), x -> x * x, x -> (x % 5 == 0) ? 0 : 1),
                new Monkey(new ArrayList<>(List.of(68, 57, 95, 85, 98, 75, 98, 75)), x -> x + 2, x -> (x % 13 == 0) ? 4 : 0),
                new Monkey(new ArrayList<>(List.of(74)), x -> x + 4, x -> (x % 7 == 0) ? 3 : 2),
                new Monkey(new ArrayList<>(List.of(68, 64, 60, 68, 87, 80, 82)), x -> x * 19, x -> (x % 11 == 0) ? 4 : 5)
        );
    }

    static List<Monkey> getExampleMonkeys() {

        return List.of(
                new Monkey(new ArrayList<>(List.of(79, 98)), x -> x * 19, x -> (x % 23 == 0) ? 2 : 3),//0
                new Monkey(new ArrayList<>(List.of(54, 65, 75, 74)), x -> x + 6, x -> (x % 19 == 0) ? 2 : 0),
                new Monkey(new ArrayList<>(List.of(79, 60, 97)), x -> x * x, x -> (x % 13 == 0) ? 1 : 3),
                new Monkey(new ArrayList<>(List.of(74)), x -> x + 3, x -> (x % 17 == 0) ? 0 : 1)
        );

    }
}
