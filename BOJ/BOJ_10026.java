package problem_solving_java;

import java.io.*;
import java.util.*;

class Pair {
	public int x;
	public int y;
	public char color;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_10026 {

	static int n;
	static char[][] grid;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, 1, -1, 0 };

	static void bfs(boolean[][] check, int x, int y, char[] color) {
		check[x][y] = true;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int i = 0; i < 4; i++) {
				int next_x = p.x + dx[i];
				int next_y = p.y + dy[i];
				if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n) {
					if ((color.length == 2 ? grid[next_x][next_y] == color[0] || grid[next_x][next_y] == color[1]
							: grid[next_x][next_y] == color[0]) && !check[next_x][next_y]) {
						check[next_x][next_y] = true;
						q.add(new Pair(next_x, next_y));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		grid = new char[n][n];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < n; j++) {
				grid[i][j] = input.charAt(j);
			}
		}

		int not_rg = 0;
		boolean[][] check = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!check[i][j]) {
					char[] color = new char[1];
					color[0] = grid[i][j];
					bfs(check, i, j, color);
					not_rg++;
				}
			}
		}

		int rg = 0;
		check = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!check[i][j]) {
					if (grid[i][j] == 'B') {
						char[] color = new char[1];
						color[0] = 'B';
						bfs(check, i, j, color);
						rg++;
					} else {
						char[] color = new char[2];
						color[0] = 'R';
						color[1] = 'G';
						bfs(check, i, j, color);
						rg++;
					}

				}
			}
		}

		System.out.print(not_rg + " " + rg);

	}

}
