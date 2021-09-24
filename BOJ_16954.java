package problem_solving_java;

import java.util.*;
import java.io.*;

// bfs
// n초 일때 n-1초의 벽 체크, n초의 벽 체크 

class User {
	public int x;
	public int y;
	public int sec;

	public User(int x, int y, int sec) {
		this.x = x;
		this.y = y;
		this.sec = sec;
	}
}

public class BOJ_16954 {

	static ArrayList<ArrayList<Integer>> walls = new ArrayList<>();
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1, 0 };

	static boolean is_wall(int x, int y, int sec) {
		if (walls.get(y) != null) {
			for (int i = 0; i < walls.get(y).size(); i++) {
				if (walls.get(y).get(i) + sec == x) {
					return true;
				}

				if (walls.get(y).get(i) + sec < x) {
					break;
				}

			}
		}

		return false;
	}

	static int bfs() {

		Queue<User> q = new LinkedList<>();
		q.add(new User(7, 0, 0));

		while (!q.isEmpty()) {
			User user = q.poll();

			if (user.x == 0 && user.y == 7) {
				return 1;
			}

			for (int i = 0; i < 9; i++) {
				int next_x = user.x + dx[i];
				int next_y = user.y + dy[i];
				if (0 <= next_x && next_x < 8 && 0 <= next_y && next_y < 8) {
					if (!is_wall(next_x, next_y, user.sec) && !is_wall(next_x, next_y, user.sec + 1)) {
						q.add(new User(next_x, next_y, user.sec + 1));
					}
				}
			}

		}

		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 8; i++) {
			walls.add(new ArrayList<>());
		}

		for (int i = 0; i < 8; i++) {
			String line = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (line.charAt(j) == '#') {
					walls.get(j).add(i);
				}
			}
		}

		for (int i = 0; i < 8; i++) {
			if (walls.get(i) != null) {
				Collections.sort(walls.get(i), (a, b) -> b - a);
			}

		}

		System.out.print(bfs());

	}

}
