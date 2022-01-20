package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_16940 {

	static int n;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static ArrayList<Integer> input = new ArrayList<>();

	static boolean solve() {
		Queue<Integer> q = new LinkedList<>();
		q.add(1);

		int[] parent = new int[n + 1];
		boolean[] check = new boolean[n + 1];
		check[1] = true;
		int index = 1;

		for (int i = 0; i < n; i++) {
			if (q.size() == 0) {
				return false;
			}

			int node = q.poll();
			if (node != input.get(i)) {
				return false;
			}

			int cnt = 0;

			for (int j = 0; j < graph.get(node).size(); j++) {
				if (!check[graph.get(node).get(j)]) {
					parent[graph.get(node).get(j)] = node;
					cnt++;
				}
			}

			if (index + cnt > n) {
				return false;
			}

			for (int j = index; j < index + cnt; j++) {
				int next_node = input.get(j);
				if (parent[next_node] == node && !check[next_node]) {
					q.add(next_node);
					check[next_node] = true;
				}
			}

			index += cnt;
		}

		return true;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		for (int i = 0; i <= n; i++) {
			ArrayList<Integer> list = new ArrayList<>();
			graph.add(list);
		}

		for (int i = 1; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			graph.get(parent).add(child);
			graph.get(child).add(parent);
		}

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		while (st.hasMoreTokens()) {
			input.add(Integer.parseInt(st.nextToken()));
		}

		if (solve()) {
			System.out.print(1);
		} else {
			System.out.print(0);
		}

	}

}
