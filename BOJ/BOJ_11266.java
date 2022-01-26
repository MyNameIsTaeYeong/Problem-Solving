package problem_solving_java;

import java.util.*;
import java.io.*;

public class BOJ_11266 {

    static int V, E;
    static List<Integer> [] graph;
    static int[] orderArr;
    static boolean[] isCut;

    // 인접한 노드중 가장 작은 Order를 리턴한다.
    static int dfs(boolean[] visited, int root, int cur, int order){
        visited[cur] = true;
        orderArr[cur] = order;
        int rtn = Integer.MAX_VALUE;

        if(root == cur){
            rtn = 0;
            for(int i=0; i<graph[root].size(); i++){
                int next = graph[root].get(i);
                if(!visited[next]){
                    dfs(visited, root, next, order + 1);
                    rtn++;
                }
            }
        }else{

            for (int i = 0; i < graph[cur].size(); i++) {
                int next = graph[cur].get(i);
                if(!visited[next]){
                    int lowOrder = dfs( visited, root, next, order + 1 );
                    if(order <= lowOrder){
                        isCut[cur] = true;
                    }

                    rtn = Math.min(rtn, lowOrder);
                } else {
                    rtn = Math.min(rtn, orderArr[next]);
                }

            }


        }

        return rtn;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new List[V + 1];
        orderArr = new int[V + 1];
        isCut = new boolean[V + 1];

        Arrays.fill(orderArr, Integer.MAX_VALUE);

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        boolean[] visited = new boolean[V+1];

        for (int i = 1; i <= V; i++) {
            if(!visited[i])
                if(dfs(visited, i, i, 1) >= 2){
                    isCut[i] = true;
                }
        }

        StringBuilder sb = new StringBuilder("");
        int cnt = 0;
        for (int i = 1 ; i <= V ; i++) {
            if(isCut[i]) {
                cnt++;
                sb.append(i + " ");
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }
}
