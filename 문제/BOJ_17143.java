package problem_solving_java;

import java.util.*;
import java.io.*;

class Shark {
	public int s;
	public int d;
	public int z;

	public Shark(int s, int d, int z) {
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

class Triple {
	public int x;
	public int y;
	public int d;

	public Triple(int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

public class BOJ_17143 {
	static int r, c, m;
	static Shark[][] water;

	// 1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽

	static void up(Triple t, int s, int cur) {

		for (int i = cur; i < s; i++) {
			if (t.x == 0) {
				t.d = 2;
				down(t, s, i);
				break;
			}
			t.x--;
		}

	}

	static void down(Triple t, int s, int cur) {
		for (int i = cur; i < s; i++) {
			if (t.x == r - 1) {
				t.d = 1;
				up(t, s, i);
				break;
			}
			t.x++;
		}

	}

	static void left(Triple t, int s, int cur) {
		for (int i = cur; i < s; i++) {
			if (t.y == 0) {
				t.d = 3;
				right(t, s, i);
				break;
			}
			t.y--;
		}

	}

	static void right(Triple t, int s, int cur) {
		for (int i = cur; i < s; i++) {
			if (t.y == c - 1) {
				t.d = 4;
				left(t, s, i);
				break;
			}
			t.y++;
		}
	}

	static void move() {
		Shark[][] new_water = new Shark[r][c];

		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				if (water[x][y] != null) {
					Triple t = new Triple(x, y, water[x][y].d);
					switch (water[x][y].d) {
					case 1:
						up(t, water[x][y].s, 0);
						break;
					case 2:
						down(t, water[x][y].s, 0);
						break;
					case 3:
						right(t, water[x][y].s, 0);
						break;
					case 4:
						left(t, water[x][y].s, 0);
						break;
					}

					if (new_water[t.x][t.y] == null) {
						new_water[t.x][t.y] = water[x][y];
						new_water[t.x][t.y].d = t.d;
						water[x][y] = null;
					} else {
						if (new_water[t.x][t.y].z > water[x][y].z) {
							water[x][y] = null;
						} else {
							new_water[t.x][t.y] = water[x][y];
							new_water[t.x][t.y].d = t.d;
							water[x][y] = null;
						}
					}
				}
			}
		}

		water = new_water;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		water = new Shark[r][c];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			water[x - 1][y - 1] = new Shark(s, d, z);
		}

		int ans = 0;

		for (int king = 0; king < c; king++) {

			for (int x = 0; x < r; x++) {
				if (water[x][king] != null) {
					ans += water[x][king].z;
					water[x][king] = null;
					break;
				}
			}
			move();

		}

		System.out.print(ans);

	}

}
