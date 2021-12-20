package common.string;

public class StringUtils {

    public static String insert(String input, int start, int indexTo, String insertion) {
        return input.substring(0, start) + insertion + input.substring(indexTo);
    }
}
