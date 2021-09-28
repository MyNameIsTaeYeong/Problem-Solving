package problem_solving_java;

import java.util.*;
import java.io.*;

class Cal {
	public long s;
	public StringBuilder op;

	public Cal(long s, StringBuilder op) {
		this.s = s;
		this.op = op;
	}

}

public class BOJ_14395 {

	static long s, t;

	static void bfs() {
		Queue<Cal> q = new LinkedList<>();
		q.add(new Cal(s, new StringBuilder("")));
		HashSet<Long> check = new HashSet<>();
		check.add(s);

		while (!q.isEmpty()) {
			Cal c = q.poll();

			if (c.s == t) {
				if (c.op.length() == 0) {
					System.out.print(0);
				} else {
					System.out.print(c.op.toString());
				}

				return;
			}

			// '*', '+', '-', '/'

			if (c.s * c.s <= t) {
				if (!check.contains(c.s * c.s)) {
					check.add(c.s * c.s);
					StringBuilder op = new StringBuilder(c.op);
					op.append("*");
					q.add(new Cal(c.s * c.s, op));
				}

			}

			if (c.s + c.s <= t) {
				if (!check.contains(c.s + c.s)) {
					check.add(c.s + c.s);
					StringBuilder op = new StringBuilder(c.op);
					op.append("+");
					q.add(new Cal(c.s + c.s, op));
				}

			}

			if (c.s != 0) {
				if (!check.contains(c.s / c.s)) {
					check.add(c.s / c.s);
					StringBuilder op = new StringBuilder(c.op);
					op.append("/");
					q.add(new Cal(1, op));
				}

			}
		}

		System.out.print(-1);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		s = Long.parseLong(st.nextToken());
		t = Long.parseLong(st.nextToken());

		bfs();

	}

}
