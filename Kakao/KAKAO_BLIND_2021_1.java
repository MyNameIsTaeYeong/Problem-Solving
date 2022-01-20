class Solution {
    
    static boolean upper_check(char ch){
        int test = ch - 'A';
        if(test >= 0 && test <= 25){
            return true;
        }else{
            return false;
        }
    }
    
    static boolean char_check(char ch){
        int test = ch - 'a';
        if((test >= 0 && test <= 25) || 
           (test >= -49 && test <= -40) || 
           test == -2 || test == -51 || test == -52 ){
            return true;
        }else{
            return false;
        }
    }
    
   
    public String solution(String new_id) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        
        // 1단계
        for(int i=0; i<new_id.length(); i++){
            char ch = new_id.charAt(i);
            if(upper_check(ch)){
                sb.append((char)(ch+32));
            }else{
                sb.append(ch);
            }
        }
        
        
        // 2단계
        
        
        int idx = 0;
        while(true){
            if(idx >= sb.length()){
                break;
            }
            char ch = sb.charAt(idx);
            if(!char_check(ch)){
                sb.deleteCharAt(idx);
            }else{
                idx++;
            }
        }
        
        // 3단계
       idx = 0;
        while(true){
            if(idx >= sb.length()){
                break;
            }
            
            char ch = sb.charAt(idx);
            if(ch == '.'){
                int end = idx+1;
                while(end < sb.length() && sb.charAt(end) == '.'){
                    end++;
                }
                
                if(end == (idx+1)){
                    idx++;
                }else{
                    sb.replace(idx, end, ".");
                }
            }else{
                idx++;
            }
        }
        
        //4단계
        if(sb.length() > 0 && sb.charAt(0) == '.'){
            sb.deleteCharAt(0);
        }
        
        if((sb.length()-1) >= 0 && sb.charAt(sb.length()-1) == '.'){
            sb.deleteCharAt(sb.length()-1);
        }
        
        // 5단계
        if(sb.length() == 0){
            sb.append("a");
        }
       
        // 6단계
        if(sb.length() >= 16){
            sb.delete(15, sb.length());
        }
        
        if((sb.length()-1) >= 0 && sb.charAt(sb.length()-1) == '.'){
            sb.deleteCharAt(sb.length()-1);
        }
        
        // 7단계
        while(sb.length() <= 2){
            sb.append(sb.charAt(sb.length()-1));
        }
        
        answer = sb.toString();
        
        answer.toLowerCase();
        
        return answer;
    }
}