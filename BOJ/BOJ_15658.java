package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_15658 {

	static int n;
	static int[] a;
	static int[] duplicated = new int[4];
	static int max_val = -2000000000;
	static int min_val = 2000000000;

	static void solve(int index, int val, int plus, int minus, int mul, int div) {

		if (index == (n - 1)) {
			if (val > max_val) {
				max_val = val;
			}
			if (val < min_val) {
				min_val = val;
			}
			return;
		}

		if (plus < duplicated[0]) {
			solve(index + 1, val + a[index + 1], plus+1, minus, mul, div);
		}

		if (minus < duplicated[1]) {
			solve(index + 1, val - a[index + 1], plus, minus+1, mul, div);
		}
		if (mul < duplicated[2]) {
			solve(index + 1, val * a[index + 1], plus, minus, mul+1, div);
		}
		if (div < duplicated[3]) {
			if(val < 0) {
				solve(index + 1, -(-val / a[index + 1]), plus, minus, mul, div+1);
			}else {
				solve(index + 1, val / a[index + 1], plus, minus, mul, div+1);
			}
			
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		a = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < 4; i++) {
			duplicated[i] = Integer.parseInt(st.nextToken());
		}

		solve(0, a[0], 0, 0, 0, 0);

		System.out.println(max_val);
		System.out.println(min_val);
	}

}
