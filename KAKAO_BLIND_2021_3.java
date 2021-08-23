import java.util.*;

class Solution {

	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static HashMap<String, Integer> map = new HashMap<>();
	static int[][][][] idx = new int[4][3][3][3];

	static int find_idx(ArrayList<Integer> arrList, int start, int end, int score) {
		if (start >= end) {
			return (start + end) / 2;
		}

		int mid = (start + end) / 2;

		if (arrList.get(mid) >= score) {
			return find_idx(arrList, start, mid - 1, score);
		} else {
			return find_idx(arrList, mid + 1, end, score);
		}
	}

	public int[] solution(String[] info, String[] query) {
		int[] answer = {};

		answer = new int[query.length];

		for (int i = 0; i < 108; i++) {
			ArrayList<Integer> list_temp = new ArrayList<>();
			list.add(list_temp);
		}

		int idx_val = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					for (int r = 0; r < 3; r++) {
						idx[i][j][k][r] = idx_val++;
					}
				}
			}
		}

		map.put("-", 0);
		map.put("cpp", 1);
		map.put("java", 2);
		map.put("python", 3);

		map.put("backend", 1);
		map.put("frontend", 2);

		map.put("junior", 1);
		map.put("senior", 2);

		map.put("chicken", 1);
		map.put("pizza", 2);

		for (int i = 0; i < info.length; i++) {
			StringTokenizer st = new StringTokenizer(info[i], " ");
			int lang = map.get(st.nextToken());
			int major = map.get(st.nextToken());
			int age = map.get(st.nextToken());
			int food = map.get(st.nextToken());
			int score = Integer.parseInt(st.nextToken());

			for (int first = 0; first < 2; first++) {
				for (int second = 0; second < 2; second++) {
					for (int third = 0; third < 2; third++) {
						for (int fourth = 0; fourth < 2; fourth++) {
							list.get(idx[lang * first][major * second][age * third][food * fourth]).add(score);
						}
					}
				}
			}
		}

		for (int i = 0; i < 108; i++) {
			Collections.sort(list.get(i));
		}

		for (int i = 0; i < query.length; i++) {
			StringTokenizer st = new StringTokenizer(query[i], " ");
			int lang = map.get(st.nextToken());
			st.nextToken();
			int major = map.get(st.nextToken());
			st.nextToken();
			int age = map.get(st.nextToken());
			st.nextToken();
			int food = map.get(st.nextToken());
			int score = Integer.parseInt(st.nextToken());

			ArrayList<Integer> arrList = list.get(idx[lang][major][age][food]);

			int rtn_idx = find_idx(arrList, 0, arrList.size() - 1, score);

			answer[i] = arrList.size() - rtn_idx;
			if (arrList.size() > 0 && arrList.get(rtn_idx) < score) {
				if (answer[i] > 0)
					answer[i]--;
			}

		}

		return answer;
	}
}