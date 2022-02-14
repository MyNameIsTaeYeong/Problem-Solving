import java.util.*;

class Solution {

    static int parseBalanced(String s){
        int balanceCnt = 0;
        int rtn = 0;
        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '(')
                balanceCnt++;
            else
                balanceCnt--;

            if(balanceCnt == 0){
                rtn = i+1;
                break;
            }
        }

        return rtn;
    }

    static boolean check(String s){
        Stack<Character> stack = new Stack<>();
        if(s.charAt(0) == ')')
            return false;
        else
            stack.push(s.charAt(0));

        for(int i=1; i<s.length(); i++){
            if(s.charAt(i) == ')'){
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }else{
                stack.push(s.charAt(i));
            }
        }

        if(!stack.isEmpty())
            return false;

        return true;
    }

    static String go(String s){
        if(s.length() == 0){
            return "";
        }
        int idx = parseBalanced(s);
        String u = s.substring(0, idx);
        String v = s.substring(idx, s.length());
        if(check(u)){
            return u += go(v);
        }

        String rtn = "(";
        rtn += go(v);
        rtn += ")";
        u = u.substring(1, u.length()-1);
        for(int i=0; i<u.length(); i++){
            if(u.charAt(i) == '(')
                rtn += ")";
            else
                rtn += "(";
        }

        return rtn;
    }

    public String solution(String p) {
        String answer = go(p);

        return answer;
    }
}