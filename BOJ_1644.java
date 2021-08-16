package problem_solving_java;

import java.io.*;
import java.util.ArrayList;

public class BOJ_1644 {

	static int n;
	static boolean[] prime_check = new boolean[4000001];
	static ArrayList<Integer> primes = new ArrayList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		for (int i = 2; i <= 2000; i++) {
			if (!prime_check[i]) {
				for (int j = i * i; j <= 4000000; j += i) {
					prime_check[j] = true;
				}
			}
		}
		
		for(int i=2; i<= 4000000; i++) {
			if(!prime_check[i]) {
				primes.add(i);
			}
		}

		int ans = 0;

		int sum = primes.get(0);
		int left = 0;
		int right = 0;

		while (true) {
			if (sum < n) {
				if (right == primes.size() - 1) {
					break;
				}
				sum += primes.get(++right);
			} else if (sum > n) {
				sum -= primes.get(left++);
			} else {
				ans++;
				sum -= primes.get(left++);
			}

			if (left == primes.size()) {
				break;
			}
			
			if(left > right) {
				right = left;
				sum += primes.get(left);
			}
		}
		
		System.out.print(ans);

	}

}
