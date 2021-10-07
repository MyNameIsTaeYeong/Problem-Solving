package problem_solving_java;

// Bottom - up : for
// Top - down : 재귀 

import java.util.*;
import java.io.*;

public class BOJ_11048 {

	static int n, m;
	static int[][] miro;
	static int[][] d;

	static int go(int x, int y) {
		if (x > n || y > m) {
			return 0;
		}

		if (d[x][y] != -1) {
			return d[x][y]; 
		}
		
		d[x][y] = Math.max(go(x+1, y), go(x, y+1)) + miro[x][y];
		
		return d[x][y];

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		miro = new int[n + 1][m + 1];
		d = new int[n + 1][m + 1];

		for (int[] init : d) {
			Arrays.fill(init, -1);
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= m; j++) {
				miro[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.print(go(1, 1));

	}

}
