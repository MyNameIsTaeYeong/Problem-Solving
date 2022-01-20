package problem_solving_java;

import java.util.*;
import java.io.*;

// d[n][m] : n번째 ~ m번째까지가 팰린드롬인지?
// go(n, m) : n번째 ~ m번째까지가 팰린드롬인지를 구하는 함수.

public class BOJ_10942 {

	static int n, m;
	static int[] seq;
	static int[][] d;

	static int go(int i, int j) {
		if (d[i][j] != -1) {
			return d[i][j];
		}

		if (seq[i] == seq[j]) {
			d[i][j] = go(i + 1, j - 1);
		} else {
			d[i][j] = 0;
		}

		return d[i][j];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		seq = new int[n];
		d = new int[n][n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}

		for (int[] init : d) {
			Arrays.fill(init, -1);
		}

		for (int i = 0; i < n; i++) {
			d[i][i] = 1;
		}

		for (int i = 0; i < n - 1; i++) {
			if (seq[i] == seq[i + 1]) {
				d[i][i + 1] = 1;
			} else {
				d[i][i + 1] = 0;
			}
		}

		m = Integer.parseInt(br.readLine());

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int ans = go(s - 1, e - 1);

			bw.write((char) ('0' + ans));
			bw.newLine();
		}
		bw.flush();
		bw.close();

	}

}
