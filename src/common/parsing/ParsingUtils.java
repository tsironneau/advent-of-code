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
     * x = lines
     * y = columns
     * 0 → y
     * ↓
     * x
     *
     * @param lines puzzle input lines
     * @param splitBy separator of element of the grid
     * @param supplier new element of the grid
     * @return a map representing the grid with x as line and y as columns
     * @param <T>
     */
    public static <T> Map<IPoint, T> toPointMapXLinesYColumns(List<String> lines, String splitBy, BiFunction<IPoint, String, T> supplier){
        Map<IPoint, T> result = new HashMap<>();

        for (int line = 0; line < lines.size(); line++) {
            String s = lines.get(line);
            String[] split = s.split(splitBy);
            for (int column = 0; column < split.length; column++) {
                String element = split[column];

                Point pos = new Point(line, column);
                result.put(pos, supplier.apply(pos, element));
            }
        }

        return result;
    }

    /**
     * x = columns
     * y = lines
     * 0 → x
     * ↓
     * y
     *
     * @param lines puzzle input lines
     * @param splitBy separator of element of the grid
     * @param supplier new element of the grid
     * @return a map representing the grid with x as columns and y as lines
     * @param <T>
     */
    public static <T> Map<IPoint, T> toPointMapXColumnsYLines(List<String> lines, String splitBy, BiFunction<IPoint, String, T> supplier){
        Map<IPoint, T> result = new HashMap<>();

        for (int line = 0; line < lines.size(); line++) {
            String s = lines.get(line);
            String[] split = s.split(splitBy);
            for (int column = 0; column < split.length; column++) {
                String element = split[column];

                Point pos = new Point(column, line);
                result.put(pos, supplier.apply(pos, element));
            }
        }

        return result;
    }
}
