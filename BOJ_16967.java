package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_16967 {
	static int h, w, x, y;
	static int[][] b;
	static int[][] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		
		b = new int[h+x][w+y];
		a = new int[h][w];
		
		for(int i=0; i<(h+x); i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<(w+y); j++) {
				b[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<h; i++) {
			if(i<x) {
				for(int j=0; j<w; j++) {
					a[i][j] = b[i][j];
				}
			}else {
				for(int j=0; j<w; j++) {
					if(j<y) {
						a[i][j] = b[i][j];
					}else {
						a[i][j] = b[i][j] - a[i-x][j-y];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				sb.append(a[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

}
