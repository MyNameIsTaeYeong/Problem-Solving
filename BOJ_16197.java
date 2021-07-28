package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_16197 {
	static int n, m;
	static String[] board;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int ans = 1000000;
	
	
	static void solve(int cnt, int coin1_x, int coin1_y, int coin2_x, int coin2_y) {
		
		if(cnt >= ans) {
			return;
		}
		if(cnt > 10) {
			ans = 100;
			return;
		}
		
		for(int i=0; i<4; i++) {
			if((coin1_x + dx[i] < 0 || coin1_x + dx[i] >= n || coin1_y+dy[i] < 0 || coin1_y+dy[i] >= m) && (coin2_x + dx[i] < 0 || coin2_x + dx[i] >= n || coin2_y+dy[i] < 0 || coin2_y+dy[i] >= m)) {
				continue;
			}else if(coin1_x + dx[i] < 0 || coin1_x + dx[i] >= n || coin1_y+dy[i] < 0 || coin1_y+dy[i] >= m) {
				if(cnt+1 < ans) {
					ans = cnt+1;
				}
				return;
			}else if(coin2_x + dx[i] < 0 || coin2_x + dx[i] >= n || coin2_y+dy[i] < 0 || coin2_y+dy[i] >= m){
				if(cnt+1 < ans) {
					ans = cnt+1;
				}
				return;
			}
			else{
				if(board[coin1_x + dx[i]].charAt(coin1_y+dy[i]) == '#' && board[coin2_x + dx[i]].charAt(coin2_y+dy[i]) == '#') {
					continue;
				}else if(board[coin1_x + dx[i]].charAt(coin1_y+dy[i]) == '#') {
					solve(cnt+1, coin1_x, coin1_y, coin2_x+dx[i], coin2_y+dy[i]);
				}else if(board[coin2_x + dx[i]].charAt(coin2_y+dy[i]) == '#') {
					solve(cnt+1, coin1_x+dx[i], coin1_y+dy[i], coin2_x, coin2_y);
				}else {
					solve(cnt+1, coin1_x+dx[i], coin1_y+dy[i], coin2_x+dx[i], coin2_y+dy[i]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new String[n];
		
		for(int i=0; i<n; i++) {
			board[i] = br.readLine();
		}
		
		boolean coin1_check = false;
		
		int coin1_x = 0, coin1_y = 0, coin2_x = 0, coin2_y = 0;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(board[i].charAt(j) == 'o') {
					if(coin1_check) {
						coin2_x = i;
						coin2_y = j;
					}else {
						coin1_x = i;
						coin1_y = j;
						coin1_check = true;
					}
				}
			}
		}
		
		solve(0, coin1_x, coin1_y, coin2_x, coin2_y);
		
		if(ans > 10) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
		
	}

}
