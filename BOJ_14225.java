package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14225 {
	
	static boolean[] possible = new boolean[20000001];
	static int n;
	static int[] s;
	
	static void solve(int index, int cur) {
		possible[cur] = true;
		
		if(index == n) {
			return;
		}

		solve(index+1, cur);
		solve(index+1, cur + s[index]);
		
	}
	
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		s = new int[n];
		
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<n; i++) {
			s[i] = Integer.parseInt(st.nextToken());
		}
		
		solve(0, 0);
		
		for(int i=0; i<20000001; i++) {
			if(!possible[i]) {
				System.out.println(i);
				break;
			}
		}
		
	}

}
