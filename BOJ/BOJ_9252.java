package problem_solving_java;

import java.io.*;

public class BOJ_9252 {

    // len[i][j] : s1의 i번째까지와 s2의 j번째까지 중 가장 긴 문자열의 길이.
    static int[][] len;
    static char[][] tracking;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s2 = br.readLine();
        String s1 = br.readLine();

        len = new int[s1.length() + 1][s2.length() + 1];
        tracking = new char[s1.length() + 1][s2.length() + 1];

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    len[i][j] = len[i-1][j-1] + 1;
                    tracking[i][j] = 'c';
                }else{
                    // 위 < 왼
                    if(len[i-1][j] < len[i][j-1]){
                        len[i][j] = len[i][j-1];
                        tracking[i][j] = 'l';
                    }else{
                        len[i][j] = len[i-1][j];
                        tracking[i][j] = 'u';
                    }
                }
            }
        }

        int x = s1.length();
        int y = s2.length();
        StringBuilder seq = new StringBuilder("");
        while( x > 0 && y > 0 ){
            switch (tracking[x][y]){
                case 'c':
                    seq.append(s2.charAt(y-1));
                    x--;
                    y--;
                    break;
                case 'l':
                    y--;
                    break;
                case 'u':
                    x--;
                    break;
            }
        }

        if(len[s1.length()][s2.length()] == 0){
            System.out.println(0);
        }else{
            System.out.println(len[s1.length()][s2.length()]);
            System.out.println(seq.reverse());
        }

    }
}
