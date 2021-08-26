import java.util.*;

class Solution {
    
    static int press(String s, int size){
    
        int idx = size;
        
        String firstsub = s.substring(0, idx);
        
        StringBuilder pressed = new StringBuilder("");
        
        int cnt = 1;
        while(idx+size <= s.length()){
            String secondsub = s.substring(idx, idx+size);
            idx += size;
            
            if(firstsub.equals(secondsub)){
                cnt++;
            }else{
                if(cnt == 1){
                    pressed.append(firstsub);
                }else{
                    pressed.append(cnt+firstsub);
                }
                firstsub = secondsub;
                cnt = 1;
            }
        }
        
        if(cnt == 1){
            pressed.append(firstsub);
        }else{
            pressed.append(cnt+firstsub);
        }
        
        if(idx + size > s.length()){
            for(int i=idx; i<s.length(); i++){
                pressed.append(s.charAt(i));
            }
        }
        
        
        
        return pressed.length();
    }
    
    public int solution(String s) {
        int answer = 3000;
        
        
        
        for(int i=1; i<=s.length(); i++){
            answer = Math.min(answer, press(s, i));
        }
        
        return answer;
    }
}