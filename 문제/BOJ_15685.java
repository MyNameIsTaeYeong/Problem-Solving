package problem_solving_java;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_15685 {
	
	static int n;
	static int[][] grid = new int[101][101];
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	
 	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x, y, d, g;
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> dragon_curve = new ArrayList<>();
			dragon_curve.add(d);
			for(int j=0; j<g; j++) {
				for(int k=(dragon_curve.size()-1); k>=0; k--) {
					if(dragon_curve.get(k) == 3) {
						dragon_curve.add(0);
					}else {
						dragon_curve.add(dragon_curve.get(k) + 1);
					}
				}
			}
			
			grid[x][y]++;
			
			for(int j=0; j<dragon_curve.size(); j++) {
				int direction = dragon_curve.get(j);
				grid[x+dx[direction]][y+dy[direction]]++;
				x = x+dx[direction];
				y = y+dy[direction];
			}
			
		}
		int ans = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(grid[i][j] != 0 && grid[i+1][j] != 0 && grid[i][j+1] != 0 && grid[i+1][j+1] != 0) {
					ans++;
				}
			}
		}
		
		System.out.println(ans);
		
	}

}
