package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_2252 {

	static int n, m;

	static void bfs(ArrayList<ArrayList<Integer>> edge, int[] indegree) {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int stu = q.poll();

			System.out.print(stu + " ");

			for (int i = 0; i < edge.get(stu).size(); i++) {
				int next_stu = edge.get(stu).get(i);
				indegree[next_stu]--;
				if (indegree[next_stu] == 0) {
					q.add(next_stu);
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		ArrayList<ArrayList<Integer>> edge = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			edge.add(new ArrayList<>());
		}

		int[] indegree = new int[n + 1];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edge.get(start).add(end);
			indegree[end]++;
		}

		bfs(edge, indegree);

	}

}
