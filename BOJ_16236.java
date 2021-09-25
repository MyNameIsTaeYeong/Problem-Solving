package problem_solving_java;

import java.util.*;
import java.io.*;

// 1. bfs로 가장 가까운 물고기를 찾는다. 
// 2. 그 지점에서 다시 bfs를 한다. 

class Pair implements Comparable<Pair> {
	public int x;
	public int y;
	public int distance;

	public Pair(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}

	@Override
	public int compareTo(Pair p) {
		if (this.distance == p.distance) {
			if (this.x == p.x) {
				return this.y - p.y;
			} else {
				return this.x - p.x;
			}
		} else {
			return this.distance - p.distance;
		}

	}
}

public class BOJ_16236 {

	static int n;
	static int[][] space;
	static int start_x, start_y;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };
	static int baby = 2;
	static int eat = 0;

	static int bfs() {
		boolean[][] check = new boolean[n][n];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(start_x, start_y, 0));
		check[start_x][start_y] = true;

		while (!pq.isEmpty()) {
			Pair p = pq.poll();

			if (space[p.x][p.y] != 0 && space[p.x][p.y] != 9 && space[p.x][p.y] < baby) {
				eat++;
				if (eat == baby) {
					baby++;
					eat = 0;
				}

				space[p.x][p.y] = 9;
				space[start_x][start_y] = 0;

				start_x = p.x;
				start_y = p.y;

				return p.distance;
			}

			for (int i = 0; i < 4; i++) {
				int next_x = p.x + dx[i];
				int next_y = p.y + dy[i];
				if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n && space[next_x][next_y] <= baby
						&& !check[next_x][next_y]) {
					check[next_x][next_y] = true;
					pq.add(new Pair(next_x, next_y, p.distance + 1));
				}
			}

		}

		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		space = new int[n][n];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				space[i][j] = Integer.parseInt(st.nextToken());
				if (space[i][j] == 9) {
					start_x = i;
					start_y = j;
				}
			}
		}

		int rtn = bfs();
		int ans = rtn;
		while (rtn != 0) {
			rtn = bfs();
			ans += rtn;
		}

		System.out.print(ans);

	}

}
