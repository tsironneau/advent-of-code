package y2021.day25;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day25_1 {

    public static void main(String[] args) {


        final List<List<String>> initialState = INPUT.lines()
                .map(s -> s.split(""))
                .map(array -> Arrays.stream(array).toList())
                .toList();

        boolean hasMoved = true;

        int i = 0;
        List<List<String >> currentState = initialState;
        while (hasMoved){

            List<List<String >> previousState = currentState;
            currentState = moveEast(currentState);
            currentState = moveSouth(currentState);

            hasMoved = !previousState.equals(currentState);
            printState(currentState);
            i++;
        }

        System.out.println(i);
    }

    private static void printState(final List<List<String>> currentState) {
        for (List<String> strings : currentState) {
            System.out.println(strings);
        }
    }

    private static List<List<String>> moveSouth(final List<List<String>> currentState) {
        final int lineSize = currentState.size();
        final List<List<String>> newState = new ArrayList<>();
        for (List<String> strings : currentState) {
            newState.add(new ArrayList<>(strings));
        }

        for (int i = 0; i < currentState.size(); i++) {
            List<String> strings = currentState.get(i);
            for (int j = 0; j < strings.size(); j++) {
                String s = strings.get(j);
                if (s.equals("v")) {
                    int newLineIndex = i >= lineSize - 1 ? 0 : i + 1;

                    final List<String> currentDestLine = currentState.get(newLineIndex);
                    final String occupying = currentDestLine.get(j);
                    if (occupying.equals(".")) {
                        newState.get(i).set(j, ".");
                        newState.get(newLineIndex).set(j, "v");
                    }
                }

            }
        }
        return newState;
    }

    private static List<List<String>> moveEast(final List<List<String>> currentState) {
        final List<List<String>> newState = new ArrayList<>();
        for (List<String> line : currentState) {
            final List<String> newLine = new ArrayList<>(line);
            newState.add(newLine);
            for (int j = line.size() - 1; j >= 0; j--) {
                String s = line.get(j);
                if (s.equals(">")) {
                    int newIndex = j >= line.size() - 1 ? 0 : j + 1;

                    final String occupying = line.get(newIndex);
                    if (occupying.equals(".")) {
                        newLine.set(j, ".");
                        newLine.set(newIndex, ">");
                    }
                }
            }

        }
        return newState;
    }

    public static final String INPUT = """
            .vvvv>.vv...v.>...>.vv..>..>.....>.v>>v>>.>.>v.vv...v...vv>v.vv....>.v>.v>.>.>v....>v...v>.>vvv..vv>v>......v....v>.vv.>.vv>..vv.....>.....
            v.vvv>..v.>.vv..v.v......vv...>....>.v>>v...v..>.v......v...v>.>vv.v.vvvv>...v..>>.vv.....>>..>.vv...v...>v>.>v>.v>.>.v>>>v.>....v.vv..>...
            >.>.>vv>v.vv....v.....vvv...v..v>>v.>v.vv>.>>v.>>.vv..vvv>>>..v.>.v>...vvv.v.v.....vvv>..>.v>>.>>>.>>vv>>>.>v....v>vv.>v>v>>v.v>v..>.vvvv>.
            vv>....>vv>>.v...v>..>..>..vv>..>.>vv...>...v.>.v>>.>......vv>v>..v..v>>v...>.>..>...>>v>>v.>>>v>vv...>...>..>...v.v...>.vvv....>.>.>.>>.v.
            v....>>vvv.v..>v....v.>>v.v.>v>vvv..>>v>v.>.>>..>.v..v>.v>>v>v...vv...v..>.v>.>v...vv.v>.vv..>>...v.v>>vv.vv.v>>>..v>....v...>>>.......>>v>
            ..vv.....>....>>v>....>.vv....vv.>>.>v.....v>vv..v.>v.>.v..v>>>vv.>..v.vv.>>>...vv.v..>.vv..vv...>.>..v.v.v>v>>v.vvv>......v.>>>.>>...>.>v.
            v..>.v.v.>v>.v.>..v>v....vv...v....>v.>.vv..>v.>.>v.>.>>.>v.>..v>..>.v.v.v..vv>v>.v>v>.....vv..>.>....>....v..vv.v.>......v..v.>.v>>.v.>vv.
            .v.v..>vvv.v..v>..>.v.v.vv>.>....v>>>>v..v..v..v>.>.vv>...>...>v>>.v>>.v.v........>v...vv.vv.v>>.vvv..>..v...>v.v.v>v.v.v.>.v..v>..v.v.v...
            v.>v.v.>v>...>.>..>vv..>vv.vvvv...>>vv...>....>vv.>>>.>.>>v..>>v>vvvv.v.v.vv.>>v>...>v>.>>....>>v.v..>>>v>.>.>.vv..>....>.>>vvv.v..v.vvv.>.
            v.>v>v..>v...>vv.v.v.v>.v....v>..>.>>>..>>.>....v..>..v.v...vv>.>....>>..v>>.>..>.v>>v....>v....vv.>vv>v>>vv>....v>>>.v>.>v.>vv.>.v...>.v..
            ..>...>.>...v.v....>>v>.v.v.>v.>.vv.>v.v..vv.v....>vv>.>vv...v.v...v.vvv..vv.>..>v.>..v...v.vv>>v>v>.v>.v...v.v...v.v.v.v..>>v...>vvv..>>>.
            >.v>>>.v.>>v.>v>>...>.v.....>.>...v>>v>vv...v.v....>>vv..>>..vvv...v>>.>.vv..v......v.>>v.>>..>.v.>>>>.v...>>.>vv.>.v..v.v>v>..v.vv.v.>v...
            ..v>vv>..>>v....v....>>>..>>>vv...>.>.>v>v>.>.....>........>..>.>....v...>>.v>vv.v..v......v.v....>.v...v....>vv.vv....v.....>...>>>v......
            >....vv>>.>.>..v.vvv...v>>.v>....vv...v..vv>..v........>....>..vv.>>>.>....v...v..>vvv.>vv.....v.vvv.>>v....vv.>v.v.>..v...v.vv>.v.>vvv....
            .>.>....v>.....>..>>>.>.vv>vv..>.>v.vv....vv.>.>v.v..v>..>>v>>>v.>>v>vv.>>>v>v>>v..v...>..v>.>vv.>.>.v.>v>vv.>...v.v.>>v......v.>..>.>v>..>
            >v...v..>..v>v.>.>..v.v>...v>vv.v>v..>.>>......>......vv>.>.v>.v..v.v>.>v>vv>..>.v.>.>..v.v.vvv.v..>.v>>>v.v>....v.v>.v.v...v.vv.>...v..>.v
            .>v.v...>>.>.>vvv..v>.v.....v.>.....v..v.>v....>.v>....v>>vv..>v>....v>.>.....v.....>>...v>..>>....v..v.>.v.>.v..v.>v.>......vv.v..v>...>..
            ..>v>v..vv..v..>v>v..v.v...v..v.>>.v.v.>v..vv>...v..v.v.vv>.>>vv.>v.>v>.v...vv.........v.vv.v...v>v>...>vvv.>.>>v..>v>vv.>vvvv>.>v.>v..>>.v
            >v>...>v.>>>>....>vv>.v>.vv>>.>v.v..>.vv.vv..v.>.....v>v.>.vv..vvv>...vvvv>v..v.vv.v>.vvv.v>v>...v>.v.v>...vv...v.vvv..vv...>..>..>...>....
            >.v.v>..>..v..>.>>vv>>..vvv>.>.>.v.vvv.......>>vvv.vvv.>v>.>.>..v>..>>.....>v.v>>v>.v>.>..>.vv...>>.v.>vv>>v>.>.v>v>vv>.....v....v..>vv...>
            .v..>.v>....>v.>>..v.>.vv......v.>>...v.>..v>>.>.>....>>..>.vv>>.v...v.....v..v.>v.v>v.>>.>.v.v...>v....>v>.>>v.>v>.>.>.v...vv.>v...>......
            v..v.v..>v.>..>.>>...>v>v..>v>..>..v...v>.v.v.>..>.v.>v>.>>>...v.vv.....>>..v.>..v..vv....>.>...>...>>v..>...v.......v>..v.......>v..v>v...
            .v...>v>.>.v>..v...v..>..v>>.>.v>..v..>....>>.v.>.>..v>>>vv>>v>..v..v...>>.>v>vv.>..>v..>>.v.>v..vv>>.v....v>......>v>..vvvv>vvv>vv.v>.v>>>
            ..vv..>..>v...vv.>.v>.v>.>>vv>>.v..v.v.>v.v>..v....>>.vv.>.v>.v>>.v>v...>.>..vv..v>..>....>>..v>v.>......>vv>...>vv>>....>>>>..vv>v.vv....v
            ...v>..>.v.v...>.>.>.v......v..>v..v.v.....>>vvv>v>.>.vv..>vv.>>v.vv..v....>..>>..>.....v....v.>......>.v>.v.....vv>...vv.>....v.>.v>v>v>.>
            .>.>.>...>v.....vv....>v.v>..>.v.>.>>.v>....>...v.....>vv.>vv..v>...>.v.v..>>>.>.vv..vv...>.>v>v.>...>.v.vv.v....v........v..>>v>..v.>.>.>>
            v.v>>...>v.>..v>.>.v..v....vv.>...v>...>>...v.>.......v>.>>.>.v>....v.>>..vv.v>..>v...>vv...vvv.v.v.>v.>>..v>vv..>v.>....v.>>>>v......>v.vv
            .v>>..v>.>.v>>v..>vvv.>.>v.v...>>v.>>>...>vv...>.v>>>.v>.>.v..>v.>.>v..v..>..v.....v>...>>>.>.vvv>>>v>.>>v..v>>>..>....>.vv>..>>v>..>>.v...
            >>>v.vvv.vv.>>>....vv...>>...vv>v>.>>>v.>.>.>...>v>...>.v>......>.>..v.>>v.>v..>v..>v......>.....>.v..v...v..v....>.>...>>.>>v.v>..>.>v..v>
            .v.v....v>.>.>.v>>v......>>v.>.vv>.vvvv.vvvv.v..v..v.>.v.v.>...>...vv>v....vv......v.v>v.>>>>>v>.>>>vv>.v>>..v>.vv>v.>..v>>v.v>>.>..>>.>v..
            .vv.>v>>...>.>>v.v..>v...v.v.>..........v...v....v..vv..>v.v...v>>.>.v>vv>.vv>.v.v>.v.>v>.>>>..>..>...>....v.v.>v.vv.vv.....>.vv>>>.>>..>>.
            .v..>..>..>>vv.v.....>>.>..v.....v..v..>..v>..v...>.....>>>>..v..vv.v...>>>.v.>..>v.>>>..>...>>>>.v.>.>>>v>v.v.>.v.v>.>>v>....v.>>>..v>...>
            >.>.>...>v..>v.v...v>..>>>....>>.v>v>v..>.>>.>v.v.>vv.v..v.v.>v>v......>v>.v>.v.....v>..v...v>v.v>vvv.>.v>....>v.>.>>>.v..>vv>....v..>..>vv
            >.v>>>.vv..v..>.vv>v..>>..>...>.v>.v..>vv>....>vv.>.>vvv.vv.v>.v>>.....>>...>.v>.>.v.v.v.v.....v>>..v..>...vv.>.>.vv..v.>>v.....v>vvv>>.v..
            >>.>v>>..>..v>..>v...>>v..>vv..>..>..>..>>>>>vv.>..v.v.>>v..>...>>..v>>....v...>..v.v.>..v>.v>vv>.v.v.v...>>....v..>>v>.vv..>.>>>.....vv.v.
            ...>>....>v...>v.>v>.>>vv..>>>>.v.v.>....v...>>v>.>...v>.v..........v....v.vvv..v>vv...vv.vv>>..>>....>vv.>.>..vv.v>.>v.>>>>v...v.vv.>..>.v
            .v.>..>.v.>..vv..>v..>>.v>>>>>vv>.v>.>.>.v.vv.v>.v.v.vvvv>......v....v.vvv...>.>.>.>>.>v>..v>vv.>...vv>>>v>vv.v..........>>v...v>.>>>.>.v>.
            .v..vvv.>..>.vvv.>>vv.vv.....>.vv..vvv..vv.v>vv...v.v.>.v>vv.....>vv..>...>.vv.v>v.>.vv.vvv>v..v.v>>>.v.v>>.>>.v.>....>..>v>v.v.v.>>.v>v...
            v>.>.v.>..>.>.>>..>v>.v..>...>..v>>.vv...vv.>v.>..>..>.......>..v>>v.>v>v..>v.>v.vv>.>v.vvv>v.v...>.v.v....>>.vv>v>>v.v>>v>>..>>...>v.....>
            >>>>>.>....>.v>vv..>.>.vv..vv.v.v...>.>>.v..v..>vv>..v..>v.>>.>>..>.vvv.vv>...v.....v..v>.>>v>v....>........v>>vv.>...v.>v.......vv.>>.....
            .vvv.v......>>...>.>..v.>..vv..v>...vv>v>>>.>...>.v..v.v......>...>v......v.vv>...>...>..>.v...v..v>.v.vv>....v......>..v.>.vv....v.v.>>..v
            ...>v...>>..v.v.>........>>vv>>.>v>>>>v..>.v...v..>v....>>.v.vv>v>v...>>.>vvv>v..>...v>vv..>v..>.>.v.v...>.vv.....>v>.v>v>v>..v.>.>..v.>v..
            vvv.>.>v.vv..>>.>v>>..>.v>>.v.>.>>>.>>.v.v.v.>>v.>.>...vv...>....v.>...>.v..>v....v>v..v.>>........v>v.....>..v>v....v.>.>..v.v>v..>...v>.>
            .v.>v..v...>>...>....vv.>>.v...v>v>.>v>>>.vv...v.......>>...vv>.>.v.....>>.v.>...>vv>>>>...vvv.>.....v...>>vv...vv>..v.>v>>....v...v>>>.v.v
            .>..v>vv>.>v.v.>.v>..>v...v>.>v>>v..vv.>.>vvvvvv.v...>...v>..>.>>>.>v..v..>>.vvv.>...v.>...>>..>........>>.v.vv..vv.>>v..vv>......>v>v.....
            ...v>>..>..v..v.v>>.v.vv>..v..v...vv..>..>v..>v..v>..v>>...v>.v.>>..>.>>v>...vv..>>.>.vv.vv.>>..v>.v.>.v...v>vv>.....v>.v>....>>v...v.....>
            ...>>.>.....v..v...>.>>....>>v>.v..>.>v>v...>.>....v.>.>v.v..>v>>vvv..vvv.>>.>.v>v>...v>.v...v.v.>>...v.v>vv>>>>.v>.>>...v.>.>>......>.....
            .v..vv.vv..>....vv...vvvv.v..v..v.vv>.vv>v>vvv.>>v>.>v>....v.>.>>v.....v.>......>...v.>>.>.>..>.>..v.v.v...>v.>.v..>.v.>.vvv.>v>.>..>vvv>v.
            vvv...vv>v>>vvv..v>..>>>>.>>..v>..>.v.>....>vv.>vv.>>.vv.v.>>..>..vvv>...>..vv>>>..>v...>>v...>>.v.>>..vv>>.vv..v...>>>....>v.>.>>>v.>v.>>v
            >>..v..vv>v.>>v.>...>.vvvv..v....v.v.v...>>.>..>v.v>.>>v.....v.v.......>vvvv>.>v>v.v....v.v>.v.v.v>>>v...v...v..>vvv..>.v>>.v.v.>..>>>.>.>.
            ..>>.>>vv.>v>>.>.v..vv>.v.>..v.v>>>>.v.vv..vvv...v.vv>v...>>..v.vv.>>v.v...>.vvv>..>v..>.v....>>v..>>..>.v.>....v>.v...v..v...>.v>>>v>..v.v
            >>.>vv..vvv..v>.>vv...vv.>>>.v>v..>.vv..vvv>.v.v..v.>...v>v.v..v.vvv.>v>....>..>..>.>.v.v..vv..>>>>.vv>>.v>v>v>>.vv>...v.v>>v..>.>>..>..>v>
            >>v..v.vv.......v.>>.>.vvv.v...>v.>v>.>>>..>.>v>.vv>>v..>v..>..>v...v..v..>>>>.>>vv>.>.>..>.>.>..v....v.v.v.>.....>v>...>.v>.>>..>vvv>..v.>
            v.....>vvv..vv.....>v..vv...>.>.>v..v..v>>>v.>.v>.vvvvvv>vvv...>>>....v...>v>v..>..>....v>.>..>v>vvv.>>.v.v>v>.>.vvvvv.>>vvv.>>...>v>.>>..v
            ...>.>.>...v...vv..v>.v>.>>.>v>....v.v..>>v..v.>.v..>v>v>>..>vv>>...vv>.v.>>v>v...v....>>.>.v.>>vv......>v......>>.vv..>.v>>..>..>.>>.v.>>.
            .>.>>>v.v..>.>..>v.>..>v>>>..vv.vv.>v.>.>.v..v>>v.vv.v..>>...>v>.v>v.v>v.>v.v..v.>.>.>v..>.>v.v>.>v>v>.v.....>....v.>.>v..>.>v.vv.>....>>.v
            .>v.>v.>>.>..>>>.>.>.....>.v>...>vvvv.v.>>v>vv..>v.>v>v..v>>.v>v>.>....v..v.vvv.>...vv.vvvv>v..vvv>v.>>>>..v..v.vv>..v.v.v>...>v.....v.>>.>
            >>v.v.>>>>.>>...v..>v.v..v..>>...>........>.>.>.v..v>...v....vv.>..vvv>>.vv.....>vvv>..>....vv.v.v>.v>v..v>.>v..v.>..v.vv>>>>....v.v..>..>.
            >.vvv>vv...>v>v>.v>>>...vv...v...>.....>>...vv.>..v......vv.....v.>...v.>.vvv..>..>>..v...vv...v.>.v>v>v.>..>>.>.>v....>..v.v....>>.v>...v>
            .v..v>.>.>>>..v.>v>..v.v.....>.v..>v>.>vv.>.v>>vv..vv>....v.vv.>..v>v..>..vvv...v...v>>.v..v..>.>>vvv..>vvv..>vv>v>>...v.vv.....>.>...v>..>
            .vvv..vv>..>....vv.>...v>....>..>....>..v>.....v..>.>.vv>.vv.v.>>v>>v..v.v.>.......>>....>v..v>>...>....v>vv..>.v..>....>vvvvvv..v>.vvv.v.v
            v>>v>...>v..>v..v>..vv>v..v>>.vv.>>>v.v....vv>vv>>>>v.v.v....>>.vv.vv>....>.....v...>.v>>...>.>v>.>v>.>.>>..>....>.v>.....v...vv.vv.v>v.>.v
            ..>.v>...v.>v..v.>.>.....vv.v..>>..>.>.v.>.>v......>...>.>.>.vv.vv.vv>vv.v>...>>..>>.>..>>....v.>.v>v.v>.v>>..vv>>...v>>v..v>vv>.>.>>...v>.
            ...vvvv..v.>...vv..v.>.vv>.vv>>.v.>.>.>.v>.vv>>.v>>.>>v..v.v.>vv.v..v.vvvv>..v>..>>>.v.v>..>v...vv.v>v>v..v.v.>v.vv....v.>....vvv..>vvvv>>>
            .v>>.v..>>..vv..>v...v..v>.>.v.>>v>...>>.v.v.v.v..>.vv>>.>>....v.v.>v..v.>..>>..v.>...vv.v....>>>v>>v.....>>>>vv....vv..v>v>vv>..>.vv..vv..
            >>>>.v>..>.v..vv..v.v..>...>..>.>v.>>>.>..>.>.v>.......>..>.>..v>..v.v..>>...vvv..v...>...vv.>v>.>v.>...vv.>..>..>v>.>>.>vv.v..v.v.vv>>v...
            v..>>v.>.>.>>.vv.vv.v.>..>.vv.v...vv.v.>>v..v.v....>v..v..>>...>...v....v>..v..vvv>vv..v..v.v...>.....vv>>v.....>>v..>.>.vv>.>>.>.v..>..vv.
            .v....>..v.v.v>..vv.>...v.v>......v.>..>.v....>..vv.vvvvv>>>.v..vv.v>>..vv.v>.>..v..vv.>......>v.v.....v.>.v..vv...>..>v>vvvv>>.v...v.v....
            ...v.v.>.>....vvv..>vv.>...>>>.v>v.v>>>..>.v>...vvv.>>>>.>>..........v.>......v>...vv...>v....>..>>.v>.>>.v>....>..>..vvv.>.vv..v.>....>..>
            .>.v>.>.v.>....>.>>>>.>..vvvv>>.v..>.>.>.v.>>>.>..v.>v...v....>>v.>..>>v..>>v>.>>.>>.>.....vv>.>>>v..>>>v.>..vv..v.>.vv>v>.vvv...vv.>.v.v..
            ..v>.v..>..>vv...v.v...v...>.......vvvvv>..v.....v...>vv.v...>v.>>v>v>.v>>>.v>.>v>v.>.....v>>vv.>>.>v..>.vv...>.>>..>.v.....v.v.v.vv.v>..>v
            vv>.vvv>.>.v.....v..vv>vv...>v...v.v..>.>v.>.>vv.v>..v>vv.....>.v.>.v....v.......v>>..v>vv.v.v>>.....v>v...>>>..vv>v.v.v.v>.v.>.v..v>...v>.
            ..>v..>..>vv>>.vv.>..>.v...>vv....v>.>........>...>vv.v>>..>vvv.>.v>vv.vv..>.>>>.>v..v>.v.>>...>.>.v>>v....vv>>>....>v.v>v.v....v..v>v>>>>.
            .>..v...v..v>.v.vv..>>......vv>>v....v....>>.vv>v..>.v.v....>>vv.v>v>v>v.>.>v..v..v>vvvv..vv>vvv.>.>..>v>>>..v..>.v>>>....>.>.v.>v>>...vv>v
            v>..>.v..v.........v.>.>>...>>.vv>vv>....>..>.>>v>...>>>vvv>v...v.>vv....v.>v.v..v.v.vvvv.>>>.>vvv.>..vv..v....>..vvv>vv>v.>v>...v.>v.vvv>v
            v..vv>>v...v>.>>...vv.>.>v.v..>...v>>>.>vv.v..v..v....v.v.>>.>>.....v>v..........>>.>vv......>.v.v>v...>>>>>v.>vvv>>.>v.v>v..>.vv>.v>>v.v..
            .>>>>..v.....>.>..>..>.>..v>...>..>.>.vvv.v>>>>>.vv.......>.v>>>v>.....>>>...>v..>.>>..v..>.v...v>...v..vvv.....>..>.>.v.v..v.>....vv.v...v
            .vv>v.v.>.>..>.>.vv>>>vv...vvv.>.>v.>.v...>>>.vv>>>vv>>vv>v>>..vv>..>.....>.>>v...>.>>>v..>...>v..>>v>.v>.>.>.>.v>.v.>..v.vv.vvv.>v.>...v.>
            ...>..v....>v.v>.>vv>.>...>.vvv>...v>.vv.>v..v>.>>>..>.v....>>vv....v...>...>>>>.>.....v>v....>..>v.>v>.v..vv.....>>..v.>.>..>.>.v>>>v.>vv.
            v>v>....>..>>.>........>....v...>....>.vv>.vv.v..v.....>...v.vvv>vvvv.v.>.>.vv..v.>>.>v>.>v.v.v>>.vvvvv>v>v.v.vvvvv>vv.>..v>v.>..vv>.v..vv.
            .>..>>>..v..>>..vv..v.v..>>.v.v>.>.v>vv..>...v.v..v>v..>.v..v...v.v.v.>..>>>>.>vv.vvvvv...vvv....>..v..>...v.>..>.vv>v>>>.v..v>.vvv..>...vv
            .v.....>.v.>.>.v>v..v..v.>..>v>.v.>v..>vvv.vv..>v.vvvv>v..v....v>>>vv>v.v>vv..>....>v.......>vvv.>..>vv.>.>>....>.>.........>v>vv>v>>>>..v.
            >vvv>.vv.v.v.>.v.v..>v.>>..v>.v>v.v.>..>....>..v..v.v>vv..vv>>.v..>....v....v.>>>vv.....v.v.>>v.vv...>..>>>.>>.vv>.>v....>.>.vv...v>v.>v>vv
            >.vvv...>.>....vv.vv..>>vv...v..v>>..>..vv....vv..>v..vvv>vvv.>.>.v.>>>v>..>vv.vv.v.....>v>>v...v.v.v...vv..v>>>v>>v..v>..>>>.>.vv.vv>>..vv
            .>.....v....vvv...>v.>.....v.>.>....v>.>v.v>>>>..>.v.>v..v>vv>..>.vv>>>v>>vv>...>......>v..>.>....>.v.>>.v>....>........>>.....>>>vv.v.v>v.
            ...v>.>>..vv.v........>>>.>.v>v..v...v.v>>...vv...vvv...>vv.>>v.v.>>....>>>..>...v>.>..v.vv.v.v>..v>...v>.vv.>v>.v>..>.>.>>....vv>>>.>v>...
            >v>..v...>v....>.>>>v.v....v.>>.>>..v>.v.>v>.>.v>v.v>.>.....>..vv..v>v.>v.>.v..>>...>.>v.v.>>>.>vv>>.>>.v....v.>>.>.v.vvvv.>>v..>.vv.v>.v>v
            .v.>.vv.>..v.....v>>v.>>>..v.>.>v>...v.>..>>.>v>.v..>v>v....>v.>..v....>v.>.v>...>>>..>>vv>..v>.v..v>v.vv.>.>v..>....v...v.>...v.v...v>v.>>
            v.>vv....>..>v.>vvv..vv.>vv>.>>..v>>>>>v......>>>..v..>v>........v>..>.vvv>>>.vv....v.v....v>.....>..>>vv.>.vv...>.v>......v>>v...v>...v.>.
            v>.>.>..>>.....>..>>...>v.v.>>...>.>v>v>vvv.v.v...>.v>vv...>>...>..vvv.v.>.>.>v>.v>>>>.vv...vv>v.vv.>.>>v>.v>v.......v..>>.v>.v>...>.....>v
            v.....v....v.v.v>v.v>..>>.v..>....vvvv>.v..v>>vvv.>.v>v>>..>.v..>vv>v.>>..v..v>>..v.>v.v...v..v.>..v>..>v..v>v>...>.>v......v.>.v>..vv.v.>>
            >>vv...>v>>>>v..v.v..>.....>..>>>..v>vv.v.vv.v.>...vv>>>v>v...v>.>v..>..v>.v>.vv.v>v.>....>..v.>>..>>>....v>>vv>.>.vv.>>>>>..>.>.vvv..v.>>.
            v..v>..>....vv.>..>>.>>.vv...v.....>.vv>.v.>>.>v.....>.v.v....vv.>vv.v>v..>..>...>>.v.>......>.v.>>>.>>v..v.v...v.v.>>....>v..vv>.>.>.v..vv
            .v>v>v....>v>v>vv>.......v.v>>v..v.....v...>v..>..v>..v>.......>v>.v....v.v.>>.v...>..vv.>v.v......v...v...>...v.v.....v>..>.>.vv>>v......v
            >>..v.....vv>.>>>>....>..v...>v...>.vv>vv......>>.>.>>>vv.v.v>>...v>.>.>>>v>...>..v>vv.v.>>v>....>.v...vvv>v....v>>.>v....vv..v>v.vvv.>.v>v
            >..vv>>v>.>v>..v>....>vv>.....>>........>.....v>.v..>..v...v...>v.>....>vv.v>...vvv>vv.>.>v>..v>.>..>>vv.>...>v...>>vv.>>.vv>..>>...v>.>.v.
            .v.>.......v>.....>vv..>..vv...>>..vv>v.>..v>.>...v..v.v.>..vv.v....>>...v.v>.>vv..>>>.>v>>>.>>v.v.....>..>v>v.>.v.>>...v>.>>>.>...v...>...
            ..>v...>...v.v.>.>>>>.v...>..>v..>.v.v>..v>v>>>.v>.v.vv...>v.>v>vv..v...vv>>.>.>...vv.>v>v>.v..v>.v>v.vvv..>.v...v.....>.>>vv>>>..>....vv.>
            >..v.v>>v......>>>.>.v>.>vv..v>vv...........>>...>...>v..v.v>..>.>.v.>v.v..v>....>v.>...v..v.vv>..>>>....vv>.>>...v...>>.>>.v>>.v.>.v>.....
            .....v....v>>v>..vvv.>v.>..v..>.vv>v>.v.....v.....>>v...>>>.vv.v.>..v>.v.>vv.>v.>.v.v.v.v..>.>>.v.>.v>...>v.vvv.>.>..>.v.>vv..v..>..v.>>..>
            ..vvv>>>.>>..v..>.v>..>>v.>v>.v>vv..>v.v....>.vv...>>.v.>..vvvv...v>.......v>v>..>.>vvv>>>>.>>......v....v......>vv>.vv>.>....>v..>v.>v....
            .>.v.>>v>.>....>>.v.>v..>v....>v.v.v..>.....v>.v..>...>.vv.>.v.vv>v..v..>.v>...>.>..>vvv...>....v...>vvv.>v...v>v>.>...>>..>..v..>.vv.v>v>>
            .v>..vvv.....v...>v>v>..v>.>.....v.>.>v.v>.>.>..>..v....>.>>.>vv.>v.>.vv>v>>v.v>v.v.>v.v......>>.>>vv...v>v>..v.v.....>..>...>.....v.vv.>>>
            >>>..>.vvv>..vv..>>v...>>.v...vv.....>.v...v.v..v.v>>vvvv>vv>..v>....>...v....>.>v.v>>>.>.>>v.v.vv.vv...>v>.v..vvvvvv...>>.>>.vv..vv....vv>
            >.>...>>...>v.>..>>>>...v..>.....v..>>>.v.>..v>v>..v>v>..v..>>..v>>....>...v>vvv>.v>.....vv>..>.>>..>.>..>...v...v>>v.....>>..>>...>......>
            v...>v..>..>.>>..v>.>...v>..>v.vv.>.v.v.vv>>v.>v....v...>vv...vvvv.vv...>>>>vvv..v.>.....v.v.v..>...v>.v>.>>>v..v>>...v..>.>.>.>>..v.vvv...
            .>v..v..v>.v.v...v.>...vv..>..v.v....>.v.>>..>>.vv>>...v.....v>..v.vvv>.v...>v..v..v.>.>>vvv.>...v>.>.>..v.vv>.>..>v.v..vv>v.>..>>..v.>...v
            >>...>.>.....v...v.>.v.....v.>>..vvv.v.>.vv>..v....>....>....v..>>>.v>.>v.>>v...v.>...>...>>>.>>v>...v>..>vv>>.....vv>v..v....>v.>.>>v..v..
            v.>>>.>.v......>v>v.>.....vv.>.>.>>v...v.v..v..vv>..v>>vv..>v.vv>....v>...>>>v>v.>.....v.>vv>v.>....>.>.v..>v>>.>....vvv.v.v.v>.vv>..v.....
            vv>.>.....>.v..>>>...v.>....>vv....>v>>..v.>.>..>.v..>.....>v..v>v..>.vv>.>.>.v.vv>.>>.....vvv>>v.vvv.>v...vv.v>.>.v>>v....v>..v>..v.>vvvv.
            .v.>v.v..>>.v..v>..v..v>.....>.>......>v..v....>.>.>...v>>...>>..v......>v>.>.>>>...v..v>v>.v..>..>v.vv.>.v>...v.>......v>>>....v>>v>>...>.
            .v..v....v>v.v.v.>....v.>v>>.v..v>v>.>v.>vv>..>v>v.>v..>v..v.v>v>v..>>>.>.......v.>...>vv.v>.v>v...>vv>..>..v>..vv>...vv...v>>..v.v.vv>..>.
            .>...vv>.>....v.v.>..>.>>>.v>>.>.>v..v>.vv..v>.v..v.>.>v..>...v..v...v.>v>>.>..>v>v.>v.>.v>v>v>v>...>.v>..vv.v..>.....v>v>v.v.v>>>...v>.>..
            vvv.v.v.>vvvv>.vv.>v.>.>.vv.>..v.vvvv.>..>v>.>v>v.>v.>..v>..>v..>vvv.....vv.v>v.v.>vvv.vv.....v>vv..>.>v.vv.>>>v..vv>.>vv..v>.>v>>.v...vvv>
            v>vv.v.>.>.vv>..>...v....>.>vv..v>vv..v.v.v>v.v.....>.>>..v...>.>>.v..v.>.v>.>.>.v>>...v.>v.v>>......v>v>.>>v....vv.v.v..>v.v>.v...>v.v>>..
            >...v.....v..v>.>..>>.>..>>.v.v..vv>vvv.>v>>v>...>..>....v..vv>.>..>>.v.vvv>...>>.>v..v.....>..v.v>..v>.v....v>......>..v.vv....>.>....>..v
            ..>>v>v>..v>>...>>v.....>.>>.>...>>........v>v..>.>>>..>vvv.v.>..v.>v.v..v>>vv....v>..>...v>>..>..v.v.v......>>..v.v>..v..vvvv.>>..>..>vv..
            ..v.>.>v....>...>..>....>>v..>v..>...>vvv..>v>...>vvv.......>>v....>..vv.v..v.vvv.v......>v.v.>.>.>>v.>.>>>>vvv...>>.>.>.v>vv..>...>.v>v.>.
            >v.>..>>.>v..>>..>.vv.>..v.v.>v>..v..>>...>>v.v...vv>.v..>.vv..>.v>>.>....>....>...>.......>>.vv>v>.v>vv..v....>.vvvv>>..v>..v>>.>>v>>.>v..
            >....v..>....>v...>..>v....>>>.v>.v......>v.v.v...v>vvv>.>vv.v......>...v..v>>.......v>...>>>...v...>...v...vv>>..v>v.vvv.v..>.v.v>vv.>>>>v
            >..vv>.>.>>....vv>..>vvv.vv..>>>v>..>>.....v...v>...v..>.>..>v>.........v>....v....>..>v.v.>>.v.v.>v..>.>v.>...>>..>...>.vvv>..v.>...>v>.v.
            >v>v..v.v>v..>...>>vv.>>.v>..>.>>.v...>vv...vv>>.>>..v>v>>vv.v..>>>.>.v.>.>..v..>>.v>>.>>....>.>>v..>vv.>>>v>>....v..vv.>>>.vvv>>.>v.....>v
            ...v..v...>.>...>.>v....v>...>...>v>v.....v>>>v...v..>v>.>vv>.v...>........>.vv>v..v..v>>v.v.>>v>>..>vv>.>>.vvv.......v.........>..v.>v....
            ...>v.>>.>.vv..>v.vvv.>..v>vv.>.v>v...>>v.v>.>v.>....v.......v>>v>..>v>..>>....>>>v.>.v>...>..v..v.>>v>>.>>.>>.v.>...vv.vv>vv..v...v..vvv..
            .>>.vv.>>..v.>.v>...v>>.>.vv.v>.....v.v.vvvv>..>.>.v.>......>v..>v>.v...>.>>.>....>>>.>......>..>..v>...>.vv..vv.>>..v...>v..vv.>vv>vv..v>>
            ...v.>v>vv>v.>>......v...vvvv....v>.>.>>v>>..>....>.v.....>>...>>vvv>v>v.>.vv.vv>>>......v..v.v.v....v....vv>vv.>>.>.vv>.v..>.v...vvv>..v.>
            .>>..>>..>..vv>>.>..............>v>>.....>vv.>v..>.>v.>>vv.v..>>>v..>>v>v..v>>.v......v>.>>.v.>v>..v.v>..vv...>.>>...vvvv..v..v>>>.......vv
            ...>v..>v>..v>vv...v.>.v>>.vvv>>>.......v.>...v>..>v......>>..>....>vvv...>..v.vv>>v>v.v.>..>>v>v.>.v.v......vv.....>>..v.>>...v....vv.....
            ...v.>v>..v..v...v>.v>>>>>...v>v...v>..vv>>v>v....>vvv...>....>>v..>>v>.vv...v.>v....vv.>...>>...v....vv>>........>..v..>vvv.v....>vv....v.
            >v>>....>v.v...>.>>.v.>.vv>vv....vv>.>.>>.>..>..>v>v..>>>.>.vv...>>.>v.v>>>..v>>>v.>..>..>..v.....v.v...v>.v.>>vv..>>>....>.v..vvv>.vv..v..
            .vv>vvv...>v...>v>v>.>.v>.>.v>.>....vv.>.v..vv..>...>>...vv>v.v.v...>v.v.>..>......>v....v....vv.....>>.>..>.>v.>v..vv>>vvv>..>..>v.v>>..>.
            ...v>>vvv>...>...v.v...>vv.v.v..v>.vv..>>...>>.v.>..vv>.v.v>>..>>..>..>>vv>v.>...>..>.>v.vv.>...v>>..>...v>.>.vvv.v...v..v...>v..v..>>v>..>
            v>.vv>....v>>..v..vv.v.v.v.v.>..vvvv>>>.>v...v>.v..vv>.>>...v>>..>........v.>v>...>.>..>.v....>...>.>.>>...vvvv.>>>..>v.v..>..>v>>>.>.>.vv.
            v.v......>>..v>>.vvv.>v..>.>.v.>...>>v..v.....>..>vv.>.>v.>.>.v.v>>>>>...>>.v.v.>>.v>.>..>v..v>>v..>vv..>..>v>.vvv>.>>...>.>....>>>.......>
            .>...v>...vv.v.v..v>.v>...>>>..v.>.........>.>...>.>>..>v.v.>vv>.vv....>v.vv.v>vv...>>.v>.........>vvv....>>v>>...>>..>>v.>>v>.v>vvvv>>v>.>
            .>>.>..>>v...>.v.v>..vvv..>...v....vvv>..>..v>.>.v>>>v>.>.v.>..v>>...vv..v>v>>.>>.v.......>>>......>..v>...>.v>>>.>.v...v>.v>..>>v..>.>..>v
            >.vv.>......>.>.v>>v..>vv>>>.....v>.v>.>..v.>.v>v.v>..>...>v....v.vv>v...v.......v...v>v>>v.v..>v...v.v.vvv>...v...>v.>.......v..>.v.>vvv>>""";

    public static final String EXAMPLE = """
            v...>>.vv>
            .vv>>.vv..
            >>.>v>...v
            >>v>>.>.v.
            v>v.vv.v..
            >.>>..v...
            .vv..>.>v.
            v.v..>>v.v
            ....v..v.>""";
}
