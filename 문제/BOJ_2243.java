import java.util.*;
import java.io.*;

public class BOJ_2243 {

    static int n, S;
    static int[] trees;

    static int query(int left, int right, int node, int rank){
        if(left == right)
            return node - S + 1;

        int mid = (left + right) / 2;

        if(trees[2 * node] >= rank)
            return query(left, mid, 2 * node, rank);
        else
            return query(mid+1, right, 2 * node + 1, rank - trees[2 * node]);

    }

    static void update(int left, int right, int node, int target, int diff){
        trees[node] += diff;

        if(left == right)
            return;

        int mid = (left + right) / 2;

        if(target <= mid)
            update(left, mid, 2 * node, target, diff);
        else
            update(mid+1, right, 2 * node + 1, target, diff);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        S = 1;

        while(S < 1000000){
            S *= 2;
        }

        trees = new int[2*S];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a;
            a = Integer.parseInt(st.nextToken());



            if( a == 1 ){
                int b = Integer.parseInt(st.nextToken());
                // query
                int target = query(1, S, 1, b);
                // update
                update(1, S, 1, target, -1);

                System.out.println(target);
            }else{
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                // update
                update(1, S, 1, b, c);
            }

        }
    }
}
