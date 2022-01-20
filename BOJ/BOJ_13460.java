package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

class Board implements Cloneable {
	int red_x, red_y, blue_x, blue_y;
	char[][] board;

	Board(int a, int b, int c, int d, char[][] e) {
		red_x = a;
		red_y = b;
		blue_x = c;
		blue_y = d;
		board = e;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}

public class BOJ_13460 {

	static int n, m;
	static int ans = 20;
	static int[][] next = { { 2, 3 }, { 2, 3 }, { 0, 1 }, { 0, 1 }, { 0, 1, 2, 3 } };

	static int check(Board board, int x, int y, int direction) {

		if (direction == 0 && board.board[x - 1][y] == '.') {
			return 0;
		}
		if (direction == 1 && board.board[x + 1][y] == '.') {
			return 0;
		}
		if (direction == 2 && board.board[x][y - 1] == '.') {
			return 0;
		}
		if (direction == 3 && board.board[x][y + 1] == '.') {
			return 0;
		}
		if (direction == 0 && board.board[x - 1][y] == 'O') {
			return 1;
		}
		if (direction == 1 && board.board[x + 1][y] == 'O') {
			return 1;
		}
		if (direction == 2 && board.board[x][y - 1] == 'O') {
			return 1;
		}
		if (direction == 3 && board.board[x][y + 1] == 'O') {
			return 1;
		}
		return -1;
	}

	static int move_red(Board board, int direction) {
		int x = board.red_x;
		int y = board.red_y;

		if (check(board, x, y, direction) == 0) {
			do {
				if (direction == 0) {
					x--;
				} else if (direction == 1) {
					x++;
				} else if (direction == 2) {
					y--;
				} else {
					y++;
				}
			} while (check(board, x, y, direction) == 0);
			board.board[board.red_x][board.red_y] = '.';
			board.board[x][y] = 'R';
			board.red_x = x;
			board.red_y = y;

			if (check(board, x, y, direction) == 1) {
				board.board[board.red_x][board.red_y] = '.';
				return 0;
			}

			return 1;
		}
		if (check(board, x, y, direction) == 1) {
			board.board[board.red_x][board.red_y] = '.';
			return 0;
		}

		return -1;

	}

	static int move_blue(Board board, int direction) {
		int x = board.blue_x;
		int y = board.blue_y;

		if (check(board, x, y, direction) == 0) {
			do {
				if (direction == 0) {
					x--;
				} else if (direction == 1) {
					x++;
				} else if (direction == 2) {
					y--;
				} else {
					y++;
				}
			} while (check(board, x, y, direction) == 0);
			board.board[board.blue_x][board.blue_y] = '.';
			board.board[x][y] = 'B';
			board.blue_x = x;
			board.blue_y = y;

			if (check(board, x, y, direction) == 1) {
				board.board[board.blue_x][board.blue_y] = '.';
				return 0;
			}

			return 1;
		}

		if (check(board, x, y, direction) == 1) {
			board.board[board.blue_x][board.blue_y] = '.';
			return 0;
		}

		return -1;

	}

	static int move(Board board, int direction) {

		int red_rtn = -10;
		int blue_rtn = -10;
		boolean red_hole = false;

		while (true) {

			if (!red_hole) {
				red_rtn = move_red(board, direction);
				if (red_rtn == 0) {
					red_hole = true;
				}
			}

			blue_rtn = move_blue(board, direction);
			if (blue_rtn == 0) {
				return -1;
			}
			
			if(red_hole && blue_rtn == -1) {
				return 1;
			}

			if (red_rtn == -1 && blue_rtn == -1) {
				return 0;
			}

		}

	}

	static void board_copy(char[][] copy, Board board) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				copy[i][j] = board.board[i][j];
			}
		}
	}

	static void solve(Board board, int index, int prev) throws CloneNotSupportedException {
		if (index == 10) {
			return;
		}

		for (int next_dir = 0; next_dir < next[prev].length; next_dir++) {
			char[][] copy = new char[n][m];
			board_copy(copy, board);

			Board copy_board = new Board(board.red_x, board.red_y, board.blue_x, board.blue_y, copy);
			int move_rtn = move(copy_board, next[prev][next_dir]);

			if (move_rtn == 1) {
				if (ans > (index + 1)) {
					ans = index + 1;
					return;
				}
			}
			if (move_rtn == -1) {
				continue;
			}
			solve(copy_board, index + 1, next[prev][next_dir]);
		}

	}

	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		int red_x = 0, red_y = 0, blue_x = 0, blue_y = 0;
		char[][] board = new char[n][m];

		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = line.charAt(j);

				if (board[i][j] == 'R') {
					red_x = i;
					red_y = j;
				}
				if (board[i][j] == 'B') {
					blue_x = i;
					blue_y = j;
				}
			}
		}

		Board b = new Board(red_x, red_y, blue_x, blue_y, board);

		solve((Board) b.clone(), 0, 4);

		if (ans == 20) {
			System.out.print(-1);
		} else {
			System.out.print(ans);
		}
	}

}
