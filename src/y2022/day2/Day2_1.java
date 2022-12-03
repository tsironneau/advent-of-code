package y2022.day2;

import java.util.Arrays;
import java.util.List;

public class Day2_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {

        int score = 0;
        for (int i = 0; i < collect.size(); i++) {
            String s = collect.get(i);

            String[] game = s.split(" ");
            int evaluate = evaluate(game[0], game[1]);
            score += evaluate;
        }
        return score;
    }

    private static int evaluate(String p1, String p2) {
        //A Rock, B Paper, C Scissors
        //X Rock, Y Paper, Z Scissors
        //Rock 1, Paper 2, Scissors 3

        return scoreGame(p1, p2) + scoreType(p2);
    }

    private static int scoreGame(String p1, String p2) {
        if (p1.equals("A")) {
            if (p2.equals("Y"))
                return 6;
            else if (p2.equals("X"))
                return 3;
            else
                return 0;
        } else if (p1.equals("B")) {
            if (p2.equals("Z"))
                return 6;
            else if (p2.equals("Y"))
                return 3;
            else {
                return 0;
            }
        } else if (p1.equals("C")) {
            if (p2.equals("X"))
                return 6;
            if (p2.equals("Z"))
                return 3;
            else
                return 0;
        }
        throw new IllegalArgumentException();
    }

    private static int scoreType(String p2) {
        switch (p2) {
            case "X" -> {
                return 1;
            }
            case "Y" -> {
                return 2;
            }
            case "Z" -> {
                return 3;
            }
        }
        ;
        throw new IllegalArgumentException(p2);
    }
}
