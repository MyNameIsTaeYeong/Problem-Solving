package problem_solving_java;

import java.util.*;
import java.io.*;

class Height {
	public int height;
	public int cnt;

	public Height(int height, int cnt) {
		this.height = height;
		this.cnt = cnt;
	}
}

public class BOJ_5014 {

	static int f, s, g, u, d;

	static void bfs() {
		boolean[] check = new boolean[f + 1];
		check[s] = true;
		Queue<Height> q = new LinkedList<>();
		q.add(new Height(s, 0));

		while (!q.isEmpty()) {
			Height h = q.poll();

			if (h.height == g) {
				System.out.print(h.cnt);
				return;
			}

			if (h.height + u <= f && !check[h.height + u]) {
				check[h.height + u] = true;
				q.add(new Height(h.height + u, h.cnt + 1));
			}

			if (h.height - d >= 1 && !check[h.height - d]) {
				check[h.height - d] = true;
				q.add(new Height(h.height - d, h.cnt + 1));
			}

		}

		System.out.print("use the stairs");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		bfs();

	}

}
