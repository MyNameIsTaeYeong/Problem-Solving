package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_4577 {

	static int remain_box;

	static void move_box(char[][] board, int cur_x, int cur_y, int dx, int dy) {
		if (board[cur_x][cur_y] == 'B') {
			if (board[cur_x + dx][cur_y + dy] == '.') {
				board[cur_x + dx][cur_y + dy] = 'b';
				board[cur_x][cur_y] = '+';
				remain_box++;
			} else if (board[cur_x + dx][cur_y + dy] == '+') {
				board[cur_x + dx][cur_y + dy] = 'B';
				board[cur_x][cur_y] = '+';
			}
		} else if (board[cur_x][cur_y] == 'b') {
			if (board[cur_x + dx][cur_y + dy] == '.') {
				board[cur_x + dx][cur_y + dy] = 'b';
				board[cur_x][cur_y] = '.';
			} else if (board[cur_x + dx][cur_y + dy] == '+') {
				board[cur_x + dx][cur_y + dy] = 'B';
				board[cur_x][cur_y] = '.';
				remain_box--;
			}
		}
	}

	static void move_character(char[][] board, int cur_x, int cur_y, int dx, int dy) {
		if (board[cur_x][cur_y] == 'w') {
			if (board[cur_x + dx][cur_y + dy] == '.') {
				board[cur_x + dx][cur_y + dy] = 'w';
				board[cur_x][cur_y] = '.';
			} else if (board[cur_x + dx][cur_y + dy] == '+') {
				board[cur_x + dx][cur_y + dy] = 'W';
				board[cur_x][cur_y] = '.';
			}
		} else if (board[cur_x][cur_y] == 'W') {

			if (board[cur_x + dx][cur_y + dy] == '.') {
				board[cur_x + dx][cur_y + dy] = 'w';
				board[cur_x][cur_y] = '+';
			} else if (board[cur_x + dx][cur_y + dy] == '+') {
				board[cur_x + dx][cur_y + dy] = 'W';
				board[cur_x][cur_y] = '+';
			}
		}
	}

	static boolean move(char[][] board, int cur_x, int cur_y, int dx, int dy) {

		// 못가는 경우 : 벽 일때, 박스가 이동할 칸에 다른 박스나 벽이 있는 경우
		if (board[cur_x + dx][cur_y + dy] == '#') {
			return false;
		}

		if (board[cur_x + dx][cur_y + dy] == 'b' || board[cur_x + dx][cur_y + dy] == 'B') {
			if (board[cur_x + 2 * dx][cur_y + 2 * dy] == 'b' || board[cur_x + 2 * dx][cur_y + 2 * dy] == 'B'
					|| board[cur_x + 2 * dx][cur_y + 2 * dy] == '#') {
				return false;
			}
		}

		// 가는 경우 : 빈칸
		if (board[cur_x + dx][cur_y + dy] == '.') {
			move_character(board, cur_x, cur_y, dx, dy);
		}
		// 가는 경우 : 박스랑 가는 경우
		else {
			move_box(board, cur_x + dx, cur_y + dy, dx, dy);
			move_character(board, cur_x, cur_y, dx, dy);
		}

		return true;
	}

	static void print_board(char[][] board, int r, int c) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int round = 0;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (r == 0 && c == 0) {
				break;
			}
			round++;
			remain_box = 0;

			char[][] board = new char[r][c];

			int cur_x = -1;
			int cur_y = -1;

			for (int i = 0; i < r; i++) {
				String line = br.readLine();
				for (int j = 0; j < c; j++) {
					board[i][j] = line.charAt(j);
					if (board[i][j] == 'w' || board[i][j] == 'W') {
						cur_x = i;
						cur_y = j;
					}
					if (board[i][j] == '+' || board[i][j] == 'W') {
						remain_box++;
					}
				}
			}
			boolean complete = false;
			if (remain_box == 0) {
				complete = true;
			} else {
				String commands = br.readLine();
				for (int i = 0; i < commands.length(); i++) {
					char command = commands.charAt(i);
					switch (command) {
					case 'U':
						if (move(board, cur_x, cur_y, -1, 0)) {
							cur_x--;
						}
						break;
					case 'D':
						if (move(board, cur_x, cur_y, 1, 0)) {
							cur_x++;
						}
						break;
					case 'L':
						if (move(board, cur_x, cur_y, 0, -1)) {
							cur_y--;
						}
						break;
					case 'R':
						if (move(board, cur_x, cur_y, 0, 1)) {
							cur_y++;
						}
						break;
					}
					if (remain_box == 0) {
						complete = true;
						break;
					}
				}
			}

			if (complete) {
				System.out.println("Game " + round + ": " + "complete");
				print_board(board, r, c);
			} else {
				System.out.println("Game " + round + ": " + "incomplete");
				print_board(board, r, c);
			}

		}

	}

}
