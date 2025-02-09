package gbpark.programmers.level2;

import gbpark.common.TestUtil;
import org.w3c.dom.ranges.Range;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
[본 문제는 정확성과 효율성 테스트 각각 점수가 있는 문제입니다.]

세로길이가 n 가로길이가 m인 격자 모양의 땅 속에서 석유가 발견되었습니다. 석유는 여러 덩어리로 나누어 묻혀있습니다. 
당신이 시추관을 수직으로 단 하나만 뚫을 수 있을 때, 가장 많은 석유를 뽑을 수 있는 시추관의 위치를 찾으려고 합니다. 
시추관은 열 하나를 관통하는 형태여야 하며, 열과 열 사이에 시추관을 뚫을 수 없습니다.

x x x o o o x x
x x x x o o x x
o o x x x o o x
o o o x x x x x
o o o x x x o o


예를 들어 가로가 8, 세로가 5인 격자 모양의 땅 속에 위 그림처럼 석유가 발견되었다고 가정하겠습니다. 
상, 하, 좌, 우로 연결된 석유는 하나의 덩어리이며, 석유 덩어리의 크기는 덩어리에 포함된 칸의 수입니다. 
그림에서 석유 덩어리의 크기는 왼쪽부터 8, 7, 2입니다.
시추관은 위 그림처럼 설치한 위치 아래로 끝까지 뻗어나갑니다. 
만약 시추관이 석유 덩어리의 일부를 지나면 해당 덩어리에 속한 모든 석유를 뽑을 수 있습니다. 
시추관이 뽑을 수 있는 석유량은 시추관이 지나는 석유 덩어리들의 크기를 모두 합한 값입니다. 
시추관을 설치한 위치에 따라 뽑을 수 있는 석유량은 다음과 같습니다.

시추관의 위치	획득한 덩어리	총 석유량
1	[8]	8
2	[8]	8
3	[8]	8
4	[7]	7
5	[7]	7
6	[7]	7
7	[7, 2]	9
8	[2]	2
오른쪽 그림처럼 7번 열에 시추관을 설치하면 크기가 7, 2인 덩어리의 석유를 얻어 뽑을 수 있는 석유량이 9로 가장 많습니다.

석유가 묻힌 땅과 석유 덩어리를 나타내는 2차원 정수 배열 land가 매개변수로 주어집니다. 
이때 시추관 하나를 설치해 뽑을 수 있는 가장 많은 석유량을 return 하도록 solution 함수를 완성해 주세요.

제한사항
1 ≤ land의 길이 = 땅의 세로길이 = n ≤ 500
1 ≤ land[i]의 길이 = 땅의 가로길이 = m ≤ 500
land[i][j]는 i+1행 j+1열 땅의 정보를 나타냅니다.
land[i][j]는 0 또는 1입니다.
land[i][j]가 0이면 빈 땅을, 1이면 석유가 있는 땅을 의미합니다.
정확성 테스트 케이스 제한사항
1 ≤ land의 길이 = 땅의 세로길이 = n ≤ 100
1 ≤ land[i]의 길이 = 땅의 가로길이 = m ≤ 100
효율성 테스트 케이스 제한사항
주어진 조건 외 추가 제한사항 없습니다.

입출력 예
land	result
[
[0, 0, 0, 1, 1, 1, 0, 0], 
[0, 0, 0, 0, 1, 1, 0, 0], 
[1, 1, 0, 0, 0, 1, 1, 0], 
[1, 1, 1, 0, 0, 0, 0, 0],
[1, 1, 1, 0, 0, 0, 1, 1]]	9
[
[1, 0, 1, 0, 1, 1], 
[1, 0, 1, 0, 0, 0], 
[1, 0, 1, 0, 0, 1], 
[1, 0, 0, 1, 0, 0], 
[1, 0, 0, 1, 0, 1], 
[1, 0, 0, 0, 0, 0], 
[1, 1, 1, 1, 1, 1]]
* */
public class OilExtraction {
    static int cnt = 0;

    public int solution(int[][] land) {
        int[] sum = new int[land[0].length];
        Queue<int[]> que = new LinkedList<>();
        Set<Integer> resultX;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 1) {
                    cnt = 0;
                    resultX = new HashSet<>();

                    resultX.add(j);
                    land[i][j] = -1;
                    que.add(new int[]{i, j});

                    while (!que.isEmpty()) {
                        findNearbyOil(que, land, resultX);
                    }

                    for (int x : resultX) {
                        sum[x] += cnt;
                    }
                }
            }
        }

        return Arrays.stream(sum).max().getAsInt();
    }

    public void findNearbyOil(Queue<int[]> que, int[][] land, Set<Integer> resultX) {
        int[] oil = que.poll();
        int i = oil[0];
        int j = oil[1];
        cnt++;
        resultX.add(j);

        if (i < land.length - 1 && land[i + 1][j] == 1) {
            que.add(new int[]{i + 1, j});
            land[i + 1][j] = -1;
        }

        if (i > 0 && land[i - 1][j] == 1) {
            que.add(new int[]{i - 1, j});
            land[i - 1][j] = -1;
        }

        if (j > 0 && land[i][j - 1] == 1) {
            que.add(new int[]{i, j - 1});
            land[i][j - 1] = -1;
        }

        if (j < land[0].length - 1 && land[i][j + 1] == 1) {
            que.add(new int[]{i, j + 1});
            land[i][j + 1] = -1;
        }

    }

    public static void main(String[] args) {
        OilExtraction ob = new OilExtraction();
        int[][] land = {{1, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 0, 0, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};
        int result = 14;
        TestUtil.test(result, ob.solution(land));
    }
}

/*
전형적인 탐색 알고리즘
bfs의 경우 방문 전에 -1 로 바꾸어 효율 올리는 것을 잊어버리지 않고 잘 구현했다.

오답노트
* *
* 1. 오랜만에 풀어서 좌표평면 x,y 로 놓고 풀었더니 햇갈려서 다시 했다. 웬만하면 그냥 행렬로 생각해서 풀이시간을 줄이자.
* 2. 처음엔 올라오는 탐색은 필요없다고 생각했으나 ㅂ 자 같이 내려가는 경우를 생각 못했다. 어줍잖게 줄이지 말고 일단 모든방향으로 탐색하자

ai 에세 문제 풀이의 효율을 올릴수 있는 방법을 물어보니,
1. Queue<int[]> 부분을 Queue<Integer> 로 바꿔서 효율을 조금 올리는게 가능.
2. int[] dx = {1, -1, 0, 0}; int[] dy = {0, 0, -1, 1};로 두고 for 문을 하나로 

1의 경우는 신빙성이 있을것 같았으나 실제로 해보니 정수로 집어넣고 다시 좌표를 뽑을때 연산이 들어가는것과 실제로
int[] 를 읽는것에 효율차이가 거의 나지 않고, 오히려 평균적으로 정수 큐의 점수가 낮았다. hash 가 역시 메모리를 많이 안먹는다.

2의 경우는 조건문을 내가 구현한 방법과 저렇게 한번에 하는 방법은 for문 4번을 하는것과 같으므로 차이 없고,
&&조건의 경우 뒤 조건식을 계산하지 않기 때문에 최대한 계산을 줄이려 퍼뜨려 놓은건데 반대로 말해서 의외였다.
역시나 구현해보니 내가 구현한것보다 느림...

* */
