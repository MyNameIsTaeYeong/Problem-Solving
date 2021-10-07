package problem_solving_java;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14888 {
	static int n;
	static int[] a;
	static ArrayList<Character> operators = new ArrayList<>();
	static boolean[] check;
	static int max_val = -2000000000;
	static int min_val = 2000000000;
	

	
	static void solve(int index, int val) {
		
		if(index == (n-1)) {
			if(val > max_val) {
				max_val = val;
			}
			if(val < min_val) {
				min_val = val;
			}
			return;
		}
		
		for(int i=0; i<operators.size(); i++) {
			if(check[i]) {
				continue;
			}
			
			check[i] = true;
			
			if(operators.get(i) == '/' && val < 0) {
				solve(index+1, -(-val / a[index+1]));
			}else {
				switch(operators.get(i)) {
				case '+':
					solve(index+1, val + a[index+1]);
					
					break;
				case '-':
					solve(index+1, val - a[index+1]);
					break;
				case '*':
					solve(index+1, val * a[index+1]);
					break;
				case '/':
					solve(index+1, val / a[index+1]);
					break;
				}
			}
			
			
			check[i] = false;
			
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		a = new int[n];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < 4; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < cnt; j++) {
				switch (i) {
				case 0:
					operators.add('+');
					break;
				case 1:
					operators.add('-');
					break;
				case 2:
					operators.add('*');
					break;
				case 3:
					operators.add('/');
					break;
				}
			}
		}
		
		check = new boolean[operators.size()];
		
		solve(0, a[0]);
		
		System.out.println(max_val);
		System.out.println(min_val);
	}

}
