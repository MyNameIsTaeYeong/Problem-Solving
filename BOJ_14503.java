package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14503 {
	
	static int n, m, r, c, d;
	static int[][] room;
	
	static int[] left_x = {0, -1, 0, 1};
	static int[] left_y = {-1, 0, 1, 0};
	
	static int[] back_x = {1, 0, -1, 0};
	static int[] back_y = {0, -1, 0, 1};
	
	static int ans = 0;
	
	static boolean clean_the_room() {
		
		// 현재위치 청소
		room[r][c] = 5;
		
		ans++;
		
		
		int cnt = 0;
		
		while(true) {
			if(room[r+left_x[d]][c+left_y[d]] == 0) {
				r = r+left_x[d];
				c = c+left_y[d];
				
				d--;
				if(d < 0) {
					d = 3;
				}
				
				break;
			}
			else {
				
				if(cnt == 4) {
					if(room[r+back_x[d]][c+back_y[d]] == 1) {
						return false;
					}else {
						r = r+back_x[d];
						c = c+back_y[d];
						cnt = 0;
						continue;
					}
				}
				
				d--;
				if(d < 0) {
					d = 3;
				}
			}
			
			
			cnt++;
		}
		
		
		
		
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		room = new int[n][m];
		
		st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		while(clean_the_room());
		
		System.out.println(ans);

	}

}
