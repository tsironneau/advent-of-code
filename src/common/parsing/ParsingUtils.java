package common.parsing;

import common.IPoint;
import common.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class ParsingUtils {

    //↑↑←→↓

    /**
     * x = lines<br>
     * y = columns<br>
     * 0 → y<br>
     * ↓<br>
     * x<br>
     *
     * @param lines puzzle input lines
     * @param splitBy separator of element of the grid
     * @param supplier new element of the grid
     * @return a map representing the grid with x as line and y as columns
     * @param <T> type of the element of the grid
     */
    public static <T> Map<IPoint, T> toPointMapXLinesYColumns(List<String> lines, String splitBy, BiFunction<IPoint, String, T> supplier){
        return toPointMap(lines, splitBy, supplier, Point::new);
    }

    /**
     * x = columns<br>
     * y = lines<br>
     * 0 → x<br>
     * ↓<br>
     * y<br>
     *
     * @param lines puzzle input lines
     * @param splitBy separator of element of the grid
     * @param supplier new element of the grid
     * @return a map representing the grid with x as columns and y as lines
     * @param <T> type of the element of the grid
     */
    public static <T> Map<IPoint, T> toPointMapXColumnsYLines(List<String> lines, String splitBy, BiFunction<IPoint, String, T> supplier){
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
}
