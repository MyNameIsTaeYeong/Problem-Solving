package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2580 {
	static boolean[][] row_check = new boolean[9][10];
	static boolean[][] col_check = new boolean[9][10];
	static boolean[][] square_check = new boolean[9][10];
	static int[][] board = new int[9][9];
	
	static boolean solve(int index) {
		if(index == 81) {
			return true;
		}
		
		
		
		int row = index / 9;
		int col = index % 9;
		
		if(board[row][col] != 0) {
			if(solve(index+1)) {
				return true;
			}
		}else {
			for(int i=1; i<10; i++) {
				if(row_check[row][i] == false && 
					col_check[col][i] == false && 
					square_check[(row/3)*3 + (col/3)][i] == false) {
					
					row_check[row][i] = true;
					col_check[col][i] = true;
					square_check[(row/3)*3 + (col/3)][i] = true;
					board[row][col] = i;
					
					if(solve(index+1)) {
						return true;
					}
					board[row][col] = 0;
					row_check[row][i] = false;
					col_check[col][i] = false;
					square_check[(row/3)*3 + (col/3)][i] = false;
					
				}
			}
		}
		
		
		
		
		
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i=0; i<9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				row_check[i][board[i][j]] = true;
				col_check[j][board[i][j]] = true;
				square_check[(i/3)*3 + (j/3)][board[i][j]] = true;
			}
		}
		
		solve(0);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		
	}

}
