package problem_solving_java;

import java.util.*;
import java.io.*;

class Triple {
	public int x;
	public int y;
	public int z;
	public boolean broken;

	public Triple(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}

/*
 0일 때, 부수고 간적이 없거나 그냥 간적이 없다면 간다.
 1일 때, 부수고 간적이 없으면서, 현재까지 부순적이 없다면 간다.
*/

public class BOJ_2206 {

	static int n, m;
	static int[][] map;
	static int[][][] distance;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static void bfs() {
		distance[0][0][0] = 1;
		
		Queue<Triple> q = new LinkedList<>();
		q.add(new Triple(0, 0, 0));

		while (!q.isEmpty()) {
			Triple t = q.poll();

			for (int i = 0; i < 4; i++) {
				int next_x = t.x + dx[i];
				int next_y = t.y + dy[i];
				if (0 <= next_x && next_x < n && 0 <= next_y && next_y < m) {
					if (map[next_x][next_y] == 0 && distance[next_x][next_y][t.z] == 0) {
						distance[next_x][next_y][t.z] = distance[t.x][t.y][t.z] + 1;
						q.add(new Triple(next_x, next_y, t.z));
					}

					if (map[next_x][next_y] == 1 && t.z == 0 && distance[next_x][next_y][1] == 0) {
						distance[next_x][next_y][1] = distance[t.x][t.y][0] + 1;
						q.add(new Triple(next_x, next_y, 1));
					}
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		distance = new int[n][m][2];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}

		bfs();

		if (distance[n - 1][m - 1][0] == 0 && distance[n - 1][m - 1][1] == 0) {
			System.out.print(-1);
		} else if (distance[n - 1][m - 1][0] == 0 && distance[n - 1][m - 1][1] != 0) {
			System.out.print(distance[n - 1][m - 1][1]);
		} else if (distance[n - 1][m - 1][1] == 0 && distance[n - 1][m - 1][0] != 0) {
			System.out.print(distance[n - 1][m - 1][0]);
		} else {
			System.out.print(Math.min(distance[n - 1][m - 1][0], distance[n - 1][m - 1][1]));
		}

	}

}
