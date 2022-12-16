package y2022.day16;

import common.tuple.Tuple2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day_16_1 {

    private static Map<String, Valve> VALVE_MAP = new HashMap<>();
    private static Map<State, Long> CACHE = new HashMap<State, Long>();

    //DONT WORK
    public static void main(String[] args) {
        final String[] lines = Example.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int steps = 30;
        System.out.println(steps);
        long result = puzzle(collect, steps);
        System.out.println(result);

        System.out.println(max);
    }

    private static long puzzle(List<String> collect, int steps) {
        int result = 0;
        List<Valve> valves = readValves(collect);
        VALVE_MAP = valves.stream().collect(Collectors.toMap(Valve::name, Function.identity()));
        System.out.println(valves);
        System.out.println(VALVE_MAP);
        Valve start = valves.stream().filter(valve -> valve.name.equals("AA")).findFirst().orElseThrow();
        Map<Integer, Valve> activatedValves = new HashMap<>();


        return nextStep(0, steps, start, activatedValves);
    }

    private static long max = Long.MIN_VALUE;

    private static long nextStep(long releasedPressure, int step, Valve currentValve, Map<Integer, Valve> activatedValves) {
        if (step == 0) {
            max = Math.max(max, releasedPressure);
            return releasedPressure;
        }

        State key = new State(currentValve, activatedValves, step);
       if (CACHE.containsKey(key)) {
           Long cached = CACHE.get(key);
           return cached;
       }

        int newStep = step - 1;
        if (!activatedValves.containsValue(currentValve) && currentValve.flow > 0) {
            HashMap<Integer, Valve> updatedActivatedValves = new HashMap<>(activatedValves);
            updatedActivatedValves.put(newStep, currentValve);

            nextStep(releasedPressure + newStep * currentValve.flow, newStep, currentValve, updatedActivatedValves);
        }
        //nextStep(releasedPressure, newStep, currentValve, activatedValves);

        Set<String> neighbours = currentValve.neighbours;
        long maxResult = releasedPressure;
        for (String neighbour : neighbours) {
            long i = nextStep(releasedPressure, newStep, VALVE_MAP.get(neighbour), activatedValves);
            maxResult = Math.max(
                    maxResult,
                    i
            );

        }
        CACHE.put(key, maxResult);
        return maxResult;
    }


    private static List<Valve> readValves(List<String> collect) {
        List<Valve> res = new ArrayList<>();
        List<String[]> valve = collect.stream().map(s ->
                s.replace("Valve ", "")
                 .replace("has flow rate=", "")
                 .replace(" tunnels lead to valves ", "")
                 .replace(" tunnel leads to valves ", "")
                 .replace(" tunnel leads to valve ", "")
                 .split(";")
        ).toList();

        for (String[] strings : valve) {
            res.add(readValve(strings));
        }
        return res;
    }

    private static Valve readValve(String[] strings) {
        String[] ids = strings[0].split(" ");
        Set<String> neighbours = Arrays.stream(strings[1].split(", ")).collect(Collectors.toSet());


        return new Valve(ids[0], Integer.parseInt(ids[1]), neighbours);
    }

    record Valve(String name, long flow, Set<String> neighbours) {
    }

    record State(Valve visitedValve, Map<Integer, Valve> visitedValves, int step) {
    }


    public record State2(Map<String, Long> open, Valve valve, long totalFlow) {}


}
