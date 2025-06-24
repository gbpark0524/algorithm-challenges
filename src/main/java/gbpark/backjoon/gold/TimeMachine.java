package gbpark.backjoon.gold;


import gbpark.common.CodingTest;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static gbpark.common.CodingTest.DataType.INT;
import static gbpark.common.CodingTest.DataType.INT_MATRIX;
import static gbpark.common.CodingTest.DataType.LONG_ARRAY;

public class TimeMachine {
	public long[] solution(int n, int m, int[][] lines) {
		int[][] map = new int[n+1][n+1];
		for (int[] arr : map) {
			Arrays.fill(arr, Integer.MAX_VALUE);
		}

		for (int[] line : lines) {
			int s = line[0];
			int e = line[1];
			int d = line[2];
			if (map[s][e] > d) map[s][e] = d;
		}

		Queue<Integer> queue = new LinkedList<>();
		long[] dist = new long[n+1];
		Arrays.fill(dist, Long.MAX_VALUE);
		boolean[] inQueue = new boolean[n+1];
		int[] countLoop = new int[n+1];
		
		queue.add(1);
		dist[0] = 0;
		dist[1] = 0;
		inQueue[1] = true;
		countLoop[1]++;
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			inQueue[cur] = false;
			long curDist = dist[cur];
			
			for (int to = 1; to <= n; to++) {
				int nextDist = map[cur][to];
				if (to == cur || dist[cur] == Long.MAX_VALUE || nextDist == Integer.MAX_VALUE) continue;
				if (dist[to] > curDist + nextDist) {
					dist[to] = curDist + nextDist;
					if (!inQueue[to]) {
						queue.add(to);
						countLoop[to]++;
						inQueue[to] = true;
						if (countLoop[to] >= n) return new long[] {-1};
					}
				}

			}
		}

		long[] ans = new long[n-1];

		for (int i = 2; i <= n; i++) {
			ans[i-2] = dist[i] == Long.MAX_VALUE ? -1 : dist[i];
		}
		
		return ans;
	}

	public static void main(String[] args) {
		TimeMachine thisClass = new TimeMachine();
		CodingTest test = new CodingTest<>(thisClass::solution, INT , INT, INT_MATRIX, LONG_ARRAY);

		test.codingTest("3	4	[[1, 2, 4],[1, 3, 3],[2, 3, -1],[3, 1, -2]]	[4,3]");
		test.codingTest("3	4	[[1, 2, 4],[1, 3, 3],[2, 3, -4],[3, 1, -2]]	[-1]");
		test.codingTest("3	2	[[1, 2, 4],[1, 2, 3]]	[3,-1]");
		test.codingTest("4	9	[[1, 2,  3],[1, 3,  4],[1, 4, 0],[2, 1, 5],[2, 3, 1],[2, 4, -1],[3, 4, 5],[4, 2, 3],[4, 3, 3]]	[3, 3, 0]");
	}

	/*public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] lines = new int[M][3];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = Integer.parseInt(st.nextToken());
			lines[i][1] = Integer.parseInt(st.nextToken());
			lines[i][2] = Integer.parseInt(st.nextToken());
		}

		long[] ans = new TimeMachine().solution(N, M, lines);

		StringBuilder sb = new StringBuilder();
		for (long a : ans) {
			sb.append(a).append('\n');
		}
		System.out.print(sb);

		br.close();
	}*/
}

/*
타임머신
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	256 MB	75129	19152	12217	26.358%
문제
N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 각 버스는 A, B, C로 나타낼 수 있는데, 
A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 시간 C가 양수가 아닌 경우가 있다. 
C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.

1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 
둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 

출력
만약 1번 도시에서 출발해 어떤 도시로 가는 과정에서 시간을 무한히 오래 전으로 되돌릴 수 있다면 첫째 줄에 -1을 출력한다. 
그렇지 않다면 N-1개 줄에 걸쳐 각 줄에 1번 도시에서 출발해 2번 도시, 3번 도시, ..., N번 도시로 가는 가장 빠른 시간을 순서대로 출력한다. 
만약 해당 도시로 가는 경로가 없다면 대신 -1을 출력한다.

예제 입력 1 
3 4
1 2 4
1 3 3
2 3 -1
3 1 -2
예제 출력 1 
4
3
예제 입력 2 
3 4
1 2 4
1 3 3
2 3 -4
3 1 -2
예제 출력 2 
-1
예제 입력 3 
3 2
1 2 4
1 2 3
예제 출력 3 
3
-1
* */
