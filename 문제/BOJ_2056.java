package problem_solving_java;

import java.util.*;
import java.io.*;

// 위상정렬 
// indegree가 0일때 큐에 들어가는 작업중 최대값을 취한다. 

public class BOJ_2056 {

	static int n;
	static ArrayList<ArrayList<Integer>> edges;
	static int[] weight;
	static int[] indegree;
	static int[] time;
	static int ans = 0;

	static void bfs() {
		Queue<Integer> q = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.add(i);
				time[i] = weight[i];
			}
		}

		while (!q.isEmpty()) {
			int job = q.poll();

			ans = Math.max(ans, time[job]);

			for (int i = 0; i < edges.get(job).size(); i++) {
				int next_job = edges.get(job).get(i);
				time[next_job] = Math.max(time[next_job], time[job] + weight[next_job]);
				indegree[next_job]--;
				if (indegree[next_job] == 0) {
					q.add(next_job);
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		edges = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			edges.add(new ArrayList<>());
		}

		weight = new int[n + 1];
		indegree = new int[n + 1];
		time = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			weight[i] = Integer.parseInt(st.nextToken());

			int pre_cnt = Integer.parseInt(st.nextToken());
			indegree[i] = pre_cnt;
			for (int index = 0; index < pre_cnt; index++) {
				int pre = Integer.parseInt(st.nextToken());
				edges.get(pre).add(i);
			}
		}

		bfs();

		System.out.print(ans);
	}

}
