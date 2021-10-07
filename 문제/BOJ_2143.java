package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_2143 {

	static int t, n, m;
	static int[] a, b;
	static long ans = 0;

	static ArrayList<Integer> make_partials(int[] arr) {
		ArrayList<Integer> partials = new ArrayList<>();

		for (int i = 0; i < arr.length; i++) {
			int sum = arr[i];
			partials.add(sum);
			for (int j = i + 1; j < arr.length; j++) {
				sum += arr[j];
				partials.add(sum);
			}
		}

		return partials;
	}

	static void solve(ArrayList<Integer> partials_a, ArrayList<Integer> partials_b) {

		int left = 0;
		int right = partials_b.size() - 1;

		while (left < partials_a.size() && right >= 0) {
			int sum = partials_a.get(left) + partials_b.get(right);

			if (sum == t) {
				long cnt_l = 1;

				while (left != partials_a.size() - 1 && partials_a.get(left).equals(partials_a.get(left + 1))) {
					left++;
					cnt_l++;
				}
				left++;
				long cnt_r = 1;
				while (right > 0 && partials_b.get(right).equals(partials_b.get(right - 1))) {
					right--;
					cnt_r++;
				}
				right--;
				ans += cnt_l * cnt_r;

			} else if (sum < t) {
				left++;
			} else {
				right--;
			}
		}

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());

		a = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		m = Integer.parseInt(br.readLine());

		b = new int[m];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> partials_a = make_partials(a);
		ArrayList<Integer> partials_b = make_partials(b);

		Collections.sort(partials_a);
		Collections.sort(partials_b);

		solve(partials_a, partials_b);

		System.out.print(ans);
	}

}
