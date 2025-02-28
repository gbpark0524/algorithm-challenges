package gbpark.programmers.level3;

import gbpark.common.ArrayConverter;
import gbpark.common.TestUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MovingCart {
	public static void main(String[] args) {
		MovingCart mc = new MovingCart();
		List<Integer> an = new ArrayList<>();
		List<int[][]> qu = new ArrayList<>();

		qu.add(ArrayConverter.toIntMatrix("[[1, 4], [0, 0], [2, 3]]"));
		an.add(3);
		qu.add(ArrayConverter.toIntMatrix("[[1, 0, 2], [0, 0, 0], [5, 0 ,5], [4, 0, 3]]"));
		an.add(7);
		qu.add(ArrayConverter.toIntMatrix("[[1, 5], [2, 5], [4, 5], [3, 5]]	        "));
		an.add(0);
		qu.add(ArrayConverter.toIntMatrix("[[4, 1, 2, 3]]	                            "));
		an.add(0);


		for (int i = 0; i < an.size(); i++) {
			TestUtil.startTimer();
			TestUtil.test(an.get(i), mc.solution(qu.get(i)));
		}
	}

	static int[][] posMove = new int[4][];

	public int solution(int[][] maze) {
		int answer = 0;
		posMove[0] = new int[]{1, 0};
		posMove[1] = new int[]{-1, 0};
		posMove[2] = new int[]{0, 1};
		posMove[3] = new int[]{0, -1};
		// red, blue, turn
		Queue<Object[]> que = new LinkedList<>();
		int xr = 0;
		int yr = 0;
		int xb = 0;
		int yb = 0;
		int lengthN = maze.length;
		int lengthM = maze[0].length;

		int[][] map = new int[lengthN + 2][];
		map[0] = new int[lengthM + 2];
		map[lengthN + 1] = new int[lengthM + 2];
		for (int i = 0; i < lengthM + 2; i++) {
			map[0][i] = 5;
			map[lengthN + 1][i] = 5;
		}

		for (int i = 1; i <= lengthN; i++) {
			map[i] = new int[lengthM + 2];
			map[i][0] = 5;
			for (int j = 1; j <= lengthM; j++) {
				map[i][j] = maze[i - 1][j - 1];
				if (map[i][j] == 1) {
					xr = i;
					yr = j;
				} else if (map[i][j] == 2) {
					xb = i;
					yb = j;
				}
			}
			map[i][lengthM + 1] = 5;
		}

		Node red = new Node(xr, yr, map);
		red.map[xr][yr] = 5;
		Node blue = new Node(xb, yb, map);
		blue.map[xb][yb] = 5;
		que.add(new Object[]{red, blue, 0});

		while (!que.isEmpty()) {
			Object[] cur = que.poll();
			answer = dfs((Node) cur[0], (Node) cur[1], (int) cur[2], que);
			if (answer != 0) break;
		}

		return answer;
	}

	int dfs(Node red, Node blue, int turn, Queue<Object[]> que) {
		int xR = red.x;
		int yR = red.y;
		int xB = blue.x;
		int yB = blue.y;
		turn++;

		if (red.isGoal) {
			for (int[] ints : posMove) {
				xB = blue.x + ints[0];
				yB = blue.y + ints[1];

				if (blue.map[xB][yB] == 4) {
					return turn;
				} else if (blue.map[xB][yB] == 5 || (xB == xR && yB == yR)) {
					continue;
				}

				Node blueN = new Node(xB, yB, blue.map);
				blueN.map[xB][yB] = 5;
				que.add(new Object[]{red, blueN, turn});
			}
		} else if (blue.isGoal) {
			for (int[] ints : posMove) {
				xR = red.x + ints[0];
				yR = red.y + ints[1];

				if (red.map[xR][yR] == 3) {
					return turn;
				} else if (red.map[xR][yR] == 5 || (xB == xR && yB == yR)) {
					continue;
				}
				Node redN = new Node(xR, yR, red.map);
				redN.map[xR][yR] = 5;
				que.add(new Object[]{redN, blue, turn});
			}
		} else {
			for (int[] posR : posMove) {
				xR = red.x + posR[0];
				yR = red.y + posR[1];

				if (red.map[xR][yR] != 5) {
					for (int[] posB : posMove) {
						xB = blue.x + posB[0];
						yB = blue.y + posB[1];
						if (blue.map[xB][yB] == 5 || (xB == xR && yB == yR) || (xR == blue.x && yR == blue.y && xB == red.x && yB == red.y)) {
							continue;
						}

						Node redN = new Node(xR, yR, red.map);
						if (redN.map[xR][yR] == 3) redN.isGoal = true;
						redN.map[xR][yR] = 5;
						Node blueN = new Node(xB, yB, blue.map);
						if (blueN.map[xB][yB] == 4) blueN.isGoal = true;
						blueN.map[xB][yB] = 5;

						if (redN.isGoal && blueN.isGoal) return turn;

						que.add(new Object[]{redN, blueN, turn});
					}
				}
			}
		}

		return 0;
	}

	static class Node {
		int x;
		int y;
		int[][] map;
		boolean isGoal = false;

		public Node(int x, int y, int[][] map) {
			this.x = x;
			this.y = y;
			this.map = new int[map.length][];
			for (int i = 0; i < map.length; i++) {
				int[] arr = map[i];
				this.map[i] = new int[arr.length];
				System.arraycopy(arr, 0, this.map[i], 0, arr.length);
			}
		}
	}
}

	/*
	n x m 크기 격자 모양의 퍼즐판이 주어집니다.

	퍼즐판에는 빨간색 수레와 파란색 수레가 하나씩 존재합니다. 각 수레들은 자신의 시작 칸에서부터 자신의 도착 칸까지 이동해야 합니다.
	모든 수레들을 각자의 도착 칸으로 이동시키면 퍼즐을 풀 수 있습니다.

	당신은 각 턴마다 반드시 모든 수레를 상하좌우로 인접한 칸 중 한 칸으로 움직여야 합니다. 단, 수레를 움직일 때는 아래와 같은 규칙이 있습니다.

	수레는 벽이나 격자 판 밖으로 움직일 수 없습니다.
	수레는 자신이 방문했던 칸으로 움직일 수 없습니다.
	자신의 도착 칸에 위치한 수레는 움직이지 않습니다. 계속 해당 칸에 고정해 놓아야 합니다.
	동시에 두 수레를 같은 칸으로 움직일 수 없습니다.
	수레끼리 자리를 바꾸며 움직일 수 없습니다.

	퍼즐판의 정보를 나타내는 2차원 정수 배열 maze가 매개변수로 주어집니다.
	퍼즐을 푸는데 필요한 턴의 최솟값을 return 하도록 solution 함수를 완성해 주세요.
	퍼즐을 풀 수 없는 경우 0을 return 해주세요.

	제한사항
	1 ≤ maze의 길이 (= 세로 길이) ≤ 4
	1 ≤ maze[i]의 길이 (= 가로 길이) ≤ 4
	maze[i][j]는 0,1,2,3,4,5 중 하나의 값을 갖습니다.
	maze[i][j]	의미
	0	빈칸
	1	빨간 수레의 시작 칸
	2	파란 수레의 시작 칸
	3	빨간 수레의 도착 칸
	4	파란 수레의 도착 칸
	5	벽
	빨간 수레의 시작 칸, 빨간 수레의 도착 칸, 파란 수레의 시작 칸, 파란 수레의 도착 칸은 퍼즐판에 1개씩 존재합니다.
	입출력 예
	maze	result
	[[1, 4], [0, 0], [2, 3]]	                        3
	[[1, 0, 2], [0, 0, 0], [5, 0 ,5], [4, 0, 3]]        7
	[[1, 5], [2, 5], [4, 5], [3, 5]]	                0
	[[4, 1, 2, 3]]	                                    0

	예를 들어, 아래 그림처럼 n = 3, m = 2인 퍼즐판이 있습니다.

	rb_horse1.jpg

	속이 빨간색인 원은 빨간색 수레를 나타냅니다.
	속이 파란색인 원은 파란색 수레를 나타냅니다.
	테두리가 빨간색인 원은 빨간색 수레의 도착 칸을 나타냅니다.
	테두리가 파란색인 원은 파란색 수레의 도착 칸을 나타냅니다.
	위 퍼즐판은 아래와 같은 순서로 3턴만에 풀 수 있습니다.

	rb_horse2.jpg

	빨간색 사선이 처진 칸은 빨간색 수레가 방문했던 칸을 나타냅니다. 규칙에 따라 빨간색 수레는 빨간색 사선이 처진 칸(방문했던 칸)으로는 이동할 수 없습니다.
	파란색 사선이 처진 칸은 파란색 수레가 방문했던 칸을 나타냅니다. 규칙에 따라 파란색 수레는 파란색 사선이 처진 칸(방문했던 칸)으로는 이동할 수 없습니다.
	rb_horse3.jpg

	위처럼 동시에 수레를 같은 칸으로 움직일 수는 없습니다.
	* */

	/*
	난이도 어려웠음.
	머리 속에서 암산하지 말고 코드로 풀려고 노력 해야 함.
	줄이는건 리팩토링으로.
	* */