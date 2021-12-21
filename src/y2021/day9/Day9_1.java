package y2021.day9;

import java.util.List;

public class Day9_1 {

    public static void main(String[] args) {

        final List<String[]> lines = Input.INPUT.lines().map(line -> line.split("")).toList();

        int result = 0;
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            final String[] line = lines.get(i);

            for (int j = 0; j < line.length; j++) {
                final String height = line[j];

                if (isLowest(i, j, lines)) {
                    result += Integer.parseInt(height) + 1;
                    count++;
                }

            }
        }

        System.out.println(count);
        System.out.println(result);
    }

    private static boolean isLowest(final int i, final int j, final List<String[]> lines) {
        final int value = Integer.parseInt(lines.get(i)[j]);

        if(i > 0){
            final int south = Integer.parseInt(lines.get(i - 1)[j]);
            if (south <= value) {
                return false;
            }
        }

        if (i < lines.size() -1){
            final int north = Integer.parseInt(lines.get(i + 1)[j]);
            if (north <= value) {
                return false;
            }
        }

        if(j > 0){
            final int west = Integer.parseInt(lines.get(i)[j - 1]);
            if (west <= value) {
                return false;
            }
        }

        if (j < lines.get(i).length - 1){
            final int east = Integer.parseInt(lines.get(i)[j + 1]);
            return east > value;
        }

        return true;
    }
}
