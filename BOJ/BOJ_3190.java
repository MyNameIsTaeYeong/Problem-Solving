package problem_solving_java;

import java.util.*;
import java.io.*;

class Pair {
	public int x;
	public int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Rotate {
	public int sec;
	public char dir;

	public Rotate(int sec, char dir) {
		this.sec = sec;
		this.dir = dir;
	}
}

public class BOJ_3190 {

	static int n, k, l;
	static int[][] board;
	static Queue<Rotate> q;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static void print_board() {
		for (int i = 0; i < n; i++) {
			
			for (int j = 0; j < n; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	static void solve() {

		board[0][0] = 1;

		Deque<Pair> dq = new LinkedList<>();

		dq.add(new Pair(0, 0));

		int game_sec = 0;

		int dir = 0;

		Rotate r = null;

		while (true) {

			game_sec++;
			
//			System.out.println(game_sec);
//			print_board();
//			System.out.println();

			if (!q.isEmpty() && r == null) {
				r = q.poll();
			}

			int next_x = dq.peekFirst().x + dx[dir];
			int next_y = dq.peekFirst().y + dy[dir];

			if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n) {
				// 자기 자신에게 부딪힌 경우
				if (board[next_x][next_y] == 1) {
					break;
				}

				// 사과
				if (board[next_x][next_y] == 4) {
					board[next_x][next_y] = 1;
					dq.addFirst(new Pair(next_x, next_y));
				}
				// 빈 칸
				else {
					board[next_x][next_y] = 1;
					dq.addFirst(new Pair(next_x, next_y));
					Pair p = dq.pollLast();
					board[p.x][p.y] = 0;
				}

			}
			// 벽에 부딪힌 경우
			else {
				break;
			}

			if (r != null && r.sec == game_sec) {

				// System.out.println(game_sec);

				if (r.dir == 'L') {
					dir--;
					if (dir == -1) {
						dir = 3;
					}
				}
				// D
				else {
					dir++;
					if (dir == 4) {
						dir = 0;
					}
				}

				r = null;

			}
		}

		System.out.print(game_sec);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		board = new int[n][n];

		k = Integer.parseInt(br.readLine());

		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 4;
		}

		q = new LinkedList<>();

		l = Integer.parseInt(br.readLine());

		for (int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int sec = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			q.add(new Rotate(sec, dir));
		}

		solve();

	}

}
