package gbpark.programmers.level4;

import gbpark.common.CodingTest;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import static gbpark.common.CodingTest.DataType.INT;
import static gbpark.common.CodingTest.DataType.INT_MATRIX;

public class TerrainMovement {
	static Queue<Node> queue = new LinkedList<>();
	static int[][] paddedLand;
	static int diff;
	static PriorityQueue<Node> diffNodes = new PriorityQueue<>();
	static int[][] moves = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
	
	public int solution(int[][] land, int height) {
		int answer = 0;
		diff = height;
		int n = land.length;
		paddedLand = new int[n +2][n +2];

		for (int i = 0; i < n; i++) {
			System.arraycopy(land[i],0, paddedLand[i+1],1, n);
		}
		queue.clear();
		queue.add(new Node(1,1, paddedLand[1][1]));
		paddedLand[1][1] = 0;
		boolean hasRemain = true;
		Node ladderNode;
		
		while (hasRemain) {
			while (!queue.isEmpty()) {
				bfs();
			}
			
			hasRemain = false;
			while (!diffNodes.isEmpty()) {
				ladderNode = diffNodes.poll();
				if (paddedLand[ladderNode.m][ladderNode.n] != 0) {
					answer += ladderNode.h;
					queue.add(new Node(ladderNode.m, ladderNode.n, paddedLand[ladderNode.m][ladderNode.n]));
					paddedLand[ladderNode.m][ladderNode.n] = 0;
					hasRemain = true;
					break;
				}
			}
		}
		
		return answer;
	}
	
	void bfs() {
		Node node = queue.poll();
		int m = node.m;
		int n = node.n;
		int h = node.h;

		for (int[] move : moves) {
			int newM = m + move[0];
			int newN = n + move[1];
			if (paddedLand[newM][newN] == 0) continue;
			
			int curDiff = Math.abs(paddedLand[newM][newN] - h);
			if (curDiff <= diff) {
				queue.add(new Node(newM, newN, paddedLand[newM][newN]));
				paddedLand[newM][newN] = 0;
			} else {
				diffNodes.add(new Node(newM, newN, curDiff));
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int m, n, h;
		public Node(int m, int n, int h) {
			this.m = m;
			this.n = n;
			this.h = h;
		}

		@Override
		public int compareTo(Node o) {
			return h - o.h;
		}
	}

	public static void main(String[] args) {
		TerrainMovement thisClass = new TerrainMovement();
		CodingTest test = new CodingTest<>(thisClass::solution, INT_MATRIX, INT, INT);
		test.codingTest("[[1, 4, 8, 10], [5, 5, 5, 5], [10, 10, 10, 10], [10, 10, 10, 20]]\t3\t15");
		test.codingTest("[[10, 11, 10, 11], [2, 21, 20, 10], [1, 20, 21, 11], [2, 1, 2, 1]]\t1\t18 ");
	}
}

/* 지형이동
N x N 크기인 정사각 격자 형태의 지형이 있습니다. 각 격자 칸은 1 x 1 크기이며, 숫자가 하나씩 적혀있습니다. 
격자 칸에 적힌 숫자는 그 칸의 높이를 나타냅니다.

이 지형의 아무 칸에서나 출발해 모든 칸을 방문하는 탐험을 떠나려 합니다. 
칸을 이동할 때는 상, 하, 좌, 우로 한 칸씩 이동할 수 있는데, 
현재 칸과 이동하려는 칸의 높이 차가 height 이하여야 합니다. 
높이 차가 height 보다 많이 나는 경우에는 사다리를 설치해서 이동할 수 있습니다. 
이때, 사다리를 설치하는데 두 격자 칸의 높이차만큼 비용이 듭니다. 따라서, 최대한 적은 비용이 들도록 사다리를 설치해서 모든 칸으로 이동 가능하도록 해야 합니다. 설치할 수 있는 사다리 개수에 제한은 없으며, 설치한 사다리는 철거하지 않습니다.

각 격자칸의 높이가 담긴 2차원 배열 land와 이동 가능한 최대 높이차 height가 매개변수로 주어질 때, 모든 칸을 방문하기 위해 필요한 사다리 설치 비용의 최솟값을 return 하도록 solution 함수를 완성해주세요.

제한사항
land는 N x N크기인 2차원 배열입니다.
land의 최소 크기는 4 x 4, 최대 크기는 300 x 300입니다.
land의 원소는 각 격자 칸의 높이를 나타냅니다.
격자 칸의 높이는 1 이상 10,000 이하인 자연수입니다.
height는 1 이상 10,000 이하인 자연수입니다.
입출력 예
land	height	result
[[1, 4, 8, 10], [5, 5, 5, 5], [10, 10, 10, 10], [10, 10, 10, 20]]	3	15
[[10, 11, 10, 11], [2, 21, 20, 10], [1, 20, 21, 11], [2, 1, 2, 1]]	1	18 
* */
