package problem_solving_java;

import java.io.*;
import java.util.*;

public class BOJ_5582 {

    // len[i][j] : s1의 i번째까지와 s2의 j번째까지 중 가장 긴 부분 문자열의 길이.
    static int[][] len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s2 = br.readLine();
        String s1 = br.readLine();

        len = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    len[i][j] = len[i-1][j-1] + 1;
                }
            }
        }

        int ans = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                ans = Math.max(ans, len[i][j]);
            }
        }

        System.out.println(ans);
    }
}
