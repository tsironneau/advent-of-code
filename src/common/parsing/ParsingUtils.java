package common.parsing;

import common.IPoint;
import common.Point;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ParsingUtils {

    //↑↑←→↓

    /**
     * x = lines<br>
     * y = columns<br>
     * 0 → y<br>
     * ↓<br>
     * x<br>
     *
     * @param lines    puzzle input lines
     * @param splitBy  separator of element of the grid
     * @param supplier new element of the grid
     * @param <T>      type of the element of the grid
     * @return a map representing the grid with x as line and y as columns
     */
    public static <T> Map<IPoint, T> toPointMapXLinesYColumns(List<String> lines, String splitBy, BiFunction<IPoint, String, T> supplier) {
        return toPointMap(lines, splitBy, supplier, Point::new);
    }

    /**
     * x = columns<br>
     * y = lines<br>
     * 0 → x<br>
     * ↓<br>
     * y<br>
     *
     * @param lines    puzzle input lines
     * @param splitBy  separator of element of the grid
     * @param supplier new element of the grid
     * @param <T>      type of the element of the grid
     * @return a map representing the grid with x as columns and y as lines
     */
    public static <T> Map<IPoint, T> toPointMapXColumnsYLines(List<String> lines, String splitBy, BiFunction<IPoint, String, T> supplier) {
        return toPointMap(lines, splitBy, supplier, (line, column) -> new Point(column, line));
    }

    private static <T> Map<IPoint, T> toPointMap(List<String> lines, String splitBy,
                                                 BiFunction<IPoint, String, T> supplier,
                                                 BiFunction<Integer, Integer, IPoint> iPointSupplier) {
        Map<IPoint, T> result = new HashMap<>();

        for (int line = 0; line < lines.size(); line++) {
            String s = lines.get(line);
            String[] elements = s.split(splitBy);
            for (int column = 0; column < elements.length; column++) {
                String element = elements[column];

                IPoint pos = iPointSupplier.apply(line, column);
                result.put(pos, supplier.apply(pos, element));
            }
        }

        return result;
    }

    public static <T> List<?> readAsList(String line, String start, String end, String delimiter, Function<String, T> converter) {

        List<?> root = new ArrayList<>();
        List current = root;
        Stack<List<?>> parent = new Stack<>();
        parent.add(root);
        for (int i = 1; i < line.length(); ) {
            String next = readNext(line, i);
            i += next.length();
            if (next.equals(start)) {
                List<?> newList = new ArrayList<>();
                current.add(newList);
                parent.push(current);
                current = newList;
            } else if (next.equals(end)) {
                current = parent.pop();
            } else if (next.equals(delimiter)) {
                continue;
            } else {
                current.add(converter.apply(next));

            }
        }
        return root;
    }

    public static <T> List<?> readAsList(String line, Function<String, T> converter) {
        return readAsList(line, "[", "]", ",", converter);
    }

    public static List<?> readAsIntegerList(String line) {
        return readAsList(line, "[", "]", ",", Integer::parseInt);
    }


    private static String readNext(String s, int i) {
        if (s.charAt(i) == ']' || s.charAt(i) == '[' || s.charAt(i) == ',')
            return String.valueOf(s.charAt(i));
        int nextOpenBr = s.indexOf('[', i);
        int nextCloseBr = s.indexOf(']', i);
        int nextComma = s.indexOf(',', i);

        int nextToken = s.length() - 1;
        if (nextOpenBr > 0)
            nextToken = Math.min(nextToken, nextOpenBr);
        if (nextCloseBr > 0)
            nextToken = Math.min(nextToken, nextCloseBr);
        if (nextComma > 0)
            nextToken = Math.min(nextToken, nextComma);

        return s.substring(i, nextToken);
    }

}
