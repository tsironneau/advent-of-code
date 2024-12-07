package y2024.day07;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day07_2 {

    public static void main(String[] args) {
        final String[] lines = Input.INPUT.split("\n");

        final List<String> collect = Arrays.stream(lines)
                                           .toList();

        long result = puzzle(collect);

        System.out.println(result);
    }

    private static long puzzle(List<String> collect) {
        long result = 0;

        for (String line : collect) {
            final var equation = line.split(": ");
            if(isValidEquation(equation))
                result += Long.parseLong(equation[0]);
        }

        return result;
    }

    private static boolean isValidEquation(String[] equation) {
        final var result = Long.parseLong(equation[0]);
        final var operands = Arrays.stream(equation[1].split(" ")).map(Long::parseLong)
                                   .toList();

        final var queue = new LinkedList<>(operands);
        return takes(queue.pop(), queue, result);
    }

    private static boolean takes(Long current,
                                 Queue<Long> remainingOperands,
                                 long result) {
        if (remainingOperands.isEmpty() && current == result)
            return true;
        if(remainingOperands.isEmpty())
            return false;
        if(current > result)
            return false;

        final var next = remainingOperands.poll();
        return takes(current + next, new LinkedList<>(remainingOperands), result)
                || takes(current * next, new LinkedList<>(remainingOperands), result)
                || takes(Long.parseLong(current.toString() + next),
                new LinkedList<>(remainingOperands), result)
                ;

    }


}
