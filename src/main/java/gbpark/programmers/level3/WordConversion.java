package gbpark.programmers.level3;

import gbpark.common.ArrayConverter;
import gbpark.common.TestUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class WordConversion {
	public static void main(String[] args) {
		WordConversion wc = new WordConversion();
		List<Integer> an = new ArrayList<>();
		List<String> begins = new ArrayList<>();
		List<String> targets = new ArrayList<>();
		List<String[]> wordsList = new ArrayList<>();

		begins.add("hit");
		begins.add("hit");
		targets.add("cog");
		targets.add("cog");
		wordsList.add(ArrayConverter.toStringArray("[hot, dot, dog, lot, log, cog]"));
		wordsList.add(ArrayConverter.toStringArray("[hot, dot, dog, lot, log]	"));
		an.add(4);
		an.add(0);

		for (int i = 0; i < begins.size(); i++) {
			TestUtil.startTimer();
			TestUtil.test(an.get(i), wc.solution(begins.get(i), targets.get(i), wordsList.get(i)));
		}
	}

	public int solution(String begin, String target, String[] words) {
		Queue<Node> que = new LinkedList<>();
		boolean[] visited = new boolean[words.length];
		que.add(new Node(begin, visited, 0));

		while (!que.isEmpty()) {
			Node node = que.poll();
			if (node.word.equals(target)) {
				return node.turn;
			}
			bfs(node, words, que);
		}

		return 0;
	}

	int bfs(Node node, String[] words, Queue<Node> que) {
		String word = node.word;
		boolean[] visited = node.visited;
		int turn = node.turn + 1;

		for (int i = 0; i < visited.length; i++) {
			if (!visited[i] && node.isLinked(words[i])) {
				boolean[] newVisited = new boolean[words.length];
				System.arraycopy(visited, 0, newVisited, 0, visited.length);
				newVisited[i] = true;
				que.add(new Node(words[i], newVisited, turn));
			}
		}

		return 0;
	}

	static class Node {
		String word;
		boolean[] visited;
		int turn;

		public Node(String word, boolean[] visited, int turn) {
			this.word = word;
			this.visited = visited;
			this.turn = turn;
		}

		boolean isLinked(String target) {
			int cnt = 0;
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) != target.charAt(i)) {
					cnt++;
					if (cnt > 1) return false;
				}
			}
			return cnt == 1;
		}
	}
}

/*
두 개의 단어 begin, target과 단어의 집합 words가 있습니다. 
아래와 같은 규칙을 이용하여 begin에서 target으로 변환하는 가장 짧은 변환 과정을 찾으려고 합니다.

1. 한 번에 한 개의 알파벳만 바꿀 수 있습니다.
2. words에 있는 단어로만 변환할 수 있습니다.
예를 들어 begin이 "hit", target가 "cog", words가 ["hot","dot","dog","lot","log","cog"]라면 
"hit" -> "hot" -> "dot" -> "dog" -> "cog"와 같이 4단계를 거쳐 변환할 수 있습니다.

두 개의 단어 begin, target과 단어의 집합 words가 매개변수로 주어질 때, 
최소 몇 단계의 과정을 거쳐 begin을 target으로 변환할 수 있는지 return 하도록 solution 함수를 작성해주세요.

제한사항
각 단어는 알파벳 소문자로만 이루어져 있습니다.
각 단어의 길이는 3 이상 10 이하이며 모든 단어의 길이는 같습니다.
words에는 3개 이상 50개 이하의 단어가 있으며 중복되는 단어는 없습니다.
begin과 target은 같지 않습니다.
변환할 수 없는 경우에는 0를 return 합니다.
입출력 예
begin	target	words	return
hit	cog	[hot, dot, dog, lot, log, cog]	4
hit	cog	[hot, dot, dog, lot, log]		0
* */