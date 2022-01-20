package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_20055 {

	static int[][] conveyer;
	static int n, k;
	static int ans = 0;

	static void solve() {
		int cnt = 0;

		int start = 0;
		int end = n - 1;

		while (cnt < k) {
			ans++;
			start--;
			end--;
			if (start < 0) {
				start = 2 * n - 1;
			}
			if (end < 0) {
				end = 2 * n - 1;
			}
			
			conveyer[end][1] = 0;

			int idx = end;

			while (idx != start) {
				int prev = idx - 1;

				if (prev < 0)
					prev = 2 * n - 1;

				if (conveyer[prev][1] == 1 && conveyer[idx][0] != 0 && conveyer[idx][1] == 0) {
					conveyer[idx][0]--;
					conveyer[idx][1] = 1;
					conveyer[prev][1] = 0;
					if (conveyer[idx][0] == 0)
						cnt++;
				}

				idx--;
				if (idx < 0)
					idx = 2 * n - 1;
			}
			conveyer[end][1] = 0;

			if (conveyer[start][0] != 0) {
				conveyer[start][1] = 1;
				conveyer[start][0]--;
				if (conveyer[start][0] == 0)
					cnt++;
			}

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		conveyer = new int[2 * n][2];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * n; i++) {
			conveyer[i][0] = Integer.parseInt(st.nextToken());
		}

		

		solve();

		System.out.print(ans);

	}

}
