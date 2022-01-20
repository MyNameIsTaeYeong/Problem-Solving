package problem_solving_java;

import java.util.*;
import java.io.*;

class Trie {
    char alphabet;
    boolean isEnd;
    boolean isHit;
    Trie[] child;

    public Trie(){
        this.isEnd = false;
        this.isHit = false;
        child = new Trie[26];
    }

    public void insert(String word, int idx){
        alphabet = word.charAt(idx);

        if(idx + 1 == word.length()){
            isEnd = true;
            return;
        }

        int childIdx = word.charAt(idx+1)-'A';

        if(child[childIdx] == null){
            child[childIdx] = new Trie();
        }

        child[childIdx].insert(word, idx+1);
    }



}


public class BOJ_9202 {

    static int w;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
    static Trie root;
    static int score, cnt;
    static StringBuilder longest;

    static void memo(StringBuilder word){
        cnt++;
        int len = word.length();
        if(len <= 2){

        }else if(len <= 4){
            score += 1;
        }else if(len == 5){
            score += 2;
        }else if(len == 6){
            score += 3;
        }else if(len == 7){
            score += 5;
        }else {
            score += 11;
        }

        if(word.length() > longest.length())
            longest = new StringBuilder(word);
        if(word.length() == longest.length()){
            if(word.compareTo(longest) < 0){
                longest = new StringBuilder(word);
            }
        }
    }

    static void dfs(int x, int y, boolean[][] check, Trie cur, StringBuilder word, char[][] boggle){
        check[x][y] = true;

        if(cur.isEnd && !cur.isHit){
            cur.isHit = true;
            memo(word);
        }

        for(int i=0; i<8; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(0<=nextX && nextX <4 && 0<=nextY && nextY < 4 && !check[nextX][nextY] && cur.child[boggle[nextX][nextY] - 'A'] != null){
                word.append(boggle[nextX][nextY]);
                dfs(nextX, nextY, check, cur.child[boggle[nextX][nextY] - 'A'], word, boggle);
                word.deleteCharAt(word.length()-1);
            }
        }

        check[x][y] = false;
    }

    static void search(char[][] boggle){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(root.child[boggle[i][j]-'A'] != null)
                    dfs(i, j, new boolean[4][4], root.child[boggle[i][j]-'A'], new StringBuilder(boggle[i][j]+""), boggle );
            }
        }
    }

    static void clear(Trie cur){
        cur.isHit = false;

        for (int i = 0; i < cur.child.length; i++) {
            if(cur.child[i] != null){
                clear(cur.child[i]);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = Integer.parseInt(br.readLine());

        root = new Trie();

        for (int i = 0; i < w; i++) {
            String word = br.readLine();
            if(root.child[word.charAt(0) - 'A'] == null)
                root.child[word.charAt(0) - 'A'] = new Trie();
            root.child[word.charAt(0) - 'A'].insert(word, 0);
        }

        br.readLine();
        int b = Integer.parseInt(br.readLine());

        for (int i = 0; i < b; i++) {
            score = 0;
            cnt = 0;
            longest = new StringBuilder("");
            char[][] boggle = new char[4][4];
            for (int j = 0; j < 4; j++) {
                String line = br.readLine();
                for (int k = 0; k < 4; k++) {
                    boggle[j][k] = line.charAt(k);
                }
            }

            // start
            search(boggle);

            System.out.println(score+" "+longest+" "+cnt);

            // clear
            clear(root);

            br.readLine();
        }
    }
}


//        String a = new String("bb");
//        String b = new String("bb");
//        System.out.println(a.compareTo(b));