package problem_solving_java;

import java.io.*;
import java.util.*;

class Edge {
    int a;
    int b;
    int c;
    public Edge(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }
}

public class BOJ_1922_kruskal {

    static int[] parent;

    static int find(int a){
        if(parent[a] == a)
            return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b){
        int aRoot = parent[a];
        int bRoot = parent[b];
        parent[aRoot] = bRoot;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n+1];

        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges.add(new Edge(a, b, c));
        }

        Collections.sort(edges, (e1, e2) -> e1.c - e2.c);

        int edgeIdx = 0;

        int ans = 0;
        for(int i=0; i<n-1; i++){

            while (true){
                Edge edge = edges.get(edgeIdx++);
                if(find(edge.a) != find(edge.b)) {
                    ans += edge.c;
                    union(edge.a, edge.b);
                    break;
                }
            }

        }

        System.out.println(ans);
    }
}
