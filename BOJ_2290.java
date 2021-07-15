package problem_solving_java;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2290 {

	static int s;
	static String n;
	static char[][][] number = {
			{
				// 0 0 0 0
				{' ', '-', ' '},
				{'|', ' ', '|'},
				{' ', ' ', ' '},
				{'|', ' ', '|'},
				{' ', '-', ' '},
			},
			{
				{' ', ' ', ' '},
				{' ', ' ', '|'},
				{' ', ' ', ' '},
				{' ', ' ', '|'},
				{' ', ' ', ' '},
			},
			{
				{' ', '-', ' '},
				{' ', ' ', '|'},
				{' ', '-', ' '},
				{'|', ' ', ' '},
				{' ', '-', ' '},
			},
			{
				{' ', '-', ' '},
				{' ', ' ', '|'},
				{' ', '-', ' '},
				{' ', ' ', '|'},
				{' ', '-', ' '},
			},
			{
				{' ', ' ', ' '},
				{'|', ' ', '|'},
				{' ', '-', ' '},
				{' ', ' ', '|'},
				{' ', ' ', ' '},
			},
			{
				{' ', '-', ' '},
				{'|', ' ', ' '},
				{' ', '-', ' '},
				{' ', ' ', '|'},
				{' ', '-', ' '},
			},
			{
				{' ', '-', ' '},
				{'|', ' ', ' '},
				{' ', '-', ' '},
				{'|', ' ', '|'},
				{' ', '-', ' '},
			},
			{
				{' ', '-', ' '},
				{' ', ' ', '|'},
				{' ', ' ', ' '},
				{' ', ' ', '|'},
				{' ', ' ', ' '},
			},
			{
				{' ', '-', ' '},
				{'|', ' ', '|'},
				{' ', '-', ' '},
				{'|', ' ', '|'},
				{' ', '-', ' '},
			},
			{
				{' ', '-', ' '},
				{'|', ' ', '|'},
				{' ', '-', ' '},
				{' ', ' ', '|'},
				{' ', '-', ' '},
			}
	};
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		s = Integer.parseInt(st.nextToken());
		n = st.nextToken();

		//1
		for(int i=0; i<n.length(); i++) {
			System.out.print(number[n.charAt(i)-'0'][0][0]);
			
			for(int j=0; j<s; j++) {
				System.out.print(number[n.charAt(i)-'0'][0][1]);
			}
			System.out.print(number[n.charAt(i)-'0'][0][2]);
			System.out.print(' ');
		}
		
		System.out.println();
		//s
		
		for(int i=0; i<s; i++) {
			for(int j=0; j<n.length(); j++) {
				System.out.print(number[n.charAt(j)-'0'][1][0]);
				for(int k=0; k<s; k++) {
					System.out.print(number[n.charAt(j)-'0'][1][1]);
				}
				System.out.print(number[n.charAt(j)-'0'][1][2]);
				System.out.print(' ');
			}
			System.out.println();
		}
		
		//1
		
		for(int i=0; i<n.length(); i++) {
			System.out.print(number[n.charAt(i)-'0'][2][0]);
			
			for(int j=0; j<s; j++) {
				System.out.print(number[n.charAt(i)-'0'][2][1]);
			}
			System.out.print(number[n.charAt(i)-'0'][2][2]);
			System.out.print(' ');
		}
		System.out.println();
		//s
		
		for(int i=0; i<s; i++) {
			for(int j=0; j<n.length(); j++) {
				System.out.print(number[n.charAt(j)-'0'][3][0]);
				for(int k=0; k<s; k++) {
					System.out.print(number[n.charAt(j)-'0'][3][1]);
				}
				System.out.print(number[n.charAt(j)-'0'][3][2]);
				System.out.print(' ');
			}
			System.out.println();
		}
		
	
		//1

		for(int i=0; i<n.length(); i++) {
			System.out.print(number[n.charAt(i)-'0'][4][0]);
			
			for(int j=0; j<s; j++) {
				System.out.print(number[n.charAt(i)-'0'][4][1]);
			}
			System.out.print(number[n.charAt(i)-'0'][4][2]);
			System.out.print(' ');
		}
	}

}
