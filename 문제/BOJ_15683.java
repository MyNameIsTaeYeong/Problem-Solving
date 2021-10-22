package problem_solving_java;

import java.io.*;
import java.util.*;

class CCTV {
	public int x;
	public int y;
	public int num;

	public CCTV(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}

public class BOJ_15683 {

	static int n, m;
	static int[][] office;
	static ArrayList<CCTV> list = new ArrayList<>();

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };

	static int ans = 100;

	static void print_office() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(office[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	static void cctv1(int x, int y, int dir, int val) {

		while (true) {
			int next_x = x + dx[dir];
			int next_y = y + dy[dir];
			if (0 <= next_x && next_x < n && 0 <= next_y && next_y < m && office[next_x][next_y] != 6) {
				if (1 <= office[next_x][next_y] && office[next_x][next_y] <= 6) {

				} else {
					office[next_x][next_y] += val;
				}

			} else {
				break;
			}
			x = next_x;
			y = next_y;
		}

	}

	static void cctv2(int x, int y, int dir, int val) {
		cctv1(x, y, dir, val);
		cctv1(x, y, dir + 2, val);
	}

	static void cctv3(int x, int y, int dir, int val) {
		if (dir == 3) {
			cctv1(x, y, 0, val);
			cctv1(x, y, dir, val);
		} else {
			cctv1(x, y, dir, val);
			cctv1(x, y, dir + 1, val);
		}
	}

	static void cctv4(int x, int y, int dir, int val) {
		if (dir == 2) {
			cctv1(x, y, dir, val);
			cctv1(x, y, dir + 1, val);
			cctv1(x, y, 0, val);
		} else if (dir == 3) {
			cctv1(x, y, dir, val);
			cctv1(x, y, 0, val);
			cctv1(x, y, 1, val);
		} else {
			cctv1(x, y, dir, val);
			cctv1(x, y, dir + 1, val);
			cctv1(x, y, dir + 2, val);
		}
	}

	static void cctv5(int x, int y, int dir, int val) {
		cctv1(x, y, 0, val);
		cctv1(x, y, 1, val);
		cctv1(x, y, 2, val);
		cctv1(x, y, 3, val);
	}

	static void check() {

		// print_office();

		int temp = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (office[i][j] == 0) {
					temp++;
				}
			}
		}

		ans = Math.min(ans, temp);
	}

	static void solve(int index) {
		if (index == list.size()) {
			check();
			return;
		}

		CCTV cctv = list.get(index);
		// 1, 3, 4 4방향
		// 2번 2방향
		// 5 1방향

		if (cctv.num == 5) {
			cctv5(cctv.x, cctv.y, 0, 10);
			solve(index + 1);
			cctv5(cctv.x, cctv.y, 0, -10);
		}

		else if (cctv.num == 2) {
			for (int i = 0; i < 2; i++) {
				cctv2(cctv.x, cctv.y, i, 10);
				solve(index + 1);
				cctv2(cctv.x, cctv.y, i, -10);
			}
		} else if (cctv.num == 1) {
			for (int i = 0; i < 4; i++) {
				cctv1(cctv.x, cctv.y, i, 10);
				solve(index + 1);
				cctv1(cctv.x, cctv.y, i, -10);
			}
		} else if (cctv.num == 3) {
			for (int i = 0; i < 4; i++) {
				cctv3(cctv.x, cctv.y, i, 10);
				solve(index + 1);
				cctv3(cctv.x, cctv.y, i, -10);
			}
		} else if (cctv.num == 4) {
			for (int i = 0; i < 4; i++) {
				cctv4(cctv.x, cctv.y, i, 10);
				solve(index + 1);
				cctv4(cctv.x, cctv.y, i, -10);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		office = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < m; j++) {
				office[i][j] = Integer.parseInt(st.nextToken());
				if (office[i][j] != 0 && office[i][j] != 6) {
					list.add(new CCTV(i, j, office[i][j]));
				}
			}
		}

		solve(0);

		System.out.print(ans);
	}

}
