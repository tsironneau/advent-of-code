package y2021.day4;

import java.util.*;
import java.util.stream.Collectors;

public class Day4_1 {

    public static void main(String[] args) {
        List<String> lines = Input.INPUT.lines().collect(Collectors.toList());

        List<Integer> numbers = Arrays.stream(lines.get(0).split(","))
                .map(Integer::valueOf)
                .toList();

        lines.remove(0);
        List<Board> boards = buildBoards(lines);

        List<Integer> checkedNumbers = new ArrayList<>();
        List<Integer> remainingNumbers = new ArrayList<>(numbers);

        while (!remainingNumbers.isEmpty()) {
            Integer lastCheckedNumber = remainingNumbers.remove(0);
            checkedNumbers.add(lastCheckedNumber);

            Board winningBoard = findWiningBoard(checkedNumbers, boards);

            if (winningBoard != null) {
                int sum = winningBoard.lines.stream()
                        .flatMap(Collection::stream)
                        .filter(i -> !checkedNumbers.contains(i))
                        .mapToInt(i -> i)
                        .sum();
                System.out.println(sum * lastCheckedNumber);

                return;
            }
        }
    }

    private static Board findWiningBoard(List<Integer> checkedNumbers, List<Board> boards) {
        return boards.stream().filter(
                board -> isWinningBoard(board, checkedNumbers)
        ).findFirst().orElse(null);
    }

    private static boolean isWinningBoard(Board board, List<Integer> checkedNumbers) {

        return board.lines.stream().anyMatch(checkedNumbers::containsAll)
                || board.columns.stream().anyMatch(checkedNumbers::containsAll);
    }

    private static List<Board> buildBoards(List<String> lines) {
        ArrayList<String> mutableLines = new ArrayList<>(lines);
        List<Board> result = new ArrayList<>();
        List<List<Integer>> currentBoardLines = new ArrayList<>();
        while (!mutableLines.isEmpty()) {
            String current = mutableLines.remove(0);
            if (current.isEmpty()) {
                result.add(new Board(currentBoardLines));
                currentBoardLines = new ArrayList<>();
            } else {
                currentBoardLines.add(Arrays.stream(current.split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).toList());
            }
        }
        return result;
    }

    static final class Board {
        private final List<List<Integer>> lines;
        private final List<List<Integer>> columns;

        Board(List<List<Integer>> lines) {
            this.lines = lines;
            columns = buildColumns();
        }

        private List<List<Integer>> buildColumns() {
            List<List<Integer>> result = new ArrayList<>();
            for (List<Integer> currentLine : lines) {
                for (int j = 0; j < currentLine.size(); j++) {
                    Integer integer = currentLine.get(j);
                    if (result.size() < j + 1)
                        result.add(new ArrayList<>());
                    List<Integer> c = result.get(j);
                    c.add(integer);
                }
            }

            return result;
        }

        public List<List<Integer>> lines() {
            return lines;
        }

        public List<List<Integer>> columns() {
            return columns;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Board) obj;
            return Objects.equals(this.lines, that.lines) &&
                    Objects.equals(this.columns, that.columns);
        }

        @Override
        public int hashCode() {
            return Objects.hash(lines, columns);
        }

        @Override
        public String toString() {
            return "Board[" +
                    "lines=" + lines + ", " +
                    "columns=" + columns + ']';
        }

    }

}
