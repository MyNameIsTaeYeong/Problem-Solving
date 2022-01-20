import java.io.*;
import java.util.*;

public class BOJ_2042_Top_Down {

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

    // ex) 1, 8, 1, 3, 7
    static long query(int left, int right, int node, long queryLeft, long queryRight){
        if(queryRight < left || right < queryLeft){
            return 0;
        }

        if(queryLeft <= left && right <= queryRight){
            return trees[node];
        }

        long rtn = 0;

        int mid = (left + right) / 2;

        // 왼쪽자식
        rtn += query(left, mid, node*2, queryLeft, queryRight);

        // 오른쪽자식
        rtn += query(mid+1, right, node*2 + 1, queryLeft, queryRight);

        return rtn;
    }

    static void update(int left, int right, int node, long target, long diff){
        trees[node] += diff;

        if(left == right)
            return;

        int mid = (left + right) / 2;

        if( target <= mid ){
            update(left, mid, node*2, target, diff);
        }else{
            update(mid+1, right, node*2+1, target, diff);
        }

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
                update(1, S, 1, b, diff);
            }else{
                long rtn = query(1, S, 1, b, c );
                System.out.println(rtn);
            }

        }
    }
}
