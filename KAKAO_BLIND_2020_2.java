import java.util.*;

class Solution {

	static boolean check(String p) {

		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(') {
				stack.push(p.charAt(i));
			} else {
				if (stack.empty()) {
					return false;
				}
				stack.pop();
			}

		}

		return true;
	}

	static String u_v_split(String p) {
		StringBuilder rtn = new StringBuilder("");

		if (p.equals("")) {
			return rtn.toString();
		}

		StringBuilder u = new StringBuilder("");
		String v = new String();

		int pair_cnt = 0;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '(') {
				pair_cnt++;
			} else {
				pair_cnt--;
			}

			u.append(p.charAt(i));

			if (pair_cnt == 0) {
				v = p.substring(i + 1, p.length());
				break;
			}
		}

		if (check(u.toString())) {
			rtn.append(u.toString() + u_v_split(v));

		} else {
			rtn.append("(");
			rtn.append(u_v_split(v));
			rtn.append(")");
			u.deleteCharAt(u.length() - 1);
			u.deleteCharAt(0);
			for (int i = 0; i < u.length(); i++) {
				if (u.charAt(i) == '(') {
					rtn.append(")");
				} else {
					rtn.append("(");
				}
			}
		}

		return rtn.toString();
	}

	public String solution(String p) {
		String answer = "";

		if (check(p)) {
			return p;
		} else {
			answer = u_v_split(p);
		}

		return answer;
	}
}