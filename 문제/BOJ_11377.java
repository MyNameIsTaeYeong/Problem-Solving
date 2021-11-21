package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_11377 {

	// 0 : source, n + 1 : sink, n + 2 : bridge
	static int[] sourceEdges;
	static int[] bridgeEdges;
	static int[][] edges;

	static int n, m, k;

	static int maxFlow() {
		int result = 0;
		int[][] flows = new int[2005][2005];
		int[] prev = new int[2005];

		while (true) {
			Arrays.fill(prev, -1);
			Queue<Integer> q = new LinkedList<>();
			q.add(0);

			while (!q.isEmpty()) {
				int node = q.poll();

				if (node == n + 1)
					break;

				if (node >= 1003 || node == 0 || node == n + 2) {
					for (int i = 0; i <= n + 2; i++) {
						if (prev[i] == -1 && edges[node][i] - flows[node][i] > 0) {
							prev[i] = node;
							q.add(i);
						}
					}
				} else if ((1 <= node && node <= n) || node == n + 1) {
					for (int i = 1003; i <= m + 1002; i++) {
						if (prev[i] == -1 && edges[node][i] - flows[node][i] > 0) {
							prev[i] = node;
							q.add(i);
						}
					}
					if (1 <= node && node <= n) {
						if (prev[0] == -1 && edges[node][0] - flows[node][0] > 0) {
							prev[0] = node;
							q.add(0);
						}

						if (prev[n + 2] == -1 && edges[node][n + 2] - flows[node][n + 2] > 0) {
							prev[n + 2] = node;
							q.add(n + 2);
						}
					}

				}

			}

			if (prev[n + 1] == -1)
				break;

			int minFlow = Integer.MAX_VALUE;
			for (int i = n + 1; i != 0; i = prev[i]) {
				minFlow = Math.min(minFlow, edges[prev[i]][i] - flows[prev[i]][i]);
			}

			for (int i = n + 1; i != 0; i = prev[i]) {
				flows[prev[i]][i] += minFlow;
				flows[i][prev[i]] -= minFlow;
			}

			result += minFlow;

		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		edges = new int[2005][2005];

		edges[0][n + 2] = k;
		for (int i = 1; i <= n; i++) {
			edges[0][i] = 1;
			edges[n + 2][i] = 1;
			st = new StringTokenizer(br.readLine(), " ");
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				int job = Integer.parseInt(st.nextToken()) + 1002;
				edges[i][job] = 1;
				edges[job][n + 1] = 1;
			}
		}

		System.out.print(maxFlow());

	}

}
