package problem_solving_java;

import java.util.*;
import java.io.*;

class Pair {
    int cnt;
    int r;
    int c;
    public Pair(int cnt, int r, int c){
        this.cnt = cnt;
        this.r = r;
        this.c = c;
    }
}

public class BOJ_11049 {

    static Pair[] matrix;
    static Pair[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        matrix = new Pair[n];
        memo = new Pair[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            matrix[i] = new Pair(0, r, c);
            memo[i][i] = new Pair(0, r, c);
        }

        if(n == 1)
            System.out.println(0);
        else{
            for(int i=n-1; i>=0; i--){
                int row = 0;
                int col = n - i;
                for (int j = 0; j < i; j++) {
                    memo[row][col] = new Pair(Integer.MAX_VALUE, 0, 0);
                    for (int k = row; k < col; k++) {
                        int temp = memo[row][k].cnt + memo[k+1][col].cnt + memo[row][k].r * memo[row][k].c * memo[k+1][col].c;
                        if(temp < memo[row][col].cnt){
                            memo[row][col].cnt = temp;
                            memo[row][col].r = memo[row][k].r;
                            memo[row][col].c = memo[k+1][col].c;
                        }
                    }

                    row++;
                    col++;
                }

            }


            System.out.println(memo[0][n-1].cnt);
        }





    }
}
