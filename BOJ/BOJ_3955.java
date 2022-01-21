package problem_solving_java;

import java.util.*;
import java.io.*;

class Triple {
    long s, t, r;

    public Triple(long s, long t, long r){
        this.s = s;
        this.t = t;
        this.r = r;
    }
}

public class BOJ_3955 {

    static final long MAXCNT = 1000000000;

    static Triple extendedGcd(long a, long b){
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        while(r0 % r1 != 0){
            long q = r0 / r1;

            long tempT = t0 - t1 * q;
            long tempS = s0 - s1 * q;
            long tempR = r0 - r1 * q;

            s0 = s1;
            t0 = t1;
            r0 = r1;

            s1 = tempS;
            t1 = tempT;
            r1 = tempR;
        }


        return new Triple(s1, t1, r1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());


        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long k = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            Triple sol = extendedGcd(k, c);
            // 해가 존재하는지 확인
            if(1 % sol.r != 0){
                System.out.println("IMPOSSIBLE");
                continue;
            }

            // 해가 존재하는 경우 && 해의 범위 체크 x < 0, 0 < y <= 10^9
            if(sol.s < 0 && 0 < sol.t && sol.t <= MAXCNT ){
                System.out.println(sol.t);
                continue;
            }

            // 일반해 구하기
            double maxFromX = Math.ceil((double) (-1 * sol.s) / (double) c) - 1;
            double maxFromY = Math.ceil((double) sol.t / (double) k);

            double candidate = Math.min(maxFromX, maxFromY);

            double minRange = ((double) (sol.t - MAXCNT) / (double) k);

            if(minRange <= candidate){
                System.out.println(sol.t - k * (long) candidate);
            }else{
                System.out.println("IMPOSSIBLE");
            }


        }


    }
}
