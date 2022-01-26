package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_11660 {

    static int[][] arr;
    static int[][] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        sum = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sum[0][0] = arr[0][0];

        for (int i = 1; i < n; i++) {
            sum[i][0] = arr[i][0] + sum[i-1][0];
        }
        for (int j = 1; j < n; j++) {
            sum[0][j] = arr[0][j] + sum[0][j-1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                sum[i][j] = sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1] + arr[i][j];
            }
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken()) - 1;

            int ans = sum[x2][y2];
            if(y1 >= 1)
                ans -= sum[x2][y1-1];

            if(x1 >= 1)
                ans -= sum[x1-1][y2];

            if(x1 >= 1 && y1 >= 1)
                ans += sum[x1-1][y1-1];

            System.out.println(ans);
        }
    }
}
