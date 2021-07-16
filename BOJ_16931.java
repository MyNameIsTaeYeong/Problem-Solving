package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_16931 {
	
	static int n, m;
	static int[][] shape;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		shape = new int[n][m];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<m; j++) {
				shape[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = 2 * n * m;
		
		//위에서 
		for(int j=0; j<m; j++) {
			
			int prev = shape[0][j];
			ans += shape[0][j];
			
			for(int i=1; i<n; i++) {
				if(shape[i][j] > prev) {
					ans += (shape[i][j] - prev);
				}
				prev = shape[i][j];
			}
		}
			
		// 아래에서 
		
		for(int j=0; j<m; j++) {
			int prev = shape[n-1][j];
			ans += shape[n-1][j];
			
			for(int i=(n-2); i>=0; i--) {
				if(shape[i][j] > prev) {
					ans += (shape[i][j] - prev);
				}
				prev = shape[i][j];
			}
		}
		// 왼쪽에서
		
		for(int i=0; i<n; i++) {
			int prev = shape[i][0];
			ans += shape[i][0];
			
			for(int j=0; j<m; j++) {
				if(shape[i][j] > prev) {
					ans += (shape[i][j] - prev);
				}
				prev = shape[i][j];
			}
		}
			
		// 오른쪽에서 
		
		for(int i=0; i<n; i++) {
			int prev = shape[i][m-1];
			ans += shape[i][m-1];
			
			for(int j=(m-2); j>=0; j--) {
				if(shape[i][j] > prev) {
					ans += (shape[i][j] - prev);
				}
				prev = shape[i][j];
			}
		}
		
		System.out.println(ans);
	}

}
