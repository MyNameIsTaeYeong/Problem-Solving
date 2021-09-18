package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_16964 {

	static int n;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static ArrayList<Integer> input = new ArrayList<>();
	static int index = 0;

	static boolean dfs(int node, boolean[] check) {

		if (node != input.get(index++)) {
			return false;
		}

		if (index == n) {
			return true;
		}

		check[node] = true;

		for (int i = 0; i < graph.get(node).size(); i++) {
			if (!check[graph.get(node).get(i)]) {
				if (dfs(graph.get(node).get(i), check)) {
					return true;
				}
			}
		}

		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			graph.get(parent).add(child);
			graph.get(child).add(parent);
		}

		int[] order = new int[n + 1];
		int order_cnt = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		while (st.hasMoreTokens()) {
			int val = Integer.parseInt(st.nextToken());
			input.add(val);
			order[val] = order_cnt++;
		}

		for (int i = 1; i <= n; i++) {
			Collections.sort(graph.get(i), (a, b) -> order[a] - order[b]);
		}

		if (dfs(1, new boolean[n + 1])) {
			System.out.print(1);
		} else {
			System.out.print(0);
		}
	}

}
