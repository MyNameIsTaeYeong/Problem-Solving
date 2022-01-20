import java.util.*;

class MyComparator implements Comparator<String> {
	@Override
	public int compare(String a, String b) {
		if (a.length() > b.length()) {
			return 1;
		} else if (a.length() == b.length()) {
			return 0;
		} else {
			return -1;
		}
	}
}

class Solution {

	static String[][] table;
	static int row, col;
	static ArrayList<String> super_keys = new ArrayList<>();
	static int answer = 0;

	static void uniqueness_check(int combination, int n) {
		ArrayList<Integer> key = new ArrayList<>();
		StringBuilder str_key = new StringBuilder();

		for (int i = 0; i < n; i++) {
			if ((combination & (1 << i)) != 0) {
				key.add(i);
				str_key.append(i);
			}
		}

		HashSet<String> set = new HashSet<>();

		for (int i = 0; i < row; i++) {
			StringBuilder sb = new StringBuilder("");
			for (int j = 0; j < key.size(); j++) {
				sb.append(table[i][key.get(j)]);
			}
			set.add(sb.toString());
		}

		if (set.size() == row) {
			super_keys.add(str_key.toString());
		}

	}

	static boolean contain_check(String super_key, String test_key) {
		for (int i = 0; i < super_key.length(); i++) {
			if (!test_key.contains(super_key.subSequence(i, i + 1))) {
				return false;
			}
		}
		return true;
	}

	static void minimality_check() {
		for (int i = 0; i < super_keys.size(); i++) {
			String super_key = super_keys.get(i);
			if (super_key.equals("")) {
				continue;
			}

			answer++;

			for (int j = i + 1; j < super_keys.size(); j++) {
				String test_key = super_keys.get(j);

				if (contain_check(super_key, test_key)) {
					super_keys.set(j, "");
				}
			}

		}
	}

	static void make_combinations(int n) {
		for (int i = 1; i < (1 << n); i++) {
			uniqueness_check(i, n);
		}
		Collections.sort(super_keys, new MyComparator());
		minimality_check();
	}

	public int solution(String[][] relation) {

		row = relation.length;
		col = relation[0].length;

		table = new String[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				table[i][j] = relation[i][j];
			}
		}

		make_combinations(col);

		return answer;
	}
}