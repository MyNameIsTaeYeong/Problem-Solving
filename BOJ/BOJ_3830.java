package problem_solving_java;

import java.util.*;
import java.io.*;

class Pair {
    int num;
    long weight;
    public Pair(int num, long weight){
        this.num = num;
        this.weight = weight;
    }
}

public class BOJ_3830 {

    static long[] weights;
    static int[] parents;

    static void union(int a, int b, long w){
        Pair aRoot = find(a);
        Pair bRoot = find(b);

        parents[aRoot.num] = bRoot.num;
        weights[aRoot.num] = bRoot.weight - aRoot.weight + w;
    }

    static Pair find(int a){
        if(parents[a] == a)
            return new Pair(a, 0);

        Pair rtn = find(parents[a]);
        rtn.weight += weights[a];

        parents[a] = rtn.num;
        weights[a] = rtn.weight;

        return rtn;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0)
                break;

            parents = new int[ n + 1 ];
            weights = new long[ n + 1 ];

            for (int i = 1; i <= n; i++) {
                parents[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                char q = st.nextToken().charAt(0);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(q == '!'){
                    long w = Long.parseLong(st.nextToken());
                    union(a, b, w);
                }else{
                    if(find(a).num != find(b).num)
                        System.out.println("UNKNOWN");
                    else
                        System.out.println(find(a).weight - find(b).weight);
                }
            }
        }



    }
}
