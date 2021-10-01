package problem_solving_java;

import java.util.*;
import java.io.*;

// 먼지가 있는 칸에서 각각 bfs -> 최대 40만 
// 순열을 만들어 거리 계산 -> 최대 4천만 이하 

class Pair {
	public int x;
	public int y;
	public int dist;

	public Pair(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

class Dust {
	public int x;
	public int y;
	public int[][] dist;

	public Dust(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.dist = new int[h][w];
		for (int[] first : this.dist) {
			Arrays.fill(first, 50000);
		}
	}
}

public class BOJ_4991 {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int ans;

	static void bfs(char[][] room, int start_x, int start_y, int[][] dist, int w, int h) {
		dist[start_x][start_y] = 0;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(start_x, start_y, 0));

		while (!q.isEmpty()) {
			Pair p = q.poll();

			for (int i = 0; i < 4; i++) {
				int next_x = p.x + dx[i];
				int next_y = p.y + dy[i];
				if (0 <= next_x && next_x < h && 0 <= next_y && next_y < w && room[next_x][next_y] != 'x') {
					if (p.dist + 1 < dist[next_x][next_y]) {
						dist[next_x][next_y] = p.dist + 1;
						q.add(new Pair(next_x, next_y, p.dist + 1));
					}
				}
			}

		}

	}

	static int cal(Dust start, ArrayList<Dust> dusts, ArrayList<Integer> permutation, int n) {

		Dust src = dusts.get(permutation.get(0));

		int rtn = start.dist[src.x][src.y];

		for (int i = 1; i < n; i++) {
			int idx = permutation.get(i);
			Dust dest = dusts.get(idx);
			rtn += src.dist[dest.x][dest.y];
			src = dest;
		}

		return rtn;
	}

	static void dfs(int cnt, int n, boolean[] check, Dust start, ArrayList<Dust> dusts,
			ArrayList<Integer> permutation) {
		if (cnt == n) {
			ans = Math.min(ans, cal(start, dusts, permutation, n));
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!check[i]) {
				permutation.add(i);
				check[i] = true;
				dfs(cnt + 1, n, check, start, dusts, permutation);
				check[i] = false;
				permutation.remove(permutation.size() - 1);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int w = -1, h = -1;
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			if (w == 0 && h == 0) {
				break;
			}

			char[][] room = new char[h][w];

			Dust start = null;
			ArrayList<Dust> dusts = new ArrayList<>();

			for (int i = 0; i < h; i++) {
				String line = br.readLine();
				for (int j = 0; j < w; j++) {
					room[i][j] = line.charAt(j);
					if (room[i][j] == 'o') {
						start = new Dust(i, j, w, h);
					}
					if (room[i][j] == '*') {
						dusts.add(new Dust(i, j, w, h));
					}
				}
			}

			bfs(room, start.x, start.y, start.dist, w, h);

			for (int i = 0; i < dusts.size(); i++) {
				Dust dust = dusts.get(i);
				bfs(room, dust.x, dust.y, dust.dist, w, h);

			}

			ans = 50000;

			int n = dusts.size();
			boolean[] check = new boolean[n];
			ArrayList<Integer> permutation = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				permutation.add(i);
				check[i] = true;
				dfs(1, n, check, start, dusts, permutation);
				check[i] = false;
				permutation.remove(permutation.size() - 1);
			}

			if (ans == 50000) {
				System.out.println(-1);
			} else {
				System.out.println(ans);
			}

		}

	}

}
