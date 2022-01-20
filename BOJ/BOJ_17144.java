package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_17144 {

	static int r, c, t;
	static int[][] a;
	static int air_x, air_y;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static void spread() {

		int[][] new_a = new int[r][c];

		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				if (a[x][y] == -1) {
					new_a[x][y] = -1;
				} else {
					if (a[x][y] != 0) {

						int dust = a[x][y] / 5;

						for (int i = 0; i < 4; i++) {
							int next_x = x + dx[i];
							int next_y = y + dy[i];
							if (0 <= next_x && next_x < r && 0 <= next_y && next_y < c && a[next_x][next_y] != -1) {
								new_a[next_x][next_y] += dust;
								a[x][y] -= dust;
							}
						}

						new_a[x][y] += a[x][y];

					}
				}

			}
		}
		
		a = new_a;

	}

	static void air_clean() {
		for (int x = air_x - 2; x >= 0; x--) {
			a[x + 1][0] = a[x][0];
		}

		for (int y = 1; y < c; y++) {
			a[0][y - 1] = a[0][y];
		}

		for (int x = 1; x <= air_x; x++) {
			a[x - 1][c - 1] = a[x][c - 1];
		}

		for (int y = c - 2; y >= 1; y--) {
			a[air_x][y + 1] = a[air_x][y];
		}

		a[air_x][1] = 0;

		for (int x = air_x + 3; x < r; x++) {
			a[x - 1][0] = a[x][0];
		}

		for (int y = 1; y < c; y++) {
			a[r - 1][y - 1] = a[r - 1][y];
		}

		for (int x = r - 2; x >= air_x + 1; x--) {
			a[x + 1][c - 1] = a[x][c - 1];
		}

		for (int y = c - 2; y >= 1; y--) {
			a[air_x + 1][y + 1] = a[air_x + 1][y];
		}

		a[air_x + 1][1] = 0;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		a = new int[r][c];

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < c; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < r; i++) {
			boolean done = false;
			for (int j = 0; j < c; j++) {
				if (a[i][j] == -1) {
					air_x = i;
					air_y = j;
					done = true;
					break;
				}
			}
			if (done) {
				break;
			}
		}

		for (int i = 0; i < t; i++) {
			spread();
			air_clean();
		}
			

		int ans = 0;

		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				if (a[x][y] != 0 && a[x][y] != -1) {
					ans += a[x][y];
				}
			}
		}

		
		
		System.out.print(ans);
	}

}
