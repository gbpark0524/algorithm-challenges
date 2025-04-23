#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};

#end

import gbpark.common.CodingTest;

import static gbpark.common.CodingTest.DataType.*;

#parse("File Header.java")
public class ${NAME} {
    public int solution(int[] args) {
        int answer = 0;
        return answer;
    }
    
    public static void main(String[] args) {
        ${NAME} thisClass = new ${NAME}();
        CodingTest test = new CodingTest<>(thisClass::solution, INT_ARRAY, INT);
        test.codingTest("");
    }
}
