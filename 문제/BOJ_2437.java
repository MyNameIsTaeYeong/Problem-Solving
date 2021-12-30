package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_2437 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		Collections.sort(list);

		int sum = 0;
		int ans = 0;

		for (int i = 0; i < n; i++) {
			if (list.get(i) > sum + 1) {
				ans = sum + 1;
				break;
			}

			sum += list.get(i);
		}

		if (ans == 0) {
			ans = sum + 1;
		}

		System.out.print(ans);

	}

}
