package y2021.day21;

import java.util.HashMap;
import java.util.Map;

public class Day21_2_2 {

    public static final int FINAL_SCORE = 21;
    public static final int NB_OF_DICE_FACES = 3;
    public static final int NB_OF_ROLLS = 3;
    private static long p1Wins = 0;
    private static long p2Wins = 0;
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
        final Result recursive = recursive(0, 0, 6 - 1, 2 - 1, 0, 1);

        System.out.println(nbRec);
        System.out.println(recursive);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    /*
    This one doesn't work, i've tried to use a cache with the NB_UNIVERS_PER_SCORE shortcut but i did not
    manage to compute the expected result
     */
    public static Result recursive(
            int p1Score, int p2Score,
            int p1Pos, int p2Pos,
            int turn,
            final long nbUniverse) {
        nbRec++;
        if (p1Score >= FINAL_SCORE)
            return new Result(nbUniverse, 0);
        if (p2Score >= FINAL_SCORE)
            return new Result(0, nbUniverse);

        final State state = new State(p1Score, p2Score, p1Pos, p2Pos);
        if (CACHE.containsKey(state))
            return CACHE.get(state);

        Result res = new Result(0, 0);
        for (int i = NB_OF_ROLLS; i <= NB_OF_ROLLS * NB_OF_DICE_FACES; i++) {
            Result result;
            int pos = advance(p1Pos, i);
            result = recursive( p2Score,p1Score + pos + 1,  p2Pos,pos, turn,
                               nbUniverse * NB_UNIVERS_PER_SCORE.get(i));
            res = res.add(result.reverse());
        }
        CACHE.put(state, res);
        return res;
    }

    record State(int p1Score, int p2Score, int p1pos, int p2Pos) {

    }

    record Result(long p1Wins, long p2Wins) {

        Result add(Result result) {
            return new Result(p1Wins + result.p1Wins, p2Wins + result.p2Wins);
        }

        Result reverse(){
            return new Result(p2Wins, p1Wins);
        }

        Result mult(long multiplier) {
            return new Result(p1Wins * multiplier, p2Wins * multiplier);
        }
    }

    private static int advance(final int pos, final int dice) {
        return (pos + dice) % 10;
    }
}
