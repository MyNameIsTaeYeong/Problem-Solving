package problem_solving_java;

import java.util.*;
import java.io.*;

// memo[n][m] n행 m열에 도착하는 최소 직선 개수    
// 거울개수 = 직선개수 - 1 
// 0 : 가로, 1 : 세로 

class Node {
	public int x;
	public int y;
	public int direction;
	public int line;

	public Node(int x, int y, int direction, int line) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.line = line;
	}
}

public class BOJ_6087 {

	static int w, h;
	static char[][] map;
	static int[][] memo;
	static Node start, end;

	static int[][] dx = { { 0, 0 }, { -1, 1 } };
	static int[][] dy = { { 1, -1 }, { 0, 0 } };

	static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(start);

		while (!q.isEmpty()) {
			Node node = q.poll();

			for (int i = 0; i < 2; i++) {
				if (node.direction == i) {
					continue;
				}
				for (int j = 0; j < 2; j++) {
					int next_x = node.x + dx[i][j];
					int next_y = node.y + dy[i][j];
					while (0 <= next_x && next_x < h && 0 <= next_y && next_y < w && map[next_x][next_y] != '*'
							&& (memo[next_x][next_y] == 0 ||  (node.line + 1) <= memo[next_x][next_y])) {
						memo[next_x][next_y] = node.line + 1;
						q.add(new Node(next_x, next_y, i, node.line + 1));
						next_x += dx[i][j];
						next_y += dy[i][j];
					}
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new char[h][w];
		memo = new int[h][w];

		for (int i = 0; i < h; i++) {
			String input = br.readLine();
			for (int j = 0; j < w; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j] == 'C') {
					if (start == null) {
						start = new Node(i, j, -1, 0);
					} else {
						end = new Node(i, j, -1, 0);
					}
				}
			}
		}

		if (start == null) {
			start = new Node(0, 0, -1, 0);
		}

		if (end == null) {
			end = new Node(0, 0, -1, 0);
		}

		bfs();


		if (memo[end.x][end.y] == 0) {
			System.out.print(memo[end.x][end.y]);
		} else {
			System.out.print(memo[end.x][end.y] - 1);
		}

	}

}
