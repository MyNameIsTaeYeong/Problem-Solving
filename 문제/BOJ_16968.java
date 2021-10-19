package problem_solving_java;

import java.io.*;

public class BOJ_16968 {

	static boolean check(StringBuilder number) {
		for (int i = 0; i < number.length() - 1; i++) {
			if (number.charAt(i) == number.charAt(i + 1)) {
				return false;
			}
		}

		return true;
	}

	static int go(String format, int index, StringBuilder number) {

		int rtn = 0;

		if (index == format.length()) {
			// check
			if (check(number)) {
				return 1;
			} else {
				return 0;
			}

		}

		if (format.charAt(index) == 'd') {
			for (int i = 0; i < 10; i++) {
				rtn += go(format, index + 1, number.append((char) ('0' + i)));
				number.deleteCharAt(index);
			}
		}
		// 'c'
		else {
			for (int i = 0; i < 26; i++) {
				rtn += go(format, index + 1, number.append((char) ('a' + i)));
				number.deleteCharAt(index);
			}
		}

		return rtn;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String format = br.readLine();

		System.out.print(go(format, 0, new StringBuilder("")));

	}

}
