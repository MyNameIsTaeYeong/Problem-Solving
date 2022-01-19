import java.io.*;
import java.util.*;

public class BOJ_2042_Bottom_Up {
    static int N, M, K, S;
    static long[] arr, trees;

    static void init(){
        for (int i = 0; i < N; i++) {
            trees[S+i] = arr[i];
        }

        for(int i = 2 * S - 1; i > 1; i--){
            trees[i/2] += trees[i];
        }
    }

    static void update(int node, long diff){
        trees[node] += diff;

        if(node == 1)
            return;

        update(node/2, diff);
    }

    static long query(long queryLeft, long queryRight){
        long left = S + queryLeft -1;
        long right = S + queryRight - 1;

        long rtn = 0;

        while (left <= right){
            if(left % 2 == 1){
                rtn += trees[(int)left];
                left++;
            }

            if(right % 2 == 0){
                rtn += trees[(int)right];
                right--;
            }
            left /= 2;
            right /= 2;
        }

        return rtn;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new long[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        S =  1;

        while(S < N){
            S *= 2;
        }

        trees = new long[2 * S];

        init();

        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());


            if(a == 1){
                long diff = c - trees[(int) (S + b - 1)];
                update((int) (S + b - 1), diff);
            }else{
                long rtn = query(b, c );
                System.out.println(rtn);
            }

        }
    }
}
