package y2021.day21;

public class Day21_1 {

    public static void main(String[] args) {

        int p1Pos = 6;
        int p2Pos = 2;

        int p1Score = 0;
        int p2Score = 0;

        int turn = 0;
        int dice = 1;
        while (p1Score < 1000 && p2Score < 1000) {
            turn++;

            if (turn % 2 == 1) {
                p1Pos = advance(p1Pos, dice);
                p1Score += p1Pos;
            } else {
                p2Pos = advance(p2Pos, dice);
                p2Score += p2Pos;
            }
            dice += 3;
            dice = turn(dice, 100);
            System.out.printf("turn %s : p1(%s) p2(%s) dice(%s)\n", turn, p1Score, p2Score, dice);
        }

        long result = (long) Math.min(p1Score, p2Score) * turn * 3;
        System.out.println(result);
    }

    private static int advance(final int p1Pos, final int dice) {
        int score = dice + turn(dice + 1, 100) + turn(dice + 2, 100);
        final int i = p1Pos + score;
        final int mod = i % 10;
        return mod == 0 ? 10 : mod;
    }

    private static int turn(final int score, final int maxValue) {
        final int mod = score % maxValue;
        return mod == 0 ? maxValue : mod;
    }
}
