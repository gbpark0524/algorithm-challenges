package gbpark.common;

import java.util.Arrays;
import java.util.Collection;

public class TestUtil {
    private static int testCount = 0;

    public static void test(Object expected, Object actual) {
        testCount++;
        boolean isEqual = isEqual(expected, actual);

        if (!isEqual) {
            System.err.println("❌ Test Case #" + testCount + " FAIL");
            System.err.println("Expected: " + toString(expected));
            System.err.println("Actual  : " + toString(actual));
            System.err.println();
        } else {
            System.err.println("🎉Test Case #" + testCount + " SUCCESS 🎊");
        }
    }

    private static boolean isEqual(Object expected, Object actual) {
        boolean isEqual;

        if (expected instanceof Collection<?> expectedCol && actual instanceof Collection<?> actualCol) {
            isEqual = expectedCol.size() == actualCol.size() && expectedCol.containsAll(actualCol);
        } else if (expected instanceof Object[] && actual instanceof Object[]) {
            isEqual = Arrays.equals((Object[]) expected, (Object[]) actual);
        } else {
            isEqual = expected.equals(actual);
        }
        return isEqual;
    }

    private static String toString(Object obj) {
        if (obj instanceof Object[]) {
            return Arrays.toString((Object[]) obj);
        }
        return obj.toString();
    }
}
