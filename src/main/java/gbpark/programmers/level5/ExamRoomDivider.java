package gbpark.programmers.level5;

import gbpark.common.CodingTest;

import static gbpark.common.CodingTest.DataType.INT;
import static gbpark.common.CodingTest.DataType.INT_ARRAY;
import static gbpark.common.CodingTest.DataType.INT_MATRIX;

public class ExamRoomDivider {
	static int size;
	static Tree[] trees;

	public int solution(int k, int[] num, int[][] links) {
		trees = new Tree[links.length];
		int root = -1;
		int low = 0;
		int high = 0;

		makeTrees(links, trees);
		
		for (int i = 0; i < trees.length; i++) {
			high += num[i];
			low = Math.max(num[i], low);
			Tree tree = trees[i];
			if (tree.parent == -1) {
				root = i;
			}
		}

		int mid = (high - low) / 2 + low;
		while (low < high) {
			if (count(num, mid, root) <= k) {
				high = mid;
			} else {
				low = mid + 1;
			}
			mid = (high - low) / 2 + low;
		}
		return mid;
	}

	int count(int[] num, int max, int root) {
		size = 1;
		dfs(num, root, max);
		return size;
	}

	int dfs(int[] num, int cur, int max) {
		int left = 0, right = 0;
		if (trees[cur].left != -1) left = dfs(num, trees[cur].left, max);
		if (trees[cur].right != -1) right = dfs(num, trees[cur].right, max);

		if (num[cur] + left + right <= max) {
			return num[cur] + left + right;
		} else if (num[cur] + Math.min(left, right) <= max) {
			size += 1;
			return num[cur] + Math.min(left, right);
		}

		size += 2;
		return num[cur];
	}

	static void makeTrees(int[][] links, Tree[] trees) {
		for (int i = 0; i < links.length; i++) {
			int[] link = links[i];
			Tree tree;
			if (trees[i] == null) {
				trees[i] = new Tree();
			}

			tree = trees[i];
			int leftId = link[0];
			int rightId = link[1];
			if (leftId > -1) {
				if (trees[leftId] == null) {
					Tree temp = new Tree();
					trees[leftId] = temp;
				}
				trees[leftId].parent = i;
				tree.left = leftId;
			}

			if (rightId > -1) {
				if (trees[rightId] == null) {
					Tree temp = new Tree();
					trees[rightId] = temp;
				}
				trees[rightId].parent = i;
				tree.right = rightId;
			}
		}
	}

	static class Tree {
		int parent = -1;
		int left = -1;
		int right = -1;
	}

	public static void main(String[] args) {
		ExamRoomDivider thisClass = new ExamRoomDivider();
		CodingTest<Integer, int[], int[][], Object, Object, Object, Integer> test = new CodingTest<>(thisClass::solution, INT, INT_ARRAY, INT_MATRIX, INT);
		test.codingTest("3	[12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1]	[[-1, -1], [-1, -1], [-1, -1], [-1, -1], [8, 5], [2, 10], [3, 0], [6, 1], [11, -1], [7, 4], [-1, -1], [-1, -1]]	40");
		test.codingTest("1	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]	27");
		test.codingTest("2	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]	14");
		test.codingTest("4	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]	9");
	}
}

/* 시험장 나누기
[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

카카오 인턴을 선발하는 코딩 테스트 시험장이 하나의 이진 트리1 형태로 연결되어 있습니다. 
아래 그림은 12개의 시험장이 연결된 예시입니다.

img1.png

하나의 노드는 하나의 시험장을 나타냅니다.
검은 바탕의 흰 숫자는 해당 시험장의 고유 번호(ID)를 나타냅니다.

2-1. 시험장이 n개 있다면, 시험장의 고유 번호는 0부터 n-1까지 부여됩니다.

노드 안의 빨간 숫자는, 해당 시험장의 응시자 수를 나타냅니다.

3-1. 위의 그림에서, 9번 시험장에는 10명, 4번 시험장에는 8명, 6번 시험장에는 20명의 응시자가 시험을 볼 예정입니다.

노드 사이의 간선은 해당 시험장이 연결되어 있음을 의미합니다.

4-1. 위의 그림에서, 9번 시험장은 7번 시험장과, 7번 시험장은 6번 시험장과 연결되어 있습니다.

코딩 테스트를 총괄하는 무지는 안정적인 시험을 위해, 시험장에서 오는 트래픽을 k개의 그룹으로 나누어 각 그룹별 서버로 분산시키기로 하였습니다. 시험장 사이를 연결한 간선들 중 k-1개를 끊어서 시험장을 k 개의 그룹으로 나눌 계획입니다. 이때, 그룹별 최대 트래픽을 최소화하기 위하여 가장 큰 그룹의 인원을 최소화시켜야 합니다.

img2.png

위의 그림에서 7번과 6번 시험장을 잇는 간선을 끊고, 9번과 7번 시험장을 잇는 간선을 끊는다면, 전체 시험장은 3개의 그룹으로 나누어집니다.

주황색 노드로 표시된 A그룹의 인원은 35명(10+8+5+6+1+1+4)
보라색 노드로 표시된 B그룹의 인원은 37명(7+30)
녹색 노드로 표시된 C그룹의 인원은 40명(20+8+12)
즉, 인원이 가장 많은 그룹은 40명입니다. 다른 어떤 방법으로 시험장을 3개의 그룹으로 나눈다고 해도, 인원이 가장 많은 그룹의 인원이 40명 미만이 되도록 나눌 수는 없습니다.

나눌 그룹의 수를 나타내는 정수 k, 각 시험장의 응시자 수를 나타내는 1차원 정수 배열 num, 시험장의 연결 상태를 나타내는 2차원 정수 배열 links가 매개변수로 주어집니다. 
인원이 가장 많은 그룹의 인원이 최소화되도록 k개의 그룹으로 나누었을 때, 최소화된 최대 그룹의 인원을 return 하도록 solution 함수를 완성해주세요.

제한사항
1 ≤ k ≤ 10,000
k ≤ num의 길이 ≤ 10,000
num[i]에는 i번 시험장의 응시자 수가 담겨있습니다.
1 ≤ num의 원소 ≤ 10,000
links의 길이 = num의 길이
links의 i번째 행은 i번 노드(시험장)의 [왼쪽 자식 노드 번호, 오른쪽 자식 노드 번호]입니다.
해당 위치에 자식 노드가 없는 경우 -1이 담겨있습니다.
잘못된 노드 번호나, 하나의 이진 트리 구조가 아닌 입력은 주어지지 않습니다.
정확성 테스트 케이스 제한 사항
1 ≤ k ≤ 20
k ≤ num의 길이 ≤ 20
효율성 테스트 케이스 제한 사항
주어진 조건 외 추가 제한사항 없습니다.
입출력 예
k	num	links	result
3	[12, 30, 1, 8, 8, 6, 20, 7, 5, 10, 4, 1]	[[-1, -1], [-1, -1], [-1, -1], [-1, -1], [8, 5], [2, 10], [3, 0], [6, 1], [11, -1], [7, 4], [-1, -1], [-1, -1]]	40
1	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]	27
2	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]	14
4	[6, 9, 7, 5]	[[-1, -1], [-1, -1], [-1, 0], [2, 1]]	9
* */