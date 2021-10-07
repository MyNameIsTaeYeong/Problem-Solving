import java.util.*;

class Pair implements Comparable<Pair>{
    
    public double fail_rate;
    public int stage_number;
    
    public Pair(double fail_rate, int stage_number){
        this.fail_rate = fail_rate;
        this.stage_number = stage_number;
    }
    
    @Override
    public int compareTo(Pair p){
        if(this.fail_rate == p.fail_rate){
            return this.stage_number - p.stage_number;
        }
        
        if(this.fail_rate < p.fail_rate){
            return 1;
        }else{
            return -1;
        }
        
    }
}

class Solution {
    
    int[][] user_record;
    
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        
        user_record = new int[2][N+3];
        
        for(int stage : stages){
            user_record[0][1]++;
            user_record[0][stage+1]--;
            user_record[1][stage]++;
        }
        
        for(int i=2; i<=N; i++){
            user_record[0][i] += user_record[0][i-1];
        }
        
       
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for(int i=1; i<=N; i++){ 
            double fail_rate;
            if(user_record[0][i] == 0){
                fail_rate = 0;
            }else{
                fail_rate = (double)user_record[1][i] / user_record[0][i];
            }
            
            pq.add(new Pair(fail_rate, i));
        }
        
        answer = new int[N];
        
        for(int i=0; i<N; i++){
            answer[i] = pq.poll().stage_number;
        }
        
        return answer;
    }
}