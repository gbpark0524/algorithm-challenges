package gbpark.programmers.level3;


import gbpark.common.CodingTest;

import static gbpark.common.CodingTest.DataType.INT_ARRAY;
import static gbpark.common.CodingTest.DataType.LONG_ARRAY;

public class ExpressableBinaryTree {
	static long number;
	static boolean possible;
	static Tree[] dic = new Tree[10];

	public int[] solution(long[] numbers) {
		int[] ans = new int[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			possible = true;
			number = numbers[i];
			Tree root = makeTree(number);
			chkBin(root);
			chkTree(root);
			ans[i] = possible ? 1 : 0;
		}

		return ans;
	}

	Tree makeTree(long number) {
		int depth = 1;

		while ((long)Math.pow(2,depth) - 1 < Long.toBinaryString(number).length()) {
			depth++;
		}

		if (dic[depth] == null) {
			Tree root = new Tree();
			makeTree(root, depth, (int) Math.pow(2,depth) -2, 0);
			dic[depth] = root;
		}

		return dic[depth];
	}

	void chkBin(Tree tree) {
		if (tree == null) return;
		long val = (long) Math.pow(2, tree.x);

		chkBin(tree.left);
		if (number >= val) {
			number -= val;
			tree.bin = true;
		} else {
			tree.bin = false;
		}
		chkBin(tree.right);
	}

	void chkTree(Tree tree) {
		if (tree.left == null) return;
		if (!tree.bin) {
			if (tree.left.bin || tree.right.bin) {
				possible = false;
				return;
			}
		}
		chkTree(tree.left);
		chkTree(tree.right);
	}

	void makeTree(Tree root, int depth, int lv, int rv) {
		root.x = (lv + rv)/2;
		if (depth == 1) return;
		Tree left = new Tree();
		root.left = left;
		Tree right = new Tree();
		root.right = right;
		depth--;
		makeTree(left, depth, lv, root.x+1);
		makeTree(right, depth, root.x-1, rv);
	}

	static class Tree {
		int x;
		boolean bin;
		Tree left;
		Tree right;
	}

	public static void main(String[] args) {
		ExpressableBinaryTree thisClass = new ExpressableBinaryTree();
		CodingTest test = new CodingTest<>(thisClass::solution, LONG_ARRAY, INT_ARRAY);
		test.codingTest("[7, 42, 5]	[1, 1, 0]");
		test.codingTest("[63, 111, 95]	[1, 1, 0]");
		test.codingTest("[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 128, 129, 16512]	[1, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0]");
	}
}

/*
표현 가능한 이진트리
당신은 이진트리를 수로 표현하는 것을 좋아합니다.

이진트리를 수로 표현하는 방법은 다음과 같습니다.

이진수를 저장할 빈 문자열을 생성합니다.
주어진 이진트리에 더미 노드를 추가하여 포화 이진트리로 만듭니다. 루트 노드는 그대로 유지합니다.
만들어진 포화 이진트리의 노드들을 가장 왼쪽 노드부터 가장 오른쪽 노드까지, 왼쪽에 있는 순서대로 살펴봅니다. 노드의 높이는 살펴보는 순서에 영향을 끼치지 않습니다.
살펴본 노드가 더미 노드라면, 문자열 뒤에 0을 추가합니다. 살펴본 노드가 더미 노드가 아니라면, 문자열 뒤에 1을 추가합니다.
문자열에 저장된 이진수를 십진수로 변환합니다.
이진트리에서 리프 노드가 아닌 노드는 자신의 왼쪽 자식이 루트인 서브트리의 노드들보다 오른쪽에 있으며, 자신의 오른쪽 자식이 루트인 서브트리의 노드들보다 왼쪽에 있다고 가정합니다.

다음은 이진트리를 수로 표현하는 예시입니다.

주어진 이진트리는 다음과 같습니다.
제목 없는 다이어그램.drawio \(4\).png

주어진 이진트리에 더미노드를 추가하여 포화 이진트리로 만들면 다음과 같습니다. 더미 노드는 점선으로 표시하였고, 노드 안의 수는 살펴보는 순서를 의미합니다.
제목 없는 다이어그램.drawio \(5\).png

노드들을 왼쪽에 있는 순서대로 살펴보며 0과 1을 생성한 문자열에 추가하면 "0111010"이 됩니다. 이 이진수를 십진수로 변환하면 58입니다.

당신은 수가 주어졌을때, 하나의 이진트리로 해당 수를 표현할 수 있는지 알고 싶습니다.

이진트리로 만들고 싶은 수를 담은 1차원 정수 배열 numbers가 주어집니다. numbers에 주어진 순서대로 하나의 이진트리로 해당 수를 표현할 수 있다면 1을, 표현할 수 없다면 0을 1차원 정수 배열에 담아 return 하도록 solution 함수를 완성해주세요.

제한사항
1 ≤ numbers의 길이 ≤ 10,000
1 ≤ numbers의 원소 ≤ 10^15
입출력 예
numbers	result
[7, 42, 5]	[1, 1, 0]
[63, 111, 95]	[1, 1, 0]
* */
