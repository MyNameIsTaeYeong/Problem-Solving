import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        Map<Integer, Integer> history = new HashMap<>();


        for(String operation : operations){
            StringTokenizer st = new StringTokenizer(operation, " ");
            char cmd = st.nextToken().charAt(0);
            int val = Integer.parseInt(st.nextToken());

            if(cmd == 'I'){
                maxHeap.add(val);
                minHeap.add(val);
            }else{

                // 최댓값 삭제
                if(val == 1){
                    while(history.get(maxHeap.peek()) != null && history.get(maxHeap.peek()) > 0){
                        history.put(maxHeap.peek(), history.get(maxHeap.peek()) - 1);
                        maxHeap.poll();
                    }

                    if(!maxHeap.isEmpty()){
                        history.put(maxHeap.peek(), history.getOrDefault(maxHeap.peek(), 0) + 1);
                        maxHeap.poll();
                    }

                }
                // 최솟값 삭제.
                else{
                    while(history.get(minHeap.peek()) != null && history.get(minHeap.peek()) > 0){
                        history.put(minHeap.peek(), history.get(minHeap.peek()) - 1);
                        minHeap.poll();
                    }

                    if(!minHeap.isEmpty()){
                        history.put(minHeap.peek(), history.getOrDefault(minHeap.peek(), 0) + 1);
                        minHeap.poll();
                    }
                }

            }
        }

        while(history.get(maxHeap.peek()) != null && history.get(maxHeap.peek()) > 0){
            history.put(maxHeap.peek(), history.get(maxHeap.peek()) - 1);
            maxHeap.poll();
        }

        while(history.get(minHeap.peek()) != null && history.get(minHeap.peek()) > 0){
            history.put(minHeap.peek(), history.get(minHeap.peek()) - 1);
            minHeap.poll();
        }

        if(maxHeap.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
        }else{
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }

        return answer;
    }
}