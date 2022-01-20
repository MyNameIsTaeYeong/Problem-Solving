package problem_solving_java;

// 10:26
import java.util.*;
import java.io.*;

public class BOJ_17822 {

	static int n, m, t;
	static int[][] circleBoard;

	// left, right , top , bottom
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static void secondJob() {
		int total = 0;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (circleBoard[i][j] == 0)
					continue;
				total += circleBoard[i][j];
				cnt++;
			}
		}

		double avg = (double) total / cnt;
//		System.out.println();
//		System.out.println(avg);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (circleBoard[i][j] == 0)
					continue;

				if (circleBoard[i][j] < avg)
					circleBoard[i][j]++;
				else {
					if (circleBoard[i][j] != avg)
						circleBoard[i][j]--;
				}

			}
		}

	}

	static boolean delete() {
		boolean rtn = false;
		boolean[][] check = new boolean[n][m];

		// 인접한 곳이 같은지 확인
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {

				if (circleBoard[i][j] == 0)
					continue;

				for (int k = 0; k < 4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];

					if (x == -1 || x == n)
						continue;

					if (y == m) {
						y = 0;
					} else if (y == -1) {
						y = m - 1;
					}

					if (circleBoard[i][j] == circleBoard[x][y]) {
						check[i][j] = true;
						check[x][y] = true;
						rtn = true;
					}

				}
			}
		}

		// 삭제
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (check[i][j])
					circleBoard[i][j] = 0;
			}
		}

		return rtn;

	}

	static void rotate(int where, int dir, int how) {
		int idx = 0;

		if (dir == 0) {
			idx -= how;
			if (idx < 0) {
				idx += m;
			}
		} else {
			idx += how;
			if (idx >= m) {
				idx %= m;
			}
		}

		int[] temp = new int[m];

		for (int i = 0; i < m; i++) {
			temp[i] = circleBoard[where][idx];
			idx++;
			if (idx == m)
				idx = 0;
		}

		for (int i = 0; i < m; i++) {
			circleBoard[where][i] = temp[i];
		}

	}

	static void printBoard() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(circleBoard[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());

		circleBoard = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				circleBoard[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int where = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int how = Integer.parseInt(st.nextToken());
			int row = where-1;
			while (row < n) {
				rotate(row, dir, how);

				row += where;
			}
			//printBoard();

			if (!delete()) {

				secondJob();
			}
			//printBoard();
		}

		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				ans += circleBoard[i][j];
			}
		}

		System.out.print(ans);

	}

}
