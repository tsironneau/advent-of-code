package y2021.day14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static y2021.day14.Input.INPUT;

public class Day14_2 {

    public static void main(String[] args) {

        final int count = 40;
        final String[] base = INPUT.split("\n\n");

        final String startingState = base[0];
        final String rawPairMapping = base[1];
        final Map<String, Set<String>> pairsMapping = computePairsMapping(rawPairMapping);

        System.out.println(startingState);
        System.out.println(pairsMapping);

        Map<String, Long> currentPairs = computePairsCounter(startingState);
        System.out.println(currentPairs);
        for (int i = 0; i < count; i++) {
            currentPairs = splitPairs(currentPairs, pairsMapping);
        }
        System.out.println(currentPairs);

        Map<Character, Long> atomsCounter = computeAtomsCounter(currentPairs, startingState);
        System.out.println(atomsCounter);
        final long max = atomsCounter.values().stream().max(Comparator.naturalOrder()).get();
        final long min = atomsCounter.values().stream().min(Comparator.naturalOrder()).get();
        System.out.println(max - min);
    }

    private static Map<String, Set<String>> computePairsMapping(final String rawPairMapping) {
        return rawPairMapping.lines()
                .flatMap(l -> Arrays.stream(l.split("\n")))
                .map(l -> l.split(" -> "))
                .collect(Collectors.toMap(op -> op[0],
                                          op -> Set.of(
                                                  op[0].charAt(0) + op[1],
                                                  op[1] + op[0].charAt(1)))
                );
    }

    private static Map<Character, Long> computeAtomsCounter(
            final Map<String, Long> currentPairs,
            final String startingState) {
        final Map<Character, Long> result = new HashMap<>();
        result.put(startingState.charAt(0), 1L);
        result.put(startingState.charAt(startingState.length() - 1), 1L);
        currentPairs.forEach((k, v) -> {
            final char a = k.charAt(0);
            final char b = k.charAt(1);
            result.compute(a, (k2, v2) -> v2 == null ? v : v2 + v);
            result.compute(b, (k2, v2) -> v2 == null ? v : v2 + v);
        });
        result.replaceAll((k, v) -> v / 2);
        return result;
    }

    private static Map<String, Long> computePairsCounter(final String start) {

        final Map<String, Long> result = new HashMap<>();

        for (int i = 0; i < start.length() - 1; i++) {
            result.compute(start.substring(i, i + 2), (k, v) -> v == null ? 1 : v + 1);
        }
        return result;
    }

    private static Map<String, Long> splitPairs(final Map<String, Long> currentPairs,
                                                final Map<String, Set<String>> pairsMapping) {
        Map<String, Long> result = new HashMap<>();

        currentPairs.forEach((k, v) -> {
            final Set<String> newPairs = pairsMapping.get(k);
            if (newPairs != null) {
                newPairs.forEach(pair -> result.compute(pair, (k2, v2) -> v2 == null ? v : v2 + v));
            } else {
                System.out.println("Unknown pair " + newPairs);
            }
        });
        return result;
    }
}
