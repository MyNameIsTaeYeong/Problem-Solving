package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_2098 {

    static int[][] weight;
    static int[][] d;
    static int n, bit;

    static int solve(int node, int visited, int start){
        if((visited & (1<<node)) != 0 && node == start)
            return 0;

        visited |= (1 << node);

        if(d[node][visited] != 0){
            return d[node][visited];
        }

        int rtn = 2000000;

        for (int i = 0; i < n; i++) {
            // 연결
            if(weight[node][i] != 0 ){
                // 방문하지 않은 곳
                if((visited & 1 << i) == 0){
                    rtn = Math.min(solve(i, visited, start) + weight[node][i], rtn);
                }
                // 모든 점을 방문하고 시작점을 방문하는 경우
                else if(visited == bit-1 && i == start){
                    rtn = Math.min(solve(i, visited, start) + weight[node][i], rtn);
                }
            }
        }

        return d[node][visited] = rtn;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        bit = 1;

        for (int i = 0; i < n; i++) {
            bit *= 2;
        }

        weight = new int[n][n];
        d = new int[n][bit];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                weight[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(0, 0, 0));
    }
}
