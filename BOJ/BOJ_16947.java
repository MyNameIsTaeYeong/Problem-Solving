package problem_solving_java;

import java.util.*;
import java.io.*;

class Pair {
	public int station;
	public int dist;

	public Pair(int station, int dist) {
		this.station = station;
		this.dist = dist;
	}
}

public class BOJ_16947 {

	static int n;
	static boolean[][] edges;
	static boolean[] cycle_check;
	static int[] ans;
	
	static void bfs() {
		
		int station = 0;
		for(int i=1; i<=n; i++) {
			if(cycle_check[i]) {
				station = i;
				break;
			}
		}
		
		ans = new int[n+1];
		
		
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(station, 0));

		boolean[] check = new boolean[n + 1];

		while (q.size() != 0) {
			Pair p = q.poll();

			check[p.station] = true;
			
			for (int i = 1; i <= n; i++) {
				if (edges[p.station][i] && !check[i]) {
					
					if (cycle_check[i]) {
						q.add(new Pair(i, 0));
						ans[i] = 0;
					}else {
						q.add(new Pair(i, p.dist + 1));
						ans[i] = p.dist + 1;
					}
					
				}
			}
		}

		
	}

	static int find_cycle(int station, boolean[] check, int prev) {
		if (check[station]) {
			return station;
		}

		check[station] = true;

		for (int i = 1; i <= n; i++) {
			if (i == prev) {
				continue;
			}
			if (edges[station][i]) {
				int rtn = find_cycle(i, check, station);
				if (rtn == station) {
					cycle_check[station] = true;
					return -2;
				}
				if (rtn >= 1) {
					cycle_check[station] = true;
					return rtn;
				}
				if(rtn == -2) {
					return -2;
				}
			}
		}

		return -1;

	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		edges = new boolean[n + 1][n + 1];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edges[start][end] = true;
			edges[end][start] = true;
		}

		cycle_check = new boolean[n + 1];

		for(int i=1; i<=n; i++) {
			if(find_cycle(i, new boolean[n + 1], 0) != -1) {
				break;
			}
		}
		
		bfs();
		
		for(int i=1; i<=n; i++) {
			System.out.print(ans[i]+" ");
		}
		
	}

}
