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

public class BOJ_1753 {

    static List<List<Pair>> graph;
    static int[] cost;
    static int V, E;

    static void dijkstra(int start){
        cost[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));

        while(!pq.isEmpty()){
            Pair cur = pq.poll();

            if(cur.weight > cost[cur.num])
                continue;

            for(int i=0; i<graph.get(cur.num).size(); i++){
                Pair next = graph.get(cur.num).get(i);
                if(cost[next.num] > cost[cur.num] + next.weight){
                    cost[next.num] = cost[cur.num] + next.weight;
                    pq.add(new Pair(next.num, cost[next.num]));
                }
            }
        }


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        cost = new int[V+1];

        Arrays.fill(cost, Integer.MAX_VALUE);

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Pair(v, w));
        }


        dijkstra(k);
        StringBuilder sb = new StringBuilder("");
        for(int i=1; i<=V; i++){
            if(cost[i] == Integer.MAX_VALUE)
                sb.append("INF\n");
            else if(i == k)
                sb.append("0\n");
            else
                sb.append(cost[i]+"\n");
        }

        System.out.println(sb);
    }
}
