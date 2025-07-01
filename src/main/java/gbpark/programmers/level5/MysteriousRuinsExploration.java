package gbpark.programmers.level5;

import gbpark.common.CodingTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static gbpark.common.CodingTest.DataType.INT;
import static gbpark.common.CodingTest.DataType.INT_MATRIX;

public class MysteriousRuinsExploration {
	static Tree root1;
	static Tree root2;
	static Map<String, Map<String,Integer>> map = new HashMap<>();
	static List<int[]> staticList;

	public int solution(int n1, int[][] g1, int n2, int[][] g2) {
		staticList = new ArrayList<>();

		root1 = makeTree(n1, g1);
		root2 = makeTree(n2, g2);

		return getInter(root1, root2);
	}

	int getInter(Tree tree1, Tree tree2) {
		List<Tree> c1 = tree1.children;
		List<Tree> c2 = tree2.children;

		if (c1.isEmpty() || c2.isEmpty()) return 1;

		int[][] matrix = new int[c1.size()][c2.size()];

		for (int i = 0; i < c1.size(); i++) {
			Tree t1 = c1.get(i);
			for (int j = 0; j < c2.size(); j++) {
				Tree t2 = c2.get(j);
				String h1 = t1.hash;
				String h2 = t2.hash;

				if (!map.containsKey(h1) || !map.get(h1).containsKey(h2)) {
					int inter = getInter(t1, t2);
					Map<String, Integer> h1Map = map.getOrDefault(h1, new HashMap<>());
					h1Map.put(h2, inter);
					map.put(h1, h1Map);
					Map<String, Integer> h2Map = map.getOrDefault(h2, new HashMap<>());
					h2Map.put(h1, inter);
					map.put(h2, h2Map);
				}

				matrix[i][j] = -map.get(h1).get(h2);
			}
		}

		return -mcmf(matrix) + 1;
	}
	
	int mcmf(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int NUM_NODES = m + n + 2;
		int IDX_START_M = 1;
		int IDX_START_N = m + 1;
		int[][] pos = new int[NUM_NODES][NUM_NODES];
		int[][] cost = new int[NUM_NODES][NUM_NODES];

		for (int i = IDX_START_M; i < IDX_START_M + m; i++) {
			pos[0][i] = 1;
			for (int j = IDX_START_N; j < IDX_START_N + n; j++) {
				pos[j][NUM_NODES-1] = 1;
				pos[i][j] = 1;
				cost[i][j] = matrix[i-IDX_START_M][j-IDX_START_N];
				cost[j][i] = -cost[i][j];
			}
		}
		
		for (int j = IDX_START_N; j < IDX_START_N + n; j++) {
			pos[j][NUM_NODES-1] = 1;
		}

		boolean findSpfa = true;
		int[] totalCost = new int[1];
		while (findSpfa) {
			findSpfa = spfa(pos, cost, totalCost);
		}
		
		return totalCost[0];
	}
	
	boolean spfa(int[][] pos, int[][] cost, int[] totalCost) {
		int NUM_NODES = pos.length;
		Queue<Integer> queue = new LinkedList<>();
		boolean[] inQ = new boolean[NUM_NODES];
		int[] dist = new int[NUM_NODES];
		int[] fromPath = new int[NUM_NODES];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		queue.add(0);

		while (!queue.isEmpty()) {
			int cur = queue.poll();
			inQ[cur] = false;

			for (int i = 1; i < NUM_NODES; i++) {
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

		if (dist[NUM_NODES-1] == Integer.MAX_VALUE) return false;

		int point = NUM_NODES-1;
		while (point > 0) {
			int from = fromPath[point];
			pos[from][point]--;
			pos[point][from]++;
			point = from;
		}

		totalCost[0]+=dist[NUM_NODES-1];
		return true;
	}


	Tree makeTree(int n, int[][] g) {
		Tree[] arr = new Tree[n+1];
		boolean[] visit = new boolean[n+1];
		arr[1] = new Tree();
		arr[1].id= 1;
		visit[1] = true;
		int cnt = 1;

		while (cnt < n) {
			for (int[] graph : g) {
				int x = graph[0];
				int y = graph[1];
				if (visit[x] == visit[y]) continue;
				int parent = visit[x] ? x : y;
				int child = visit[y] ? x : y;
				arr[child] = new Tree();
				arr[child].id= child;
				Tree tree = arr[parent];
				tree.children.add(arr[child]);
				visit[child] = true;
				cnt++;
			}
		}

		traversal(arr[1]);
		return arr[1];
	}

	String traversal(Tree tree) {
		if (tree.children.isEmpty()) return "()";

		List<Tree> children = tree.children;
		StringBuilder sb = new StringBuilder("(");
		PriorityQueue<String> pq = new PriorityQueue<>();

		for (Tree child : children) {
			pq.add(traversal(child));
		}

		while (!pq.isEmpty()) {
			sb.append(pq.poll());
		}

		tree.hash =  sb.append(")").toString();
		return sb.toString();
	}

	static class Tree {
		int id = 0;
		String hash = "()";
		List<Tree> children = new ArrayList<>();
	}

	public static void main(String[] args) {
		MysteriousRuinsExploration thisClass = new MysteriousRuinsExploration();
		CodingTest test = new CodingTest<>(thisClass::solution, INT, INT_MATRIX, INT, INT_MATRIX, INT);
		test.codingTest("8\t\n" +
				"[[3, 1], [5, 7], [8, 7], [2, 3], [3, 6], [1, 5], [4, 3]]\t\n" +
				"9\t\n" +
				"[[1, 5], [5, 6], [3, 7], [3, 1], [7, 4], [7, 2], [9, 8], [5, 9]]\t\n" +
				"7");
	}
}

/*
신비로운 유적 탐험
카카오 고고학 연구팀은 20,000년 전 문명의 발상지를 조사하던 중 벽면에 그려진 비슷한 모양의 그림을 여러 개 발견하게 되었다.
각각의 그림은 트리 형태로 구성되어 있으며 그림의 일부가 유실되어 전체의 정보를 해독할 수는 없었다.

연구팀은 이 그림들이 특정한 부족의 가계도를 그린 것으로 추정하였다.
트리의 루트는 부족의 조상을 의미하며, 루트의 자식들은 트리 상에서 부모-자식 관계로 연결되어 있고,
그들의 자식은 또 새로운 가지로 연결되어 있는 식이다.

이렇게 가족 전체의 관계가 여러 개의 (같은 모양의) 그림으로 남아있으며,
유실된 정보를 제외하고 공통적으로 남아있는 정보를 토대로 이 부족에 대한 연구를 진행할 수 있다고 판단하였다.
다행히 그림의 중심부에는 유실의 흔적이 없어 두 그림의 루트는 같은 사람을 의미한다고 간주할 수 있었다.

아래 그림은 서로 다른 두 그림에서 얻은 정보를 보기 좋은 형태로 나타낸 것이다.
트리의 번호들은 한 트리에서 각각의 정점을 구별하기 위한 것으로 두 트리의 같은 번호가 같은 사람이라는 의미는 아니다.

트리

가설이 맞는지를 확인하기 위해, 두 그림에서 얻은 트리의 공통부분이 얼마나 되는지를 알아보고 싶다.
그림에서 확인할 수 있는 정보는 상대적인 부모-자식 관계가 전부이기 때문에 자식들의 이름이나 순서 등의 정보는 없다.
따라서 자식들의 순서를 무시하고, 각 트리의 루트를 포함하는 부분 트리로서 두 트리에 모두 포함되는 트리를 공통부분으로 정의하자.

두 개의 트리를 입력으로 받아 최대 공통부분의 크기를 계산하는 프로그램을 작성하라.

입력 형식
입력은 두 트리를 나타내는 n1, g1, n2, g2로 주어진다. n1, n2는 각 트리의 노드 수를 의미한다. g1, g2는 트리의 정보를 나타내는 값으로,
각각 크기가 (n1 - 1) × 2와 (n2 - 1) × 2인 2차원 배열로 주어진다.
g1, g2의 각각의 행은 연결된 두 노드를 의미하는데, 두 개의 값 중 하나가 부모 노드의 번호, 다른 하나가 자식 노드의 번호이다.

입력되는 값의 제한조건은 아래와 같다.

1 <= n1, n2 <= 100
노드 번호는 각각 1부터 n1, n2까지의 값이 사용되며, 각 트리의 1번 노드가 루트이다.
입력되는 데이터는 항상 올바른 트리임이 보장된다.
출력 형식
두 트리의 최대 공통부분의 노드 수를 리턴한다.

예제 입출력
param	value
8
[[3, 1], [5, 7], [8, 7], [2, 3], [3, 6], [1, 5], [4, 3]]
9
[[1, 5], [5, 6], [3, 7], [3, 1], [7, 4], [7, 2], [9, 8], [5, 9]]
7
* */
