package gbpark.backjoon.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class YeolhyeolGangho6 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int numNodes = N + M + 2;
		int goal = numNodes - 1;
		int[][] costs = new int[numNodes][numNodes];
		int[][] caps = new int[numNodes][numNodes];
		int[][] flows = new int[numNodes][numNodes];
		int[] ans = new int[2];
		
		for (int emp = 1; emp <= N; emp++) {
			st = new StringTokenizer(br.readLine());
			int curN = Integer.parseInt(st.nextToken());
			caps[0][emp] = 1;
			for (int j = 0; j < curN; j++) {
				int work = Integer.parseInt(st.nextToken()) + N;
				int curCost = -Integer.parseInt(st.nextToken());
				caps[emp][work] = 1;
				costs[emp][work] = curCost;
				costs[work][emp] = -curCost;
			}
		}

		for (int i = N + 1; i < goal; i++) {
			caps[i][goal] = 1;
		}

		br.close();

		boolean findSpfa = true;
		while (findSpfa) {
			findSpfa = spfa(caps, costs, flows, ans);
		}
		
		System.out.println(ans[0]);
		System.out.println(-ans[1]);
	}
	
	static boolean spfa(int[][] caps, int[][] costs, int[][] flows, int[] ans) {
		int numNodes = caps.length;
		int goal = numNodes - 1;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] inQ = new boolean[numNodes];
		int[] dist = new int[numNodes];
		int[] fromPath = new int[numNodes];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		queue.add(0);
		inQ[0] = true;

		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			inQ[cur] = false;

			for (int i = 1; i < numNodes; i++) {
				if (caps[cur][i] - flows[cur][i] > 0 && dist[cur] + costs[cur][i] < dist[i]) {
					dist[i] = dist[cur] + costs[cur][i];
					fromPath[i] = cur;
					if (!inQ[i]) {
						queue.add(i);
						inQ[i] = true;
					}
				}
			}
		}

		if (dist[goal] == Integer.MAX_VALUE) return false;
		
		int point = goal;
		while (point > 0) {
			int from = fromPath[point];
			flows[from][point]++;
			flows[point][from]--;
			point = from;
		}
		
		ans[0]++;
		ans[1]+=dist[goal];
		
		return true;
	}
}

/*
열혈강호 5
 
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
2 초	256 MB	4046	2368	1386	56.456%
문제
강호네 회사에는 직원이 N명이 있고, 해야 할 일이 M개가 있다. 직원은 1번부터 N번까지 번호가 매겨져 있고, 
일은 1번부터 M번까지 번호가 매겨져 있다.

각 직원은 자신이 할 수 있는 일들 중 한 개의 일만 담당할 수 있고, 
각각의 일을 담당하는 사람은 1명이어야 한다.

각각의 직원이 할 수 있는 일의 목록과 그 일을 할 때 강호가 지급해야 하는 월급이 주어졌을 때, 
M개의 일 중에서 최대 몇 개를 할 수 있는지, 그리고 그 때 강호가 지불해야 하는 월급의 최솟값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 직원의 수 N과 일의 개수 M이 주어진다. (1 ≤ N, M ≤ 400)

둘째 줄부터 N개의 줄의 i번째 줄에는 i번 직원이 할 수 있는 일의 개수와 할 수 있는 일의 번호와 그 일을 할 때 지급해야 하는 월급이 주어진다. 
월급은 10,000보다 작거나 같은 자연수 또는 0이다.

출력
첫째 줄에 강호네 회사에서 할 수 있는 일의 개수를 출력한다.

둘째 줄에는 강호가 지급해야 하는 월급의 최솟값을 출력한다.

예제 입력 1 
5 5
2 1 3 2 2
1 1 5
2 2 1 3 7
3 3 9 4 9 5 9
1 1 0
예제 출력 1 
4
23
*/
