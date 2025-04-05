package gbpark.programmers.level4;

import gbpark.common.CodingTest;

import java.util.Stack;

import static gbpark.common.CodingTest.DataType.INT;

public class HighPitchedVoice {
	static int ans;
	static Stack<Node> stack = new Stack<>();

	public int solution(int n) {
		ans = 0;
		int mc = calculateIterationCount(n);
		int pc = mc * 2;
		stack.clear();
		stack.add(new Node(n, mc - 1, pc));

		while (!stack.isEmpty()) {
			dfs();
		}

		return ans;
	}

	int calculateIterationCount(int n) {
		int count = 1;
		long m = 5;
		while (m < (long) n) {
			count++;
			m = m * 3 + 2;
		}
		return count;
	}

	void dfs() {
		Node node = stack.pop();
		int n = node.n;
		int mc = node.mc;
		int pc = node.pc;

		if ((mc + 1) * 2 < pc) return;

		if (Math.pow(3, mc + 1) + pc == n) {
			ans++;
			return;
		}

		int mod = n % 3;

		if (mod == 0) {
			if (mc > 0) stack.add(new Node(n / 3, mc - 1, pc));
			if (pc - 3 >= 0) stack.add(new Node(n - 3, mc, pc - 3));
		} else {
			if (pc - mod >= 0) stack.add(new Node(n - mod, mc, pc - mod));
		}
	}

	static class Node {
		int n;
		int mc;
		int pc;

		public Node(int n, int mc, int pc) {
			this.n = n;
			this.mc = mc;
			this.pc = pc;
		}
	}


	public static void main(String[] args) {
		HighPitchedVoice thisClass = new HighPitchedVoice();
		CodingTest test = new CodingTest<>(thisClass::solution, INT, INT);
		test.codingTest("15	1");
		test.codingTest("24	0");
		test.codingTest("41	2");
		test.codingTest("2147483647	1735");
	}
}

/*
4단 고음

I'm in my dream~↗ ~↗ ~↗
IU는 본인의 장기인 3단 고음으로 유명하다. 그러던 그녀가 어느 날 4단 고음을 성공했고 그녀의 고음은 학계에서 연구가 될 만큼 유명해졌다

폭포 밑 득음 수련을 하던 어느 날, 그녀는 4단 고음이 끝이 아님을 깨달았다. 3단 고음 직후 3단 고음을 연이어하거나, 3단 고음 중 다시 3단 고음을 해서 음높이를 올리는 방법이다. 
어떤 순서로 3단 고음을 했는지에 따라 최종 음높이가 달라지기 때문에, 연속 3단 고음을 연습할 때마다 그 결과를 기록으로 남기기로 했다.

3단 고음은 다음과 같이 적용된다. 1단계에서는 음높이가 세 배가 되며, 2단계와 3단계에서 음높이가 각각 1씩 증가한다. 
이를 기록으로 남길 때 * 와 + 기호를 사용하기로 했다. 즉, 3단 고음을 한 번 한 경우는 문자열로 나타내면 다음과 같다.

*++

이때 3단 고음을 마치고 연달아 3단 고음을 한 경우는 *++*++ 와 같이 표현할 수 있다. 
3단 고음의 2단계를 마친 후 3단 고음을 새로 시작한 다음, 
나머지 단계를 이어서 하는 경우는 *+*+++로 표현할 수 있다. (강조된 부분이 2번째 3단 고음을 부른 부분이다.)

이와 같이 * 와 + 로 구성된 문자열이 3단 고음의 규칙을 적용하여 만들 수 있는 문자열인 경우 '올바른 문자열'이라고 하자. 
다음의 문자열은 3단 고음의 규칙으로 만들 수 있는 문자열이 아니므로 올바른 문자열이 아니다.

+**+++
*+++*+
올바른 문자열에 대해 음높이는 다음과 같이 계산할 수 있다. 시작 음높이는 항상 1이며, 문자열의 처음부터 순서대로 * 기호의 경우 3을 곱하고 + 기호의 경우 1을 더한다. *+*+++ 의 음높이를 계산하는 과정을 예로 들면 아래와 같다.

시작 음 높이: 1

*	+	*	+	+	+
*3	+1	*3	+1	+1	+1
최종 음높이: 15

그날 기분에 따라 최종 음높이를 정하는 IU는 최종 음높이를 결정했을 때 서로 다른 3단 고음 문자열이 몇 가지나 있는지 궁금하다. 여러분의 도움이 필요하다.

입력 형식
입력은 5 이상 2^31-1 이하의 정수 n으로 주어진다.
출력 형식
입력을 만족하는 서로 다른 문자열의 수를 리턴한다.
예제 입출력
n	answer
15	1
24	0
41	2
2147483647	1735
예제에 대한 설명
세 번째 예제의 두 가지 경우는 다음과 같다.

**++++*++
*+**+++++
* */
