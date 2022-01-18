package y2021.day21;

import java.util.HashMap;
import java.util.Map;

public class Day21_2_4 {

    public static final int FINAL_SCORE = 21;
    public static final int NB_OF_DICE_FACES = 3;
    private static long nbRec = 0;

    private static final Map<State, Result> CACHE = new HashMap<>();
    private static final Map<Integer, Long> NB_UNIVERS_PER_SCORE = new HashMap<>();

    static {
        for (int i = 1; i <= NB_OF_DICE_FACES; i++) {
            for (int j = 1; j <= NB_OF_DICE_FACES; j++) {
                for (int k = 1; k <= NB_OF_DICE_FACES; k++) {
                    NB_UNIVERS_PER_SCORE.compute(i + j + k, (key, value) -> value == null ? 1 : ++value);
                }
            }
        }
    }

    public static void main(String[] args) {

        final long start = System.currentTimeMillis();
        final Result recursive = recursive(0, 0, 6 - 1, 2 - 1);

        System.out.println(nbRec);
        System.out.println(recursive);
        System.out.println(recursive.max());
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static Result recursive(
            int p1Score, int p2Score,
            int p1Pos, int p2Pos) {
        nbRec++;
        if (p1Score >= FINAL_SCORE) {
            return new Result(1, 0);
        }
        if (p2Score >= FINAL_SCORE) {
            return new Result(0, 1);
        }

        final State state = new State(p1Score, p2Score, p1Pos, p2Pos);
        if (CACHE.containsKey(state))
            return CACHE.get(state);

        Result accumulator = new Result(0, 0);
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                for (int k = 1; k < 4; k++) {
                    final Result res;
                    final int dice = i + j + k;
                    int pos = advance(p1Pos, dice);
                    res = recursive(p2Score, p1Score + pos + 1, p2Pos, pos);
                    accumulator = accumulator.add(res.reverse());
                }
            }
        }
        System.out.println(state);
        CACHE.put(state, accumulator);
        return accumulator;
    }

    private static int advance(final int p1Pos, final int dice) {
        return (p1Pos + dice) % 10;
    }

    record Result(long p1Wins, long p2Wins) {

        Result add(Result result) {
            return new Result(p1Wins + result.p1Wins, p2Wins + result.p2Wins);
        }

        Result reverse(){
            return new Result(p2Wins, p1Wins);
        }

        long max() {
            return Math.max(p1Wins, p2Wins);
        }
    }

    record State(int p1Score, int p2Score, int p1pos, int p2Pos) {

    }
}
