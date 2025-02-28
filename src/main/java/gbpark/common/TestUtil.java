package gbpark.common;

import java.util.Arrays;
import java.util.Collection;

public class TestUtil {
    private static int testCount = 0;
    private static long startTime = 0;

    public static void test(Object expected, Object actual) {
        testCount++;
        boolean isEqual = isEqual(expected, actual);

        if (!isEqual) {
            System.err.println("❌ Test Case #" + testCount + " FAIL");
            System.err.println("Expected: " + toString(expected));
            System.err.println("Actual  : " + toString(actual));
            System.err.println();
        } else {
            String time = startTime != 0 ? " :" + getTime() : "";
            System.err.println("🎉Test Case #" + testCount + " SUCCESS 🎊 " + time);
        }
    }

    public static void startTimer() {
        startTime = System.nanoTime();
    }

    private static String getTime() {
        if (startTime == 0) {
            System.err.println("⚠️ Timer was not started.");
            return"";
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        startTime = 0;
        return "⏱️ Execution time: " + formatTime(duration);
    }

    private static String formatTime(long nanos) {
        if (nanos < 1_000) {
            return nanos + " ns";
        } else if (nanos < 1_000_000) {
            return String.format("%.2f μs", nanos / 1_000.0);
        } else if (nanos < 1_000_000_000) {
            return String.format("%.2f ms", nanos / 1_000_000.0);
        } else {
            return String.format("%.2f s", nanos / 1_000_000_000.0);
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
