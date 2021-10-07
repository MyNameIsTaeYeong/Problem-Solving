package problem_solving_java;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 	1. n, m 중 작은 값의 반이 층의 개수.
 	2. 최외각층부터 이동.
 	3. 이동이 끝나면, 시계 반대 방향으로 대입.
 	4. 모든 층에 대해 반복.
 	
 */

public class BOJ_16926 {
	
	static int n, m, r;
	static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ArrayList<Integer> input = new ArrayList<>();
			ArrayList<Integer> input2 = new ArrayList<>();
			while(st.hasMoreTokens()) {
				input.add(Integer.parseInt(st.nextToken()));
				input2.add(0);
			}
			arr.add(input);
			ans.add(input2);
		}
		
		int layer_cnt = Math.min(n, m) / 2;
		
		for(int i=0; i<layer_cnt; i++) {
			int start_x = i;
			int start_y = i;
			
			for(int j=0; j<r; j++) {
				
				if(start_y == i && start_x < (n-i-1)) {
					start_x++;
				}
				else if(start_x == (n-i-1) && start_y < (m-i-1)) {
					start_y++;
				}
				else if(start_y == (m-i-1) && start_x > i) {
					start_x--;
				}else if(start_x == i && start_y > i) {
					start_y--;
				}
				
			}
			
			int x = i;
			int y = i;
			
			int cnt = 2*(m-2*i) + 2*(n-2*i) - 4;
			
			for(int j=0; j<cnt; j++) {
				
				ans.get(start_x).set(start_y, arr.get(x).get(y));
				
				// 왼쪽 
				if(start_y == i && start_x > i) {
					start_x--;
				}
				// 아래  
				else if(start_x == i && start_y < (m-i-1)) {
					start_y++;
				}
				// 오른쪽  
				else if(start_y == (m-i-1) && start_x < (n-i-1)) {
					start_x++;
				}
				// 위  
				else if(start_x == (n-i-1) && start_y > i) {
					start_y--;
				}
				
				if(x == i && y<(m-i-1)) {
					y++;
				}
				else if(y == (m-i-1) && x < (n-i-1)) {
					x++;
				}
				else if(x == (n-i-1) && y>i) {
					y--;
				}
				else if(y == i && x>i) {
					x--;
				}
				
			}
			
			
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(ans.get(i).get(j)+" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		
	}

}
