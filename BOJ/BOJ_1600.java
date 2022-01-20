package problem_solving_java;

import java.util.*;
import java.io.*;

// distance[n][m][k] : k번 말처럼 뛰어서 이동할때 최소 이동 횟수.

class Monkey {
	public int x;
	public int y;
	public int k;
	public int dist;

	public Monkey(int x, int y, int k, int dist) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.dist = dist;
	}
}

public class BOJ_1600 {

	static int k, w, h;
	static int[][] map;
	static int[][][] distance;

	static int[] h_dx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] h_dy = { -2, -1, 1, 2, 2, 1, -1, -2 };

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static void bfs() {
		Queue<Monkey> q = new LinkedList<>();
		q.add(new Monkey(0, 0, 0, 0));
		for (int i = 0; i <= k; i++) {
			distance[0][0][k] = 0;
		}

		while (!q.isEmpty()) {
			Monkey m = q.poll();

			// 원숭이
			for (int i = 0; i < 4; i++) {
				int next_x = m.x + dx[i];
				int next_y = m.y + dy[i];
				if (0 <= next_x && next_x < h && 0 <= next_y && next_y < w && map[next_x][next_y] == 0) {
					if (m.dist + 1 < distance[next_x][next_y][m.k]) {
						distance[next_x][next_y][m.k] = m.dist + 1;
						q.add(new Monkey(next_x, next_y, m.k, m.dist + 1));
					}
				}
			}

			// 말
			if (m.k < k) {
				for (int i = 0; i < 8; i++) {
					int next_x = m.x + h_dx[i];
					int next_y = m.y + h_dy[i];
					if (0 <= next_x && next_x < h && 0 <= next_y && next_y < w && map[next_x][next_y] == 0) {
						if (m.dist + 1 < distance[next_x][next_y][m.k + 1]) {
							distance[next_x][next_y][m.k + 1] = m.dist + 1;
							q.add(new Monkey(next_x, next_y, m.k + 1, m.dist + 1));
						}
					}
				}
			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new int[h][w];
		distance = new int[h][w][k + 1];

		for (int i = 0; i < h; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int[][] first : distance) {
			for (int[] second : first) {
				Arrays.fill(second, 50000);
			}
		}

		bfs();

		int ans = 50000;

		for (int i = 0; i <= k; i++) {
			if (distance[h - 1][w - 1][i] < ans) {
				ans = distance[h - 1][w - 1][i];
			}
		}

		if (ans == 50000) {
			System.out.print(-1);
		} else {
			System.out.print(ans);
		}

	}

}
