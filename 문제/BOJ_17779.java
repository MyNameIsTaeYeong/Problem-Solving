package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_17779 {

	static int n;
	static int[][] a;
	static int ans = 50000;

	static void check(int[][] area_id) {
		int[] total = new int[6];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				total[area_id[i][j]] += a[i][j];
		}

		Arrays.sort(total);

		ans = Math.min(ans, total[5] - total[1]);

	}

	static void divide(int x, int y, int d1, int d2) {
		int[][] area_id = new int[n][n];
		area_id[x][y] = 5;

		for (int i = 1; i <= d1; i++) {
			area_id[x + i][y - i] = 5;
		}

		for (int i = 1; i <= d2; i++) {
			area_id[x + i][y + i] = 5;
		}

		for (int i = 1; i <= d2; i++) {
			area_id[x + d1 + i][y - d1 + i] = 5;
		}

		for (int i = 1; i <= d1; i++) {
			area_id[x + d2 + i][y + d2 - i] = 5;
		}

		// 1번
		for (int i = 0; i < x + d1; i++) {
			for (int j = 0; j <= y; j++) {
				if (area_id[i][j] == 5)
					break;
				area_id[i][j] = 1;
			}
		}

		// 2
		for (int i = 0; i <= x + d2; i++) {
			for (int j = n - 1; j >= y + 1; j--) {
				if (area_id[i][j] == 5)
					break;
				area_id[i][j] = 2;
			}

		}

		// 3
		for (int i = x + d1; i < n; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				if (area_id[i][j] == 5)
					break;
				area_id[i][j] = 3;

			}
		}

		// 4
		for (int i = x + d2 + 1; i < n; i++) {
			for (int j = n - 1; j >= y - d1 + d2; j--) {
				if (area_id[i][j] == 5)
					break;
				area_id[i][j] = 4;
			}
		}

		// 5
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (area_id[i][j] == 0)
					area_id[i][j] = 5;
			}
		}

//		for (int i = 0; i < n; i++) {
//			System.out.println();
//			for (int j = 0; j < n; j++) {
//				System.out.print(area_id[i][j]);
//			}
//		}

		check(area_id);

	}

	static void solve(int x, int y) {

		for (int d1 = 1; y - d1 >= 0; d1++) {
			for (int d2 = 1; y + d2 < n; d2++) {
				if (x + d1 + d2 >= n) {
					break;
				}
				divide(x, y, d1, d2);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		a = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				solve(i, j);
			}
		}

		System.out.print(ans);
	}

}
