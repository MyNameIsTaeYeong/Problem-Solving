package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_1837 {

    static boolean divide(String bigInteger, int num){
        int remain = 0;
        for(int i=0; i<bigInteger.length(); i++){
            remain *= 10;
            remain += bigInteger.charAt(i) - '0';
            if(remain % num == 0){
                remain = 0;
                continue;
            }

            remain = remain % num;
        }

        if(remain == 0)
            return true;
        else
            return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String bigInteger = st.nextToken();
        int k = Integer.parseInt(st.nextToken());

        boolean[] isNotPrime = new boolean[k+1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        List<Integer> prime = new ArrayList<>();

        for(int i=2; i<k; i++){
            if(!isNotPrime[i]){
                prime.add(i);
                for(int j=i*2; j<k; j+=i){
                    isNotPrime[j] = true;
                }
            }
        }

        String ans = null;
        int ansPrime = -1;
        for (int i = 0; i < prime.size(); i++) {
            if(divide(bigInteger, prime.get(i))){
                ans = new String("BAD");
                ansPrime = prime.get(i);
                break;
            }
        }

        if(ans == null){
            System.out.println("GOOD");
        }else{
            System.out.println(ans + " " + ansPrime);
        }

    }
}
