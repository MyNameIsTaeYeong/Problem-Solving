package problem_solving_java;

// 행 또는 열을 순회하며 해시맵에 수와 횟수 저장.

// 해시맵을 순회하며 pair를 우선순위 큐에 넣는다. 
// 하나씩 뽑아서 배열을 채운다.

import java.util.*;
import java.io.*;

class Pair implements Comparable<Pair> {
	public int number;
	public int cnt;

	public Pair(int number, int cnt) {
		this.number = number;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Pair p) {
		if (this.cnt == p.cnt) {
			return this.number - p.number;
		} else {
			return this.cnt - p.cnt;
		}
	}
}

public class BOJ_17140 {

	static int r, c, k;
	static int[][] a;

	static int cur_r, cur_c;

	static void op_r() {
		int[][] new_a = new int[100][100];
		int new_c = 0;

		for (int x = 0; x < cur_r; x++) {
			HashSet<Integer> set = new HashSet<>();
			HashMap<Integer, Integer> map = new HashMap<>();
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			for (int y = 0; y < cur_c; y++) {
				if (a[x][y] != 0) {
					set.add(a[x][y]);
					map.put(a[x][y], map.getOrDefault(a[x][y], 0) + 1);
				}
			}

			Iterator<Integer> it = set.iterator();
			while (it.hasNext()) {
				int number = it.next();
				int cnt = map.get(number);
				pq.add(new Pair(number, cnt));
			}

			int index = 0;
			while (!pq.isEmpty()) {
				new_a[x][index++] = pq.peek().number;
				new_a[x][index++] = pq.peek().cnt;
				pq.poll();
				if (index == 100) {
					break;
				}
			}

			new_c = Math.max(new_c, index);
		}

		cur_c = new_c;
		a = new_a;

	}

	static void op_c() {
		int[][] new_a = new int[100][100];
		int new_r = 0;

		for (int y = 0; y < cur_c; y++) {
			HashSet<Integer> set = new HashSet<>();
			HashMap<Integer, Integer> map = new HashMap<>();
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			for (int x = 0; x < cur_r; x++) {
				if (a[x][y] != 0) {
					set.add(a[x][y]);
					map.put(a[x][y], map.getOrDefault(a[x][y], 0) + 1);
				}
			}

			Iterator<Integer> it = set.iterator();
			while (it.hasNext()) {
				int number = it.next();
				int cnt = map.get(number);
				pq.add(new Pair(number, cnt));
			}

			int index = 0;
			while (!pq.isEmpty()) {
				new_a[index++][y] = pq.peek().number;
				new_a[index++][y] = pq.peek().cnt;
				pq.poll();
				if (index == 100) {
					break;
				}
			}

			new_r = Math.max(new_r, index);
		}

		cur_r = new_r;
		a = new_a;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		a = new int[100][100];

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		cur_r = 3;
		cur_c = 3;

		int ans = 0;

		while (a[r - 1][c - 1] != k) {
			ans++;
			if (ans > 100) {
				ans = -1;
				break;
			}
			if (cur_r >= cur_c) {
				op_r();
			} else {
				op_c();
			}
		}

		System.out.print(ans);

	}

}
