package problem_solving_java;

import java.util.*;
import java.io.*;

// 1. 연합나누기 -> dfs로 범위안에 들면 같은 id부여. 
// 2. 인구이동 발생 체크

public class BOJ_16234 {

	static int n, l, r;
	static int[][] ddang;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static void dfs(int[][] union, int union_id, boolean[][] check, int x, int y) {
		check[x][y] = true;
		union[x][y] = union_id;

		for (int i = 0; i < 4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n && !check[next_x][next_y]) {
				int dif = Math.abs(ddang[x][y] - ddang[next_x][next_y]);
				if (l <= dif && dif <= r) {
					dfs(union, union_id, check, next_x, next_y);
				}
			}
		}

	}

	static boolean simulate() {

		int[][] union = new int[n][n];
		boolean[][] check = new boolean[n][n];
		int union_id = 1;

		// 연합나누기.
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (union[x][y] == 0) {
					dfs(union, union_id++, check, x, y);
				}
			}
		}

		HashMap<Integer, Integer> total_population = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> total_count = new HashMap<Integer, Integer>();

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				total_population.put(union[x][y], total_population.getOrDefault(union[x][y], 0) + ddang[x][y]);
				total_count.put(union[x][y], total_count.getOrDefault(union[x][y], 0) + 1);
			}
		}

		boolean move = false;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				ddang[x][y] = total_population.get(union[x][y]) / total_count.get(union[x][y]);
				if (total_count.get(union[x][y]) != 1) {
					move = true;
				}
			}
		}

		return move;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		ddang = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				ddang[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int ans = 0;

		while (simulate()) {
			ans++;
		}

		System.out.print(ans);
	}

}
