# 🎯 Algorithm Study

## 📚 문제

### Level 2

| 문제                                                                            | 핵심 개념       | 풀이 코드                                                                    |
|-------------------------------------------------------------------------------|-------------|--------------------------------------------------------------------------|
| [아날로그 시계](https://school.programmers.co.kr/learn/courses/30/lessons/250135)   | 수학, 구현      | [코드](./src/main/java/gbpark/programmers/level2/AnalogClock.java)         |
| [충돌위험 찾기](https://school.programmers.co.kr/learn/courses/30/lessons/340211)   | 구현, 시뮬레이션   | [코드](./src/main/java/gbpark/programmers/level2/CollisionDetection.java)  |
| [석유 시추](https://school.programmers.co.kr/learn/courses/30/lessons/250136)     | BFS, 그래프 탐색 | [코드](./src/main/java/gbpark/programmers/level2/OilExtraction.java)       |
| [퍼즐 게임 챌린지](https://school.programmers.co.kr/learn/courses/30/lessons/340212) | 이진 탐색       | [코드](./src/main/java/gbpark/programmers/level2/PuzzleGameChallenge.java) |
| [올바른 괄호](https://school.programmers.co.kr/learn/courses/30/lessons/12909)     | 스택          | [코드](./src/main/java/gbpark/programmers/level2/ValidBrackets.java)       |
| [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165)      | DFS, 완전탐색   | [코드](./src/main/java/gbpark/programmers/level2/TargetNumber.java)        |
| [게임 맵 최단거리](https://school.programmers.co.kr/learn/courses/30/lessons/1844)   | BFS, 완전탐색   | [코드](./src/main/java/gbpark/programmers/level2/ShortestGameMapPath.java) |

### Level 3

| 문제                                                                          | 핵심 개념       | 풀이 코드                                                                   |
|-----------------------------------------------------------------------------|-------------|-------------------------------------------------------------------------|
| [수식 복원하기](https://school.programmers.co.kr/learn/courses/30/lessons/340210) | 수학, 구현      | [코드](./src/main/java/gbpark/programmers/level3/MathReconstruction.java) |
| [수레 움직이기](https://school.programmers.co.kr/learn/courses/30/lessons/250134) | BFS, 그래프 탐색 | [코드](./src/main/java/gbpark/programmers/level3/MovingCart.java)         |

### Level 4

| 문제                                                                           | 핵심 개념   | 풀이 코드                                                                         |
|------------------------------------------------------------------------------|---------|-------------------------------------------------------------------------------|
| [매출 하락 최소화](https://school.programmers.co.kr/learn/courses/30/lessons/72416) | DP, DFS | [코드](./src/main/java/gbpark/programmers/level4/MinimizingRevenueDecline.java) |

## 🛠 공통 유틸리티

- [TestUtil](./src/main/java/gbpark/common/TestUtil.java): 테스트 케이스 실행 및 결과 검증
- [ArrayConverter](./src/main/java/gbpark/common/ArrayConverter.java): 문자열 입력을 다양한 배열 형태로 변환

### 🛠️ ArrayConverter

| 메소드              | 설명                                                                                            |
|------------------|-----------------------------------------------------------------------------------------------|
| `toStringArray`  | "[item1, item2]" 형태의 문자열을 String 배열로 변환합니다.                                                   |
| `toStringMatrix` | "[[row1item1, row1item2], [row2item1, row2item2]]" 형태의 2차원 문자열을 String[][] 형태의 2차원 배열로 변환합니다. |
| `toCharArray`    | "[c,h,a,r,s]" 또는 "chars" 형태의 문자열을 char[] 배열로 변환합니다.                                           |
| `toCharMatrix`   | 2차원 문자 배열 형태의 문자열을 char[][] 배열로 변환합니다.                                                        |
| `toIntArray`     | "[1, 2, 3, 4]" 형태의 문자열을 int[] 배열로 변환합니다.                                                      |
| `toIntMatrix`    | "[[1, 2], [3, 4]]" 형태의 2차원 정수 배열 문자열을 int[][] 배열로 변환합니다.                                      | |