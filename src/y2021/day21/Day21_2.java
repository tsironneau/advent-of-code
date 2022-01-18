package y2021.day21;

import java.util.HashMap;
import java.util.Map;

public class Day21_2 {

    public static final int FINAL_SCORE = 21;
    public static final int NB_OF_DICE_FACES = 3;
    private static long p1Wins = 0;
    private static long p2Wins = 0;
    private static long nbRec = 0;

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
        recursive(0, 0, 6, 2, 0, 1);

        System.out.println(nbRec);
        System.out.println("p2 wins " + p2Wins);
        System.out.println("p1 wins " + p1Wins);
        System.out.println(System.currentTimeMillis() - start + " ms");
    }

    public static void recursive(
            int p1Score, int p2Score,
            int p1Pos, int p2Pos,
            int turn, final long nbUniverse) {
        nbRec++;
        if (p1Score >= FINAL_SCORE) {
            p1Wins += nbUniverse;
            return;
        }
        if (p2Score >= FINAL_SCORE) {
            p2Wins += nbUniverse;
            return;
        }

        turn++;
        for (int i = NB_OF_DICE_FACES; i <= 9; i++) {
            if (turn % 2 == 1) {
                int pos = advance(p1Pos, i);
                recursive(p1Score + pos, p2Score, pos, p2Pos, turn, nbUniverse * NB_UNIVERS_PER_SCORE.get(i));
            } else {
                int pos = advance(p2Pos, i);
                recursive(p1Score, p2Score + pos, p1Pos, pos, turn, nbUniverse * NB_UNIVERS_PER_SCORE.get(i));
            }
        }
    }

    private static int advance(final int p1Pos, final int dice) {
        final int i = p1Pos + dice;
        final int mod = (i - 1) % 10;
        return mod + 1;
    }
}
