import java.util.*;

class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = {};

        long q = left / n;
        long r = left % n;


        long num = left % n + 1;


        List<Long> list = new ArrayList<>();

        System.out.print(num);

        for(long i=0; i<=(right - left); i++){
            if(num > n){
                num = 1;
                q++;
            }


            if(num <= q + 1)
                list.add(q + 1);
            else
                list.add(num);

            num++;
        }

        answer = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i).intValue();
        }

        return answer;
    }
}