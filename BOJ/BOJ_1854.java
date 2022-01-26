package problem_solving_java;

import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
    int num;
    long weight;
    public Node(int num, long weight){
        this.num = num;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node e){
        return Long.compare(this.weight, e.weight);
    }
}

public class BOJ_1854 {

    static int n, m, k;
    static List<Node> [] graph;

    static void dijkstra(int k){
        long[] cost = new long[n+1];
        Arrays.fill(cost, Long.MAX_VALUE);
        cost[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        PriorityQueue<Long>[] seq = new PriorityQueue[n+1];

        for (int i = 1; i <= n; i++) {
            seq[i] = new PriorityQueue<>(Collections.reverseOrder());
        }


        pq.add(new Node(1, 0));
        seq[1].add(0L);

        while (!pq.isEmpty()){
            Node node = pq.poll();

            if(node.weight > seq[node.num].peek())
                continue;

            for (int i = 0; i < graph[node.num].size(); i++) {
                Node next = graph[node.num].get(i);

                if(seq[next.num].size() < k){
                    seq[next.num].add(node.weight + next.weight);
                    pq.add(new Node(next.num, node.weight + next.weight));
                } else if(node.weight + next.weight < seq[next.num].peek()){
                    seq[next.num].poll();
                    seq[next.num].add(node.weight + next.weight);
                    pq.add(new Node(next.num, node.weight + next.weight));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if(seq[i].size() < k)
                System.out.println(-1);
            else
                System.out.println(seq[i].peek());
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            graph[a].add(new Node(b, c));
        }

        dijkstra(k);
    }
}
