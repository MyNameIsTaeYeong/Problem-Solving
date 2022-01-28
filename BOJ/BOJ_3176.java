package problem_solving_java;

import java.io.*;
import java.util.*;

class Pair{
    int num;
    int weight;
    public Pair(int num, int weight){
        this.num = num;
        this.weight = weight;
    }
}

public class BOJ_3176 {

    static int n;
    static List<Pair> [] edges;
    static int[][] parent, minDist, maxDist;
    static int[] height;

    static void bfs(){
        boolean[] check = new boolean[n+1];
        check[1] = true;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(1, 0));

        while (!q.isEmpty()){
            Pair cur = q.poll();

            for (int i = 0; i < edges[cur.num].size(); i++) {
                Pair next = edges[cur.num].get(i);
                if(!check[next.num]){
                    check[next.num] = true;
                    parent[0][next.num] = cur.num;
                    maxDist[0][next.num] = next.weight;
                    minDist[0][next.num] = next.weight;
                    height[next.num] = cur.weight + 1;
                    q.add(new Pair(next.num, height[next.num]));
                }
            }
        }

    }

    static void find(int a, int b, int mulCnt){
        // 항상 a를 깊은쪽 노드로 생각
        if(height[a] < height[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        int maxA = Integer.MIN_VALUE;
        int maxB = Integer.MIN_VALUE;

        int diff = height[a] - height[b];
        int idx = 0;
        for(int i=1; i<=diff; i = i << 1){
            if((diff & i) != 0){
                minA = Math.min(minA, minDist[idx][a]);
                maxA = Math.max(maxA, maxDist[idx][a]);
                a = parent[idx][a];
            }
            idx++;
        }

        if(a == b){
            System.out.println(minA +" "+ maxA);
            return;
        }


        while(mulCnt >= 0){
            if (parent[mulCnt][a] == parent[mulCnt][b]){
                mulCnt--;
            }else{
                minA = Math.min(minA, minDist[mulCnt][a]);
                minB = Math.min(minB, minDist[mulCnt][b]);
                maxA = Math.max(maxA, maxDist[mulCnt][a]);
                maxB = Math.max(maxB, maxDist[mulCnt][b]);
                a = parent[mulCnt][a];
                b = parent[mulCnt][b];
            }
        }

        minA = Math.min(minA, minDist[0][a]);
        minB = Math.min(minB, minDist[0][b]);
        maxA = Math.max(maxA, maxDist[0][a]);
        maxB = Math.max(maxB, maxDist[0][b]);

        System.out.println(Math.min(minA, minB) +" "+ Math.max(maxA, maxB));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        edges = new List[n + 1];

        for (int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[a].add(new Pair(b, c));
            edges[b].add(new Pair(a, c));
        }

        // n <= 2^mulCnt 를 만족하는 최소 mulCnt
        int temp = 1;
        int mulCnt = 0;
        while (temp < n){
            temp *= 2;
            mulCnt++;
        }

        parent = new int[mulCnt + 1][n+1];
        minDist = new int[mulCnt + 1][n+1];
        maxDist = new int[mulCnt + 1][n+1];
        height = new int[n+1];


        for(int[] init : minDist){
            Arrays.fill(init, Integer.MAX_VALUE);
        }


        bfs();


        for (int i = 1; i <= mulCnt; i++) {
            for (int j = 1; j <= n; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
                maxDist[i][j] = Math.max(maxDist[i-1][j], maxDist[i-1][parent[i-1][j]]);
                minDist[i][j] = Math.min(minDist[i-1][j], minDist[i-1][parent[i-1][j]]);
            }
        }


        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            find(d, e, mulCnt);
        }
    }
}
