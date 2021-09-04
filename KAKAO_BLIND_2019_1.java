import java.util.*;

class Solution {
    
    HashMap<String, String> map = new HashMap<>();
    
    public String[] solution(String[] record) {
        String[] answer = {};
        
        ArrayList<String> ans = new ArrayList<>();
        
        for(String input : record){
            StringTokenizer st = new StringTokenizer(input, " ");
            String instruction = st.nextToken();
            String userid = st.nextToken();
            if(instruction.equals("Leave")){
                ans.add(userid);
                ans.add(instruction);
            }else if(instruction.equals("Change")){
                String nickname = st.nextToken();
                map.put(userid, nickname);
            }else{
                ans.add(userid);
                ans.add(instruction);
                String nickname = st.nextToken();
                map.put(userid, nickname);
            }
            
        }
        
        answer = new String[ans.size()/2];
        
        for(int i=0; i<ans.size(); i+=2){
            String nickname = map.get(ans.get(i));
            String instruction = ans.get(i+1);
            
            if(instruction.equals("Enter")){
                answer[i/2] = nickname+"님이 "+"들어왔습니다.";
            }else{
                answer[i/2] = nickname+"님이 "+"나갔습니다.";
            }
        }
        
        
        return answer;
    }
}