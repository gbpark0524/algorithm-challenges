package gbpark.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayConverterTest {

    @Test
    void toStringArray() {
        // given
        String input = "[a, b,c ,d]";

        // when
        String[] result = ArrayConverter.toStringArray(input);

        // then
        assertArrayEquals(new String[]{"a", "b", "c", "d"}, result);

        // edge cases
        assertArrayEquals(new String[]{"1", "2", "3"}, ArrayConverter.toStringArray("[1,2,3]"));
    }

    @Test
    void toStringMatrix() {
        // given
        String input = "[[a,b],[c,d]]";

        // when
        String[][] result = ArrayConverter.toStringMatrix(input);

        // then
        String[][] expected = {{"a", "b"}, {"c", "d"}};
        assertArrayEquals(expected, result);

        // different sized arrays
        String input2 = "[[a, b, c], [d,e]]";
        String[][] expected2 = {{"a", "b", "c"}, {"d", "e"}};
        assertArrayEquals(expected2, ArrayConverter.toStringMatrix(input2));
    }

    @Test
    void toCharArray() {
        // given
        String input = "[a,b ,c ,d]";

        // when
        char[] result = ArrayConverter.toCharArray(input);

        // then
        assertArrayEquals(new char[]{'a', 'b', 'c', 'd'}, result);
    }

    @Test
    void toCharMatrix() {
        // given
        String input = "[[a, b] ,[c,d]]";

        // when
        char[][] result = ArrayConverter.toCharMatrix(input);

        // then
        char[][] expected = {{'a', 'b'}, {'c', 'd'}};
        assertArrayEquals(expected, result);
    }

    @Test
    void toIntArray() {
        // given
        String input = "[1,2 ,3,4 ]";

        // when
        int[] result = ArrayConverter.toIntArray(input);

        // then
        assertArrayEquals(new int[]{1, 2, 3, 4}, result);

        // edge cases
        assertArrayEquals(new int[]{-1, 0, 1}, ArrayConverter.toIntArray("[-1,0,1]"));
    }

    @Test
    void toIntMatrix() {
        // given
        String input = "[[1, 2], [3,4]]";

        // when
        int[][] result = ArrayConverter.toIntMatrix(input);

        // then
        int[][] expected = {{1, 2}, {3, 4}};
        assertArrayEquals(expected, result);

        // different sized arrays with negative numbers
        String input2 = "[[1, 2,3], [ -4,-5]]";
        int[][] expected2 = {{1, 2, 3}, {-4, -5}};
        assertArrayEquals(expected2, ArrayConverter.toIntMatrix(input2));
    }
}