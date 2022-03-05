import java.util.*;

class Pair implements Comparable<Pair>{
    int weight;
    int idx;
    public Pair(int weight, int idx){
        this.weight = weight;
        this.idx = idx;
    }

    @Override
    public int compareTo(Pair p){
        return p.weight - this.weight;
    }
}

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Pair> q = new LinkedList<>();
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for(int i=0; i<priorities.length; i++){
            q.add(new Pair(priorities[i], i));
            pq.add(new Pair(priorities[i], i));
        }



        while(true){
            Pair cur = q.poll();

            if(cur.weight >= pq.peek().weight){
                pq.poll();
                answer++;
                if(cur.idx == location){
                    break;
                }
            }else{
                q.add(cur);
            }
        }

        return answer;
    }
}