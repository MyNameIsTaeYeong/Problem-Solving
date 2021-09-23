package problem_solving_java;

import java.util.*;
import java.io.*;

// check 0 -> 방문x, not 0 -> 연결된 0들의 개수.
// id -> 연결된 0들은 같은 id값을 가진다.

class Pair {
	public int x;
	public int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_16946 {

	static int n, m;
	static int[][] map;
	static int[][] check;
	static int[][] id;
	static int idx = 1;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static void bfs(int x, int y) {
		check[x][y] = 1;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y));

		ArrayList<Pair> list = new ArrayList<>();
		list.add(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int i = 0; i < 4; i++) {
				int next_x = p.x + dx[i];
				int next_y = p.y + dy[i];
				if (0 <= next_x && next_x < n && 0 <= next_y && next_y < m) {
					if (map[next_x][next_y] == 0 && check[next_x][next_y] == 0) {
						check[next_x][next_y] = 1;
						q.add(new Pair(next_x, next_y));
						list.add(new Pair(next_x, next_y));
					}
				}
			}
		}

		int cnt = list.size();
		for (int i = 0; i < cnt; i++) {
			Pair p = list.get(i);
			check[p.x][p.y] = cnt;
			id[p.x][p.y] = idx;
		}
		idx++;

	}

	static void cal(int x, int y) {

		ArrayList<Integer> check_id = new ArrayList<>();

		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int next_x = x + dx[i];
			int next_y = y + dy[i];
			if (0 <= next_x && next_x < n && 0 <= next_y && next_y < m) {
				if (map[next_x][next_y] == 0) {
					if (check_id.contains(id[next_x][next_y])) {
						continue;
					}
					check_id.add(id[next_x][next_y]);
					cnt += check[next_x][next_y];
				}
			}
		}

		map[x][y] = (cnt + 1) % 10;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		check = new int[n][m];
		id = new int[n][m];

		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = (line.charAt(j) - '0');
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0 && check[i][j] == 0) {
					bfs(i, j);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1) {
					cal(i, j);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());

	}

}
