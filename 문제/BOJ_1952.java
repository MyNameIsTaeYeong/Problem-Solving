package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_1952 {

	static int m, n;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		arr = new int[m + 2][n + 2];

		for (int i = 0; i < m + 2; i++) {
			arr[i][0] = 1;
		}

		for (int i = 0; i < m + 2; i++) {
			arr[i][n + 1] = 1;
		}

		for (int j = 0; j < n + 2; j++) {
			arr[0][j] = 1;
		}

		for (int j = 0; j < n + 2; j++) {
			arr[m + 1][j] = 1;
		}

		int cur_x = 1;
		int cur_y = 1;
		int dir = 0;

		int ans = 0;

		while (true) {
			if (arr[cur_x][cur_y] != 0) {
				ans--;
				break;
			}

			arr[cur_x][cur_y] = 1;

			if (arr[cur_x + dx[dir]][cur_y + dy[dir]] != 0) {
				dir++;
				ans++;
				if (dir == 4) {
					dir = 0;
				}
			}

			cur_x += dx[dir];
			cur_y += dy[dir];

		}
		
		System.out.print(ans);

	}

}
