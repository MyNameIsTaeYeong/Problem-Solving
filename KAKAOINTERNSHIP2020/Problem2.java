import java.util.*;

class Solution {

    static List<Character> operator = new ArrayList<>();
    static long maxValue = 0;

    static long cal(long num1, long num2, char op){
        long rtn = 0;
        switch(op){
            case '+':
                rtn = num1 + num2;
                break;
            case '-':
                rtn = num1 - num2;
                break;
            case '*':
                rtn = num1 * num2;
                break;
        }

        return rtn;
    }

    static long check(String expression){
        Stack<Long> numStack = new Stack<>();
        Stack<Character> operStack = new Stack<>();

        String num = "";
        for(int i=0; i<expression.length(); i++){
            char cur = expression.charAt(i);

            if(cur - '0' >= 0 && cur - '0' < 10){
                num += cur;
            }else{
                // 1. 숫자는 숫자스택에
                numStack.push(Long.parseLong(num));
                num = "";

                // 2. 연산자 스택이 비어있다면 연산자 푸쉬
                if(operStack.isEmpty())
                    operStack.push(cur);

                    // 3. 비어있지 않다면 탑과 비교후
                else{
                    //  3.a 자기가 낮거나 같다면 숫자스택 1개와 현재숫자 연산자 스택1 개를 뽑아 계산후 다시 숫자스택에 반복
                    while(!operStack.isEmpty()
                            && operator.indexOf(operStack.peek()) <= operator.indexOf(cur)){
                        long num2 = numStack.pop();
                        long num1 = numStack.pop();
                        char op = operStack.pop();
                        numStack.push(cal(num1, num2, op));
                    }
                    //  3.b 자기가 높다면 연산자스택에 푸쉬
                    operStack.push(cur);
                }
            }
        }
        // 3. 숫자스택, 연산자스택 비우면서 나머지 계산
        numStack.push(Long.parseLong(num));
        while(!operStack.isEmpty()){
            long num2 = numStack.pop();
            long num1 = numStack.pop();
            char op = operStack.pop();
            numStack.push(cal(num1, num2, op));
        }

        return numStack.pop();
    }

    static void permutation(char[] op, boolean[] flag, String expression){
        if(operator.size() == 3){
            maxValue = Math.max(maxValue, Math.abs(check(expression)));
            return;
        }


        for(int i=0; i<3; i++){
            if(!flag[i]){
                flag[i] = true;
                operator.add(op[i]);
                permutation(op, flag, expression);
                operator.remove(operator.size()-1);
                flag[i] = false;
            }
        }

    }

    public long solution(String expression) {
        long answer = 0;

        char[] op = {'+', '-', '*'};

        permutation(op, new boolean[3], expression);

        answer = maxValue;

        return answer;
    }
}