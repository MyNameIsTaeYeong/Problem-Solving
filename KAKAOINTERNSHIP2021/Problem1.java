class Solution {
    public int solution(String s) {
        int answer = 0;

        int idx = 0;

        while(idx < s.length()){
            answer *= 10;
            // z => +4
            if(s.charAt(idx) == 'z'){
                answer += 0;
                idx += 4;
            }
            // o => +3
            else if(s.charAt(idx) == 'o'){
                answer += 1;
                idx += 3;
            }
            // t => two => +3, three => +5
            else if(s.charAt(idx) == 't'){
                if(s.charAt(idx+1) == 'w'){
                    answer += 2;
                    idx += 3;
                }else{
                    answer += 3;
                    idx += 5;
                }
            }
            // f => four, five +4
            else if(s.charAt(idx) == 'f'){
                if(s.charAt(idx+1) == 'o'){
                    answer += 4;
                    idx += 4;
                }else{
                    answer += 5;
                    idx += 4;
                }
            }
            // s => six, seven +3, +5
            else if(s.charAt(idx) == 's'){
                if(s.charAt(idx + 1) == 'i'){
                    answer += 6;
                    idx += 3;
                }else{
                    answer += 7;
                    idx += 5;
                }
            }
            // e => eight => +5
            else if(s.charAt(idx) == 'e'){
                answer += 8;
                idx += 5;
            }
            // n => nine +4
            else if(s.charAt(idx) == 'n'){
                answer += 9;
                idx += 4;
            }
            // number
            else {
                answer += s.charAt(idx) - '0';
                idx++;
            }


        }


        return answer;
    }
}