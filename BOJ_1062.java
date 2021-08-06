package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1062 {
	
	static int n, k;
	static int[] words;
	static int alphabets = 0;
	static int ans = 0;
	
	static void count(int selected) {
		int cnt = 0;
		for(int i=0; i<n; i++) {
			if((words[i] & ((1<<26)- 1 - selected)) == 0) {
				cnt++;
			}
		}
		
		if(cnt > ans) {
			ans = cnt;
		}
		
	}
	
	static void solve(int index, int cnt, int selected) {
		if(cnt > k-5) {
			return;
		}
		
		if(index == 26) {
			count(selected);
			return;
		}
		
		if((alphabets & (1<<index)) == 0) {
			solve(index+1, cnt, selected);
		}else {
			selected |= (1<<index);
			solve(index+1, cnt+1, selected);
			selected &= ~(1<<index);
			solve(index+1, cnt, selected);
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		words = new int[n];
		
		
		for(int i=0; i<n; i++) {
			String word = br.readLine(); 
			
			for(int j=4; j<word.length()-4; j++) {
				char alphabet = word.charAt(j);
				if(	alphabet == 'a' 
					|| alphabet == 'n'
					|| alphabet == 't' 
					|| alphabet == 'i' 
					|| alphabet == 'c') {
					continue;
				}
				words[i] |= (1 << (alphabet - 'a'));
				alphabets |= (1 << (alphabet - 'a'));
			}
		}
		
		if((k-5) < 0) {
			System.out.println(0);
		}else{
			solve(0, 0, 0);
			System.out.println(ans);
		}
		
		 
	}

}
