package y2022.day13;

import java.util.*;

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

            Iterable<?> left = readStruct(collect.get(i));
            Iterable<?> right = readStruct(collect.get(i + 1));

            if (isInRightOrder(left, right) == 1)
                result += ((i / 3) + 1);
        }

        return result;
    }

    private static int isInRightOrder(Iterable<?> left, Iterable<?> right) {

        Iterator<?> leftIt = left.iterator();
        Iterator<?> rightIt = right.iterator();

        while (rightIt.hasNext()) {
            Object currentRight = rightIt.next();

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
                int inRightOrder = isInRightOrder(leftL, rightL);
                if (inRightOrder != 0)
                    return inRightOrder;
                continue;
            }

            if (currentLeft instanceof List<?> leftL && currentRight instanceof Integer iright) {
                int inRightOrder = isInRightOrder(leftL, List.of(iright));
                if (inRightOrder != 0)
                    return inRightOrder;
                continue;
            }

            if (currentLeft instanceof Integer ileft && currentRight instanceof List<?> rightL) {
                int inRightOrder = isInRightOrder(List.of(ileft), rightL);
                if (inRightOrder != 0)
                    return inRightOrder;
            }
        }

        if (leftIt.hasNext())
            return -1;//left still has items -> not the right order;
        return 0;//same length but no decisions
    }

    private static Iterable<?> readStruct(String s) {
        String[] chars = s.split(",");

        List root = new ArrayList<>();
        List current = root;
        List<List> parent = new ArrayList<>();
        parent.add(root);
        for (int i = 1; i < s.length();) {
            String next = readNext(s, i);
            i += next.length() ;
            if (next.equals("[")) {
                ArrayList<?> newList = new ArrayList<>();
                current.add(newList);
                parent.add(current);
                current = newList;
            } else if (next.equals("]"))
                current = parent.remove(parent.size() -1);

            else if (next.equals(","))
                continue;
            else
                current.add(Integer.parseInt(next));
        }

        String toString = toString(root);
        if (!toString.equals(s)) {
            System.out.println("a = " + s);
            System.out.println("b = " + toString);
        }
        return root;
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

    private static String toString(List root) {
        return root.toString();
    }

    private class Struct {

        public Object getCurrentElement() {
            return null;
        }
    }
}
