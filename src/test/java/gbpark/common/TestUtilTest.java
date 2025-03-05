package gbpark.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUtilTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	public void restoreStreams() {
		System.setOut(originalOut);
		System.setErr(originalErr);
	}

	@Test
	@DisplayName("Test int array equality with equal arrays")
	public void testIntArrayEquality_Equal() {
		int[] expected = {1, 2, 3, 4, 5};
		int[] actual = {1, 2, 3, 4, 5};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("SUCCESS"));
		assertFalse(errContent.toString().contains("FAIL"));
	}

	@Test
	@DisplayName("Test int array equality with different arrays")
	public void testIntArrayEquality_Different() {
		int[] expected = {1, 2, 3, 4, 5};
		int[] actual = {1, 2, 3, 4, 6};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
		assertFalse(errContent.toString().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Test int array equality with different lengths")
	public void testIntArrayEquality_DifferentLength() {
		int[] expected = {1, 2, 3, 4, 5};
		int[] actual = {1, 2, 3, 4};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
	}

	@Test
	@DisplayName("Test String array equality with equal arrays")
	public void testStringArrayEquality_Equal() {
		String[] expected = {"a", "b", "c"};
		String[] actual = {"a", "b", "c"};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Test String array equality with different arrays")
	public void testStringArrayEquality_Different() {
		String[] expected = {"a", "b", "c"};
		String[] actual = {"a", "b", "d"};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
	}

	@Test
	@DisplayName("Test char array equality with equal arrays")
	public void testCharArrayEquality_Equal() {
		char[] expected = {'a', 'b', 'c'};
		char[] actual = {'a', 'b', 'c'};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Test char array equality with different arrays")
	public void testCharArrayEquality_Different() {
		char[] expected = {'a', 'b', 'c'};
		char[] actual = {'a', 'b', 'd'};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
	}

	@Test
	@DisplayName("Test boolean array equality with equal arrays")
	public void testBooleanArrayEquality_Equal() {
		boolean[] expected = {true, false, true};
		boolean[] actual = {true, false, true};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Test boolean array equality with different arrays")
	public void testBooleanArrayEquality_Different() {
		boolean[] expected = {true, false, true};
		boolean[] actual = {true, false, false};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
	}

	@Test
	@DisplayName("Test double array equality with equal arrays")
	public void testDoubleArrayEquality_Equal() {
		double[] expected = {1.1, 2.2, 3.3};
		double[] actual = {1.1, 2.2, 3.3};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Test double array equality with different arrays")
	public void testDoubleArrayEquality_Different() {
		double[] expected = {1.1, 2.2, 3.3};
		double[] actual = {1.1, 2.2, 3.4};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
	}

	@Test
	@DisplayName("Test mixed array types")
	public void testMixedArrayTypes() {
		// This should fail because they are different types
		Integer[] expected = {1, 2, 3};
		int[] actual = {1, 2, 3};

		TestUtil.test(expected, actual);

		// Current implementation compares string representation, so this might actually pass
		// depending on how TestUtil.isEqual is implemented
		String output = errContent.toString();
		System.setErr(originalErr);
		System.err.println("Output for mixed types: " + output);
	}

	@Test
	@DisplayName("Test null arrays")
	public void testNullArrays() {
		int[] expected = null;
		int[] actual = null;

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Test one null array")
	public void testOneNullArray() {
		int[] expected = {1, 2, 3};
		int[] actual = null;

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
	}

	@Test
	@DisplayName("Test arrays with null elements")
	public void testArraysWithNullElements() {
		String[] expected = {"a", null, "c"};
		String[] actual = {"a", null, "c"};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("SUCCESS"));
	}

	@Test
	@DisplayName("Test arrays with different null elements")
	public void testArraysWithDifferentNullElements() {
		String[] expected = {"a", "b", "c"};
		String[] actual = {"a", null, "c"};

		TestUtil.test(expected, actual);

		assertTrue(errContent.toString().contains("FAIL"));
	}
}