package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_11438 {

    static int n;
    static List<Integer> [] edges;
    static int[] height;
    static int[][] parent;

    // 스택오버플로우 주의
    static void dfs(boolean[] check, int node, int curHeight){
        check[node] = true;
        height[node] = curHeight;

        for(int i=0; i<edges[node].size(); i++){
            int next = edges[node].get(i);
            if(!check[next]){
                parent[0][next] = node;
                dfs(check, next, curHeight + 1);
            }
        }
    }

    static int find(int node1, int node2){
        if(node1 == node2)
            return node1;

        if(height[node1] != height[node2]){
            // 항상 node1이 낮은상태에서 로직을 짜기위해.
            if(height[node1] < height[node2]){
                int temp = node1;
                node1 = node2;
                node2 = temp;
            }

            // 같은 높이까지 올리기.
            int diff = height[node1] - height[node2];

            int idx = 0;
            for(int i=1; i <= diff; i = i << 1){
                if((diff & i) != 0){
                    node1 = parent[idx][node1];
                }
                idx++;
            }
        }

        if(node1 == node2)
            return node1;

        int diff = 2;
        int k = 0;
        while ( diff < height[node1]){
            diff *= 2;
            k++;
        }

        while(true){
            if(parent[k][node1] == parent[k][node2])
                k--;
            else{
                node1 = parent[k][node1];
                node2 = parent[k][node2];
            }
            if(k < 0)
                break;
        }

        return parent[0][node1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        edges = new List[n+1];



        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(edges[a] == null)
                edges[a] = new ArrayList<>();
            edges[a].add(b);
            if(edges[b] == null)
                edges[b] = new ArrayList<>();
            edges[b].add(a);
        }

        int depth = 1;
        int k = 0;
        while(depth < n){
            depth *= 2;
            k++;
        }


        height = new int[n+1];
        parent = new int[k+1][n+1];

        // 루트부터 탐색하며 height와 parent[0] 조사
        dfs(new boolean[n+1], 1, 0);

        // parent배열 채우기
        for(int i = 1; i <= k; i++){
            for(int j=1; j<=n; j++){
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }


        // 탐색

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(find(a, b));
        }

    }
}
