import java.util.*;

class Solution {

    static boolean check(String s){
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '['){
                if(i == s.length() - 1)
                    return false;
                stack.push(ch);
            }else{
                if(stack.isEmpty())
                    return false;

                char top = stack.pop();

                if(ch == ')' && top != '(')
                    return false;
                if(ch == '}' && top != '{')
                    return false;
                if(ch == ']' && top != '[')
                    return false;

            }
        }

        return true;
    }

    public int solution(String s) {
        int answer = 0;

        if(s.length() % 2 != 1){
            for(int i=0; i<s.length(); i++){
                String rotate = s.substring(i) + s.substring(0, i);
                if(check(rotate))
                    answer++;
            }
        }



        return answer;
    }
}