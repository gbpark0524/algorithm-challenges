package gbpark.programmers.level2;

import gbpark.common.TestUtil;

import java.util.*;

public class CollisionDetection {

    public static void main(String[] args) {
        CollisionDetection ob = new CollisionDetection();
        int[][] points = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes = {{2, 3, 4, 5}, {1, 3, 4, 5}};
        int result = 0;
        TestUtil.test(result, ob.solution(points, routes));
    }

    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        List<Map<Integer, Integer>> mapList = new ArrayList<>();
        mapList.add(new HashMap<>());

        for (int[] route : routes) {
            int time = 0;
            int allLength = 1;
            int curr = points[route[0]-1][0] * 100 + points[route[0]-1][1];
            answer = checkAns(mapList.get(0), curr , answer);
            time++;
            
            for (int k = 0; k < route.length-1; k++) {
                int[] end = points[route[k+1]-1];
                int[] start = points[route[k]-1];
                int iLength = Math.abs(end[0] - start[0]);
                int jLength = Math.abs(end[1] - start[1]);
                int iDirection = end[0] - start[0] > 0 ? 1 : -1;
                int jDirection = end[1] - start[1] > 0 ? 1 : -1;
                allLength += iLength + jLength;
    
                while (mapList.size() < allLength) {
                    mapList.add(new HashMap<>());
                }
    
                for (int i = 0; i < iLength; i++) {
                    curr += iDirection * 100;
                    answer = checkAns(mapList.get(time), curr, answer);
                    time++;
                }
    
                for (int j = 0; j < jLength; j++) {
                    curr += jDirection;
                    answer = checkAns(mapList.get(time), curr, answer);
                    time++;
                }
            }
        }

        return answer;
    }

    private static int checkAns(Map<Integer, Integer> mapTime, int curr, int answer) {
        if (mapTime.containsKey(curr)) {
            if (mapTime.get(curr) == 1) {
                answer++;
                System.out.println("curr: " + curr + ", answer: " + answer);
            }
            mapTime.put(curr, mapTime.get(curr) + 1);
        } else {
            mapTime.put(curr, 1);
            System.out.println("no:" + curr);
        }
        return answer;
    }
}

/*
* 어떤 물류 센터는 로봇을 이용한 자동 운송 시스템을 운영합니다. 운송 시스템이 작동하는 규칙은 다음과 같습니다.

물류 센터에는 (r, c)와 같이 2차원 좌표로 나타낼 수 있는 n개의 포인트가 존재합니다. 각 포인트는 1~n까지의 서로 다른 번호를 가집니다.
로봇마다 정해진 운송 경로가 존재합니다. 운송 경로는 m개의 포인트로 구성되고 로봇은 첫 포인트에서 시작해 할당된 포인트를 순서대로 방문합니다.
운송 시스템에 사용되는 로봇은 x대이고, 모든 로봇은 0초에 동시에 출발합니다.
로봇은 1초마다 r 좌표와 c 좌표 중 하나가 1만큼 감소하거나 증가한 좌표로 이동할 수 있습니다.
다음 포인트로 이동할 때는 항상 최단 경로로 이동하며 최단 경로가 여러 가지일 경우,
* r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 합니다.
마지막 포인트에 도착한 로봇은 운송을 마치고 물류 센터를 벗어납니다. 로봇이 물류 센터를 벗어나는 경로는 고려하지 않습니다.
이동 중 같은 좌표에 로봇이 2대 이상 모인다면 충돌할 가능성이 있는 위험 상황으로 판단합니다.
관리자인 당신은 현재 설정대로 로봇이 움직일 때 위험한 상황이 총 몇 번 일어나는지 알고 싶습니다.
만약 어떤 시간에 여러 좌표에서 위험 상황이 발생한다면 그 횟수를 모두 더합니다.

운송 포인트 n개의 좌표를 담은 2차원 정수 배열 points와 로봇 x대의 운송 경로를 담은 2차원 정수 배열 routes가 매개변수로 주어집니다. 이때 모든 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수를 return 하도록 solution 함수를 완성해 주세요.

제한사항
2 ≤ points의 길이 = n ≤ 100
points[i]는 i + 1번 포인트의 [r 좌표, c 좌표]를 나타내는 길이가 2인 정수 배열입니다.
1 ≤ r ≤ 100
1 ≤ c ≤ 100
같은 좌표에 여러 포인트가 존재하는 입력은 주어지지 않습니다.
2 ≤ routes의 길이 = 로봇의 수 = x ≤ 100
2 ≤ routes[i]의 길이 = m ≤ 100
routes[i]는 i + 1번째 로봇의 운송경로를 나타냅니다. routes[i]의 길이는 모두 같습니다.
routes[i][j]는 i + 1번째 로봇이 j + 1번째로 방문하는 포인트 번호를 나타냅니다.
같은 포인트를 연속으로 방문하는 입력은 주어지지 않습니다.
1 ≤ routes[i][j] ≤ n
입출력 예
points	routes	result
[[3, 2], [6, 4], [4, 7], [1, 4]]	[[4, 2], [1, 3], [2, 4]]	1
[[3, 2], [6, 4], [4, 7], [1, 4]]	[[4, 2], [1, 3], [4, 2], [4, 3]]	9
[[2, 2], [2, 3], [2, 7], [6, 6], [5, 2]]	[[2, 3, 4, 5], [1, 3, 4, 5]]	0
* */

/*
* 오답노트 
* 문제 자체 논리는 어렵지 않으나, 구현 자체가 복잡한 편이다.
* 각 경로의 타임라인 좌표를 계산해서 뷰에 넣어놓은뒤에 한번에 검사해야 해서 까다로웠다.
* 체감상 논리 구조는 난이도 2이지만, 실제 구현은 난이도3 정도 되었다.
* 
* 1. 
* */
