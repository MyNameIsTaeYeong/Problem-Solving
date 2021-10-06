package problem_solving_java;

import java.util.*;
import java.io.*;

// go(n) : n번에서 출발하여 오른쪽 끝으로 가는 최소횟수를 리턴한다. 
// d[n] : n에서 출발하여 오른쪽 끝으로 가는 최소횟수. 

public class BOJ_11060 {

	static int n;
	static int[] d, miro;
	static final int MAX = 10000000;

	static int go(int index) {
		if (index >= n) {
			return MAX;
		}

		if (d[index] != MAX) {
			return d[index];
		}

		for (int i = 1; i <= miro[index]; i++) {
			d[index] = Math.min(d[index], go(index + i) + 1);
		}

		return d[index];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		miro = new int[n];
		d = new int[n];
		Arrays.fill(d, MAX);

		d[n - 1] = 0;

		for (int i = 0; i < n; i++) {
			miro[i] = Integer.parseInt(st.nextToken());
		}
		
		go(0);

		if (d[0] == MAX) {
			System.out.print(-1);
		} else {
			System.out.print(d[0]);
		}

	}

}
