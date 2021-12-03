package problem_solving_java;

// 2:30

// 1. 최단거리 승객 찾기 , 400
// 2. 목적지까지 최단거리 찾기 400
// 3. 반복  400

// area배열 , 승객위치배열, 도착지위치 

import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
	public int x;
	public int y;
	public int dist;

	public Node(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	public int compareTo(Node node) {
		if (this.dist == node.dist) {
			if (this.x == node.x) {
				return this.y - node.y;
			} else {
				return this.x - node.x;
			}
		} else {
			return this.dist - node.dist;
		}
	}
}

public class BOJ_19238 {

	static int n, m, fuel, taxiX, taxiY;
	static int[][] area;
	static ArrayList<Node> destList;
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	static int ans = -1;

	static Node bfs(boolean ride, int destX, int destY) {
		Node rtn = null;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] check = new boolean[n][n];
		check[taxiX][taxiY] = true;
		pq.add(new Node(taxiX, taxiY, 0));

		while (!pq.isEmpty()) {
			Node node = pq.poll();

			if (ride) {
				if (node.x == destX && node.y == destY) {
					rtn = node;
					break;
				}

			} else {
				if (area[node.x][node.y] > 0) {
					rtn = node;
					break;
				}
			}

			for (int i = 0; i < 4; i++) {
				int nextX = node.x + dx[i];
				int nextY = node.y + dy[i];

				if (0 <= nextX && nextX < n && 0 <= nextY && nextY < n && !check[nextX][nextY]
						&& area[nextX][nextY] != -1) {
					check[nextX][nextY] = true;
					pq.add(new Node(nextX, nextY, node.dist + 1));
				}
			}

		}

		return rtn;
	}

	static void go() {
		int remain = m;
		while (true) {
			Node passenger = bfs(false, -1, -1);
			if(passenger == null) {
				ans = -1;
				break;
			}
			int passengerNum = area[passenger.x][passenger.y];
			fuel -= passenger.dist;
			if (fuel < 0) {
				ans = -1;
				break;
			}

			taxiX = passenger.x;
			taxiY = passenger.y;

			area[taxiX][taxiY] = 0;

			int destX = destList.get(passengerNum).x;
			int destY = destList.get(passengerNum).y;

			Node result = bfs(true, destX, destY);
			if(result == null) {
				ans = -1;
				break;
			}
			fuel -= result.dist;
			if (fuel < 0) {
				ans = -1;
				break;
			}

			taxiX = result.x;
			taxiY = result.y;
			fuel += result.dist * 2;

			remain--;
			if (remain == 0) {
				ans = fuel;
				break;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		area = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				if (area[i][j] == 1)
					area[i][j] = -1;
			}
		}

		st = new StringTokenizer(br.readLine());
		taxiX = Integer.parseInt(st.nextToken()) - 1;
		taxiY = Integer.parseInt(st.nextToken()) - 1;

		destList = new ArrayList<>();
		destList.add(new Node(0, 0, 0));
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken()) - 1;
			int startY = Integer.parseInt(st.nextToken()) - 1;
			int destX = Integer.parseInt(st.nextToken()) - 1;
			int destY = Integer.parseInt(st.nextToken()) - 1;
			area[startX][startY] = i + 1;
			destList.add(new Node(destX, destY, 0));
		}

		go();

		System.out.print(ans);
	}

}
