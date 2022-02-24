import java.util.*;

class Solution {

    static int[][] edges;

    static int dfs(int node, boolean[] check, int n){
        int rtn = 1;

        check[node] = true;

        for(int i=1; i<=n; i++){
            if(edges[node][i] == 1 && !check[i])
                rtn += dfs(i, check, n);
        }

        return rtn;
    }

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        edges = new int[n+1][n+1];

        for(int[] wire : wires){
            edges[wire[0]][wire[1]] = 1;
            edges[wire[1]][wire[0]] = 1;
        }

        for(int[] wire : wires){
            edges[wire[0]][wire[1]] = 0;
            edges[wire[1]][wire[0]] = 0;


            boolean[] check = new boolean[n+1];
            List<Integer> list = new ArrayList<>();

            for(int i=1; i<=n; i++){
                if(!check[i])
                    list.add(dfs(i, check, n));
            }

            answer = Math.min(answer, Math.abs(list.get(0) - list.get(1)));

            edges[wire[0]][wire[1]] = 1;
            edges[wire[1]][wire[0]] = 1;
        }


        return answer;
    }
}