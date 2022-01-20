package problem_solving_java;

import java.util.*;
import java.io.*;

// check[n][m][k] : n, m을 k번 부수었을때 방문여부 
// bfs

class Node {
	public int x;
	public int y;
	public int k;
	public int distance;
	public boolean sun;

	public Node(int x, int y, int k, int distance, boolean sun) {
		this.x = x;
		this.y = y;
		this.k = k;
		this.distance = distance;
		this.sun = sun;
	}
}

public class BOJ_16933 {

	static int n, m, k;
	static int[][] map;
	static boolean[][][] check;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int bfs() {

		check[0][0][0] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, 1, true));

		while (!q.isEmpty()) {
			Node node = q.poll();

			if (node.x == n - 1 && node.y == m - 1) {
				return node.distance;
			}

			for (int i = 0; i < 4; i++) {
				int next_x = node.x + dx[i];
				int next_y = node.y + dy[i];
				if (0 <= next_x && next_x < n && 0 <= next_y && next_y < m) {
					if (map[next_x][next_y] == 0 && !check[next_x][next_y][node.k]) {
						check[next_x][next_y][node.k] = true;
						q.add(new Node(next_x, next_y, node.k, node.distance + 1, !node.sun));
					}

					if (map[next_x][next_y] == 1 && node.k < k && !check[next_x][next_y][node.k + 1]) {
						if (node.sun) {
							check[next_x][next_y][node.k + 1] = true;
							q.add(new Node(next_x, next_y, node.k + 1, node.distance + 1, !node.sun));
						} else {
							q.add(new Node(node.x, node.y, node.k, node.distance + 1, !node.sun));
						}

					}
				}
			}

		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		check = new boolean[n][m][k + 1];

		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = line.charAt(j) - '0';
			}
		}

		System.out.print(bfs());
	}

}
