package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_12100 {

	static int n;
	static int ans = 0;

	static void swap(int[][] board, int before_i, int before_j, int after_i, int after_j) {
		int temp = board[before_i][before_j];
		board[before_i][before_j] = board[after_i][after_j];
		board[after_i][after_j] = temp;
	}

	static void move_right(int[][] board) {
		// 오른쪽 부터
		boolean[][] check = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = (n - 1); j >= 0; j--) {
				if (board[i][j] == 0) {
					continue;
				}
				for (int k = j + 1; k < n; k++) {
					if (board[i][k] == 0) {
						swap(board, i, k - 1, i, k);
					} else {
						if (board[i][k] != board[i][k - 1]) {
							break;
						} else {
							if (!check[i][k]) {
								board[i][k] *= 2;
								board[i][k - 1] = 0;
								check[i][k] = true;
							}
							break;
						}

					}
				}
			}
		}

	}

	static void move_left(int[][] board) {
		// 왼쪽 부터
		boolean[][] check = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				for (int k = j - 1; k >= 0; k--) {
					if (board[i][k] == 0) {
						swap(board, i, k + 1, i, k);
					} else {
						if (board[i][k] != board[i][k + 1]) {
							break;
						} else {
							if (!check[i][k]) {
								board[i][k] *= 2;
								board[i][k + 1] = 0;
								check[i][k] = true;
							}
							break;
						}

					}
				}
			}
		}

	}

	static void move_bottom(int[][] board) {
		// 아래쪽 부터
		boolean[][] check = new boolean[n][n];

		for (int j = 0; j < n; j++) {
			for (int i = (n - 1); i >= 0; i--) {
				if (board[i][j] == 0) {
					continue;
				}
				for (int k = i + 1; k < n; k++) {
					if (board[k][j] == 0) {
						swap(board, k, j, k - 1, j);
					} else {
						if (board[k][j] != board[k - 1][j]) {
							break;
						} else {
							if (!check[k][j]) {
								board[k][j] *= 2;
								board[k - 1][j] = 0;
								check[k][j] = true;
							}
							break;
						}

					}
				}
			}
		}

	}

	static void move_top(int[][] board) {
		// 위쪽 부터
		boolean[][] check = new boolean[n][n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				if (board[i][j] == 0) {
					continue;
				}
				for (int k = i - 1; k >= 0; k--) {
					if (board[k][j] == 0) {
						swap(board, k, j, k + 1, j);
					} else {
						if (board[k][j] != board[k + 1][j]) {
							break;
						}else {
							if(!check[k][j]) {
								board[k][j] *= 2;
								board[k + 1][j] = 0;
								check[k][j] = true;
							}
							break;
						}

						

					}
				}
			}
		}

	}

	static void board_copy(int[][] board, int[][] copy_board) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				copy_board[i][j] = board[i][j];
			}
		}
	}

	static void find_max(int[][] board) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] > ans) {
					ans = board[i][j];
				}
			}
		}
	}

	static void move(int[][] board, int direction) {
		if (direction == 0) {
			move_top(board);
		} else if (direction == 1) {
			move_bottom(board);
		} else if (direction == 2) {
			move_left(board);
		} else {
			move_right(board);
		}
	}

	static void solve(int[][] board, int index) {
		if (index == 5) {
			find_max(board);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int[][] copy = new int[n][n];
			board_copy(board, copy);
			move(copy, i);
			solve(copy, index + 1);
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve(board, 0);

		System.out.println(ans);
	}

}
