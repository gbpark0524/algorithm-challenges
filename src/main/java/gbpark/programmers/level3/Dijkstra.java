package gbpark.programmers.level3;

import gbpark.common.ArrayConverter;
import gbpark.common.TestUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstra {
	public static void main(String[] args) {
		Dijkstra ob = new Dijkstra();
		int n = 5;
		int s = 1;
		int[][] fare = ArrayConverter.toIntMatrix("[[5,1,1],[1,2,2],[1,3,3],[2,3,4],[2,4,5],[3,4,6]]");
		int[][] fare2 = ArrayConverter.toIntMatrix("[1, 2, 7],[1, 3, 9],[1, 6, 14],[2, 3, 10], [2, 4, 15],[3, 4, 11],[3, 6, 2],[4, 5, 6],[5, 6, 9]");

		TestUtil.startTimer();
		TestUtil.test(ArrayConverter.toStringArray("0,2,3,7,INF"), ob.solution(n, s, fare));
		TestUtil.startTimer();
		TestUtil.test(ArrayConverter.toStringArray("0,7,9,20,26,11"), ob.solution(6, 1, fare2));
	}

	public String[] solution(int n, int s, int[][] fares) {
		n = n+1;
		int[][] map = new int[n][n];
		PriorityQueue<Node> pq = new PriorityQueue<>();

		for (int i = 1; i < n; i++) {
			Arrays.fill(map[i], -1);
			map[i][i] = 0;
		}

		for (int[] fare : fares) {
			map[fare[0]][fare[1]] = fare[2];
			if (fare[0] == s) pq.add(new Node(fare[1], fare[2]));
		}
		
		boolean[] visited = new boolean[n];
		visited[s] = true;
		int cnt = 1;
		int[] dist = new int[n];
		System.arraycopy(map[s], 0, dist, 0, n );
		
		while (cnt < n && !pq.isEmpty()) {
			Node polled = pq.poll();
			int target = polled.target;
			if (visited[target]) continue;
			visited[target] = true;
			cnt++;
			int costSum = polled.cost;

			for (int i = 1; i < n; i++) {
				if (visited[i]) continue;
				int viaCost = map[target][i];
				if (viaCost < 0) continue;
				
				int newCost = costSum + viaCost;
				if (dist[i] < 0 || dist[i] > newCost) {
					pq.add(new Node(i, newCost));
					dist[i] = newCost;
				}
			}
		}
		
		String[] ans = new String[n-1];
		for (int i = 1; i < n; i++) {
			ans[i-1] = dist[i] == -1 ? "INF" : String.valueOf(dist[i]);
		}

		return ans;
	}

	class Node implements Comparable<Node> {
		int target;
		int cost;

		public Node(int target, int cost) {
			this.target = target;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}

/*
5 6
1
5 1 1
1 2 2
1 3 3
2 3 4
2 4 5
3 4 6

0
2
3
7
INF
* */