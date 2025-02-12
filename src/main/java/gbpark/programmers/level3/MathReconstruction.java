package gbpark.programmers.level3;

import gbpark.common.TestUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MathReconstruction {
    public static void main(String[] args) {
        MathReconstruction ob = new MathReconstruction();
        List<String[]> qList = new ArrayList<>();
        qList.add("[14 + 3 = 17, 13 - 6 = X, 51 - 5 = 44]".replaceAll("[\\[\\]]", "").split(","));
        qList.add("[1 + 1 = 2, 1 + 3 = 4, 1 + 5 = X, 1 + 2 = X]	        ".replaceAll("[\\[\\]]", "").split(","));
        qList.add("[10 - 2 = X, 30 + 31 = 101, 3 + 3 = X, 33 + 33 = X]  ".replaceAll("[\\[\\]]", "").split(","));
        qList.add("[2 - 1 = 1, 2 + 2 = X, 7 + 4 = X, 5 - 5 = X]	        ".replaceAll("[\\[\\]]", "").split(","));
        qList.add("[2 - 1 = 1, 2 + 2 = X, 7 + 4 = X, 8 + 4 = X]	        ".replaceAll("[\\[\\]]", "").split(","));
        
        List<String[]> ans = new ArrayList<>();
        ans.add("[13 - 6 = 5]".replaceAll("[\\[\\]]", "").split(","));
        ans.add("[1 + 5 = ?,1 + 2 = 3]	                    ".replaceAll("[\\[\\]]", "").trim().split(","));
        ans.add("[10 - 2 = 4,3 + 3 = 10,33 + 33 = 110]	        ".replaceAll("[\\[\\]]", "").trim().split(","));
        ans.add("[2 + 2 = 4,7 + 4 = ?,5 - 5 = 0]	        ".replaceAll("[\\[\\]]", "").trim().split(","));
        ans.add("[2 + 2 = 4,7 + 4 = 12,8 + 4 = 13]	        ".replaceAll("[\\[\\]]", "").trim().split(","));
        
        for (int i = 0; i < qList.size(); i++) {
            TestUtil.test(ans.get(i), ob.solution(qList.get(i)));
        }
    }
    
    static DecimalFormat dx = new DecimalFormat("00");
    static DecimalFormat dz = new DecimalFormat("000");
    
    public String[] solution(String[] expressions) {
        Queue<String> queue = new LinkedList<>();
        Set<Integer> baseSet = new HashSet<>();
        
        int maxBase = 9;
        String allStr = Arrays.toString(expressions);
        while (maxBase > 1){
            baseSet.add(maxBase);
            if (allStr.contains(String.valueOf(maxBase-1))) {
                break;
            } else {
                maxBase--;
            }
        }
        
        for (String expression : expressions) {
            if (expression.contains("X")) {
                queue.add(expression);
                continue;
            }
            boolean isPlus = expression.contains("+");
            String operator = isPlus ? "\\+" : "-";
            
            char[] x = dx.format(Integer.parseInt(expression.split(operator)[0].trim())).toCharArray();
            char[] y = dx.format(Integer.parseInt(expression.split(operator)[1].split("=")[0].trim())).toCharArray();
            char[] z = dz.format(Integer.parseInt(expression.split(operator)[1].split("=")[1].trim())).toCharArray();
            
            Iterator<Integer> iterator = baseSet.iterator();
            while (iterator.hasNext()) {
                Integer base = iterator.next();
                int xN = Character.getNumericValue(x[0]) * base + Character.getNumericValue(x[1]);
                int yN = Character.getNumericValue(y[0]) * base + Character.getNumericValue(y[1]);
                int zN = Character.getNumericValue(z[0]) * base * base + Character.getNumericValue(z[1]) * base + Character.getNumericValue(z[2]);
                if (isPlus && xN + yN != zN) {
                    iterator.remove();
                } else if (!isPlus && xN - yN != zN){
                    iterator.remove();
                }
            }
        }
        
        String[] answer = new String[queue.size()];
        int i = 0;
        
        while (!queue.isEmpty()) {
            String expression = queue.poll();

            boolean isPlus = expression.contains("+");
            String operator = isPlus ? "\\+" : "-";

            char[] x = dx.format(Integer.parseInt(expression.split(operator)[0].trim())).toCharArray();
            char[] y = dx.format(Integer.parseInt(expression.split(operator)[1].split("=")[0].trim())).toCharArray();
            

            String z = "";
            for (Integer base : baseSet) {
                int xN = Character.getNumericValue(x[0]) * base + Character.getNumericValue(x[1]);
                int yN = Character.getNumericValue(y[0]) * base + Character.getNumericValue(y[1]);
                xN += isPlus ? yN : -1 * yN;
                
                if (z.isEmpty()) {
                    z = Integer.toString(xN, base);
                } else if (!z.equals(Integer.toString(xN, base))){
                    z = "?";
                    break;
                }
            }
            
            answer[i++] = expression.replace("X", z).trim();
        }
        
        return answer;
    }
}
/*
당신은 덧셈 혹은 뺄셈 수식이 여러 개 적힌 고대 문명의 유물을 찾았습니다. 
이 수식들을 관찰하던 당신은 이 문명이 사용하던 진법 체계가 10진법이 아니라는 것을 알아냈습니다. (2 ~ 9진법 중 하나입니다.)

수식들 중 몇 개의 수식은 결괏값이 지워져 있으며, 당신은 이 문명이 사용하던 진법에 맞도록 지워진 결괏값을 채워 넣으려 합니다.

다음은 그 예시입니다.

<수식>

14 + 3 = 17
13 - 6 = X
51 - 5 = 44
X로 표시된 부분이 지워진 결괏값입니다.
51 - 5 = 44에서 이 문명이 사용하던 진법이 8진법임을 알 수 있습니다. 따라서 13 - 6 = X의 지워진 결괏값을 채워 넣으면 13 - 6 = 5가 됩니다.

다음은 또 다른 예시입니다.

<수식>

1 + 1 = 2
1 + 3 = 4
1 + 5 = X
1 + 2 = X
주어진 수식들에서 이 문명에서 사용한 진법이 6 ~ 9진법 중 하나임을 알 수 있습니다.
1 + 5 = X의 결괏값은 6진법일 때 10, 7 ~ 9진법일 때 6이 됩니다. 이와 같이 결괏값이 불확실한 수식은 ?를 사용해 1 + 5 = ?와 같이 결괏값을 채워 넣습니다.
1 + 2 = X의 결괏값은 6 ~ 9진법에서 모두 3으로 같습니다. 따라서 1 + 2 = X의 지워진 결괏값을 채워 넣으면 1 + 2 = 3이 됩니다.

덧셈 혹은 뺄셈 수식들이 담긴 1차원 문자열 배열 expressions가 매개변수로 주어집니다.
이때 결괏값이 지워진 수식들의 결괏값을 채워 넣어 순서대로 문자열 배열에 담아 return 하도록 solution 함수를 완성해 주세요.

제한사항
2 ≤ expressions의 길이 ≤ 100
expressions의 원소는 "A + B = C" 혹은 "A - B = C" 형태의 문자열입니다. A, B, C와 연산 기호들은 공백 하나로 구분되어 있습니다.
A, B는 음이 아닌 두 자릿수 이하의 정수입니다.
C는 알파벳 X 혹은 음이 아닌 세 자릿수 이하의 정수입니다. 
C가 알파벳 X인 수식은 결괏값이 지워진 수식을 의미하며, 이러한 수식은 한 번 이상 등장합니다.
결괏값이 음수가 되거나 서로 모순되는 수식은 주어지지 않습니다.
입출력 예
expressions	result
["14 + 3 = 17", "13 - 6 = X", "51 - 5 = 44"]	                ["13 - 6 = 5"]
["1 + 1 = 2", "1 + 3 = 4", "1 + 5 = X", "1 + 2 = X"]	        ["1 + 5 = ?", "1 + 2 = 3"]
["10 - 2 = X", "30 + 31 = 101", "3 + 3 = X", "33 + 33 = X"]	    ["10 - 2 = 4", "3 + 3 = 10", "33 + 33 = 110"]
["2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "5 - 5 = X"]	        ["2 + 2 = 4", "7 + 4 = ?", "5 - 5 = 0"]
["2 - 1 = 1", "2 + 2 = X", "7 + 4 = X", "8 + 4 = X"]	        ["2 + 2 = 4", "7 + 4 = 12", "8 + 4 = 13"]
* */

/*
* 오답노트
* 1. DecimalFormat, Character.getNumericValue 함수를 잘 안쓰는거라 떠올리지 못하면 난이도가 올라감.
* 2. 식을 받아서 하는 일련의 연산을 Expression 객체로 분리해 .getVal(base) 등의 관련식들을 객체에 따로 구현했으면 더 깔끔할것 같다.
* */