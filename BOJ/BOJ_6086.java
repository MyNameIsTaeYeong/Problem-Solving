package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_6086 {

	static long[][] edges;

	static long maxFlow() {
		long result = 0;

		long[][] flows = new long[60][60];
		int[] prev = new int[60];

		while (true) {
			Arrays.fill(prev, -1);
			boolean[] check = new boolean[60];
			check[0] = true;
			Queue<Integer> q = new LinkedList<>();
			q.add(0);

			while (!q.isEmpty()) {
				int node = q.poll();

				if (node == (int) ('Z' - 'A')) {
					break;
				}

				for (int i = 0; i < 60; i++) {
					if (edges[node][i] == 0) {
						continue;
					}

					if (!check[i] && edges[node][i] - flows[node][i] > 0) {
						check[i] = true;
						prev[i] = node;
						q.add(i);
					}
				}
			}

			if (prev['Z' - 'A'] == -1) {
				break;
			}

			long flow = Long.MAX_VALUE;
			for (int i = 'Z' - 'A'; i != 0; i = prev[i]) {
				flow = Math.min(flow, edges[prev[i]][i] - flows[prev[i]][i]);
			}

			for (int i = 'Z' - 'A'; i != 0; i = prev[i]) {
				flows[prev[i]][i] += flow;
				flows[i][prev[i]] -= flow;
			}

			result += flow;

		}

		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		edges = new long[60][60];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int node1 = st.nextToken().charAt(0) - 'A';
			int node2 = st.nextToken().charAt(0) - 'A';
			long weight = Long.parseLong(st.nextToken());

			edges[node1][node2] += weight;
			edges[node2][node1] += weight;
		}
		
		System.out.print(maxFlow());
	}

}
