package gbpark.common;

import java.util.Collection;
import java.util.Objects;

public class TestUtil {
    private static int testCount = 0;
    private static long startTime = 0;

    public static void test(Object expected, Object actual) {
        testCount++;
        String time = startTime != 0 ? " :" + getTime() : "";
        boolean isEqual = isEqual(expected, actual);

        if (!isEqual) {
            System.err.println("❌ Test Case #" + testCount + " FAIL");
            System.err.println("Expected: " + toString(expected));
            System.err.println("Actual  : " + toString(actual));
            System.err.println();
        } else {
            System.err.println("🎉Test Case #" + testCount + " SUCCESS 🎊 " + time);
        }
    }

    public static void startTimer() {
        startTime = System.nanoTime();
    }

    private static String getTime() {
        if (startTime == 0) {
            System.err.println("⚠️ Timer was not started.");
            return "";
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        startTime = 0;
        return " ⏱️" + formatTime(duration);
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
        if (expected == actual) return true;
        if (expected == null || actual == null) return false;

        if (expected instanceof int[][] expectedArr && actual instanceof int[][] actualArr) {
			if (expectedArr.length != actualArr.length) return false;

            for (int i = 0; i < expectedArr.length; i++) {
                if (expectedArr[i].length != actualArr[i].length) return false;

                for (int j = 0; j < expectedArr[i].length; j++) {
                    if (expectedArr[i][j] != actualArr[i][j]) return false;
                }
            }
            return true;
        } else if (expected instanceof String[][] expectedArr && actual instanceof String[][] actualArr) {
			if (expectedArr.length != actualArr.length) return false;

            for (int i = 0; i < expectedArr.length; i++) {
                if (expectedArr[i].length != actualArr[i].length) return false;

                for (int j = 0; j < expectedArr[i].length; j++) {
                    if (!Objects.equals(expectedArr[i][j], actualArr[i][j])) return false;
                }
            }
            return true;
        } else if (expected instanceof Collection<?> expectedCol && actual instanceof Collection<?> actualCol) {
            return expectedCol.size() == actualCol.size() && expectedCol.containsAll(actualCol);
        } else if (expected.getClass().isArray() && actual.getClass().isArray()) {
            int expectedLength = java.lang.reflect.Array.getLength(expected);
            int actualLength = java.lang.reflect.Array.getLength(actual);

            if (expectedLength != actualLength) return false;

            for (int i = 0; i < expectedLength; i++) {
                Object expectedElement = java.lang.reflect.Array.get(expected, i);
                Object actualElement = java.lang.reflect.Array.get(actual, i);

                if (expectedElement == null && actualElement == null) {
                    continue;
                }
                if (expectedElement == null || actualElement == null) {
                    return false;
                }

                String expectedStr = String.valueOf(expectedElement);
                String actualStr = String.valueOf(actualElement);

                if (!expectedStr.equals(actualStr)) {
                    return false;
                }
            }

            return true;
        } else {
            return expected.equals(actual);
        }
    }

    private static String toString(Object obj) {
        if (obj == null) return "null";
        if (obj.getClass().isArray()) {
            int length = java.lang.reflect.Array.getLength(obj);
            if (length == 0) {
                return "[]";
            }

            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < length; i++) {
                sb.append(java.lang.reflect.Array.get(obj, i));
                if (i < length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]");
            return sb.toString();
        }

        return obj.toString();
    }
}