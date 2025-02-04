package common;

public class TestUtil {
    private static int testCount = 0;
 
    public static void test(Object expected, Object actual) {
        testCount++;
        if (!expected.equals(actual)) {
            System.err.println("❌ Test Case #" + testCount + " FAIL");
            System.err.println("Expected: " + expected);
            System.err.println("Actual  : " + actual);
            System.err.println();
        }
    }
 }
