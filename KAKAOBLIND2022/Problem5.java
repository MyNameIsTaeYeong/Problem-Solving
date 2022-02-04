import java.util.*;

class Solution {

    static List<List<Integer>> edgeList = new ArrayList<>();
    static int maxCnt = -1;

    static void go(int[] info, int cur, int wolf, int sheep,
                   List<Integer> curList, boolean[] check){

        // 1. 체크인
        check[cur] = true;

        // 2. 목적지인가
        if(sheep > maxCnt){
            maxCnt = sheep;
        }

        // 3. 연결되었는가?
        for(int i=0; i<curList.size(); i++){
            int node = curList.get(i);
            for(int j=0; j<edgeList.get(node).size(); j++){
                int next = edgeList.get(node).get(j);
                // 4. 갈 수 있는가?
                if(!check[next]){
                    if(info[next] == 1 && sheep > (wolf + 1)){
                        // 5. 간다.
                        curList.add(next);
                        go(info, next, wolf+1, sheep, curList, check);
                        curList.remove(curList.size() - 1);
                    }else if(info[next] == 0){
                        // 5. 간다.
                        curList.add(next);
                        go(info, next, wolf, sheep + 1, curList, check);
                        curList.remove(curList.size() - 1);
                    }
                }
            }
        }


        // 6. 체크아웃
        check[cur] = false;

    }

    public int solution(int[] info, int[][] edges) {
        int answer = 0;

        for(int i=0; i<info.length; i++){
            edgeList.add(new ArrayList<>());
        }

        for(int[] edge : edges){
            edgeList.get(edge[0]).add(edge[1]);
            edgeList.get(edge[1]).add(edge[0]);
        }

        List<Integer> curList = new ArrayList<>();
        curList.add(0);
        go(info, 0, 0, 1, curList, new boolean[info.length]);

        answer = maxCnt;

        return answer;
    }
}