package problem_solving_java;

import java.util.*;
import java.io.*;

// area[n][m] : n행 m열이 속해있는 영역의 크기.
// id[n][m] : n행 m열이 속해있는 영역의 아이디. 
// dfs -> 벽을 만났을 때, 벽 넘어가 범위안에 존재하고, id가 다르면 영역을 합쳐본다.

class Pair {
	public int x;
	public int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class BOJ_2234 {

	static int n, m;
	static int[][] castle, area, id;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int id_num = 1;
	static int max_area = 0;
	static int broken_max_area = 0;

	static void bfs(int x, int y) {
		boolean[][] check = new boolean[m][n];
		check[x][y] = true;
		Queue<Pair> q = new LinkedList<>();
		ArrayList<Pair> list = new ArrayList<>();
		q.add(new Pair(x, y));

		int size = 0;

		while (!q.isEmpty()) {
			Pair p = q.poll();
			size++;
			list.add(p);
			for (int i = 0; i < 4; i++) {
				int direction = 1 << i;

				if ((castle[p.x][p.y] & direction) == 0) {
					int next_x = p.x + dx[i];
					int next_y = p.y + dy[i];
					if (!check[next_x][next_y]) {
						check[next_x][next_y] = true;
						q.add(new Pair(next_x, next_y));
					}
				}

			}

		}

		max_area = Math.max(max_area, size);

		for (int i = 0; i < list.size(); i++) {
			Pair p = list.get(i);
			area[p.x][p.y] = size;
			id[p.x][p.y] = id_num;
		}

		id_num++;

	}

	static void dfs(int x, int y, boolean[][] check) {

		check[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int direction = 1 << i;

			if ((castle[x][y] & direction) != 0) {
				int next_x = x + dx[i];
				int next_y = y + dy[i];
				if (0 <= next_x && next_x < m && 0 <= next_y && next_y < n && id[x][y] != id[next_x][next_y]
						&& !check[next_x][next_y]) {
					check[next_x][next_y] = true;
					broken_max_area = Math.max(broken_max_area, area[x][y] + area[next_x][next_y]);
				}
			}
		}

		for (int i = 0; i < 4; i++) {
			int direction = 1 << i;

			if ((castle[x][y] & direction) == 0) {
				int next_x = x + dx[i];
				int next_y = y + dy[i];
				if (!check[next_x][next_y]) {
					dfs(next_x, next_y, check);
				}
			}

		}

		

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// m x n
		castle = new int[m][n];
		area = new int[m][n];
		id = new int[m][n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				castle[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ArrayList<Pair> list = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (area[i][j] == 0) {
					bfs(i, j);
					list.add(new Pair(i, j));
				}
			}
		}

		for (int i = 0; i < list.size(); i++) {
			Pair p = list.get(i);
			boolean[][] check = new boolean[m][n];
			dfs(p.x, p.y, check);
		}

		System.out.println(id_num - 1);
		System.out.println(max_area);
		System.out.println(broken_max_area);

	}

}
