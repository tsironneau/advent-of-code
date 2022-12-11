package y2022.day10;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Day_10_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;
        int cycle = 0;
        int spritePos = 1;
        int pixelDrawn = 0;
        Set<Integer> cycles = Set.of(40, 80, 120, 160, 200, 240);
        char[] currentLine = new char[40];
        Arrays.fill(currentLine, '.');
        for (String s : collect) {
            if (s.equals("noop")) {
                updatePixel(pixelDrawn, spritePos, currentLine);
                cycle++;//drawPixel on each cycle
                pixelDrawn++;
                if (cycles.contains(cycle)) {
                    pixelDrawn = 0;
                    printLine(currentLine);
                    Arrays.fill(currentLine, '.');
                }
            } else {
                updatePixel(pixelDrawn, spritePos, currentLine);
                cycle++;
                pixelDrawn++;
                if (cycles.contains(cycle)) {
                    pixelDrawn = 0;
                    printLine(currentLine);
                    Arrays.fill(currentLine, '.');
                }

                updatePixel(pixelDrawn, spritePos, currentLine);
                cycle++;
                pixelDrawn++;
                if (cycles.contains(cycle)) {
                    pixelDrawn = 0;
                    printLine(currentLine);
                    Arrays.fill(currentLine, '.');
                }

                int diff = Integer.parseInt(s.split(" ")[1]);
                spritePos += diff;
            }
        }
        return result;
    }

    private static void updatePixel(int pixelDrawn, int spritePos, char[] currentLine) {
        if (pixelDrawn == spritePos - 1 || pixelDrawn == spritePos || pixelDrawn == spritePos + 1)
            currentLine[pixelDrawn] = '#';
    }

    private static void printLine(char[] currentLine) {
        System.out.println(new String(currentLine));
    }
}
