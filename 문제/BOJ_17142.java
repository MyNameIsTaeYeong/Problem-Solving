package problem_solving_java;

import java.io.*;
import java.util.*;

// 각 조합에 대하여 bfs 

class Virus {
	public int x;
	public int y;
	public int sec;

	public Virus(int x, int y, int sec) {
		this.x = x;
		this.y = y;
		this.sec = sec;
	}
}

public class BOJ_17142 {

	static int n, m;
	static int[][] lab;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int cnt = 0;
	static ArrayList<Virus> virus = new ArrayList<>();
	static int ans = 30000;

	static void bfs(ArrayList<Virus> combination) {
		boolean[][] check = new boolean[n][n];
		Queue<Virus> q = new LinkedList<>();
		for (int i = 0; i < combination.size(); i++) {
			Virus v = combination.get(i);
			check[v.x][v.y] = true;
			q.add(v);
		}

		int sec = 0;
		int polluted = virus.size();
		
		if (polluted == cnt) {
			ans = Math.min(ans, sec);
		} else {
			while (!q.isEmpty()) {
				Virus v = q.poll();

				for (int i = 0; i < 4; i++) {
					int next_x = v.x + dx[i];
					int next_y = v.y + dy[i];
					if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n && lab[next_x][next_y] != 1
							&& !check[next_x][next_y]) {
						check[next_x][next_y] = true;
						q.add(new Virus(next_x, next_y, v.sec + 1));
						sec = v.sec + 1;
						if (lab[next_x][next_y] != 2) {
							polluted++;
							if (polluted == cnt) {
								ans = Math.min(ans, sec);
								break;
							}
						}
					}
				}

			}
		}

	}

	static void make_combination(int index, ArrayList<Virus> combination) {
		if (combination.size() == m) {
			bfs(combination);
			return;
		}

		if (index == virus.size()) {
			return;
		}

		combination.add(virus.get(index));
		make_combination(index + 1, combination);
		combination.remove(combination.size() - 1);

		make_combination(index + 1, combination);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		lab = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
				if (lab[i][j] == 2) {
					virus.add(new Virus(i, j, 0));
				}
				if (lab[i][j] != 1) {
					cnt++;
				}
			}
		}

		make_combination(0, new ArrayList<>());

		if (ans == 30000) {
			System.out.print(-1);
		} else {
			System.out.print(ans);
		}

	}

}
