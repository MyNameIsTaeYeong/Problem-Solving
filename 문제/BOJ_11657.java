package problem_solving_java;

import java.util.*;
import java.io.*;

// 벨만포드 알고리즘

class Bus {
	public int start;
	public int end;
	public int cost;

	public Bus(int start, int end, int cost) {
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
}

public class BOJ_11657 {

	static int n, m;
	static ArrayList<Bus> bus = new ArrayList<>();
	static final int MAX = 2000000000;
	static long[] dist;

	static boolean bf(int start) {
		dist = new long[n + 1];
		Arrays.fill(dist, MAX);
		dist[start] = 0;

		for (int i = 0; i < n; i++) {

			for (int j = 0; j < m; j++) {
				int b_start = bus.get(j).start;
				int b_end = bus.get(j).end;
				int b_cost = bus.get(j).cost;

				if (dist[b_start] != MAX && dist[b_end] > dist[b_start] + b_cost) {
					dist[b_end] = dist[b_start] + b_cost;
					if (i == n - 1) {
						return true;
					}
				}

			}

		}

		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			bus.add(new Bus(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())));
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		if (bf(1)) {
			System.out.println(-1);
		} else {
			for (int i = 2; i <= n; i++) {
				if (dist[i] == MAX) {
					bw.write("-1\n");
				} else {
					bw.write(Long.toString(dist[i]) + "\n");
				}
			}
		}
		
		bw.flush();

	}

}
