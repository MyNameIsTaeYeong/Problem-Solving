package problem_solving_java;

// memo[n][m] : n행 m열에 도착하기 위해 부숴야 하는 문의 최소 개수.
// 죄수1, 죄수2, 상근이 입장에서 bfs를 한번씩 한다. 
// 모든 점에 대해 부숴야하는 문의 개수 중 최소를 찾는다.

import java.util.*;
import java.io.*;

class Node {
	public int x;
	public int y;
	public int broken;

	public Node(int x, int y, int broken) {
		this.x = x;
		this.y = y;
		this.broken = broken;
	}
}

public class BOJ_9376 {

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static void bfs(char[][] jail, int[][][] memo, int who, int start_x, int start_y) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start_x, start_y, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();

			for (int i = 0; i < 4; i++) {
				int next_x = node.x + dx[i];
				int next_y = node.y + dy[i];
				if (0 <= next_x && next_x < jail.length && 0 <= next_y && next_y < jail[0].length) {
					if (jail[next_x][next_y] != '*') {
						if (jail[next_x][next_y] == '#') {
							if (node.broken + 1 < memo[who][next_x][next_y]) {
								memo[who][next_x][next_y] = node.broken + 1;
								q.add(new Node(next_x, next_y, node.broken + 1));
							}
						} else {
							if (node.broken < memo[who][next_x][next_y]) {
								memo[who][next_x][next_y] = node.broken;
								q.add(new Node(next_x, next_y, node.broken));
							}
						}
					}
				}
			}

		}

	}

	static void solve(char[][] jail, int h, int w) {
		int[][][] memo = new int[3][h + 2][w + 2];

		for (int[][] first : memo) {
			for (int[] second : first) {
				Arrays.fill(second, 20000);
			}
		}

		int who = 0;
		bfs(jail, memo, who, 0, 0);
		who++;

		for (int x = 0; x < h + 2; x++) {
			for (int y = 0; y < w + 2; y++) {
				if (jail[x][y] == '$') {
					bfs(jail, memo, who, x, y);
					who++;
				}
			}
		}

		int ans = 100000;

		for (int x = 0; x < h + 2; x++) {
			for (int y = 0; y < w + 2; y++) {
				if (jail[x][y] == '#') {
					ans = Math.min(ans, memo[0][x][y] + memo[1][x][y] + memo[2][x][y] - 2);
				} else {
					ans = Math.min(ans, memo[0][x][y] + memo[1][x][y] + memo[2][x][y]);
				}
			}
		}

		System.out.println(ans);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int h, w;
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());

			char[][] jail = new char[h + 2][w + 2];

			for (int y = 0; y < w + 2; y++) {
				jail[0][y] = '.';
				jail[h + 1][y] = '.';
			}

			for (int x = 0; x < h + 2; x++) {
				jail[x][0] = '.';
				jail[x][w + 1] = '.';
			}

			for (int x = 1; x <= h; x++) {
				String input = br.readLine();
				for (int y = 1; y <= w; y++) {
					jail[x][y] = input.charAt(y - 1);
				}
			}

			solve(jail, h, w);

		}

	}

}
