import java.util.*;

class Solution {

    static int[][] costs;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;

        costs = new int[n+1][n+1];

        for(int[] init : costs){
            Arrays.fill(init, Integer.MAX_VALUE);
        }

        for(int[] fare : fares){
            costs[fare[0]][fare[1]] = fare[2];
            costs[fare[1]][fare[0]] = fare[2];
            costs[fare[0]][fare[0]] = 0;
            costs[fare[1]][fare[1]] = 0;
        }

        for(int k=1; k<=n; k++){
            for(int start=1; start<=n; start++){
                for(int dest=1; dest<=n; dest++){
                    if(costs[start][k] != Integer.MAX_VALUE
                            && costs[k][dest] != Integer.MAX_VALUE
                            && costs[start][dest] > costs[start][k] + costs[k][dest])
                        costs[start][dest] = costs[start][k] + costs[k][dest];
                }
            }
        }

        for(int i=1; i<=n; i++){
            answer = Math.min(answer, costs[b][i] + costs[a][i] + costs[s][i]);
        }

        return answer;
    }
}