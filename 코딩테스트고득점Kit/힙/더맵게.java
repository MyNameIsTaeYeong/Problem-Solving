import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for(int val : scoville)
            pq.add(new Long(val));


        while(pq.peek() < K){
            answer++;

            long a = pq.poll();
            long b = pq.poll();

            long c = a + 2 * b;
            pq.add(c);

            if(pq.size() == 1 && pq.peek() < K){
                answer = -1;
                break;
            }
        }

        return answer;
    }
}