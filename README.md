# 알고리즘 노트

## 종류

### 브루트 포스
- 모든 경우에 대해 다해보기
- O(경우의 수 * 1가지 경우를 푸는데 걸리는 시간)

### 완전 탐색
- bfs, dfs

### 정렬
- 투포인터, 이분탐색

# 트리
- 트라이, 최소공통조상

### 이진트리
- heap, segment tree

### 수학
- 거듭제곱, gcd, 확장 gcd, 소수, 파스칼삼각형

# 그래프

### 유니온 파인드

### 위상정렬
- DAG(방향이 있고 사이클이 없는 그래프)인 경우 사용.
- 특정 원소끼리 위상을 유지한체 정렬.

### MST
- 크루스칼, 프림

### 단절점

### 다익스트라
- 그래프에 양의 간선만 가지고 있는 경우 최단경로를 구하는 알고리즘.
- O(nlog(n)) (n : node개수)

### 벨만포드
- 그래프에 음수간선이 포함되었을 때 최단경로를 구하는 알고리즘.
- 음수간선의 순환을 감지할 수 있다.
- O(NM) (n : node개수, m : edge개수)

### 플로이드 와샬

### DP
- 작은 문제로 큰 문제를 풀 수 있는가?
- 누적합


## 문제 접근 방식
1. 모든 경우에 대해 풀어도 가능한가? (시간복잡도 체크)
2. 문제의 상황에서 유용한 알고리즘 생각하기
    - 최단거리 => bfs, dijkstra
    - 구간 => 투 포인터, 슬라이딩 윈도우
3. 간단한 시도해보기
   1. 정렬해본다.
   2. 문제에 주어진 순서를 거꾸로해보기.
   3. 그림그려보기
   4. 천천히 경우의 수 나눠보기.
4. 넘긴다.


## 맞왜틀 체크리스트
- 답이 없는 경우도 생기는가? 생긴다면 현재 풀이가 그 경우를 포함하는가?
- 최대 제한조건을 만족하는가?
- 문제가 요구하는 순서를 정확하게 따르는가?
- 중간의 연산과정에서 overflow or underflow가 일어나는가?
- 1%의 의심이라도 든다면 무조건 틀린다.

