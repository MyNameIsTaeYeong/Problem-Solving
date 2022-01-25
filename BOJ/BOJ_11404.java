package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_11404 {

    static int n, m;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dist = new int[n + 1][n + 1];

        for(int[] init : dist){
            Arrays.fill(init, Integer.MAX_VALUE);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j)
                    dist[i][j] = 0;
                else
                    dist[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(c < dist[a][b])
                dist[a][b] = c;
        }


        for(int k=1; k<=n; k++){
            for(int start=1; start<=n; start++){
                for(int end=1; end<=n; end++){
                    if(k == start || k == end)
                        continue;

                    if(dist[start][k] != Integer.MAX_VALUE && dist[k][end] != Integer.MAX_VALUE
                            && dist[start][end] > dist[start][k] + dist[k][end]){
                        dist[start][end] = dist[start][k] + dist[k][end];
                    }
                }
            }
        }

        for (int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(dist[i][j] == Integer.MAX_VALUE)
                    System.out.print(0 + " ");
                else
                    System.out.print(dist[i][j] + " ");
            }
            System.out.println();
        }
    }
}
