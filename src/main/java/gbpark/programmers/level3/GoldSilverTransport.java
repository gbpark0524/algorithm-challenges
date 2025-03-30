package gbpark.programmers.level3;

import gbpark.common.CodingTest;

import static gbpark.common.CodingTest.DataType.INT;
import static gbpark.common.CodingTest.DataType.INT_ARRAY;
import static gbpark.common.CodingTest.DataType.LONG;

public class GoldSilverTransport {
	public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
		long max = (long) (10e9 * 2 * 10e5 * 2);
		long min = 0;
		long mid = (max - min)/2 +2;

		while (min < max) {
			if (canCarry(a, b, g, s, w, t, mid)) {
				max = mid;
			} else {
				min = mid + 1;
			}
			mid = (max + min) /2 ;
		}

		return max;
	}

	boolean canCarry (int a, int b, int[] g, int[] s, int[] w, int[] t, long time) {
		int length = g.length;
		int sum = a+b;

		for (int i = 0; i < length; i++) {
			int gold = g[i];
			int silver = s[i];
			long total = (time + t[i]) / (2L * t[i]) * w[i];
			a -= Math.min(total, gold);
			b -= Math.min(total, silver);
			sum -= Math.min(total, gold+silver);
		}

		return a <= 0 && b <= 0 && sum <= 0;
	}

	public static void main(String[] args) {
		GoldSilverTransport thisClass = new GoldSilverTransport();
		CodingTest<Integer, Integer, int[], int[], int[], int[], Long> test = new CodingTest<>(thisClass::solution, INT, INT, INT_ARRAY, INT_ARRAY, INT_ARRAY, INT_ARRAY, LONG);
		test.codingTest("10\t10\t[100]\t[100]\t[7]\t[10]\t50");
		test.codingTest("90\t500\t[70,70,0]\t[0,0,500]\t[100,100,2]\t[4,8,1]\t499");
	}
}

/*
문제 설명
어느 왕국에 하나 이상의 도시들이 있습니다. 왕국의 왕은 새 도시를 짓기로 결정하였습니다.
해당 도시를 짓기 위해서는 도시를 짓는 장소에 금 a kg과 은 b kg이 전달되어야 합니다.

각 도시에는 번호가 매겨져 있는데, i번 도시에는 금 g[i] kg, 은 s[i] kg, 그리고 트럭 한 대가 있습니다.
i번 도시의 트럭은 오직 새 도시를 짓는 건설 장소와 i번 도시만을 왕복할 수 있으며, 편도로 이동하는 데 t[i] 시간이 걸리고,
최대 w[i] kg 광물을 운반할 수 있습니다. (광물은 금과 은입니다. 즉, 금과 은을 동시에 운반할 수 있습니다.)
모든 트럭은 같은 도로를 여러 번 왕복할 수 있으며 연료는 무한대라고 가정합니다.

정수 a, b와 정수 배열 g, s, w, t가 매개변수로 주어집니다.
주어진 정보를 바탕으로 각 도시의 트럭을 최적으로 운행했을 때, 새로운 도시를 건설하기 위해 금 a kg과 은 b kg을 전달할 수 있는
가장 빠른 시간을 구해 return 하도록 solution 함수를 완성해주세요.

제한사항
0 ≤ a, b ≤ 10^9
1 ≤ g의 길이 = s의 길이 = w의 길이 = t의 길이 = 도시 개수 ≤ 10^5
0 ≤ g[i], s[i] ≤ 10^9
1 ≤ w[i] ≤ 10^2
1 ≤ t[i] ≤ 10^5
a ≤ g의 모든 수의 합
b ≤ s의 모든 수의 합
입출력 예
a	b	g	s	w	t	result
10	10	[100]	[100]	[7]	[10]	50
90	500	[70,70,0]	[0,0,500]	[100,100,2]	[4,8,1]	499
* */