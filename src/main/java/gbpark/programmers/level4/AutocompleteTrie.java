package gbpark.programmers.level4;

import gbpark.common.ArrayConverter;
import gbpark.common.TestUtil;

import java.util.ArrayList;
import java.util.List;

public class AutocompleteTrie {
	public int solution(String[] words) {
		int answer = 0;
		Node root = new Node();

		for (String word : words) {
			makeTrie(root, word);
		}

		for (String word : words) {
			answer += countAns(root, word);
		}

		return answer;
	}

	int countAns(Node node, String word) {
		int i;
		for (i = 0; i < word.length() - 1; i++) {
			int cur = word.charAt(i) - 'a';
			node = node.children[cur];

			if (node.count == 1) break;
		}
		return i + 1;
	}

	void makeTrie(Node node, String word) {
		for (int i = 0; i < word.length(); i++) {
			int idx = word.charAt(i) - 'a';
			if (node.children[idx] == null) node.children[idx] = new Node();
			node = node.children[idx];
			node.count++;
		}
	}

	static class Node {
		Node[] children = new Node[26];
		int count = 0;
	}

	public static void main(String[] args) {
		List<String> qStr = new ArrayList<>();
		List<String[]> wordsList = new ArrayList<>();
		List<Integer> answers = new ArrayList<>();

		qStr.add("[go,gone,guild]	7");
		qStr.add("[abc,def,ghi,jklm]	4");
		qStr.add("[word,war,warrior,world]	15");
		qStr.add("[\"wo\", \"word\", \"work\"]	10");


		for (String s : qStr) {
			String[] split = s.trim().split("\\t+");
			wordsList.add(ArrayConverter.toStringArray(split[0]));
			answers.add(Integer.parseInt(split[1]));
		}

		for (int i = 0; i < answers.size(); i++) {
			TestUtil.startTimer();
			TestUtil.test(answers.get(i), new AutocompleteTrie().solution(wordsList.get(i)));
		}
	}
}
/*
포털 다음에서 검색어 자동완성 기능을 넣고 싶은 라이언은 한 번 입력된 문자열을 학습해서 다음 입력 때 활용하고 싶어 졌다. 예를 들어, go 가 한 번 입력되었다면, 다음 사용자는 g 만 입력해도 go를 추천해주므로 o를 입력할 필요가 없어진다! 단, 학습에 사용된 단어들 중 앞부분이 같은 경우에는 어쩔 수 없이 다른 문자가 나올 때까지 입력을 해야 한다.
효과가 얼마나 좋을지 알고 싶은 라이언은 학습된 단어들을 찾을 때 몇 글자를 입력해야 하는지 궁금해졌다.

예를 들어, 학습된 단어들이 아래와 같을 때

go
gone
guild
go를 찾을 때 go를 모두 입력해야 한다.
gone을 찾을 때 gon 까지 입력해야 한다. (gon이 입력되기 전까지는 go 인지 gone인지 확신할 수 없다.)
guild를 찾을 때는 gu 까지만 입력하면 guild가 완성된다.
이 경우 총 입력해야 할 문자의 수는 7이다.

라이언을 도와 위와 같이 문자열이 입력으로 주어지면 학습을 시킨 후, 학습된 단어들을 순서대로 찾을 때 몇 개의 문자를 입력하면 되는지 계산하는 프로그램을 만들어보자.

입력 형식
학습과 검색에 사용될 중복 없는 단어 N개가 주어진다.
모든 단어는 알파벳 소문자로 구성되며 단어의 수 N과 단어들의 길이의 총합 L의 범위는 다음과 같다.

2 <= N <= 100,000
2 <= L <= 1,000,000
출력 형식
단어를 찾을 때 입력해야 할 총 문자수를 리턴한다.

입출력 예제
words	result
[go,gone,guild]	7
[abc,def,ghi,jklm]	4
[word,war,warrior,world]	15
* */