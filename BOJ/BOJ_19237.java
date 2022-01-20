package problem_solving_java;

import java.util.*;
import java.io.*;

// 1:35 ~ 4:11 (약 2시간 40분)

class Shark {
	public int num;
	public int x;
	public int y;
	public int dir;

	public Shark(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
		this.dir = -1;
	}
}

public class BOJ_19237 {

	static int n, m, k;

	// 1, 2, 3, 4는 각각 위, 아래, 왼쪽, 오른쪽
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int[][][] priority;
	static int[][] water, smell, smellNum;
	static int remain;

	static Shark[] sharkArr;

	static void move(int idx, int dir) {
		Shark shark = sharkArr[idx];
		int nextX = shark.x + dx[dir];
		int nextY = shark.y + dy[dir];
		shark.dir = dir;
		if (water[nextX][nextY] != 0 && water[nextX][nextY] < shark.num) {

			water[shark.x][shark.y] = 0;
			sharkArr[idx] = null;
			remain--;
		} else {

			if (water[nextX][nextY] != 0) {
				sharkArr[water[nextX][nextY]] = null;
				remain--;
			}

			water[shark.x][shark.y] = 0;
			water[nextX][nextY] = shark.num;
			shark.x = nextX;
			shark.y = nextY;

		}
	}

	// val이 0이면 빈 칸 체크, 0이 아니면 자기 냄새 체크
	static boolean check(int idx, int val) {
		Shark shark = sharkArr[idx];

//		// 현재 방향에서 확인
//		int nextX = shark.x + dx[shark.dir];
//		int nextY = shark.y + dy[shark.dir];
//
//		if (0 <= nextX && nextX < n && 0 <= nextY && nextY < n && smell[nextX][nextY] == val) {
//			move(idx, shark.dir);
//			return true;
//		}

		// 현재 방향에서 우선순위 순으로 확인
		for (int i = 0; i < 4; i++) {
			int dir = priority[idx][shark.dir][i];
			int nextX = shark.x + dx[dir];
			int nextY = shark.y + dy[dir];
			
			if(val == 0) {
				if (0 <= nextX && nextX < n && 0 <= nextY && nextY < n && smell[nextX][nextY] == val) {
					move(idx, dir);
					return true;
				}
			}else {
				if (0 <= nextX && nextX < n && 0 <= nextY && nextY < n && smellNum[nextX][nextY] == val) {
					move(idx, dir);
					return true;
				}
			}
			
		}

		return false;
	}

	// 상어의 이동
	// 1. 인접한 칸 중 빈 칸이 몇개인지 체크
	// 2. 빈 칸이 1개이면 거기로 이동.
	// 3. 빈 칸이 2개이상이면 우선순위 확인 후 이동.
	// 4. 빈 칸이 없다면 자기 냄새가나는 칸이 몇개인지 체크
	// 5. 1개이면 거기로 이동, 2개이상이면 우선순위 체크
	// 6. 이동할때 그 칸에 상어가 있다면 서열에따라 내쫓기.
	static void sharksMove() {
		for (int i = 1; i <= m; i++) {
			if (sharkArr[i] == null)
				continue;
			if (!check(i, 0)) {
				check(i, sharkArr[i].num);
			}
		}

	}

	static void mist() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (smell[i][j] != 0) {
					smell[i][j]--;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (water[i][j] != 0) {
					smell[i][j] = k;
					smellNum[i][j] = water[i][j];
				}
			}
		}
	}

	static int solve() {
		int sec = 0;

		while (true) {
			sec++;

//			if (sec < 20) {
//
//				printArr();
//				System.out.println();
//			}

			// 상어의 이동
			sharksMove();

			// 냄새뿌리면서 빈 칸에 남은 냄새 1개씩 줄이기
			mist();

			if (remain == 1) {
				break;
			}
			if (sec == 1000) {
				sec = -1;
				break;
			}
		}

		return sec;
	}

	public static void printArr() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(water[i][j] + " ");
			}
			System.out.print("\t");
			for (int j = 0; j < n; j++) {
				System.out.print(smell[i][j] + " ");
			}
			System.out.print("\t");
			for (int j = 0; j < n; j++) {
				System.out.print(smellNum[i][j] + " ");
			}
			System.out.println();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		priority = new int[m + 1][4][4];
		water = new int[n][n];
		smell = new int[n][n];
		smellNum = new int[n][n];
		remain = m;

		sharkArr = new Shark[m + 1];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				water[i][j] = Integer.parseInt(st.nextToken());
				if (water[i][j] != 0) {
					sharkArr[water[i][j]] = new Shark(water[i][j], i, j);
					smellNum[i][j] = water[i][j];
					smell[i][j] = k;
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			int dir = Integer.parseInt(st.nextToken()) - 1;
			sharkArr[i + 1].dir = dir;
		}

		for (int i = 0; i < m; i++) {

			// 위, 아래, 왼쪽, 오른쪽
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					int dir = Integer.parseInt(st.nextToken()) - 1;
					priority[i + 1][j][k] = dir;
				}

			}
		}

		int ans = solve();

		System.out.print(ans);

//		for(int i=1; i<=m; i++) {
//			for (int j = 0; j < 4; j++) {
//				
//				for (int k = 0; k < 4; k++) {
//					
//					switch(priority[i][j][k]) {
//					case 0:
//						System.out.print("↑" + " ");
//						break;
//					case 1:
//						System.out.print("↓" + " ");
//						break;
//					case 2:
//						System.out.print("←" + " ");
//						break;
//					case 3:
//						System.out.print("→" + " ");
//						break;
//						
//					}
//				}
//				System.out.println();
//
//			}
//			System.out.println();
//		}

	}

}
