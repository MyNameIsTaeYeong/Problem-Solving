package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_19236 {

	// 1부터 순서대로 ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int ans = 0;

	static void moveFish(int[][][] water, int idx) {

		int x = -1;
		int y = -1;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (water[i][j][0] == idx) {
					x = i;
					y = j;
				}
			}
		}

		if (x == -1 && y == -1)
			return;

		for (int i = 0; i < 8; i++) {
			int nextX = x + dx[water[x][y][1]];
			int nextY = y + dy[water[x][y][1]];
			if (0 <= nextX && nextX < 4 && 0 <= nextY && nextY < 4 && water[nextX][nextY][0] != 20) {
				int temp1 = water[nextX][nextY][0];
				int temp2 = water[nextX][nextY][1];
				water[nextX][nextY][0] = water[x][y][0];
				water[nextX][nextY][1] = water[x][y][1];
				water[x][y][0] = temp1;
				water[x][y][1] = temp2;
				break;
			}
			water[x][y][1]++;
			if (water[x][y][1] == 8)
				water[x][y][1] = 0;
		}

	}

	static int[][][] copyWater(int[][][] water) {

		int[][][] newWater = new int[4][4][2];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newWater[i][j][0] = water[i][j][0];
				newWater[i][j][1] = water[i][j][1];
			}
		}

		return newWater;
	}

	static boolean sharkMovePossible(int[][][] water, int sharkX, int sharkY) {
		int nextX = sharkX;
		int nextY = sharkY;
		for (int i = 0; i < 3; i++) {
			nextX += dx[water[sharkX][sharkY][1]];
			nextY += dy[water[sharkX][sharkY][1]];
			if (0 <= nextX && nextX < 4 && 0 <= nextY && nextY < 4 && water[nextX][nextY][0] != 0) {
				return true;
			}
		}

		return false;
	}

	static void printWater(int[][][] water) {
		System.out.println();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				System.out.print(water[i][j][0] + " ");
			}
			System.out.print('\t');
			for (int j = 0; j < 4; j++) {
				switch (water[i][j][1]) {
				case 0:
					System.out.print("↑ ");
					break;
				case 1:
					System.out.print("↖ ");
					break;
				case 2:
					System.out.print("← ");
					break;
				case 3:
					System.out.print("↙ ");
					break;
				case 4:
					System.out.print("↓ ");
					break;
				case 5:
					System.out.print("↘ ");
					break;
				case 6:
					System.out.print("→ ");
					break;
				case 7:
					System.out.print("↗ ");
					break;
				default:
					System.out.print("0 ");
					break;
				}

			}

			System.out.println();
		}
	}

	static void solve(int[][][] water, int sharkX, int sharkY, int cur) {

		
		// 물고기 이동
		for (int i = 1; i <= 16; i++) {
			moveFish(water, i);
			// printWater(water);
		}
		// System.out.println("물고기 이동 직후 ");

		
		if (!sharkMovePossible(water, sharkX, sharkY)) {
			ans = Math.max(ans, cur);
			return;
		}

		// 상어 이동
		int nextX = sharkX;
		int nextY = sharkY;
		for (int i = 0; i < 3; i++) {
			nextX += dx[water[sharkX][sharkY][1]];
			nextY += dy[water[sharkX][sharkY][1]];
			if (0 <= nextX && nextX < 4 && 0 <= nextY && nextY < 4 && water[nextX][nextY][0] != 0) {
				int[][][] newWater = copyWater(water);
				int eatNum = newWater[nextX][nextY][0];
				newWater[nextX][nextY][0] = 20;
				newWater[sharkX][sharkY][0] = 0;
				newWater[sharkX][sharkY][1] = -1;
				//System.out.print(nextX + "," + nextY);
				//printWater(newWater);
				//System.out.println();
				// printDir(newWater);
				solve(newWater, nextX, nextY, cur + eatNum);
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][][] water = new int[4][4][2];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				water[i][j][0] = num;
				water[i][j][1] = dir - 1;
			}
		}

		int cur = water[0][0][0];

		// 상어 번호 부여
		water[0][0][0] = 20;
		//printWater(water);
		// printDir(water);
		solve(water, 0, 0, cur);

		System.out.print(ans);

	}

}
