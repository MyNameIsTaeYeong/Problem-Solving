package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_16198 {
	static int n;
	static int ans = 0;
	static int[][] energes;
	static boolean[] check;
	
	
	static void solve(int sum, int cnt) {
		if(cnt == n-2) {
			if(sum > ans) {
				ans = sum;
			}
			return;
		}
		
		for(int i=1; i<n-1; i++) {
			if(check[i]) {
				continue;
			}
			
			check[i] = true;
			energes[energes[i][1]][2] = energes[i][2];
			energes[energes[i][2]][1] = energes[i][1];
			solve(sum + energes[energes[i][1]][0] * energes[energes[i][2]][0], cnt+1);
			check[i] = false;
			energes[energes[i][1]][2] = i;
			energes[energes[i][2]][1] = i;
			
		}
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		energes = new int[n][3];
		
		check = new boolean[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<n; i++) {
			energes[i][0] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<n-1; i++) {
			energes[i][1] = i-1;
			energes[i][2] = i+1;
		}
		
		
		solve(0, 0);
		
		System.out.println(ans);
		
	}

}
