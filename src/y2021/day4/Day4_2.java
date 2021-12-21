package y2021.day4;

import java.util.*;
import java.util.stream.Collectors;

import static y2021.day4.Input.INPUT;

public class Day4_2 extends Day4_1 {

    public static void main(String[] args) {
        List<String> lines = INPUT.lines().collect(Collectors.toList());

        List<Integer> numbers = Arrays.stream(lines.get(0).split(","))
                .map(Integer::valueOf)
                .toList();

        lines.remove(0);
        List<Board> boards = buildBoards(lines);


        List<Integer> checkedNumbers = new ArrayList<>();
        List<Integer> remainingNumbers = new ArrayList<>(numbers);
        List<Board> remainingBoards = new ArrayList<>(boards);

        while (!remainingNumbers.isEmpty()) {
            Integer lastCheckedNumber = remainingNumbers.remove(0);
            checkedNumbers.add(lastCheckedNumber);

            List<Board> winningBoard = findWinningBoards(checkedNumbers, remainingBoards);

            remainingBoards.removeAll(winningBoard);

            if (remainingBoards.size() == 0) {

                computeScore(checkedNumbers, lastCheckedNumber, winningBoard.get(0));

                return;
            }
        }
    }

    private static List<Board> findWinningBoards(List<Integer> checkedNumbers, List<Board> boards) {

        return boards.stream().filter(
                board -> isWinningBoard(board, checkedNumbers)
        ).collect(Collectors.toList());
    }

    private static void computeScore(List<Integer> checkedNumbers, Integer lastCheckedNumber, Board winningBoard) {
        int sum = winningBoard.lines().stream()
                .flatMap(Collection::stream)
                .filter(i -> !checkedNumbers.contains(i))
                .mapToInt(i -> i)
                .sum();
        System.out.println(sum * lastCheckedNumber);
    }

    private static boolean isWinningBoard(Board board, List<Integer> checkedNumbers) {

        return board.lines().stream().anyMatch(checkedNumbers::containsAll)
                || board.columns().stream().anyMatch(checkedNumbers::containsAll);
    }

    private static List<Board> buildBoards(List<String> lines) {
        ArrayList<String> mutableLines = new ArrayList<>(lines);
        List<Board> result = new ArrayList<>();
        List<List<Integer>> currentBoardLines = null;
        while (!mutableLines.isEmpty()) {
            String current = mutableLines.remove(0);
            if (current.isEmpty()) {
                if (currentBoardLines != null)
                    result.add(new Board(currentBoardLines));
                currentBoardLines = new ArrayList<>();
            } else {
                currentBoardLines.add(Arrays.stream(current.split(" ")).filter(s -> !s.isEmpty()).map(Integer::valueOf).toList());
            }
        }
        return result;
    }


}
