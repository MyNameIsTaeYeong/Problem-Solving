import java.util.*;

class Solution {

    static long find(long number){
        long rtn = -1;

        long cur = 1;
        while(cur <= number){
            if((number & cur) == 0){
                if((cur >> 1) >= 1){
                    rtn = number | cur;
                    rtn &= ~(cur >> 1);
                }else
                    rtn = number | cur;

                return rtn;
            }
            cur = cur << 1;
        }

        rtn = number | cur;

        rtn &= ~(cur >> 1);

        return rtn;
    }

    public long[] solution(long[] numbers) {
        long[] answer = {};

        List<Long> list = new ArrayList<>();

        for(long number : numbers){
            list.add(find(number));
        }

        answer = new long[list.size()];
        for(int i=0; i<list.size(); i++)
            answer[i] = list.get(i);

        return answer;
    }
}