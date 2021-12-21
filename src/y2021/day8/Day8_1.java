package y2021.day8;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day8_1 {

    //ONE -> 2 lines
    //FOUR -> 4 lines
    //SEVEN -> 3 lines
    //EIGHT -> 7 lines

    public static void main(String[] args) {
        Set<Integer> sizes = new HashSet<>(Arrays.asList(2, 4, 3, 7));
        List<String[]> lines = Input.INPUT.lines().map(
                line -> line.split("\\|")
        ).toList();

        long res = lines.stream().map(line -> line[1])
                .map(line -> line.split(" "))
                .flatMap(Arrays::stream)
                .filter(line -> sizes.contains(line.length()))
                .count();

        System.out.println(res);

    }

}
