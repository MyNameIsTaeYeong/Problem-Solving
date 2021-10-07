package problem_solving_java;

import java.util.*;
import java.io.*;

//1. 모든 빈칸중 3개를 고르는 조합을 만든다.
//2. 각 조합에 대해 바이러스로 bfs를 한 후 0의 최대 개수를 구한다.

class Virus {
	public int x;
	public int y;

	public Virus(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_14502 {

	static int n, m;
	static int[][] center;
	static int zero_cnt = -3;
	static ArrayList<Virus> virus = new ArrayList<>();

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int ans = 0;

	static void bfs(int[][] copied_center) {
		int safety = zero_cnt;
		Queue<Virus> q = new LinkedList<>();
		boolean[][] check = new boolean[n][m];
		for (int i = 0; i < virus.size(); i++) {
			Virus v = virus.get(i);
			check[v.x][v.y] = true;
			q.add(v);
		}

		while (!q.isEmpty()) {
			Virus v = q.poll();

			for (int i = 0; i < 4; i++) {
				int next_x = v.x + dx[i];
				int next_y = v.y + dy[i];
				if (next_x >= 0 && next_x < n && next_y >= 0 && next_y < m) {
					if (copied_center[next_x][next_y] == 0 && !check[next_x][next_y]) {
						check[next_x][next_y] = true;
						copied_center[next_x][next_y] = 2;
						q.add(new Virus(next_x, next_y));
						safety--;
					}
				}
			}

		}

		if (safety > ans) {
			ans = safety;
		}

	}

	static int[][] copy_center() {
		int[][] copy = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = center[i][j];
			}
		}

		return copy;
	}

	static void make_combinations(int x, int y, int walls) {
		if (walls == 3) {
			bfs(copy_center());
			return;
		}

		if (x == n - 1 && y == m - 1) {
			return;
		}

		for (int i = x; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (i == x && j < y) {
					continue;
				}
				if (center[i][j] == 0) {
					center[i][j] = 1;
					make_combinations(i, j, walls + 1);
					center[i][j] = 0;
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		center = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				center[i][j] = Integer.parseInt(st.nextToken());
				if (center[i][j] == 0) {
					zero_cnt++;
				}
				if (center[i][j] == 2) {
					virus.add(new Virus(i, j));
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (center[i][j] == 0) {
					center[i][j] = 1;
					make_combinations(i, j, 1);
					center[i][j] = 0;
				}
			}
		}

		System.out.print(ans);

	}

}
