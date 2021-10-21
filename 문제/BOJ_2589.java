package problem_solving_java;

import java.util.*;
import java.io.*;

// 모든 육지에서 bfs로 거리가 가장 먼 곳 구하기

class Pair {
	public int x;
	public int y;
	public int step;

	public Pair(int x, int y, int step) {
		this.x = x;
		this.y = y;
		this.step = step;
	}
}

public class BOJ_2589 {

	static int r, c;
	static char[][] map;
	static int ans = 0;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y, 0));
		boolean[][] check = new boolean[r][c];
		check[x][y] = true;

		while (!q.isEmpty()) {
			Pair p = q.poll();

			if (p.step > ans) {
				ans = p.step;
			}

			for (int i = 0; i < 4; i++) {
				int next_x = p.x + dx[i];
				int next_y = p.y + dy[i];
				if (0 <= next_x && next_x < r && 0 <= next_y && next_y < c && map[next_x][next_y] == 'L'
						&& !check[next_x][next_y]) {
					check[next_x][next_y] = true;
					q.add(new Pair(next_x, next_y, p.step + 1));
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];

		for (int i = 0; i < r; i++) {
			String line = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (map[i][j] == 'L') {
					bfs(i, j);
				}
			}
		}

		System.out.print(ans);
	}

}
