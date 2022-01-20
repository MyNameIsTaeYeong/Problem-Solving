package problem_solving_java;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14499 {

	static int n, m, x, y, k;
	static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
	static ArrayList<Integer> command = new ArrayList<>();
	static int[][] dice = new int[4][3];
	static int top_x, top_y, bottom_x, bottom_y;
	
	// x,y 좌표 먼저 이동
	// 주사위 좌표 이동
	// map에서 해당 값 확인
	
	
	static void op() {
		if(map.get(x).get(y) == 0) {
			map.get(x).set(y, dice[bottom_x][bottom_y]);
		}else {
			dice[bottom_x][bottom_y] = map.get(x).get(y);
			map.get(x).set(y, 0);
		}
		
		System.out.println(dice[top_x][top_y]);
	}
	
	//동 
	static void cmd1() {
		if((y+1) < m) {
			y++;
			
			int temp = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = temp;
			
			op();
			
		}else {
			return;
		}
	}
	
	//서 
	static void cmd2() {
		if((y-1) >= 0) {
			y--;
			
			int temp = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = temp;
		
			op();
			
		}else {
			return;
		}
	}
	
	
	//북 
	static void cmd3() {
		if((x-1) >= 0) {
			x--;
			
			int temp = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = dice[3][1];
			dice[3][1] = temp;
			
			op();
			
		}else {
			return;
		}
	}
	
	//남 
	static void cmd4() {
		if((x+1) < n) {
			x++;
			
			int temp = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = temp;
			
			op();
			
		}else {
			return;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ArrayList<Integer> input = new ArrayList<>();
			for(int j=0; j<m; j++) {
				input.add(Integer.parseInt(st.nextToken()));
			}
			map.add(input);
		}
		
		
		st = new StringTokenizer(br.readLine(), " ");
		
		for(int i=0; i<k; i++) {
			command.add(Integer.parseInt(st.nextToken()));
		}
		
		top_x = 1;
		top_y = 1;
		bottom_x = 3;
		bottom_y = 1;
		
		for(int i=0; i<k; i++) {
			switch(command.get(i)) {
				case 1:
					cmd1();
					break;
				case 2:
					cmd2();
					break;
				case 3:
					cmd3();
					break;
				case 4:
					cmd4();
					break;
			}
		}
		
	}

}
