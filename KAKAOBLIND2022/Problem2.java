import java.util.*;

class Solution {

    static boolean isPrime(long num){
        if(num == 1)
            return false;

        for(long i=2; i*i <= num ; i++){
            if(num % i == 0)
                return false;
        }
        return true;
    }

    public int solution(int n, int k) {
        int answer = 0;

        StringBuilder sb = new StringBuilder("");

        while(n != 0){
            sb.append((char)(n % k + '0'));
            n /= k;
        }

        sb.reverse();



        StringTokenizer st = new StringTokenizer(sb.toString(), "0");

        while(st.hasMoreTokens()){
            long num = Long.parseLong(st.nextToken());
            //System.out.print(num + " ");
            if(isPrime(num))
                answer++;
        }

        return answer;
    }
}