package problem_solving_java;

import java.io.*;
import java.util.*;



public class BOJ_16935 {
	
	static ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> operation = new ArrayList<>();
	static int n, m, r;
	
	static void op1() {
		int cnt = n/2;
		for(int i=0; i<cnt; i++) {
			Collections.swap(arr, i, n-i-1);
		}
	}	
	static void op2() {
		int cnt = m/2;
		for(int i=0; i<n; i++) {
			for(int j=0; j<cnt; j++) {
				Collections.swap(arr.get(i), j, m-j-1);
			}
		}
	}
	static void op3() {
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i<m; i++) {
			ArrayList<Integer> temp_row = new ArrayList<>();
			for(int j=n-1; j>=0; j--) {
				temp_row.add(arr.get(j).get(i));
			}
			temp.add(temp_row);
		}
		arr = temp;
		
		int temp2 = m;
		m = n;
		n = temp2;
	}
	static void op4() {
		ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
		
		for(int i=m-1; i>=0; i--) {
			ArrayList<Integer> temp_row = new ArrayList<>();
			for(int j=0; j<n; j++) {
				temp_row.add(arr.get(j).get(i));
			}
			temp.add(temp_row);
		}
		arr = temp;
		
		int temp2 = m;
		m = n;
		n = temp2;
	}
	static void op5() {
		int half_n = n/2;
		int half_m = m/2;
		
		for(int i=0; i<half_n; i++) {
			for(int j=0; j<half_m; j++) {
				Collections.swap(arr.get(i), j, j+half_m);
			}
		}
		
		for(int i=0; i<half_n; i++) {
			for(int j=0; j<half_m; j++) {
				int temp = arr.get(i).get(j);
				arr.get(i).set(j, arr.get(i+half_n).get(j));
				arr.get(i+half_n).set(j, temp);
			}
		}
		
		
		for(int i=half_n; i<n; i++) {
			for(int j=0; j<half_m; j++) {
				Collections.swap(arr.get(i), j, j+half_m);
			}
		}
		
	}
	static void op6() {
		int half_n = n/2;
		int half_m = m/2;
		
		for(int i=0; i<half_n; i++) {
			for(int j=0; j<half_m; j++) {
				Collections.swap(arr.get(i), j, j+half_m);
			}
		}
		
		for(int i=0; i<half_n; i++) {
			for(int j=half_m; j<m; j++) {
				int temp = arr.get(i).get(j);
				arr.get(i).set(j, arr.get(i+half_n).get(j));
				arr.get(i+half_n).set(j, temp);
			}
		}
		
		
		for(int i=half_n; i<n; i++) {
			for(int j=0; j<half_m; j++) {
				Collections.swap(arr.get(i), j, j+half_m);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			ArrayList<Integer> temp = new ArrayList<>();
			while(st.hasMoreTokens()) {
				temp.add(Integer.parseInt(st.nextToken()));
			}
			arr.add(temp);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		
		while(st.hasMoreTokens()) {
			operation.add(Integer.parseInt(st.nextToken()));
		}
		
		for(int i=0; i<operation.size(); i++) {
			switch(operation.get(i)) {
				case 1:
					op1();
					break;
				case 2:
					op2();
					break;
				case 3:
					op3();
					break;
				case 4:
					op4();
					break;
				case 5:
					op5();
					break;
				case 6:
					op6();
					break;
			}
		}
		
		
		for(int i=0; i<arr.size(); i++) {
			for(int j=0; j<arr.get(i).size(); j++) {
				System.out.print(arr.get(i).get(j) + " ");
			}
			System.out.println();
		}
		
	}

}

