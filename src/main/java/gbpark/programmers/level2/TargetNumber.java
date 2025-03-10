package gbpark.programmers.level2;

import gbpark.common.TestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static gbpark.common.ArrayConverter.toIntArray;

public class TargetNumber {
    public static void main(String[] args) {
        TargetNumber tn = new TargetNumber();

        List<int[]> numList = new ArrayList<>();
        numList.add(toIntArray("[1, 1, 1, 1, 1]"));
        numList.add(toIntArray("[4, 1, 2, 1]"));

        List<Integer> targets = new ArrayList<>();
        targets.add(3);
        targets.add(4);

        List<Integer> ans = new ArrayList<>();
        ans.add(5);
        ans.add(2);

        for (int i = 0; i < numList.size(); i++) {
            TestUtil.test(ans.get(i),
                    tn.solution(numList.get(i), targets.get(i)));
        }

    }

    public int solution(int[] numbers, int target) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0,numbers[0]});
        stack.push(new int[]{0,numbers[0]*-1});
        int answer = 0;

        while(!stack.isEmpty()) {
            int[] pop = stack.pop();
            if (pop[0] == numbers.length-1) {
                if (pop[1]==target) {
                    answer++;
                }
                continue;
            }
            dfs(stack, pop, numbers);
        }

        return answer;
    }

    void dfs(Stack<int[]> stack, int[] cur, int[] numbers) {
        int index = cur[0] + 1;
        stack.push(new int[]{index,cur[1]-numbers[index]});
        stack.push(new int[]{index,cur[1]+numbers[index]});
    }
}
/*
n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
예를 들어 [1, 1, 1, 1, 1]로 숫자 3을 만들려면 다음 다섯 방법을 쓸 수 있습니다.

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3
사용할 수 있는 숫자가 담긴 배열 numbers, 타겟 넘버 target이 매개변수로 주어질 때 숫자를 적절히 더하고 빼서 타겟 넘버를 만드는
방법의 수를 return 하도록 solution 함수를 작성해주세요.

제한사항
주어지는 숫자의 개수는 2개 이상 20개 이하입니다.
각 숫자는 1 이상 50 이하인 자연수입니다.
타겟 넘버는 1 이상 1000 이하인 자연수입니다.
입출력 예
numbers	target	return
[1, 1, 1, 1, 1]	3	5
[4, 1, 2, 1]	4	2
입출력 예 설명
입출력 예 #1

문제 예시와 같습니다.

입출력 예 #2

+4+1-2+1 = 4
+4-1+2-1 = 4
총 2가지 방법이 있으므로, 2를 return 합니다.
* */

