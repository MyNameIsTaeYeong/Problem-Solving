package problem_solving_java;

import java.util.*;
import java.io.*;

// bfs 

class Tower {
	public StringBuilder a;
	public StringBuilder b;
	public StringBuilder c;
	public int step;

	public Tower(StringBuilder a, StringBuilder b, StringBuilder c, int step) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.step = step;
	}
}

public class BOJ_12906 {

	static StringBuilder[] sb = new StringBuilder[3];

	static int bfs() {
		HashSet<String> check = new HashSet<>();
		check.add(sb[0] + " " + sb[1] + " " + sb[2]);
		Queue<Tower> q = new LinkedList<>();
		q.add(new Tower(new StringBuilder(sb[0]), new StringBuilder(sb[1]), new StringBuilder(sb[2]), 0));

		while (!q.isEmpty()) {
			Tower tower = q.poll();

			if (!tower.a.toString().contains("B") && !tower.a.toString().contains("C")
					&& !tower.b.toString().contains("A") && !tower.b.toString().contains("C")
					&& !tower.c.toString().contains("A") && !tower.c.toString().contains("B")) {
				return tower.step;
			}

			if (tower.a.length() != 0) {
				char top = tower.a.charAt(tower.a.length() - 1);

				StringBuilder after_a = new StringBuilder(tower.a.substring(0, tower.a.length() - 1));
				StringBuilder after_b = new StringBuilder(tower.b + "" + top);
				StringBuilder after_c = new StringBuilder(tower.c + "" + top);

				if (!check.contains(after_a + " " + after_b + " " + tower.c)) {
					check.add(after_a + " " + after_b + " " + tower.c);
					q.add(new Tower(new StringBuilder(after_a), new StringBuilder(after_b), new StringBuilder(tower.c),
							tower.step + 1));
				}

				if (!check.contains(after_a + " " + tower.b + " " + after_c)) {
					check.add(after_a + " " + tower.b + " " + after_c);
					q.add(new Tower(new StringBuilder(after_a), new StringBuilder(tower.b), new StringBuilder(after_c),
							tower.step + 1));
				}

			}

			if (tower.b.length() != 0) {
				char top = tower.b.charAt(tower.b.length() - 1);

				StringBuilder after_a = new StringBuilder(tower.a+""+top);
				StringBuilder after_b = new StringBuilder(tower.b.substring(0, tower.b.length() - 1));
				StringBuilder after_c = new StringBuilder(tower.c + "" + top);

				if (!check.contains(after_a + " " + after_b + " " + tower.c)) {
					check.add(after_a + " " + after_b + " " + tower.c);
					q.add(new Tower(new StringBuilder(after_a), new StringBuilder(after_b), new StringBuilder(tower.c),
							tower.step + 1));
				}

				if (!check.contains(tower.a + " " + after_b + " " + after_c)) {
					check.add(tower.a + " " + after_b + " " + after_c);
					q.add(new Tower(new StringBuilder(tower.a), new StringBuilder(after_b), new StringBuilder(after_c),
							tower.step + 1));
				}
			}

			if (tower.c.length() != 0) {
				char top = tower.c.charAt(tower.c.length() - 1);

				StringBuilder after_a = new StringBuilder(tower.a+""+top);
				StringBuilder after_b = new StringBuilder(tower.b+""+top);
				StringBuilder after_c = new StringBuilder(tower.c.substring(0, tower.c.length() - 1));

				if (!check.contains(after_a + " " + tower.b + " " + after_c)) {
					check.add(after_a + " " + tower.b + " " + after_c);
					q.add(new Tower(new StringBuilder(after_a), new StringBuilder(tower.b), new StringBuilder(after_c),
							tower.step + 1));
				}

				if (!check.contains(tower.a + " " + after_b + " " + after_c)) {
					check.add(tower.a + " " + after_b + " " + after_c);
					q.add(new Tower(new StringBuilder(tower.a), new StringBuilder(after_b), new StringBuilder(after_c),
							tower.step + 1));
				}
			}

		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int cnt = Integer.parseInt(st.nextToken());
			if (cnt == 0) {
				sb[i] = new StringBuilder("");
			} else {
				sb[i] = new StringBuilder(st.nextToken());
			}
		}

		System.out.print(bfs());

	}

}
