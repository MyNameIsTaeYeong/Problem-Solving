package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_1010 {

	static int[][] memo;

	// m개중 n개를 고르는 방법을 리턴.
	static int solve(int m, int n) {
		if (memo[m][n] != 0) {
			return memo[m][n];
		}

		if (n > m) {
			return 0;
		}

		if (n == m) {
			memo[m][n] = 1;
			return memo[m][n];
		}

		if (n == 1) {
			memo[m][n] = m;
			return memo[m][n];
		}

		memo[m][n] += solve(m - 1, n - 1);
		memo[m][n] += solve(m - 1, n);

		return memo[m][n];
	}

	public static void main(String[] args) throws IOException {
		memo = new int[30][30];

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			System.out.println(solve(m, n));
		}

	}

}
