package problem_solving_java;

import java.util.*;
import java.io.*;

class Pair {
	public int x;
	public int y;
	public int depth;

	public Pair(int x, int y, int depth) {
		this.x = x;
		this.y = y;
		this.depth = depth;
	}
}

public class BOJ_16948 {

	// (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1)
	static int n, r1, c1, r2, c2;
	static int[] dx = { -2, -2, 0, 0, 2, 2 };
	static int[] dy = { -1, 1, -2, 2, -1, 1 };

	static int bfs() {

		boolean[][] check = new boolean[n + 1][n + 1];
		check[r1][c1] = true;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(r1, c1, 0));

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.x;
			int y = p.y;

			for (int i = 0; i < 6; i++) {
				int next_x = x + dx[i];
				int next_y = y + dy[i];
				if (next_x >= 0 && next_x <= n && next_y >= 0 && next_y <= n) {
					if(!check[next_x][next_y]) {
						if(next_x == r2 && next_y == c2) {
							return p.depth+1;
						}
						check[next_x][next_y] = true;
						q.add(new Pair(next_x, next_y, p.depth + 1));
					}
				}
			}

		}

		return -1;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r1 = Integer.parseInt(st.nextToken());
		c1 = Integer.parseInt(st.nextToken());
		r2 = Integer.parseInt(st.nextToken());
		c2 = Integer.parseInt(st.nextToken());

		System.out.print(bfs());
	}

}
