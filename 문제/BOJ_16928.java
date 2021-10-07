package problem_solving_java;

import java.util.*;
import java.io.*;

class Pair {
	public int x;
	public int depth;

	public Pair(int x, int depth) {
		this.x = x;
		this.depth = depth;
	}
}

public class BOJ_16928 {

	static int n, m;
	static int[] board;

	static int bfs(int start) {
		boolean[] check = new boolean[101];
		check[start] = true;

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(start, 0));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.x;

			for (int i = 1; i <= 6; i++) {
				int y;

				if (x + i == 100) {
					return p.depth + 1;
				}
				if (board[x + i] != 0) {
					y = board[x + i];
				} else {
					y = x + i;
				}

				if (!check[y]) {
					check[y] = true;
					q.add(new Pair(y, p.depth + 1));
				}
			}

		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new int[101];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			board[x] = y;
		}

		System.out.print(bfs(1));
	}

}
