package gbpark.common;

import java.util.Arrays;

public class ArrayConverter {

    public static String[] toStringArray(String input) {
        return input.replaceAll("[\\[\\]]", "")
                .split("\\s*,\\s*");
    }

    public static String[][] toStringMatrix(String input) {
        String[] rows = getRows(input);
        String[][] result = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i].trim().split("\\s*,\\s*");
        }
        return result;
    }

    public static char[] toCharArray(String input) {
        return input.replaceAll("[\\[\\]\\s,\"]", "")
                .trim()
                .toCharArray();
    }

    public static char[][] toCharMatrix(String input) {
        String[] rows = getRows(input);

        char[][] result = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            result[i] = rows[i].replaceAll("[,\"\\s]", "")  // 쉼표, 따옴표, 공백 제거
                    .trim()
                    .toCharArray();
        }
        return result;
    }

    public static int[] toIntArray(String input) {
        String[] numbers = input.replaceAll("[\\[\\]]", "")
                .trim()
                .split("\\s*,\\s*");

        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = Integer.parseInt(numbers[i].trim());
        }
        return result;
    }

    public static int[][] toIntMatrix(String input) {
        String[] rows = getRows(input);

        int[][] result = new int[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            result[i] = Arrays.stream(rows[i].trim().split("\\s*,\\s*"))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        return result;
    }

    private static String[] getRows(String input) {
        String content = input.substring(1, input.length() - 1).trim();
        String[] rows = content.split("\\]\\s*,\\s*\\[");
        rows[0] = rows[0].replaceAll("^\\[", "");
        rows[rows.length - 1] = rows[rows.length - 1].replaceAll("\\]$", "");
        return rows;
    }
}
