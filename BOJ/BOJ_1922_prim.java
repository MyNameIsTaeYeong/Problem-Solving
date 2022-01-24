package problem_solving_java;

import java.util.*;
import java.io.*;

class Link implements Comparable<Link>{
    int num;
    int weight;
    public Link(int num, int weight){
        this.num = num;
        this.weight = weight;
    }

    @Override
    public int compareTo(Link l){
        return this.weight - l.weight;
    }
}


public class BOJ_1922_prim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Integer>> edges = new ArrayList<>();
        int[][] weight = new int[n+1][n+1];

        for (int i = 0; i < n+1; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.get(a).add(b);
            edges.get(b).add(a);
            weight[a][b] = c;
            weight[b][a] = c;
        }

        PriorityQueue<Link> pq = new PriorityQueue<>();
        for(int i=0; i<edges.get(1).size(); i++){
            pq.add(new Link(edges.get(1).get(i), weight[1][edges.get(1).get(i)]));
        }

        int ans = 0;

        boolean[] check = new boolean[n+1];
        check[1] = true;

        int cnt = 0;
        while(true){
            Link link = pq.poll();
            if(check[link.num])
                continue;
            ans += link.weight;
            check[link.num] = true;

            cnt++;
            if(cnt == n-1)
                break;

            for(int j=0; j<edges.get(link.num).size(); j++){
                int nextNum = edges.get(link.num).get(j);
                if(!check[nextNum]){
                    pq.add(new Link(nextNum, weight[link.num][nextNum]));
                }
            }
        }
        System.out.println(ans);
    }
}
