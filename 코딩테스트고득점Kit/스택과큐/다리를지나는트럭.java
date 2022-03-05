
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<bridge_length; i++){
            q.add(0);
        }

        int truckIdx = 0;
        int curWeight = 0;

        while(truckIdx != truck_weights.length){
            answer++;
            int front = q.poll();

            curWeight -= front;

            if(curWeight + truck_weights[truckIdx] <= weight){
                q.add(truck_weights[truckIdx]);
                curWeight += truck_weights[truckIdx];
                truckIdx++;
            }else{
                q.add(0);
            }
        }

        answer += bridge_length;

        return answer;
    }
}