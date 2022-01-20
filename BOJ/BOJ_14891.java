package problem_solving_java;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_14891 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] gear = new String[4];
		gear[0] = br.readLine();
		gear[1] = br.readLine();
		gear[2] = br.readLine();
		gear[3] = br.readLine();
		
		int k = Integer.parseInt(br.readLine());
		
		int[] gear_index = new int[4];
		gear_index[0] = 0;
		gear_index[1] = 0;
		gear_index[2] = 0;
		gear_index[3] = 0;
		
		for(int i=0; i<k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int gear_num = Integer.parseInt(st.nextToken());
			
			int[] direction = new int[4];
			direction[gear_num-1] =  Integer.parseInt(st.nextToken());
			
			
			// 인접한 톱니바퀴 중 돌아갈 톱니바퀴의 번호를 찾는다.
			
			ArrayList<Integer> will_rotate = new ArrayList<>();
			
			
			// 왼쪽 
			for(int j=gear_num-2; j>=0; j--) {
				if(gear[j].charAt((gear_index[j]+2 > 7) ? (gear_index[j]-6) : (gear_index[j]+2)) == gear[j+1].charAt((gear_index[j+1]+6)>7 ? (gear_index[j+1]-2) : (gear_index[j+1]+6) )) {
					break;
				}else {
					will_rotate.add(j);
					if(direction[j+1] == 1) {
						direction[j] = -1;
					}else {
						direction[j] = 1;
					}
				}
			}
			
			// 오른쪽 
			for(int j=gear_num; j<4; j++) {
				if(gear[j-1].charAt((gear_index[j-1]+2) > 7 ? (gear_index[j-1]-6) : (gear_index[j-1]+2)) == gear[j].charAt((gear_index[j]+6)>7 ? (gear_index[j]-2) : (gear_index[j]+6))) {
					break;
				}else {
					will_rotate.add(j);
					if(direction[j-1] == 1) {
						direction[j] = -1;
					}else {
						direction[j] = 1;
					}
				}
			}
			
			// 해당 톱니바퀴를 돌린다.
			if(direction[gear_num-1] == 1) {
				if(gear_index[gear_num-1] == 0) {
					gear_index[gear_num-1] = 7;
				}else {
					gear_index[gear_num-1]--;
				}
			}else {
				if(gear_index[gear_num-1] == 7) {
					gear_index[gear_num-1] = 0;
				}else {
					gear_index[gear_num-1]++;
				}
			}
			
			// 인접한 톱니바퀴를 돌린다.
			for(int j=0; j<will_rotate.size(); j++) {
				if(direction[will_rotate.get(j)] == 1) {
					if(gear_index[will_rotate.get(j)] == 0) {
						gear_index[will_rotate.get(j)] = 7;
					}else {
						gear_index[will_rotate.get(j)]--;
					}
				}
				else {
					if(gear_index[will_rotate.get(j)] == 7) {
						gear_index[will_rotate.get(j)] = 0;
					}else {
						gear_index[will_rotate.get(j)]++;
					}
				}
			}
			
		}
		
		int ans = 0;
		for(int i=0; i<4; i++) {
			if(gear[i].charAt(gear_index[i]) == '1') {
				switch(i) {
					case 0:
						ans += 1;
						break;
					case 1:
						ans += 2;
						break;
					case 2:
						ans += 4;
						break;
					case 3:
						ans += 8;
						break;
				}
			}
		}
		
		System.out.println(ans);
		
	}

}
