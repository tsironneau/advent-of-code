package y2021.day23;

public class Day23_1 {

    //Done by hand
    public static void main(String[] args) {

        int res_1 = (8 + 5 + 9 + 2)
                + 10 * (4 + 3 + 5)
                + 100 * (6 + 5)
                + 1000 * (7 + 8);
        int res_2 = (9 + 9 + 5 + 5 + 9 + 9)
                + 10 * (5 + 5 + 6 + 5 + 4 + 7 + 7 + 9)
                + 100 * (5 + 5 + 9 + 8 + 5 + 5)
                + 1000 * (9 + 10 + 10 + 10);
        System.out.println(res_1);
        System.out.println(res_2);
    }
}
