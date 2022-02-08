import java.util.*;

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

class Solution {

    static List<List<Pair>> edges = new ArrayList<>();

    static void dijkstra(int start, int[] cost){
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[start] = 0;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));

        while(!pq.isEmpty()){
            Pair cur = pq.poll();

            if(cur.weight > cost[cur.num])
                continue;

            for(int i=0; i<edges.get(cur.num).size(); i++){
                Pair next = edges.get(cur.num).get(i);
                if(cost[next.num] > cost[cur.num] + next.weight){
                    cost[next.num] = cost[cur.num] + next.weight;
                    pq.add(new Pair(next.num, cost[next.num]));
                }
            }
        }
    }

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        for(int i=0; i<=n; i++){
            edges.add(new ArrayList<>());
        }

        for(int[] fare : fares){
            edges.get(fare[0]).add(new Pair(fare[1], fare[2]));
            edges.get(fare[1]).add(new Pair(fare[0], fare[2]));
        }

        int[] costFromA = new int[n+1];
        int[] costFromB = new int[n+1];
        int[] costFromS = new int[n+1];

        dijkstra(a, costFromA);
        dijkstra(b, costFromB);
        dijkstra(s, costFromS);

        for(int i=1; i<=n; i++){
            answer =
                    Math.min(answer, costFromA[i] + costFromB[i] + costFromS[i]);
        }

        return answer;
    }
}