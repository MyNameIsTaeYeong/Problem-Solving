import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmds) {
        String answer = "";

        StringBuilder sb = new StringBuilder("");



        int idx = k;
        int len = n;

        Stack<Integer> stack = new Stack<>();

        for(String cmd : cmds){
            char what;
            int x = 0;
            if(cmd.charAt(0) == 'D' || cmd.charAt(0) == 'U'){
                StringTokenizer st = new StringTokenizer(cmd, " ");
                what = st.nextToken().charAt(0);
                x = Integer.parseInt(st.nextToken());
            }else{
                what = cmd.charAt(0);
            }

            if(what == 'U'){
                idx -= x;
            } else if(what == 'D'){
                idx += x;
            } else if(what == 'C'){
                stack.push(idx);
                len--;
                //sb.deleteCharAt(idx);
                if(idx == len)
                    idx--;
            } else if(what == 'Z'){
                int where = stack.pop();
                len++;
                if(where <= idx){
                    idx++;
                }

                //sb.insert(where, 'O');
            }

        }

        for(int i=0; i<len; i++){
            sb.append("O");
        }

        while(!stack.isEmpty()){
            int where = stack.pop();
            sb.insert(where, 'X');
        }


        answer = sb.toString();

        return answer;
    }
}