package gbpark.programmers.level3;

import gbpark.common.ArrayConverter;
import gbpark.common.TestUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PuzzlePieceFilling {
	public static void main(String[] args) {
		PuzzlePieceFilling ppf = new PuzzlePieceFilling();
		List<Integer> an = new ArrayList<>();
		List<int[][]> qu = new ArrayList<>();
		List<int[][]> qu2 = new ArrayList<>();


		qu.add(ArrayConverter.toIntMatrix("[[1,1,0,0,1,0],[0,0,1,0,1,0],[0,1,1,0,0,1],[1,1,0,1,1,1],[1,0,0,0,1,0],[0,1,1,1,0,0]]"));
		qu.add(ArrayConverter.toIntMatrix("[[0,0,0],[1,1,0],[1,1,1]]"));
		qu2.add(ArrayConverter.toIntMatrix("[[1,0,0,1,1,0],[1,0,1,0,1,0],[0,1,1,0,1,1],[0,0,1,0,0,0],[1,1,0,1,1,0],[0,1,0,0,0,0]]"));
		qu2.add(ArrayConverter.toIntMatrix("[[1,1,1],[1,0,0],[0,0,0]]"));
		an.add(14);
		an.add(0);


		for (int i = 0; i < qu.size(); i++) {
			TestUtil.startTimer();
			TestUtil.test(an.get(i), ppf.solution(qu.get(i), qu2.get(i)));
		}
	}

	public int solution(int[][] game_board, int[][] table) {
		int answer = 0;
		int[][] gb = wrapMatrix(game_board);
		int[][] tb = wrapMatrix(table);
		int len = tb.length;
		List<int[]> paths = new ArrayList<>();
		List<Piece> tList = new ArrayList<>();
		List<Piece> gList = new ArrayList<>();

		for (int x = 1; x < len-1; x++) {
			for (int y = 1; y < len-1; y++) {
				if (tb[x][y]==1) {
					paths.clear();
					paths.add(new int[]{x,y});
					tb[x][y]= -1;
					dfs(tb, x, y, 1, paths);
					tList.add(new Piece(paths, len));
				}

				if (gb[x][y]==0) {
					paths.clear();
					paths.add(new int[]{x,y});
					gb[x][y]= -1;
					dfs(gb, x, y, 0, paths);
					gList.add(new Piece(paths, len));
				}
			}
		}

		for (Piece tablePiece : tList) {
			if (gList.contains(tablePiece)) {
				gList.remove(tablePiece);
				answer += tablePiece.size;
			}
		}

		return answer;
	}

	private int[][] wrapMatrix(int[][] matrix) {
		int wrap = -1;
		int len = matrix.length + 2;
		int[][] result = new int[len][len];
		result[0] = new int[len];
		result[len - 1] = new int[len];
		for (int i = 1; i < len - 1; i++) {
			result[i][0] = wrap;
			result[0][i] = wrap;
			result[i][len - 1] = wrap;
			result[len - 1][i] = wrap;
			System.arraycopy(matrix[i - 1], 0, result[i], 1, len - 2);
		}

		return result;
	}

	void dfs(int[][] board, int cx, int cy, int target, List<int[]> paths) {
		int[][] ways = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

		for (int[] way : ways) {
			int x = cx + way[0];
			int y = cy + way[1];
			if (board[x][y] == target) {
				paths.add(new int[]{x, y});
				board[x][y] = -1;
				dfs(board, x, y, target, paths);
			}
		}
	}

	static class Piece {
		int[][] map;
		int lenX;
		int lenY;
		int size;

		public Piece() {}

		public Piece(List<int[]> paths, int len) {
			size = paths.size();
			int minX = len;
			int maxX = -len;
			int minY = len;
			int maxY = -len;
			for (int[] path : paths) {
				minX = Math.min(minX, path[0]);
				maxX = Math.max(maxX, path[0]);
				minY = Math.min(minY, path[1]);
				maxY = Math.max(maxY, path[1]);
			}

			lenX = maxX - minX + 1;
			lenY = maxY - minY + 1;
			map = new int[lenX][lenY];

			for (int[] path : paths) {
				map[path[0]-minX][path[1]-minY] = 1;
			}
		}

		public void rotate() {
			int[][] rotated = new int[lenY][lenX];

			for (int n = 0; n < lenX; n++) {
				for (int m = 0; m < lenY; m++) {
					rotated[m][lenX-1-n] = map[n][m];
				}
			}

			this.map = rotated;
			int t = lenX;
			this.lenX = lenY;
			this.lenY = t;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Piece)) return false;
			Piece p = (Piece) obj;

			for (int n = 0; n < 4; n++) {
				if (lenX == p.lenX && lenY == p.lenY) {
					int cnt = 0;
					int[][] pm = p.map;
					for (int i = 0; i < lenX; i++) {
						if (!Arrays.equals(map[i], pm[i])) {
							break;
						} else {
							cnt++;
						}
					}
					if (cnt == lenX) return true;
				}
				if (n < 3) p.rotate();
			}

			return false;
		}
	}
}

/*
테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈 공간에 적절히 올려놓으려 합니다. 
게임 보드와 테이블은 모두 각 칸이 1x1 크기인 정사각 격자 모양입니다. 
이때, 다음 규칙에 따라 테이블 위에 놓인 퍼즐 조각을 게임 보드의 빈칸에 채우면 됩니다.

조각은 한 번에 하나씩 채워 넣습니다.
조각을 회전시킬 수 있습니다.
조각을 뒤집을 수는 없습니다.
게임 보드에 새로 채워 넣은 퍼즐 조각과 인접한 칸이 비어있으면 안 됩니다.
다음은 퍼즐 조각을 채우는 예시입니다.

puzzle_5.png

위 그림에서 왼쪽은 현재 게임 보드의 상태를, 오른쪽은 테이블 위에 놓인 퍼즐 조각들을 나타냅니다. 
테이블 위에 놓인 퍼즐 조각들 또한 마찬가지로 [상,하,좌,우]로 인접해 붙어있는 경우는 없으며, 
흰 칸은 퍼즐이 놓이지 않은 빈 공간을 나타냅니다. 
모든 퍼즐 조각은 격자 칸에 딱 맞게 놓여있으며, 격자 칸을 벗어나거나, 걸쳐 있는 등 잘못 놓인 경우는 없습니다.

이때, 아래 그림과 같이 3,4,5번 조각을 격자 칸에 놓으면 규칙에 어긋나므로 불가능한 경우입니다.

puzzle_6.png

3번 조각을 놓고 4번 조각을 놓기 전에 위쪽으로 인접한 칸에 빈칸이 생깁니다.
5번 조각의 양 옆으로 인접한 칸에 빈칸이 생깁니다.
다음은 규칙에 맞게 최대한 많은 조각을 게임 보드에 채워 넣은 모습입니다.

puzzle_7.png

최대한 많은 조각을 채워 넣으면 총 14칸을 채울 수 있습니다.

현재 게임 보드의 상태 game_board, 테이블 위에 놓인 퍼즐 조각의 상태 table이 매개변수로 주어집니다. 
규칙에 맞게 최대한 많은 퍼즐 조각을 채워 넣을 경우, 총 몇 칸을 채울 수 있는지 return 하도록 solution 함수를 완성해주세요.

제한사항
3 ≤ game_board의 행 길이 ≤ 50
game_board의 각 열 길이 = game_board의 행 길이
즉, 게임 보드는 정사각 격자 모양입니다.
game_board의 모든 원소는 0 또는 1입니다.
0은 빈칸, 1은 이미 채워진 칸을 나타냅니다.
퍼즐 조각이 놓일 빈칸은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
table의 행 길이 = game_board의 행 길이
table의 각 열 길이 = table의 행 길이
즉, 테이블은 game_board와 같은 크기의 정사각 격자 모양입니다.
table의 모든 원소는 0 또는 1입니다.
0은 빈칸, 1은 조각이 놓인 칸을 나타냅니다.
퍼즐 조각은 1 x 1 크기 정사각형이 최소 1개에서 최대 6개까지 연결된 형태로만 주어집니다.
game_board에는 반드시 하나 이상의 빈칸이 있습니다.
table에는 반드시 하나 이상의 블록이 놓여 있습니다.
입출력 예
game_board	table	result
[[1,1,0,0,1,0],[0,0,1,0,1,0],[0,1,1,0,0,1],[1,1,0,1,1,1],[1,0,0,0,1,0],[0,1,1,1,0,0]]	[[1,0,0,1,1,0],[1,0,1,0,1,0],[0,1,1,0,1,1],[0,0,1,0,0,0],[1,1,0,1,1,0],[0,1,0,0,0,0]]	14
[[0,0,0],[1,1,0],[1,1,1]]																[[1,1,1],[1,0,0],[0,0,0]]	0
*/
