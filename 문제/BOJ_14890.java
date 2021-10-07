package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_14890 {
	
	static int n, l;
	static int[][] map;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
		
		
		// 가로 검사
		for(int i=0; i<n; i++) {
			
			// 경사로 유무 변
			int[] check = new int[n];
			
			for(int j=0; j<(n-1); j++) {
				if(map[i][j] == map[i][j+1]) {
					if(j == (n-2)) {
						ans++;
					}
					continue;
				}
				// 오르막 
				else if((map[i][j+1] - map[i][j]) == 1) {
					if((j-l) < -1) {
						break;
					}
					
					int cur_height = map[i][j];
					boolean heigt_check = false;
					
					if(check[j] == 1) {
						break;
					}
					
					for(int k=1; k<l; k++) {
						if(map[i][j-k] != cur_height || check[j-k] == 1) {
							heigt_check = true;
							break;
						}
					}
					
					if(heigt_check) {
						break;
					}
					
					// 경사로 가능
					for(int k=0; k<l; k++) {
						check[j-k] = 1;
					}
				}
				// 내리막 
				else if((map[i][j] - map[i][j+1]) == 1) {
					if((j+l) >= n) {
						break;
					}
					
					int cur_height = map[i][j];
					boolean heigt_check = false;
					
					for(int k=1; k<=l; k++) {
						if(map[i][j+k] != (cur_height-1) || check[j+k] == 1) {
							heigt_check = true;
							break;
						}
					}
					
					if(heigt_check) {
						break;
					}
					
					
					// 경사로 가능
					for(int k=1; k<=l; k++) {
						check[j+k] = 1;
					}
					
				}
				else {
					break;
				}
				
				if(j == (n-2)) {
					ans++;
				}
				
				
			}
		}
		
		// 세로 검사 
		for(int i=0; i<n; i++) {
			// 경사로 유무 변
			int[] check = new int[n];

			for (int j = 0; j < (n - 1); j++) {
				if(map[j][i] == map[j+1][i]) {
					if(j == (n-2)) {
						ans++;
					}
					continue;
				}
				// 오르막 
				else if((map[j+1][i] - map[j][i]) == 1) {
					if((j-l) < -1) {
						break;
					}
					
					int cur_height = map[j][i];
					boolean heigt_check = false;
					
					if(check[j] == 1) {
						break;
					}
					
					for(int k=1; k<l; k++) {
						if(map[j-k][i] != cur_height || check[j-k] == 1) {
							heigt_check = true;
							break;
						}
					}
					
					if(heigt_check) {
						break;
					}
					
					// 경사로 가능
					for(int k=0; k<l; k++) {
						check[j-k] = 1;
					}
				}
				
				// 내리막 
				else if((map[j][i] - map[j+1][i]) == 1) {
					if((j+l) >= n) {
						break;
					}
					
					int cur_height = map[j][i];
					boolean heigt_check = false;
					
					for(int k=1; k<=l; k++) {
						if(map[j+k][i] != (cur_height - 1) || check[j+k] == 1) {
							heigt_check = true;
							break;
						}
					}
					
					if(heigt_check) {
						break;
					}
					
					
					// 경사로 가능
					for(int k=1; k<=l; k++) {
						check[j+k] = 1;
					}
					
				}
				else {
					break;
				}
				
				if(j == (n-2)) {
					ans++;
				}
				
				

			}
		}
		
		System.out.println(ans);
		
		
	}

}
