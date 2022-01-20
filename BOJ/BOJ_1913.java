package problem_solving_java;

import java.io.*;

public class BOJ_1913 {

	static int n, m;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] arr;
	static int m_x, m_y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		arr = new int[n + 2][n + 2];

		for (int i = 0; i < n + 2; i++) {
			arr[i][0] = 1;
		}

		for (int i = 0; i < n + 2; i++) {
			arr[i][n + 1] = 1;
		}

		for (int j = 0; j < n + 2; j++) {
			arr[0][j] = 1;
		}

		for (int j = 0; j < n + 2; j++) {
			arr[n + 1][j] = 1;
		}

		int cur_x = 1;
		int cur_y = 1;
		int num = n * n;
		int dir = 0;

		while (num > 0) {
			if (num == m) {
				m_x = cur_x;
				m_y = cur_y;
			}

			arr[cur_x][cur_y] = num--;

			if (arr[cur_x + dx[dir]][cur_y + dy[dir]] != 0) {
				dir++;
				if (dir == 4) {
					dir = 0;
				}
			}

			cur_x += dx[dir];
			cur_y += dy[dir];

		}

		StringBuilder sb = new StringBuilder("");
		
		for (int x = 1; x <= n; x++) {
			for (int y = 1; y <= n; y++) {
				sb.append(arr[x][y] + " ");
				
			}
			sb.append('\n');
		}
		sb.append(m_x + " " + m_y);
		System.out.print(sb);

	}

}

