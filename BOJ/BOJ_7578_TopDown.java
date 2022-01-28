package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_7578_TopDown {

    static int[] machine, tree;
    static Map<Integer, Integer> idx;

    static int query(int left, int right, int node, int queryLeft, int queryRight){
        if(right < queryLeft || queryRight < left){
            return 0;
        }

        if(queryLeft <= left && right <= queryRight){
            return tree[node];
        }

        int mid = (left + right) / 2;

        int rtn = 0;

        rtn += query(left, mid, 2 * node, queryLeft, queryRight);
        rtn += query(mid+1, right, 2*node+1, queryLeft, queryRight);

        return rtn;
    }

    static void update(int left, int right, int node, int target, int diff){
        tree[node] += diff;

        if(left == right)
            return;

        int mid = (left + right) / 2;
        if(target <= mid){
            update(left, mid, 2*node, target, diff);
        }else{
            update(mid+1, right, 2*node+1, target, diff);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        machine = new int[n+1];
        idx = new HashMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            machine[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());
            idx.put(num, i);
        }

        int S = 1;
        while(S < n){
            S *= 2;
        }

        tree = new int[2 * S];

        long ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += query(1, S, 1, idx.get(machine[i]), S);
            update(1, S, 1, idx.get(machine[i]), 1);
        }
        System.out.println(ans);
    }
}
