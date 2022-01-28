package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_7578_BottomUp {

    static int[] machine, tree;
    static Map<Integer, Integer> idx;

    static int query(int queryLeft, int S){
        int left = S + queryLeft - 1;
        int right = 2 * S - 1;

        int rtn = 0;

        while(left <= right){
            if(left % 2 == 1){
                rtn += tree[left++];
            }

            if(right % 2 == 0){
                rtn += tree[right--];
            }

            left /= 2;
            right /= 2;
        }

        return rtn;
    }

    static void update(int node, int diff, int S){
        int idx = S + node -1;

        while(idx > 0){
            tree[idx] += diff;
            idx /= 2;
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
            ans += query(idx.get(machine[i]), S);
            update(idx.get(machine[i]), 1, S);
        }
        System.out.println(ans);
    }
}
