package gbpark.backjoon.platinum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BookPurchase {
	static int ans = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람 수
		int M = Integer.parseInt(st.nextToken()); // 서점 수 
		int START = 0;
		int MAN_START = 1;
		int MAN_END = N;
		int STORE_START = N + 1;
		int STORE_END = N + M;
		int END = N + M + 1;
		int NUM_NODES = END + 1;
		int CAP_MAX = 100;

		int[][] cost = new int[NUM_NODES][NUM_NODES];
		int[][] pos = new int[NUM_NODES][NUM_NODES];

		st = new StringTokenizer(br.readLine());
		for (int i = MAN_START; i <= MAN_END; i++) pos[START][i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = STORE_START; i <= STORE_END; i++) pos[i][END] = Integer.parseInt(st.nextToken());

		for (int s = STORE_START; s <= STORE_END; s++) {
			st = new StringTokenizer(br.readLine());
			for (int m = MAN_START; m <= MAN_END; m++) {
				pos[m][s] = CAP_MAX;
				cost[m][s] = Integer.parseInt(st.nextToken());
				cost[s][m] = -cost[m][s];
			}
		}

		br.close();

		boolean findSpfa = true;
		while (findSpfa) {
			findSpfa = spfa(pos, cost);
		}
		System.out.println(ans);
	}

	static boolean spfa(int[][] pos, int[][] cost) {
		int numNodes = pos.length;
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
				if (pos[cur][i] > 0 && dist[cur] + cost[cur][i] < dist[i]) {
					dist[i] = dist[cur] + cost[cur][i];
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
		int flow = Integer.MAX_VALUE;
		while (point > 0) {
			int from = fromPath[point];
			flow = Math.min(flow, pos[from][point]);
			point = from;
		}
		
		point = goal;
		while (point > 0) {
			int from = fromPath[point];
			pos[from][point] -= flow;
			pos[point][from] += flow;
			point = from;
		}

		ans += dist[goal] * flow;

		return true;
	}
}

/*
책 구매하기
 
시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
1 초	256 MB	2478	1561	1092	64.085%
문제
총 N명의 사람이 책을 구매하려고 한다. 각 사람은 1번부터 N번까지 번호가 매겨져 있고, 
각 사람이 사려고하는 책의 개수는 A1, A2, ..., AN권이다.
이 책을 판매하는 온라인 서점은 총 M곳이 있다.각 서점도 1번부터 M번까지 번호가 매겨져 있으며, 
각 서점이 가지고 있는 책의 개수는 B1, B2, ..., BM권 이다.

이 책을 사려고 하는 사람은 N명밖에 없으며, 서점에서 가지고 있는 책의 개수의 합과 사람들이 사려고 하는 책의 개수의 합은 같다.

이 온라인 서점은 책을 한권씩만 택배로 보낸다. 또, 택배비는 서점과 사람들 사이의 거리, 
회원 등급등 여러 가지 요인에 따라 결정된다. 서점 i에서 사람 j에게 책을 한 권 보내는데 필요한 배송비는 Cij원이다. 
모든 서점과 사람 사이의 배송비가 주어졌을 때, 각 사람이 책을 A1, A2, ..., AN권을 사는데 필요한 배송비의 합의 최솟값을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 사람의 수 N과 온라인 서점의 수 M이 주어진다. (1 ≤ N, M ≤ 100)

둘째 줄에는 각 사람이 사려고 하는 책의 개수 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100)

셋째 줄에는 각 온라인 서점이 가지고 있는 책의 개수 B1, B2, ..., BM이 주어진다. (1 ≤ Bi ≤ 100)

넷째 줄부터 M개의 줄에는 각 온라인 서점이 각 사람들에게 책을 한 권 보내는데 필요한 배송비 Cij가 주어진다. 
i번째 줄의 j번째 숫자는 온라인 서점 i에서 사람 j에게 책을 한 권 보내는데 필요한 배송비 Cij이다. (1 ≤ Cij ≤ 1,000)

A1 + A2 + ... + AN은 B1 + B2 + ... + BM과 같다.

출력
첫째 줄에 배송비의 최솟값을 출력한다.

예제 입력 1 
4 4
3 2 4 2
5 3 2 1
5 6 2 1
3 7 4 1
2 10 3 1
10 20 30 1
예제 출력 1 
30
힌트
서점 1은 책 4권을 3에게, 1권을 2에게 보내고, 
서점 2는 1, 2, 4에게 1권씩, 
서점 3은 1에게 2권, 
서점 4는 4에게 1권을 보내면 배송비의 합은 30이다.
 */
