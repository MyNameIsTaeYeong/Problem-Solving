package problem_solving_java;

import java.util.*;
import java.io.*;

class FireBall {
	public int x;
	public int y;
	public int m;
	public int s;
	public int d;

	public FireBall(int x, int y, int m, int s, int d) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.s = s;
		this.d = d;

	}
}

public class BOJ_20056 {

	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int n, m, k;
	static Queue<FireBall> q;
	static int[][] mArr, dArr;
	static int[][][] sArr;
	static int[][] temp;
	static int ans = 0;

	static void moveFireBall() {

		while (!q.isEmpty()) {
			FireBall fireball = q.poll();
			for (int move = 0; move < fireball.s; move++) {
				fireball.x += dx[fireball.d];
				fireball.y += dy[fireball.d];

				if (fireball.x < 0)
					fireball.x = n - 1;
				else if (fireball.x == n)
					fireball.x = 0;

				if (fireball.y < 0)
					fireball.y = n - 1;
				else if (fireball.y == n)
					fireball.y = 0;
			}

			mArr[fireball.x][fireball.y] += fireball.m;
			sArr[fireball.x][fireball.y][0] += fireball.s;
			sArr[fireball.x][fireball.y][1] += 1;
			temp[fireball.x][fireball.y] = fireball.d;
			if (fireball.d % 2 == 0)
				dArr[fireball.x][fireball.y] += 1;
		}
	}

	static void go() {
		// 1. 파이어볼 이동
		// 2. 2개 이상이 모인 칸에서는 분열 (이때 질량이 0이되면 소멸 0이 아니면 하나의 fireball로 합치고 cnt표시)
		// 3. 반복

		for (int cmd = 0; cmd < k; cmd++) {
			mArr = new int[n][n];
			sArr = new int[n][n][2];
			dArr = new int[n][n];
			temp = new int[n][n];

			moveFireBall();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (sArr[i][j][1] == 1) {
						q.add(new FireBall(i, j, mArr[i][j], sArr[i][j][0], temp[i][j]));
					} else if (sArr[i][j][1] >= 2 && mArr[i][j] >= 5) {
						int fireballM = mArr[i][j] / 5;
						int fireballS = sArr[i][j][0] / sArr[i][j][1];
						int fireballD = -1;
						if (dArr[i][j] == sArr[i][j][1] || dArr[i][j] == 0)
							fireballD = 0;
						else
							fireballD = 1;
						for (int k = 0; k < 4; k++) {
							q.add(new FireBall(i, j, fireballM, fireballS, fireballD));
							fireballD += 2;
						}
					}
				}
			}
		}

		while (!q.isEmpty()) {
			ans += q.poll().m;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		q = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			// ri, ci, mi, si, di
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			q.add(new FireBall(r, c, m, s, d));
		}

		go();

		System.out.print(ans);
	}

}
