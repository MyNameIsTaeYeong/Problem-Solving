package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_16929 {

	static int n, m;
	static char[][] board;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static boolean dfs(int x, int y, boolean[][] check, int node_cnt, int dest_x, int dest_y, char ch) {
		if(x == dest_x && y == dest_y) {
			if(node_cnt >= 4) {
				return true;
			}
			
			if(node_cnt >= 2) {
				return false;
			}
		}
		
		
		
		for(int i=0; i<4; i++) {
			int next_x = x+dx[i];
			int next_y = y+dy[i];
			if(next_x >=0 && next_x < n && next_y >=0 && next_y < m) {
				if(!check[next_x][next_y] && board[next_x][next_y] == ch) {
					check[next_x][next_y] = true;
					if(dfs(next_x, next_y, check, node_cnt+1, dest_x, dest_y, ch)) {
						return true;
					}
					check[next_x][next_y] = false;
				}
			}
		}
		
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new char[n][m];

		for (int i = 0; i < n; i++) {
			String input = br.readLine();
			for (int j = 0; j < m; j++) {
				board[i][j] = input.charAt(j);
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(dfs(i, j, new boolean[n][m], 1, i, j, board[i][j])) {
					System.out.print("Yes");
					return;
				}
			}
		}
		System.out.print("No");
		

	}

}
