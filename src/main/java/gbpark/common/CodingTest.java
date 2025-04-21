package gbpark.common;

import java.util.function.BiFunction;
import java.util.function.Function;

import static gbpark.common.TestUtil.startTimer;
import static gbpark.common.TestUtil.test;

public class CodingTest<T1, T2, T3, T4, T5, T6, R> {
	public enum DataType {
		INT, LONG, STRING,
		INT_ARRAY, STRING_ARRAY, CHAR_ARRAY,
		INT_MATRIX, STRING_MATRIX, CHAR_MATRIX,
		LONG_ARRAY
	}

	private Function<T1, R> function1;
	private BiFunction<T1, T2, R> function2;
	private TriFunction<T1, T2, T3, R> function3;
	private QuadFunction<T1, T2, T3, T4, R> function4;
	private PentaFunction<T1, T2, T3, T4, T5, R> function5;
	private HexaFunction<T1, T2, T3, T4, T5, T6, R> function6;

	private final DataType[] paramTypes;
	private final int paramCount;

	public CodingTest(Function<T1, R> function, DataType inputType, DataType expectedType) {
		this.function1 = function;
		this.paramTypes = new DataType[]{inputType, expectedType};
		this.paramCount = 1;
	}

	public CodingTest(BiFunction<T1, T2, R> function, DataType input1Type, DataType input2Type, DataType expectedType) {
		this.function2 = function;
		this.paramTypes = new DataType[]{input1Type, input2Type, expectedType};
		this.paramCount = 2;
	}

	public CodingTest(TriFunction<T1, T2, T3, R> function, DataType input1Type, DataType input2Type, DataType input3Type, DataType expectedType) {
		this.function3 = function;
		this.paramTypes = new DataType[]{input1Type, input2Type, input3Type, expectedType};
		this.paramCount = 3;
	}

	public CodingTest(QuadFunction<T1, T2, T3, T4, R> function, DataType input1Type, DataType input2Type, DataType input3Type, DataType input4Type, DataType expectedType) {
		this.function4 = function;
		this.paramTypes = new DataType[]{input1Type, input2Type, input3Type, input4Type, expectedType};
		this.paramCount = 4;
	}

	public CodingTest(PentaFunction<T1, T2, T3, T4, T5, R> function, DataType input1Type, DataType input2Type, DataType input3Type, DataType input4Type, DataType input5Type, DataType expectedType) {
		this.function5 = function;
		this.paramTypes = new DataType[]{input1Type, input2Type, input3Type, input4Type, input5Type, expectedType};
		this.paramCount = 5;
	}

	public CodingTest(HexaFunction<T1, T2, T3, T4, T5, T6, R> function, DataType input1Type, DataType input2Type, DataType input3Type, DataType input4Type, DataType input5Type, DataType input6Type, DataType expectedType) {
		this.function6 = function;
		this.paramTypes = new DataType[]{input1Type, input2Type, input3Type, input4Type, input5Type, input6Type, expectedType};
		this.paramCount = 6;
	}

	public void codingTest(String testCase) {
		String[] parts = testCase.trim().replaceAll("\\n", "").split("\\s*\\t+\\s*");
		if (parts.length != paramCount + 1) {
			throw new IllegalArgumentException("테스트 케이스 형식이 잘못되었습니다. 입력값과 기대값의 개수를 확인하세요.");
		}

		Object[] inputs = new Object[paramCount];
		for (int i = 0; i < paramCount; i++) {
			inputs[i] = convertToType(parts[i], paramTypes[i]);
		}

		Object expected = convertToType(parts[paramCount], paramTypes[paramCount]);

		startTimer();
		@SuppressWarnings("unchecked")
		Object actual = switch (paramCount) {
			case 1 -> function1.apply((T1) inputs[0]);
			case 2 -> function2.apply((T1) inputs[0], (T2) inputs[1]);
			case 3 -> function3.apply((T1) inputs[0], (T2) inputs[1], (T3) inputs[2]);
			case 4 -> function4.apply((T1) inputs[0], (T2) inputs[1], (T3) inputs[2], (T4) inputs[3]);
			case 5 -> function5.apply((T1) inputs[0], (T2) inputs[1], (T3) inputs[2], (T4) inputs[3], (T5) inputs[4]);
			case 6 ->
					function6.apply((T1) inputs[0], (T2) inputs[1], (T3) inputs[2], (T4) inputs[3], (T5) inputs[4], (T6) inputs[5]);
			default -> null;
		};

		test(expected, actual);
	}

	private static Object convertToType(String value, DataType type) {
		return switch (type) {
			case INT -> Integer.parseInt(value.trim());
			case LONG -> Long.parseLong(value.trim());
			case STRING -> value;
			case INT_ARRAY -> ArrayConverter.toIntArray(value);
			case STRING_ARRAY -> ArrayConverter.toStringArray(value);
			case CHAR_ARRAY -> ArrayConverter.toCharArray(value);
			case INT_MATRIX -> ArrayConverter.toIntMatrix(value);
			case STRING_MATRIX -> ArrayConverter.toStringMatrix(value);
			case CHAR_MATRIX -> ArrayConverter.toCharMatrix(value);
			case LONG_ARRAY -> ArrayConverter.toLongArray(value);
		};
	}

	@FunctionalInterface
	public interface TriFunction<T1, T2, T3, R> {
		R apply(T1 t1, T2 t2, T3 t3);
	}

	@FunctionalInterface
	public interface QuadFunction<T1, T2, T3, T4, R> {
		R apply(T1 t1, T2 t2, T3 t3, T4 t4);
	}

	@FunctionalInterface
	public interface PentaFunction<T1, T2, T3, T4, T5, R> {
		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
	}

	@FunctionalInterface
	public interface HexaFunction<T1, T2, T3, T4, T5, T6, R> {
		R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);
	}
}