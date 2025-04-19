# 🎯 Algorithm Study

<!-- TOC -->
* [🎯 Algorithm Study](#-algorithm-study)
  * [📚 문제](#-문제)
    * [Level 2](#level-2)
    * [Level 3](#level-3)
    * [Level 4](#level-4)
    * [Level 5](#level-5)
  * [🛠 공통 유틸리티](#-공통-유틸리티)
    * [🏭️ Array Converter](#-array-converter)
    * [🧪 CodingTest - 코테 문제를 위한 클래스](#-codingtest---코테-문제를-위한-클래스)
      * [사용 예시](#사용-예시)
<!-- TOC -->

## 📚 문제

### Level 2

| 문제                                                                            | 핵심 개념       | Code                                                                     | 기출                         | 코멘트             |
|-------------------------------------------------------------------------------|-------------|:-------------------------------------------------------------------------|----------------------------|-----------------|
| [아날로그 시계](https://school.programmers.co.kr/learn/courses/30/lessons/250135)   | 수학, 구현      | [💻](./src/main/java/gbpark/programmers/level2/AnalogClock.java)         | PCCP 기출문제                  | 각속도 개념 필요       |
| [충돌위험 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/340211)   | 구현, 시뮬레이션   | [💻](./src/main/java/gbpark/programmers/level2/CollisionDetection.java)  | PCCP 기출문제                  |                 |
| [석유 시추](https://school.programmers.co.kr/learn/courses/30/lessons/250136)     | BFS, 그래프 탐색 | [💻](./src/main/java/gbpark/programmers/level2/OilExtraction.java)       | PCCP 기출문제                  |                 |
| [퍼즐 게임 챌린지](https://school.programmers.co.kr/learn/courses/30/lessons/340212) | BST         | [💻](./src/main/java/gbpark/programmers/level2/PuzzleGameChallenge.java) | PCCP 기출문제                  |                 |
| [올바른 괄호](https://school.programmers.co.kr/learn/courses/30/lessons/12909)     | 스택          | [💻](./src/main/java/gbpark/programmers/level2/ValidBrackets.java)       |
| [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165)      | DFS, 완전탐색   | [💻](./src/main/java/gbpark/programmers/level2/TargetNumber.java)        |                            | 백트래킹 등으로 최적화 가능 |
| [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844)   | BFS, 그래프 탐색 | [💻](./src/main/java/gbpark/programmers/level2/ShortestGameMapPath.java) |
| [최고의 집합](https://school.programmers.co.kr/learn/courses/30/lessons/12938)     | 산술/기하 평균    | [💻](./src/main/java/gbpark/programmers/level2/BestSet.java)             | 고1 수준 산술기하를 알면 너무 쉬워 레벨 낮춤 |

### Level 3

| 문제                                                                           | 핵심 개념                      | Code                                                                           | 기출                                | 코멘트                                                              |
|------------------------------------------------------------------------------|----------------------------|--------------------------------------------------------------------------------|-----------------------------------|------------------------------------------------------------------|
| [수식 복원하기](https://school.programmers.co.kr/learn/courses/30/lessons/340210)  | 수학, 구현                     | [💻](./src/main/java/gbpark/programmers/level3/MathReconstruction.java)        | PCCP 기출문제                         |                                                                  |
| [수레 움직이기](https://school.programmers.co.kr/learn/courses/30/lessons/250134)  | BFS, 그래프 탐색                | [💻](./src/main/java/gbpark/programmers/level3/MovingCart.java)                | PCCP 기출문제                         | 체감 난이도 4                                                         |
| [네트워크](https://school.programmers.co.kr/learn/courses/30/lessons/43162)      | DFS, 완전 탐색                 | [💻](./src/main/java/gbpark/programmers/level3/Network.java)                   |                                   | 체감 난이도 2                                                         |
| [단어변환](https://school.programmers.co.kr/learn/courses/30/lessons/43163)      | BFS, 그래프 탐색                | [💻](./src/main/java/gbpark/programmers/level3/WordConversion.java)            |                                   |                                                                  |
| [퍼즐 조각 채우기](https://school.programmers.co.kr/learn/courses/30/lessons/84021) | 완전탐색, 구현, 객체 지향            | [💻](./src/main/java/gbpark/programmers/level3/PuzzlePieceFilling.java)        |                                   | equals override 사용                                               |
| [합승 택시 요금](https://school.programmers.co.kr/learn/courses/30/lessons/72413)  | 플로이드 워셜, 다익스트라             | [💻](./src/main/java/gbpark/programmers/level3/TaxiCostSharing.java)           | 2021 KAKAO BLIND RECRUITMENT      | 효율증가 요소 많음 - dijkstra, Comparable import, pq, 인접리스트              |
| [양과 늑대](https://school.programmers.co.kr/learn/courses/30/lessons/92343)     | DFS, 완전탐색                  | [💻](./src/main/java/gbpark/programmers/level3/SheepAndWolf.java)              | 2022 KAKAO BLIND RECRUITMENT      | 다음 방문 노드가 동적으로 변함                                                |
| [길 찾기 게임](https://school.programmers.co.kr/learn/courses/30/lessons/42892)   | PQ, BST, Tree Traversal    | [💻](./src/main/java/gbpark/programmers/level3/PathFindingGame.java)           | 2019 KAKAO BLIND RECRUITMENT      | 좌표로 이진트리 생성, 전위 순회/후위 순회                                         |
| [셔틀버스](https://school.programmers.co.kr/learn/courses/30/lessons/17678)      | PQ, 수학, 구현                 | [💻](./src/main/java/gbpark/programmers/level3/ShuttleBus.java)                | 2018 KAKAO BLIND RECRUITMENT [1차] | 좌표로 이진트리 생성, 전위 순회/후위 순회                                         |
| [거스름돈](https://school.programmers.co.kr/learn/courses/30/lessons/12907)      | DP, 구현                     | [💻](./src/main/java/gbpark/programmers/level3/CoinChange.java)                |                                   |                                                                  |
| [불량사용자](https://school.programmers.co.kr/learn/courses/30/lessons/64064)     | DFS, 백트래킹                  | [💻](./src/main/java/gbpark/programmers/level3/BannedUserFilter.java)          | 2019 카카오 개발자 겨울 인턴십               | Trie를 이용하면 오히려 복잡해짐.                                             |
| [금과 은 운반하기](https://school.programmers.co.kr/learn/courses/30/lessons/86053) | 이분탐색, 구현                   | [💻](./src/main/java/gbpark/programmers/level3/GoldSilverTransport.java)       | 월간 코드 챌린지 시즌3                     |                                                                  |
| [추석 트래픽](https://school.programmers.co.kr/learn/courses/30/lessons/17676)    | PQ, Greedy, Sliding Window | [💻](./src/main/java/gbpark/programmers/level3/HolidayTrafficAnalyzer.java)    | 2018 KAKAO BLIND RECRUITMENT[1차]  | 최대값이므로 시작 시간만 greedy 하게 고려하면 됨. 1.001s, 2.000s 일때 카운팅 하지 않는 점 주의 |
| [풍선 터뜨리기](https://school.programmers.co.kr/learn/courses/30/lessons/68646)   | PQ, Greedy                 | [💻](./src/main/java/gbpark/programmers/level3/BalloonPopping.java)            | 월간 코드 챌린지 시즌1                     | 좌측은 포인터를 갱신하며 최소값, 우측은 PQ로 최소값과 비교하며 최소값 풍선만 greedy 하게 계산        |
| [최적의 행렬 곱셈](https://school.programmers.co.kr/learn/courses/30/lessons/12942) | PQ, Greedy                 | [💻](./src/main/java/gbpark/programmers/level3/MatrixChainMultiplication.java) |                                   | 행렬 연쇄 곱셈(Matrix Chain Multiplication) 연산량 계산                     |
| [야근 지수](https://school.programmers.co.kr/learn/courses/30/lessons/12927)     | PQ, Greedy                 | [💻](./src/main/java/gbpark/programmers/level3/OvertimeScore.java)             |                                   | 제곱수 들의 성질을 알고있다면 너무 쉬움                                           |
| [사라지는 발판](https://school.programmers.co.kr/learn/courses/30/lessons/92345)   | DFS, 백트래킹, MinMax          | [💻](./src/main/java/gbpark/programmers/level3/VanishingFoothold.java)         | 2022 KAKAO BLIND RECRUITMENT      | Minimax 알고리즘의 alpha-beta 이용 백트래킹                                 |

### Level 4

| 문제                                                                              | 핵심 개념                    | Code                                                                          | 기출                                | 코멘트                                                                                        |
|---------------------------------------------------------------------------------|--------------------------|-------------------------------------------------------------------------------|-----------------------------------|--------------------------------------------------------------------------------------------|
| [매출 하락 최소화](https://school.programmers.co.kr/learn/courses/30/lessons/72416)    | DP, DFS                  | [💻](./src/main/java/gbpark/programmers/level4/MinimizingRevenueDecline.java) | 2021 KAKAO BLIND RECRUITMENT      | DFS 로 leafNode 탐색, leafNode 부터 root 까지 DP. DFS 로 노드 돌아오는 과정에 DP를 하는 과정이 비슷한 문제가 나올 여지가 많음. |
| [자동완성](https://school.programmers.co.kr/learn/courses/30/lessons/17685)         | PQ, Greedy               | [💻](./src/main/java/gbpark/programmers/level4/Autocomplete.java)             | 2018 KAKAO BLIND RECRUITMENT [3차] | PQ로 사전식 정렬 후 접두사 비교.                                                                       |
| [자동완성 by Trie](https://school.programmers.co.kr/learn/courses/30/lessons/17685) | Trie                     | [💻](./src/main/java/gbpark/programmers/level4/AutocompleteTrie.java)         | 2018 KAKAO BLIND RECRUITMENT [3차] | 검색, 자동완성 등에 사용되는 Trie 구조 사용                                                                |
| [지형이동](https://school.programmers.co.kr/learn/courses/30/lessons/62050)         | BFS, Greedy, PQ          | [💻](./src/main/java/gbpark/programmers/level4/TerrainMovement.java)          | Summer/Winter Coding(2019)        | BFS로 Union을 찾으며, 프림(Prim) 알고리즘과 같은 원리로 Greedy 하게 인접 Union과의 최소 경로를 연결하여 해결함.               |
| [4단 고음](https://school.programmers.co.kr/learn/courses/30/lessons/1831)         | DFS, 백트래킹, 구현            | [💻](./src/main/java/gbpark/programmers/level4/HighPitchedVoice.java)         | 2017 카카오코드 예선                     | 'x개수' * 2 < '+개수' 조건이 핵심                                                                   |
| [올바른 괄호 갯수](https://school.programmers.co.kr/learn/courses/30/lessons/12929)    | DFS, 백트래킹, Memo, Catalan | [💻](./src/main/java/gbpark/programmers/level4/CountValidParentheses.java)    |                                   | 카탈란 수                                                                                      |

### Level 5

| 문제                                                                         | 핵심 개념                   | Code                                                                 | 기출                 | 코멘트                                                                |
|----------------------------------------------------------------------------|-------------------------|----------------------------------------------------------------------|--------------------|--------------------------------------------------------------------|
| [시험장 나누기](https://school.programmers.co.kr/learn/courses/30/lessons/81305) | DFS, Tree, 이진탐색, Greedy | [💻](./src/main/java/gbpark/programmers/level4/ExamRoomDivider.java) | 2021 카카오 채용연계형 인턴십 | Tree 구조로 노드 구현. 이진탐색으로 최대값을 가정, DFS로 순회하며 Greedy 한 방식으로 집합 구성 및 체크 |

## 🛠 공통 유틸리티

- [TestUtil](./src/main/java/gbpark/common/TestUtil.java): 테스트 케이스 실행 및 결과 검증
- [ArrayConverter](./src/main/java/gbpark/common/ArrayConverter.java): 문자열 입력을 다양한 배열 형태로 변환

### 🏭️ Array Converter

| 메소드              | 설명                                                                                                |
|------------------|---------------------------------------------------------------------------------------------------|
| `toStringArray`  | "[\"item1\", item2]" 형태의 문자열을 String 배열로 변환합니다.                                                   |
| `toStringMatrix` | "[[\"row1item1\", row1item2], [row2item1, row2item2]]" 형태의 2차원 문자열을 String[][] 형태의 2차원 배열로 변환합니다. |
| `toCharArray`    | "[c,h,a,r,s]" 또는 "chars" 형태의 문자열을 char[] 배열로 변환합니다.                                               |
| `toCharMatrix`   | 2차원 문자 배열 형태의 문자열을 char[][] 배열로 변환합니다.                                                            |
| `toIntArray`     | "[1, 2, 3, 4]" 형태의 문자열을 int[] 배열로 변환합니다.                                                          |
| `toIntMatrix`    | "[[1, 2], [3, 4]]" 형태의 2차원 정수 배열 문자열을 int[][] 배열로 변환합니다.                                          | |

### 🧪 CodingTest - 코테 문제를 위한 클래스

스트링 으로 주어진 문제 반복 붙여넣기 해결을 위해 생성

| 생성자/메서드               | 설명                                                                         |
|-----------------------|----------------------------------------------------------------------------|
| `CodingTest`          | 함수형 인터페이스와 파라미터 타입을 사용하여 주어진 문제 스트링을 변환할 방법 설정                             |
| `CodingTest.DataType` | 코테에서 사용되는 입력 및 출력 값의 타입을 지정하는 enum (`INT`, `INT_ARRAY`, `STRING_MATRIX` 등) |
| `codingTest(String)`  | 생성자로 정해진 인자타입을 적용해서 매개변수로 넘어간 메소드 실행 후, TestUtil.test 실행                   |

#### 사용 예시

```java
import static gbpark.common.CodingTest.DataType.*;

public class CoinChangeExample {
	public static void main(String[] args) {
		CoinChange coinChange = new CoinChange();
		CodingTest codingTest = new CodingTest<>(coinChange::solution, INT, INT_ARRAY, INT);
		codingTest.codingTest("5\t[1,2,5]\t4");
		codingTest.codingTest("10\t[1,2,5]\t10");
	}
}
```

[⬆️Top](#-algorithm-study)