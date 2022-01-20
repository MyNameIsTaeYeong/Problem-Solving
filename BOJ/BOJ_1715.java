package problem_solving_java;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class BOJ_1715 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Long> pq = new PriorityQueue<>();

		for (int i = 0; i < n; i++) {
			pq.add(Long.parseLong(br.readLine()));
		}

		if (n == 1) {
			System.out.print(0);
		} else {

			long ans = 0;

			while (pq.size() != 1) {
				long num1 = pq.poll();
				long num2 = pq.poll();

				ans += num1 + num2;
				pq.add(num1 + num2);
			}

			System.out.print(ans);
		}

	}

}
