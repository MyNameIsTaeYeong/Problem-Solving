package problem_solving_java;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1208 {

	static int n, s;
	static int[] a;
	static long ans = 0;

	static void cal(ArrayList<Integer> s1, ArrayList<Integer> s2) {
		int left = 0;
		int right = s2.size() - 1;

		while (true) {

			int sum = s1.get(left) + s2.get(right);

			if (sum < s) {
				left++;
			} else if (sum > s) {
				right--;
			} else {
				long cnt1 = 1;
				long cnt2 = 1;

				while ((left + 1) < s1.size() && (s1.get(left + 1).equals(s1.get(left)))) {
					left++;
					cnt1++;
				}
				left++;

				while ((right - 1) >= 0 && s2.get(right).equals(s2.get(right - 1))) {
					right--;
					cnt2++;
				}
				right--;

				ans += cnt1 * cnt2;

			}

			if (left == s1.size() || right < 0) {
				break;
			}
		}

	}

	static void subset_sum(ArrayList<Integer> s, int start, int end, int sum) {
		if (start == end) {
			s.add(sum);
			return;
		}

		subset_sum(s, start + 1, end, sum);
		subset_sum(s, start + 1, end, sum + a[start]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());

		a = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		ArrayList<Integer> s1 = new ArrayList<>();
		ArrayList<Integer> s2 = new ArrayList<>();

		subset_sum(s1, 0, n / 2, 0);
		subset_sum(s2, n / 2, n, 0);

		Collections.sort(s1);
		Collections.sort(s2);

		cal(s1, s2);

		if (s == 0) {
			System.out.println(ans - 1);
		} else {
			System.out.println(ans);
		}

	}

}
