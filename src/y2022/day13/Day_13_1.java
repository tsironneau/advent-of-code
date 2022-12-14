package y2022.day13;

import java.util.*;

import static common.parsing.ParsingUtils.*;

public class Day_13_1 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        int result = puzzle(collect);

        System.out.println(result);
    }

    private static int puzzle(List<String> collect) {
        int result = 0;

        for (int i = 0; i < collect.size(); i += 3) {

            Iterable<?> left = readAsIntegerList(collect.get(i));
            Iterable<?> right = readAsIntegerList(collect.get(i + 1));

            if (compare(left, right) == 1)
                result += ((i / 3) + 1);
        }

        return result;
    }

    static int compare(Iterable<?> left, Iterable<?> right) {

        Iterator<?> leftIt = left.iterator();

        for (Object currentRight : right) {
            if (!leftIt.hasNext())
                return 1;//left no more item -> right order
            Object currentLeft = leftIt.next();

            if (currentLeft instanceof Integer ileft && currentRight instanceof Integer iright) {

                if (ileft < iright)
                    return 1;//right order
                if (ileft > iright)
                    return -1;//not right order
                //continue;
            }

            if (currentLeft instanceof List<?> leftL && currentRight instanceof List<?> rightL) {
                int inRightOrder = compare(leftL, rightL);
                if (inRightOrder != 0)
                    return inRightOrder;
                continue;
            }

            if (currentLeft instanceof List<?> leftL && currentRight instanceof Integer iright) {
                int inRightOrder = compare(leftL, List.of(iright));
                if (inRightOrder != 0)
                    return inRightOrder;
                continue;
            }

            if (currentLeft instanceof Integer ileft && currentRight instanceof List<?> rightL) {
                int inRightOrder = compare(List.of(ileft), rightL);
                if (inRightOrder != 0)
                    return inRightOrder;
            }
        }

        if (leftIt.hasNext())
            return -1;//left still has items -> not the right order;
        return 0;//same length but no decisions
    }

}
