import java.util.*;

class Solution {

    static int n;
    static int ans = 0;

    static void go(List<Integer> list, int k, int[][] dungeons){
        int cnt = 0;
        for(int i=0; i<list.size(); i++){
            int idx = list.get(i);

            if(k < dungeons[idx][0])
                continue;

            k -= dungeons[idx][1];
            cnt++;
        }

        ans = Math.max(ans, cnt);
    }

    static void makePermutation(List<Integer> list,
                                boolean[] check, int k, int[][] dungeons){
        if(list.size() == n){
            go(list, k, dungeons);
            return;
        }

        for(int i=0; i<n; i++){
            if(!check[i]){
                check[i] = true;
                list.add(i);
                makePermutation(list, check, k, dungeons);
                list.remove(list.size() - 1);
                check[i] = false;
            }
        }


    }

    public int solution(int k, int[][] dungeons) {
        int answer = -1;

        n = dungeons.length;

        makePermutation(new ArrayList<>(), new boolean[n], k, dungeons);

        answer = ans;

        return answer;
    }
}