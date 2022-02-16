import java.util.*;

class Solution {

    static Map<String, Integer> idx = new HashMap<>();
    static int[] cnt;

    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        int index = 0;
        for(String gem : gems){
            if(idx.get(gem) == null){
                idx.put(gem, index++);
            }
        }

        cnt = new int[index];

        int curSum = 0;
        int minlen = 2000000;
        int left = 0;


        for(int right=0; right<gems.length; right++){
            cnt[idx.get(gems[right])]++;
            if(cnt[idx.get(gems[right])] == 1){
                curSum++;
                while(left <= right && curSum == index){
                    int len = right - left + 1;
                    if(len < minlen){
                        minlen = len;
                        answer[0] = left + 1;
                        answer[1] = right + 1;
                    }

                    cnt[idx.get(gems[left])]--;
                    if(cnt[idx.get(gems[left])] == 0){
                        curSum--;
                    }
                    left++;
                }
            }

        }




        return answer;
    }
}