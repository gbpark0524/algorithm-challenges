# 🎯 Algorithm Study

<!-- TOC -->
* [🎯 Algorithm Study](#-algorithm-study)
  * [📚 문제](#-문제)
    * [Level 2](#level-2)
    * [Level 3](#level-3)
    * [Level 4](#level-4)
  * [🛠 공통 유틸리티](#-공통-유틸리티)
    * [🏭️ Array Converter](#-array-converter)
<!-- TOC -->

## 📚 문제

### Level 2

| 문제                                                                            | 핵심 개념       | 풀이 코드                                                                    | 기출                         | 코멘트             |
|-------------------------------------------------------------------------------|-------------|--------------------------------------------------------------------------|----------------------------|-----------------|
| [아날로그 시계](https://school.programmers.co.kr/learn/courses/30/lessons/250135)   | 수학, 구현      | [코드](./src/main/java/gbpark/programmers/level2/AnalogClock.java)         | PCCP 기출문제                  | 각속도 개념 필요       |
| [충돌위험 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/340211)   | 구현, 시뮬레이션   | [코드](./src/main/java/gbpark/programmers/level2/CollisionDetection.java)  | PCCP 기출문제                  |                 |
| [석유 시추](https://school.programmers.co.kr/learn/courses/30/lessons/250136)     | BFS, 그래프 탐색 | [코드](./src/main/java/gbpark/programmers/level2/OilExtraction.java)       | PCCP 기출문제                  |                 |
| [퍼즐 게임 챌린지](https://school.programmers.co.kr/learn/courses/30/lessons/340212) | BST         | [코드](./src/main/java/gbpark/programmers/level2/PuzzleGameChallenge.java) | PCCP 기출문제                  |                 |
| [올바른 괄호](https://school.programmers.co.kr/learn/courses/30/lessons/12909)     | 스택          | [코드](./src/main/java/gbpark/programmers/level2/ValidBrackets.java)       |
| [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165)      | DFS, 완전탐색   | [코드](./src/main/java/gbpark/programmers/level2/TargetNumber.java)        |                            | 백트래킹 등으로 최적화 가능 |
| [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844)   | BFS, 그래프 탐색 | [코드](./src/main/java/gbpark/programmers/level2/ShortestGameMapPath.java) |
| [최고의 집합](https://school.programmers.co.kr/learn/courses/30/lessons/12938)     | 산술/기하 평균    | [코드](./src/main/java/gbpark/programmers/level2/BestSet.java)             | 고1 수준 산술기하를 알면 너무 쉬워 레벨 낮춤 |

### Level 3

| 문제                                                                           | 핵심 개념                   | 풀이 코드                                                                   | 기출                                | 코멘트                                                 |
|------------------------------------------------------------------------------|-------------------------|-------------------------------------------------------------------------|-----------------------------------|-----------------------------------------------------|
| [수식 복원하기](https://school.programmers.co.kr/learn/courses/30/lessons/340210)  | 수학, 구현                  | [코드](./src/main/java/gbpark/programmers/level3/MathReconstruction.java) | PCCP 기출문제                         |                                                     |
| [수레 움직이기](https://school.programmers.co.kr/learn/courses/30/lessons/250134)  | BFS, 그래프 탐색             | [코드](./src/main/java/gbpark/programmers/level3/MovingCart.java)         | PCCP 기출문제                         | 체감 난이도 4                                            |
| [네트워크](https://school.programmers.co.kr/learn/courses/30/lessons/43162)      | DFS, 완전 탐색              | [코드](./src/main/java/gbpark/programmers/level3/Network.java)            |                                   | 체감 난이도 2                                            |
| [단어변환](https://school.programmers.co.kr/learn/courses/30/lessons/43163)      | BFS, 그래프 탐색             | [코드](./src/main/java/gbpark/programmers/level3/WordConversion.java)     |                                   |                                                     |
| [퍼즐 조각 채우기](https://school.programmers.co.kr/learn/courses/30/lessons/84021) | 완전탐색, 구현, 객체 지향         | [코드](./src/main/java/gbpark/programmers/level3/PuzzlePieceFilling.java) |                                   | equals override 사용                                  |
| [합승 택시 요금](https://school.programmers.co.kr/learn/courses/30/lessons/72413)  | 플로이드 워셜, 다익스트라          | [코드](./src/main/java/gbpark/programmers/level3/TaxiCostSharing.java)    | 2021 KAKAO BLIND RECRUITMENT      | 효율증가 요소 많음 - dijkstra, Comparable import, pq, 인접리스트 |
| [양과 늑대](https://school.programmers.co.kr/learn/courses/30/lessons/92343)     | DFS, 완전탐색               | [코드](./src/main/java/gbpark/programmers/level3/SheepAndWolf.java)       | 2022 KAKAO BLIND RECRUITMENT      | 다음 방문 노드가 동적으로 변함                                   |
| [길 찾기 게임](https://school.programmers.co.kr/learn/courses/30/lessons/42892)   | PQ, BST, Tree Traversal | [코드](./src/main/java/gbpark/programmers/level3/ShuttleBus.java)         | 2019 KAKAO BLIND RECRUITMENT      | 좌표로 이진트리 생성, 전위 순회/후위 순회                            |
| [셔틀버스](https://school.programmers.co.kr/learn/courses/30/lessons/17678)      | PQ, 수학, 구현              | [코드](./src/main/java/gbpark/programmers/level3/PathFindingGame.java)    | 2018 KAKAO BLIND RECRUITMENT [1차] | 좌표로 이진트리 생성, 전위 순회/후위 순회                            |

### Level 4

| 문제                                                                           | 핵심 개념      | 풀이 코드                                                                         | 기출                                | 코멘트                                                                                        |
|------------------------------------------------------------------------------|------------|-------------------------------------------------------------------------------|-----------------------------------|--------------------------------------------------------------------------------------------|
| [매출 하락 최소화](https://school.programmers.co.kr/learn/courses/30/lessons/72416) | DP, DFS    | [코드](./src/main/java/gbpark/programmers/level4/MinimizingRevenueDecline.java) | 2021 KAKAO BLIND RECRUITMENT      | DFS 로 leafNode 탐색, leafNode 부터 root 까지 DP. DFS 로 노드 돌아오는 과정에 DP를 하는 과정이 비슷한 문제가 나올 여지가 많음. |
| [자동완성](https://school.programmers.co.kr/learn/courses/30/lessons/17685)      | PQ, Greedy | [코드](./src/main/java/gbpark/programmers/level4/Autocomplete.java)             | 2018 KAKAO BLIND RECRUITMENT [3차] | PQ로 사전식 정렬 후 접두사 비교.                                                                       |

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

[⬆️Top](#-algorithm-study)