package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_16917 {

	static int a, b, c, x, y;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());

		int max_half = Math.max(x, y) * 2;
		long ans = Long.parseLong("10000000000");

		for (int i = 0; i <= max_half; i += 2) {
			long price = 0;
			price += c * i;
			if (i / 2 < x)
				price += (x - (i / 2)) * a;
			if (i / 2 < y)
				price += (y - (i / 2)) * b;
			ans = Math.min(ans, price);
		}

		System.out.print(ans);

	}

}
