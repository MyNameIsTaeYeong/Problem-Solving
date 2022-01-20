import java.util.*;

class Solution {
    
    static long[] times;
    
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        
        int play_sec = 0;
        
        for(int i=0; i<play_time.length(); i++){
            StringTokenizer st = new StringTokenizer(play_time, ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            play_sec = hour*3600 + min*60 + sec;
        }
        
        
        
        times = new long[play_sec+1];
        
        int adv_sec = 0;
        
        for(int i=0; i<adv_time.length(); i++){
            StringTokenizer st = new StringTokenizer(adv_time, ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            adv_sec = hour*3600 + min*60 + sec;
        }
        
        for(int i=0; i<logs.length; i++){
            StringTokenizer st = new StringTokenizer(logs[i], "-");
            String start = st.nextToken();
            String end = st.nextToken();
            st = new StringTokenizer(start, ":");
            int hour = Integer.parseInt(st.nextToken());
            int min = Integer.parseInt(st.nextToken());
            int sec = Integer.parseInt(st.nextToken());
            
            int start_sec = hour*3600 + min*60 + sec;
            
            
            
            st = new StringTokenizer(end, ":");
            hour = Integer.parseInt(st.nextToken());
            min = Integer.parseInt(st.nextToken());
            sec = Integer.parseInt(st.nextToken());
            
            int end_sec = hour*3600 + min*60 + sec;
            
            
            
            times[start_sec]++;
            times[end_sec]--;
        }
        
        for(int i=1; i<times.length; i++){
            times[i] += times[i-1];
        }
        
        for(int i=1; i<times.length; i++){
            times[i] += times[i-1];
        }
        
        
        long max_time = times[adv_sec - 1];
        int ans_start_sec = 0;
        for(int i=adv_sec; i<play_sec; i++){
            if(max_time < times[i] - times[i-adv_sec]){
                max_time = times[i] - times[i-adv_sec];
                ans_start_sec = i - adv_sec + 1;
            }
        }
        
        
        
        int ans_hour = ans_start_sec / 3600;
        ans_start_sec %= 3600;
        int ans_min = ans_start_sec / 60;
        ans_start_sec %= 60;
        int ans_sec = ans_start_sec;
        
        StringBuilder sb = new StringBuilder();
        
        if(ans_hour < 10){
            sb.append("0" + ans_hour + ":");
        }else{
            sb.append(ans_hour + ":");
        }
        
        if(ans_min < 10){
            sb.append("0" + ans_min + ":");
        }else{
            sb.append(ans_min + ":");
        }
        
        if(ans_sec < 10){
            sb.append("0" + ans_sec);
        }else{
            sb.append(ans_sec);
        }
        
        answer = sb.toString();
        
        return answer;
    }
}