package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_14719 {

	static int r, c;
	static int[] heights;
	static int ans = 0;

	static void solve() {
		int[][] world = new int[r][c];
		for (int[] init : world) {
			Arrays.fill(init, 2);
		}

		for (int y = 0; y < c; y++) {
			for (int x = r - 1; x >= 0; x--) {
				if (heights[y] != 0) {
					world[x][y] = 1;
					heights[y]--;
				}
			}
		}

		for (int x = 0; x < r; x++) {

			if (world[x][0] == 2) {
				for (int y = 0; y < c; y++) {
					if (world[x][y] == 2) {
						world[x][y] = 0;
					} else {
						break;
					}
				}
			}

			if (world[x][c - 1] == 2) {
				for (int y = c - 1; y >= 0; y--) {
					if (world[x][y] == 2) {
						world[x][y] = 0;

					} else {
						break;
					}
				}
			}
		}

		for (int x = 0; x < r; x++) {

			for (int y = 0; y < c; y++) {
				if (world[x][y] == 2) {
					ans++;
				}
			}
		}

		System.out.print(ans);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		heights = new int[c];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < c; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}

		solve();

	}

}
