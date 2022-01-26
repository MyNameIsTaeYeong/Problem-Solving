package problem_solving_java;

import java.util.*;
import java.io.*;

class Pair implements Comparable<Pair>{
    int num;
    int weight;
    public Pair(int num, int weight){
        this.num = num;
        this.weight = weight;
    }

    @Override
    public int compareTo(Pair p){
        return this.weight - p.weight;
    }

}


public class BOJ_5719 {

    static List<Pair>[] edges;
    static List<Integer>[] prev;
    static boolean[][] isShortest;

    static int dijkstra(int start, int end, int n){
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair( start, 0));

        int rtn = -1;

        while (!pq.isEmpty()){
            Pair cur = pq.poll();

            if(cur.weight > cost[cur.num]){
                continue;
            }

            if(cur.num == end)
                rtn = cur.weight;

            for (int i = 0; i < edges[cur.num].size(); i++) {
                Pair next = edges[cur.num].get(i);
                if(isShortest[cur.num][next.num])
                    continue;

                if(cost[next.num] > cost[cur.num] + next.weight){
                    prev[next.num] = new ArrayList<>();
                    prev[next.num].add(cur.num);
                    cost[next.num] = cost[cur.num] + next.weight;
                    pq.add(new Pair(next.num, cost[next.num]));
                }else if(cost[next.num] == cost[cur.num] + next.weight){
                    prev[next.num].add(cur.num);
                }
            }
        }

        return rtn;

    }

    static void removeEdge(int start, int end){
        if(end == start)
            return;

        for (int i = 0; i < prev[end].size(); i++) {
            int prevNode = prev[end].get(i);
            if(!isShortest[prevNode][end]){
                isShortest[prevNode][end] = true;
                removeEdge(start, prevNode);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            if(n == 0 && m == 0)
                break;

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            edges = new List[n];
            prev = new List[n];
            isShortest = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                edges[i] = new ArrayList<>();
                prev[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                edges[u].add(new Pair(v, p));
            }

            dijkstra(s, d, n);
            removeEdge(s, d);
            System.out.println(dijkstra(s, d, n));
        }
    }
}
