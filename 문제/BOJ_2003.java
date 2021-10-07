package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2003 {

	static int n, m;
	static int[] a;
	static int ans = 0;

	static void solve() {
		int left = 0, right = 0;

		int sum = a[0];

		while (true) {
			if (sum < m) {
				if (right == n - 1) {
					break;
				}
				sum += a[++right];
			} else if (sum > m) {
				sum -= a[left++];
			} else if (sum == m) {
				ans++;
				sum -= a[left++];
			}

			if(left == n) {
				break;
			}
			
			if (left > right) {
				right = left;
				sum += a[left];
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		a = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		solve();

		System.out.println(ans);
	}

}
