package problem_solving_java;

import java.util.*;
import java.io.*;

// 모든 칸에서 bfs

class Pair {
	public int x;
	public int y;
	public int dist;

	public Pair(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class BOJ_17086 {

	static int n, m;
	static int[][] space;

	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

	static int bfs(int start_x, int start_y) {
		boolean[][] check = new boolean[n][m];
		check[start_x][start_y] = true;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(start_x, start_y, 0));

		while (!q.isEmpty()) {
			Pair p = q.poll();

			if (space[p.x][p.y] == 1) {
				return p.dist;
			}

			for (int i = 0; i < 8; i++) {
				int next_x = p.x + dx[i];
				int next_y = p.y + dy[i];
				if (0 <= next_x && next_x < n && 0 <= next_y && next_y < m && !check[next_x][next_y]) {
					check[next_x][next_y] = true;
					q.add(new Pair(next_x, next_y, p.dist + 1));
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

		space = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (space[i][j] == 0) {
					ans = Math.max(ans, bfs(i, j));
				}
			}
		}

		System.out.print(ans);

	}

}
