package problem_solving_java;

import java.util.*;
import java.io.*;

class Node {
	public int x;
	public int y;
	public int dist;

	public Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

public class BOJ_15686 {

	static int[][] city;
	static int n, m;
	static int ans = Integer.MAX_VALUE;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };

	static int bfs(int start_x, int start_y) {
		int rtn = 0;
		boolean[][] check = new boolean[n][n];
		check[start_x][start_y] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start_x, start_y, 0));

		while (!q.isEmpty()) {
			Node node = q.poll();

			if (city[node.x][node.y] == 2) {
				rtn = node.dist;
				break;
			}

			for (int i = 0; i < 4; i++) {
				int next_x = node.x + dx[i];
				int next_y = node.y + dy[i];
				if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n && !check[next_x][next_y]) {
					check[next_x][next_y] = true;
					q.add(new Node(next_x, next_y, node.dist + 1));
				}
			}

		}

		return rtn;
	}

	static void check(ArrayList<Node> selected) {
		for (int i = 0; i < selected.size(); i++) {
			city[selected.get(i).x][selected.get(i).y] = 2;
		}

		int rtn = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (city[i][j] == 1) {
					rtn += bfs(i, j);
				}
			}
		}

		ans = Math.min(ans, rtn);

		for (int i = 0; i < selected.size(); i++) {
			city[selected.get(i).x][selected.get(i).y] = 0;
		}
	}

	static void select(ArrayList<Node> list, ArrayList<Node> selected, int index) {
		if (selected.size() == m) {
			check(selected);
			return;
		}

		for (int i = index; i < list.size(); i++) {
			selected.add(list.get(i));
			select(list, selected, i + 1);
			selected.remove(selected.size() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		city = new int[n][n];

		ArrayList<Node> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if (city[i][j] == 2) {
					list.add(new Node(i, j, 0));
					city[i][j] = 0;
				}
			}
		}

		select(list, new ArrayList<>(), 0);

		System.out.print(ans);
	}

}
