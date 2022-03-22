import java.util.*;

class Pair implements Comparable<Pair>{
    int request;
    int require;

    public Pair(int request, int require){
        this.request = request;
        this.require = require;
    }

    public int compareTo(Pair p){
        if(this.require == p.require)
            return this.request - p.request;
        else
            return this.require - p.require;
    }
}

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int idx = 0;
        int time = -1;

        boolean cpu = false;
        int completeTime = -1;

        Arrays.sort(jobs, (job1, job2) -> job1[0] - job2[0]);

        while(idx < jobs.length || !pq.isEmpty()){
            time++;

            if(time == completeTime)
                cpu = false;

            while(idx < jobs.length && jobs[idx][0] == time){
                pq.add(new Pair(jobs[idx][0], jobs[idx][1]));
                idx++;
            }

            if(!cpu && !pq.isEmpty()){
                Pair job = pq.poll();
                cpu = true;
                answer += (time - job.request + job.require);
                completeTime = time + job.require;
            }

        }

        answer /= idx;

        return answer;
    }
}