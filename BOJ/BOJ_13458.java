package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_13458 {

	static int n, b, c;
	static int[] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		a = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
//		Arrays.fill(a, 1000000);
//		
//		b = 1; 
//		c = 1;

		long ans = 0;

		for (int i = 0; i < n; i++) {
			ans++;
			a[i] -= b;
			if (a[i] <= 0) {
				continue;
			}

			int second = 0;
			if (a[i] % c == 0) {
				second = a[i] / c;

			} else {
				second = (a[i] / c) + 1;
			}

			ans += second;
		}

		System.out.print(ans);
	}

}
