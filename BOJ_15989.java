package problem_solving_java;

import java.io.*;

public class BOJ_15989 {

	static int[] d;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		d = new int[10001];

		d[0] = 1;

		for (int i = 1; i <= 10000; i++) {
			d[i] += d[i - 1];
		}

		for (int i = 2; i <= 10000; i++) {
			d[i] += d[i - 2];
		}

		for (int i = 3; i <= 10000; i++) {
			d[i] += d[i - 3];
		}

		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(d[n]);
		}

	}
}
