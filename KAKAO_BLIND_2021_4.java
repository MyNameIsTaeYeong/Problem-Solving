import java.util.*;

class Pair implements Comparable<Pair> {
	public int key;
	public int value;

	public Pair(int key, int value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public int compareTo(Pair p) {
		return p.value - this.value;
	}
}

class Solution {

	final static int INF = 2000000000;

	static int[][] graph;
	static int[][] cost;

	static void dijkstra(int start, int n) {

		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(start, 0));
		cost[start][start] = 0;

		while (pq.size() != 0) {
			Pair p = pq.poll();
			int node = p.key;
			int dist = p.value;

			if (cost[start][node] < dist) {
				continue;
			}

			for (int i = 1; i <= n; i++) {
				if (graph[node][i] == 0) {
					continue;
				}

				if (dist + graph[node][i] < cost[start][i]) {

					cost[start][i] = dist + graph[node][i];
					pq.add(new Pair(i, cost[start][i]));
				}
			}
		}

	}

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = 0;

		graph = new int[n + 1][n + 1];
		cost = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				cost[i][j] = INF;
			}
		}

		for (int i = 0; i < fares.length; i++) {
			int start = fares[i][0];
			int end = fares[i][1];
			int fare = fares[i][2];
			graph[start][end] = fare;
			graph[end][start] = fare;
		}

		dijkstra(s, n);
		dijkstra(a, n);
		dijkstra(b, n);

		answer = cost[s][a] + cost[s][b];

		for (int i = 1; i <= n; i++) {
			int both_cost = cost[s][i] + cost[a][i] + cost[b][i];
			if (both_cost < answer) {
				answer = both_cost;
			}
		}

		return answer;
	}
}