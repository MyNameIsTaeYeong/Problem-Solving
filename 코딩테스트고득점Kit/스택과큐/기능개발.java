import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int n = progresses.length;

        List<Integer> complete = new ArrayList<>();

        for(int i=0; i<n; i++){
            int remain = 100 - progresses[i];
            if(remain % speeds[i] == 0){
                complete.add(remain / speeds[i]);
            }else{
                complete.add(remain / speeds[i] + 1);
            }
        }

        List<Integer> ans = new ArrayList<>();
        int require = complete.get(0);
        int cnt = 1;
        for(int i=1; i<complete.size(); i++){
            if(complete.get(i) <= require){
                cnt++;
            }else{
                ans.add(cnt);
                require = complete.get(i);
                cnt = 1;
            }
        }

        ans.add(cnt);

        answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }
}