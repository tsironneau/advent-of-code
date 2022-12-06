package y2022.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day5_2 {

    public static void main(String[] args) {
        final String[] lines = Input.MOVES.split("\n");

        final List<String> moves = Arrays.stream(lines)
                .toList();

        String[] cratesL = Input.CRATES.split("\n");

        final List<String> crates = Arrays.stream(cratesL)
                .toList();

        List<String> cratesList = readCrates(new ArrayList<>(crates));
        for (String column : cratesList) {
            System.out.println(column);
        }

        String result = puzzle(cratesList, moves);

        System.out.println(result);
    }

    private static List<String> readCrates(List<String> crates) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            result.add("");
        }
        Collections.reverse(crates);
        for (int i = 0; i < crates.size(); i++) {
            String s = crates.get(i);
            String[] split = s.split(" ");
            for (int j = 0; j < split.length; j++) {
                String column = result.get(j);
                String s1 = split[j].substring(1, 2);
                if (!s1.equals(".")) {
                    column += s1;
                    result.set(j, column);
                }
            }
        }
        return result;
    }

    private static String puzzle(List<String> cratesList, List<String> moves) {

        for (String rawMove : moves) {
            String[] s = rawMove.split(" ");
            System.out.println(Arrays.toString(s));
            int count = Integer.parseInt(s[1]);
            int from = Integer.parseInt(s[3]) - 1;
            int to = Integer.parseInt(s[5]) - 1;

            String columnFrom = cratesList.get(from);
            String columnTo = cratesList.get(to);

            String toMove = columnFrom.substring(columnFrom.length() - count);
            columnTo += toMove;

            String newFrom = columnFrom.substring(0, columnFrom.length() - count);
            cratesList.set(from, newFrom);
            cratesList.set(to, columnTo);
        }

        String result = "";

        for (String s : cratesList) {
            result += s.charAt(s.length() - 1);
        }

        return result;
    }

}
