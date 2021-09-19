package problem_solving_java;

// 3개의 조합에 대하여 연산을 수행한다.
// 연산을 수행한 조합이 이전 조합과 같을 경우 큐에 넣지않는다. 다르다면 큐에 넣는다.
// 큐가 빈다면 -> 0, 그전에 찾으면 -> 1

import java.util.*;
import java.io.*;

public class BOJ_12886 {

	static ArrayList<Integer> make_next_list(ArrayList<Integer> list, int a, int b, int c) {
		if (!list.get(a).equals(list.get(b))) {
			if (list.get(a) < list.get(b)) {
				ArrayList<Integer> next_list = new ArrayList<>();
				next_list.add(list.get(a) + list.get(a));
				next_list.add(list.get(b) - list.get(a));
				next_list.add(list.get(c));
				Collections.sort(next_list);
				return next_list;
			} else {
				ArrayList<Integer> next_list = new ArrayList<>();
				next_list.add(list.get(a) - list.get(b));
				next_list.add(list.get(b) + list.get(b));
				next_list.add(list.get(c));
				Collections.sort(next_list);
				return next_list;
			}
		}

		return null;

	}

	static int bfs(ArrayList<Integer> input) {
		Queue<ArrayList<Integer>> q = new LinkedList<>();
		Collections.sort(input);
		HashMap<String, Boolean> check = new HashMap<>();
		check.put(input.get(0).toString() + "," + input.get(1).toString() + "," + input.get(2).toString(), true);
		q.add(input);

		while (!q.isEmpty()) {
			ArrayList<Integer> list = q.poll();

			// 같은지 체크하기
			if (list.get(0).equals(list.get(1)) && list.get(1).equals(list.get(2)) && list.get(2).equals(list.get(0))) {
				return 1;
			}

			ArrayList<Integer> next1 = make_next_list(list, 0, 1, 2);
			if (next1 != null) {
				if (check.get(next1.get(0).toString() + "," + next1.get(1).toString() + ","
						+ next1.get(2).toString()) == null) {
					check.put(next1.get(0).toString() + "," + next1.get(1).toString() + "," + next1.get(2).toString(),
							true);
					q.add(next1);
				}
			}
			ArrayList<Integer> next2 = make_next_list(list, 1, 2, 0);
			if (next2 != null) {
				if (check.get(next2.get(0).toString() + "," + next2.get(1).toString() + ","
						+ next2.get(2).toString()) == null) {
					check.put(next2.get(0).toString() + "," + next2.get(1).toString() + "," + next2.get(2).toString(),
							true);
					q.add(next2);
				}
			}
			ArrayList<Integer> next3 = make_next_list(list, 2, 0, 1);
			if (next3 != null) {
				if (check.get(next3.get(0).toString() + "," + next3.get(1).toString() + ","
						+ next3.get(2).toString()) == null) {
					check.put(next3.get(0).toString() + "," + next3.get(1).toString() + "," + next3.get(2).toString(),
							true);
					q.add(next3);
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int a, b, c;

		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		ArrayList<Integer> input = new ArrayList<>();
		input.add(a);
		input.add(b);
		input.add(c);

		System.out.print(bfs(input));
	}

}
