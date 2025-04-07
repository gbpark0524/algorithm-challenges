package gbpark.programmers.level4;

import gbpark.common.CodingTest;

import static gbpark.common.CodingTest.DataType.INT;

public class CountValidParentheses {
	static int[][] memo = new int[20][20];
	static int[] dp = new int[20];

	public int solution(int n) {
		return dfs(n, n);
	}
	
	public int memoization(int n) {
		return dfs(n, n, memo);
	}

	public int dp(int n) {
		dp[0] = 1;
		
		for (int i = 1; i <= n; i++) {
			if (dp[i] > 0) continue;
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j]*dp[i-1-j];
			}
		}		
		
		return dp[n];
	}
	public int catalan(int n) {
		long result = 1;

		for (int i = 0; i < n; i++) {
			result = result * (n + 1 + i);
			result = result / (i + 1);
		}

		result = result / (n + 1);

		return (int) result;
	}
	
	int dfs(int l, int r) {
		if (r < 0 || l < r) return 0;
		if (l == 0) return 1;
		return dfs(l, r - 1) + dfs(l - 1, r);
	}

	int dfs(int l, int r, int[][] memo) {
		if (r < 0 || l < r) return 0;
		if (l == 0) return 1;
		if (memo[l][r] != 0) return memo[l][r];
		memo[l][r] = dfs(l, r - 1, memo) + dfs(l - 1, r, memo);
		return memo[l][r];
	}


	public static void main(String[] args) {
		CountValidParentheses thisClass = new CountValidParentheses();
		CodingTest dfs = new CodingTest<>(thisClass::solution, INT, INT);
		CodingTest memoization = new CodingTest<>(thisClass::memoization, INT, INT);
		CodingTest dp = new CodingTest<>(thisClass::dp, INT, INT);
		CodingTest catalan = new CodingTest<>(thisClass::catalan, INT, INT);
		dfs.codingTest("17	129644790");
		memoization.codingTest("17	129644790");
		dp.codingTest("17	129644790");
		catalan.codingTest("17	129644790");
	}
}

/* 올바른 괄호 갯수
올바른 괄호란 (())나 ()와 같이 올바르게 모두 닫힌 괄호를 의미합니다. )(나 ())() 와 같은 괄호는 올바르지 않은 괄호가 됩니다. 
괄호 쌍의 개수 n이 주어질 때, n개의 괄호 쌍으로 만들 수 있는 모든 가능한 괄호 문자열의 갯수를 반환하는 함수 solution을 완성해 주세요.

제한사항
괄호 쌍의 개수 N : 1 ≤ n ≤ 14, N은 정수
입출력 예
n	result
2	2
3	5
입출력 예 설명
입출력 예 #1
2개의 괄호쌍으로 [ "(())", "()()" ]의 2가지를 만들 수 있습니다.
입출력 예 #2
3개의 괄호쌍으로 [ "((()))", "(()())", "(())()", "()(())", "()()()" ]의 5가지를 만들 수 있습니다.
* */
