package problem_solving_java;

import java.util.*;
import java.io.*;

class Trees {
	public ArrayList<Integer> ages;

	public Trees(int age) {
		ages = new ArrayList<>();
		ages.add(age);
	}
}

public class BOJ_16235 {
	static int n, m, k;
	static int[][] a;
	static int[][] ddang;
	static Trees[][] trees;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static void winter() {
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				ddang[x][y] += a[x][y];
			}
		}
	}

	static void fall() {

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (trees[x][y] != null && trees[x][y].ages != null) {
					for (int index = 0; index < trees[x][y].ages.size(); index++) {
						if (trees[x][y].ages.get(index) % 5 == 0) {
							for (int i = 0; i < 8; i++) {
								int next_x = x + dx[i];
								int next_y = y + dy[i];
								if (0 <= next_x && next_x < n && 0 <= next_y && next_y < n) {
									if (trees[next_x][next_y] == null) {
										trees[next_x][next_y] = new Trees(1);
									} else {
										if (trees[next_x][next_y].ages == null) {
											trees[next_x][next_y].ages = new ArrayList<>();
											trees[next_x][next_y].ages.add(1);
										} else {
											trees[next_x][next_y].ages.add(0, 1);
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	static void summer(int x, int y, int nutrient) {
		ddang[x][y] += nutrient;
	}

	static void spring() {
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (trees[x][y] == null || trees[x][y].ages == null) {
					continue;
				}

				boolean dead = false;
				int dead_start = 0;
				int dead_nutrient = 0;

				// 나이만큼 양분을 먹고 나이 1증가.
				
				for (int index = 0; index < trees[x][y].ages.size(); index++) {
					
					int cur_age = trees[x][y].ages.get(index);
					if (ddang[x][y] >= cur_age) {
						ddang[x][y] -= cur_age;
						trees[x][y].ages.set(index, cur_age + 1);
						dead_start = index + 1;
					} else {
						dead = true;
						dead_nutrient += (cur_age / 2);
					}
				}

				if (dead) {
					if (dead_start == 0) {
						trees[x][y].ages = null;
					} else {
						while(trees[x][y].ages.size() != dead_start) {
							trees[x][y].ages.remove(dead_start);
						}
						
					}
					summer(x, y, dead_nutrient);
				}

			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		ddang = new int[n][n];

		for (int[] init : ddang) {
			Arrays.fill(init, 5);
		}

		a = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		trees = new Trees[n][n];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			if (trees[x][y] == null) {
				trees[x][y] = new Trees(age);
			} else {
				trees[x][y].ages.add(age);
			}
		}

		for (int i = 0; i < k; i++) {
			
			spring();
			fall();
			winter();
			
			
		}
		
		
		

		int ans = 0;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (trees[x][y] != null && trees[x][y].ages != null) {
					ans += trees[x][y].ages.size();
				}
			}
		}

		System.out.print(ans);
		
	}

}
