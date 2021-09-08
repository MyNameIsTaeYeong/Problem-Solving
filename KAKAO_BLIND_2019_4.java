import java.util.*;

class Pair implements Comparable<Pair> {
	public int index;
	public int value;

	public Pair(int index, int value) {
		this.index = index;
		this.value = value;
	}

	@Override
	public int compareTo(Pair p) {
		return this.value - p.value;
	}
}

class Solution {

	static int one_line_eat(ArrayList<Pair> list, long remain, int prev_height) {
		ArrayList<Integer> indexes = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			indexes.add(list.get(i).index);
		}

		Collections.sort(indexes);

		long result = remain % indexes.size();

		return indexes.get((int) result);
	}

	public int solution(int[] food_times, long k) {
		int answer = 0;

		ArrayList<Pair> list = new ArrayList<>();
		for (int i = 0; i < food_times.length; i++) {
			list.add(new Pair(i + 1, food_times[i]));
		}

		Collections.sort(list);

		long done = 0;
		int prev_height = 0;
		while (true) {
			if (list.size() == 0) {
				answer = -1;
				break;
			}
			long height = list.get(0).value - prev_height;
			long will_eat = height * list.size();
			if (done + will_eat > k) {
				answer = one_line_eat(list, k - done, prev_height);
				break;
			} else {
				done += will_eat;
				prev_height = list.get(0).value;
				list.remove(0);
				while (list.size() > 0 && list.get(0).value == prev_height) {
					list.remove(0);
				}

			}

		}

		return answer;
	}
}