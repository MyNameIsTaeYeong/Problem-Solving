package problem_solving_java;

import java.io.*;
import java.util.ArrayList;

public class BOJ_1339 {

	static int n;
	static String[] words; 
	static boolean[] check;
	static ArrayList<Character> alphabets = new ArrayList<>();
	static int[] permutation;
	static int ans = 0;
	
	
	static void cal() {
		int sum = 0;
		for(int i=0; i<n; i++) {
			int val = 0;
			for(int j=0; j<words[i].length(); j++) {
				val *= 10;
				val += permutation[alphabets.indexOf(words[i].charAt(j))];
			}
			sum += val;
		}
		if(sum > ans) {
			ans = sum;
		}
	}
	
	static void solve(int index) {
		if(index == alphabets.size()) {
			cal();
			return;
		}
		
		
		for(int i=0; i<10; i++) {
			if(check[i]) {
				continue;
			}
			
			check[i] = true;
			permutation[index] = i;
			solve(index+1);
			check[i] = false;
		}
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		words = new String[n];
		check = new boolean[10];
		
		
		for(int i=0; i<n; i++) {
			words[i] = br.readLine();
			for(int j=0; j<words[i].length(); j++) {
				if(!alphabets.contains(words[i].charAt(j))) {
					alphabets.add(words[i].charAt(j));
				}
			}
		}
		
		permutation = new int[alphabets.size()];
		
		solve(0);
		
		System.out.println(ans);
	}

}
