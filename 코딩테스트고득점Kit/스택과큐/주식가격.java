import java.util.*;

class Pair{
    int price;
    int idx;
    public Pair(int price, int idx){
        this.price = price;
        this.idx = idx;
    }
}

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Stack<Pair> stack = new Stack<>();

        for(int i=0; i<prices.length; i++){
            while(!stack.isEmpty() && prices[i] < stack.peek().price){
                answer[stack.peek().idx] = i - stack.peek().idx;
                stack.pop();
            }
            stack.push(new Pair(prices[i], i));
        }

        while(!stack.isEmpty()){
            answer[stack.peek().idx] = prices.length - 1 - stack.peek().idx;
            stack.pop();
        }


        return answer;
    }
}