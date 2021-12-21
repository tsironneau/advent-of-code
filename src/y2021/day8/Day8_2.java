package y2021.day8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static y2021.day8.Input.INPUT;

public class Day8_2 {

    //ZERO -> 6   !d (-> last 6 lines #2)
    //ONE -> 2 #  cf

    //TWO -> 5    !f && !b
    //THREE -> 5  !b && !e (-> contains 1 #1)

    //FOUR -> 4 #  bcdf

    //FIVE -> 5   !c && !e

    //SIX -> 6    !c  (-> not contains 7 #1)

    //SEVEN -> 3 # acf a = not in ONE

    //EIGHT -> 7 # abcdefg

    //NINE -> 6   !e  (-> contains 4 #1)


    public static void main(String[] args) {
        List<String[]> lines = INPUT.lines().map(
                line -> line.split(" \\| ")
        ).toList();


        int sum = 0;
        for (String[] line : lines) {
            Map<String, Integer> digits = findDigits(line[0].split(" "));

            sum += toInteger(line[1].split(" "), digits);
        }

        System.out.println(sum);

    }

    private static Map<String, Integer> findDigits(String[] lines) {
        String one = Arrays.stream(lines).filter(digit -> digit.length() == 2).findFirst().orElseThrow();
        String four = Arrays.stream(lines).filter(digit -> digit.length() == 4).findFirst().orElseThrow();
        String seven = Arrays.stream(lines).filter(digit -> digit.length() == 3).findFirst().orElseThrow();
        String eight = Arrays.stream(lines).filter(digit -> digit.length() == 7).findFirst().orElseThrow();

        String nine = Arrays.stream(lines).filter(digit -> digit.length() == 6 && contains(digit, four)).findFirst().orElseThrow();
        String six = Arrays.stream(lines).filter(digit -> digit.length() == 6 && !contains(digit, seven)).findFirst().orElseThrow();
        String zero = Arrays.stream(lines).filter(digit -> digit.length() == 6 && !digit.equals(nine) && !digit.equals(six)).findFirst().orElseThrow();

        String three = Arrays.stream(lines).filter(digit -> digit.length() == 5 && contains(digit, one)).findFirst().orElseThrow();
        String two = Arrays.stream(lines).filter(digit -> digit.length() == 5 && !digit.equals(three) && contains(digit, missing(eight, six).get(0))).findFirst().orElseThrow();
        String five = Arrays.stream(lines).filter(digit -> digit.length() == 5 && !digit.equals(three) && !digit.equals(two)).findFirst().orElseThrow();

        return Map.of(
                sortString(zero), 0,
                sortString(one), 1,
                sortString(two), 2,
                sortString(three), 3,
                sortString(four), 4,
                sortString(five), 5,
                sortString(six), 6,
                sortString(seven), 7,
                sortString(eight), 8,
                sortString(nine), 9
        );
    }

    private static String sortString(String digit) {
        return Arrays.stream(digit.split("")).sorted().collect(Collectors.joining());
    }

    private static boolean contains(String digit, String four) {
        List<String> a = Arrays.stream(digit.split("")).toList();
        List<String> b = Arrays.stream(four.split("")).toList();
        return a.containsAll(b);
    }

    private static List<String> missing(String digit, String toRemove) {
        List<String> a = Arrays.stream(digit.split("")).collect(Collectors.toList());
        List<String> b = Arrays.stream(toRemove.split("")).toList();
        a.removeAll(b);
        return a;
    }

    private static Integer toInteger(String[] number, Map<String, Integer> digits) {
        String intAsString = Arrays.stream(number)
                .map(Day8_2::sortString)
                .map(digits::get)
                .map(Object::toString)
                .collect(Collectors.joining());
        return Integer.parseInt(intAsString);
    }
}
