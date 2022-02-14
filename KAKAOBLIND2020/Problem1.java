import java.util.*;

class Solution {

    // return length 
    static int press(String s, int unit){
        String pressed = "";
        String compared = s.substring(0, unit);

        int cnt = 1;
        int idx = unit;
        while(true){
            if(idx + unit > s.length())    {
                if(cnt != 1){
                    pressed += cnt;
                }
                pressed += compared;
                break;
            }


            String compare = s.substring(idx, idx + unit);
            if(compared.equals(compare)){
                cnt++;
            }else{
                if(cnt != 1){
                    pressed += cnt;
                }
                pressed += compared;
                compared = compare;
                cnt = 1;
            }
            idx += unit;
        }

        if(idx < s.length()){
            pressed += s.substring(idx, s.length());
        }
        //System.out.println(pressed);
        return pressed.length();
    }

    public int solution(String s) {
        int answer = Integer.MAX_VALUE;

        for(int i=1; i<=s.length(); i++){
            answer = Math.min(answer, press(s, i));
        }

        return answer;
    }
}