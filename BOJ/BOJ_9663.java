package problem_solving_java;

import java.io.*;

public class BOJ_9663 {
	static int n;
	static int ans = 0;
	static boolean[][] board;
	static boolean[] col_check;
	
	// row + col이 같으면 같은 대각선 '/' 모양 
	static boolean[] rdig_check;
	// n - col + row - 1이 같으면 같은 대각선 '\' 모양 
	static boolean[] ldig_check;
	
	
	
	static boolean check(int row, int col) {
		if(col_check[col]) {
			return false;
		}
		if(rdig_check[row+col]) {
			return false;
		}
		if(ldig_check[n-col+row-1]) {
			return false;
		}
		
		
		return true;
	}

	static void solve(int row) {
		if (row == n) {
			ans++;
			return;
		}
		
		for(int col=0; col<n; col++) {
			if(check(row, col)) {
				board[row][col] = true;
				col_check[col] = true;
				rdig_check[row+col] = true;
				ldig_check[n-col+row-1] = true;
				solve(row+1);
				board[row][col] = false;
				col_check[col] = false;
				rdig_check[row+col] = false;
				ldig_check[n-col+row-1] = false;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		board = new boolean[n][n];
		col_check = new boolean[n];
		rdig_check = new boolean[2*n];
		ldig_check = new boolean[2*n];
		
		solve(0);

		System.out.println(ans);
	}

}
