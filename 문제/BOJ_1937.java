package problem_solving_java;

import java.util.*;
import java.io.*;

// memo[m][n] : m행 n열을 통해 이동하는 최대 칸 수.

class Panda {
	public int x;
	public int y;
	public int step;
	public int pre;

	public Panda(int x, int y, int step, int pre) {
		this.x = x;
		this.y = y;
		this.step = step;
		this.pre = pre;
	}
}

public class BOJ_1937 {
	static int n;
	static int[][] map;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] memo;

	static int dfs(int x, int y) {
		if (memo[x][y] != 0) {
			return memo[x][y];
		}
		memo[x][y] = 1;

		for (int i = 0; i < 4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n && map[x][y] < map[next_x][next_y]) {
				memo[x][y] = Math.max(dfs(next_x, next_y) + 1, memo[x][y]);
			}
		}

		return memo[x][y];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		memo = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				dfs(i, j);
			}
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ans = Math.max(ans, memo[i][j]);
			}
		}

		System.out.print(ans);

	}

}
