package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_14476 {

    static int gcd(int a, int b){

        int r = a % b;
        while(r != 0){
            a = b;
            b = r;
            r = a % b;
        }

        return b;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] rl = new int[N];
        int[] lr = new int[N];

        lr[0] = arr[0];

        for (int i = 1; i < N; i++) {
            lr[i] = gcd(lr[i-1], arr[i]);
        }

        rl[N-1] = arr[N-1];

        for(int i=N-2; i>=0; i--){
            rl[i] = gcd(rl[i+1], arr[i]);
        }

        int ans = -1;
        int ansK = 0;

        for (int i = 0; i < N; i++) {
            int k = arr[i];
            int remainGcd = 0;
            if(0<=i-1 && i+1<N)
                remainGcd = gcd(lr[i-1], rl[i+1]);
            else if(i == 0){
                remainGcd = rl[1];
            }else{
                remainGcd = lr[N-2];
            }

            if(k % remainGcd != 0 && remainGcd > ans ){
                ans = remainGcd;
                ansK = k;
            }

        }

        if(ans == -1)
            System.out.println(ans);
        else
            System.out.println(ans + " " + ansK);
    }
}
