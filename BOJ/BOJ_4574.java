package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_4574 {
	static int[][] puzzle;
	static boolean[][] row_check;
	static boolean[][] col_check;
	static boolean[][] square_check;
	static boolean[][] domino_check;
	static int[] dx = { 0, 1 };
	static int[] dy = { 1, 0 };

	static boolean can(int row, int col, int val) {
		if (!row_check[row][val] && !col_check[col][val] && !square_check[row / 3 * 3 + col / 3][val]) {
			return true;
		} else {
			return false;
		}
	}

	static void try_it(int row, int col, int val) {
		row_check[row][val] = true;
		col_check[col][val] = true;
		square_check[row / 3 * 3 + col / 3][val] = true;
		puzzle[row][col] = val;
	}

	static void restore(int row, int col, int val) {
		row_check[row][val] = false;
		col_check[col][val] = false;
		square_check[row / 3 * 3 + col / 3][val] = false;
		puzzle[row][col] = 0;
	}

	static boolean check_range(int row, int col) {
		if (row < 9 && col < 9) {
			return true;
		} else {
			return false;
		}
	}

	static boolean solve(int index) {
		if (index == 81) {
			return true;
		}

		int row = index / 9;
		int col = index % 9;

		if (puzzle[row][col] != 0) {
			if (solve(index + 1)) {
				return true;
			}
		} else {

			for (int k = 0; k < 2; k++) {
				int nx = row + dx[k];
				int ny = col + dy[k];

				if (check_range(nx, ny) && puzzle[nx][ny] == 0) {

					for (int i = 1; i < 10; i++) {
						for (int j = 1; j < 10; j++) {
							if (i == j) {
								continue;
							}
							if (domino_check[i][j]) {
								continue;
							}
							if (can(row, col, i) && can(nx, ny, j)) {
								try_it(row, col, i);
								try_it(nx, ny, j);
								domino_check[i][j] = true;
								domino_check[j][i] = true;
								if (solve(index + 1)) {
									return true;
								}
								restore(row, col, i);
								restore(nx, ny, j);
								domino_check[i][j] = false;
								domino_check[j][i] = false;
							}

						}
					}

				}

			}
		}
		return false;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int test_cnt = 1;
		while (true) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) {
				break;
			}

			puzzle = new int[9][9];
			row_check = new boolean[9][10];
			col_check = new boolean[9][10];
			square_check = new boolean[9][10];
			domino_check = new boolean[10][10];

			StringTokenizer st;
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				// 6 B2 1 B3 U LU V LV
				int u = Integer.parseInt(st.nextToken());
				String lu = st.nextToken();
				int v = Integer.parseInt(st.nextToken());
				String lv = st.nextToken();

				puzzle[lu.charAt(0) - 'A'][lu.charAt(1) - '1'] = u;
				row_check[lu.charAt(0) - 'A'][u] = true;
				col_check[lu.charAt(1) - '1'][u] = true;
				square_check[(lu.charAt(0) - 'A') / 3 * 3 + (lu.charAt(1) - '1') / 3][u] = true;

				puzzle[lv.charAt(0) - 'A'][lv.charAt(1) - '1'] = v;
				row_check[lv.charAt(0) - 'A'][v] = true;
				col_check[lv.charAt(1) - '1'][v] = true;
				square_check[(lv.charAt(0) - 'A') / 3 * 3 + (lv.charAt(1) - '1') / 3][v] = true;

				domino_check[u][v] = true;
				domino_check[v][u] = true;
			}

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i < 10; i++) {
				String l = st.nextToken();
				puzzle[l.charAt(0) - 'A'][l.charAt(1) - '1'] = i;
				row_check[l.charAt(0) - 'A'][i] = true;
				col_check[l.charAt(1) - '1'][i] = true;
				square_check[(l.charAt(0) - 'A') / 3 * 3 + (l.charAt(1) - '1') / 3][i] = true;

			}

			solve(0);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(puzzle[i][j]);
				}
				sb.append('\n');
			}

			System.out.println("Puzzle " + test_cnt++);
			System.out.print(sb.toString());

		}

	}

}
