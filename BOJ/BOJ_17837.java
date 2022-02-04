package problem_solving_java;

import java.util.*;
import java.io.*;

class Unit{
    int x;
    int y;
    int dir;
    public Unit(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class BOJ_17837 {

    static int N, K;
    static List<Integer>[][] boardState;
    static int[][] board;
    static Unit[] units;
    // 1부터 순서대로 →, ←, ↑, ↓
    static int[] dx = {-100, 0, 0, -1, 1};
    static int[] dy = {-100, 1, -1, 0, 0};

    static void goWite(int curX, int curY, int nextX, int nextY, int num){
        int curIdx = boardState[curX][curY].indexOf(num);
        int lastIdx = boardState[curX][curY].size() - 1;

        for (int i = curIdx; i <= lastIdx; i++) {
            int curNum = boardState[curX][curY].get(i);
            units[curNum].x = nextX;
            units[curNum].y = nextY;
            boardState[nextX][nextY].add(curNum);
        }

        for (int i = lastIdx; i >= curIdx ; i--) {
            boardState[curX][curY].remove(i);
        }
    }

    static void goRed(int curX, int curY, int nextX, int nextY, int num) {
        int curIdx = boardState[curX][curY].indexOf(num);
        int lastIdx = boardState[curX][curY].size() - 1;

        for (int j = lastIdx; j >= curIdx ; j--) {
            int curNum = boardState[curX][curY].get(j);
            units[curNum].x = nextX;
            units[curNum].y = nextY;
            boardState[nextX][nextY].add(curNum);
            boardState[curX][curY].remove(j);
        }
    }

    static void solve(){

        int turn = 0;

        while(true){
            turn++;
            if(turn > 1000){
                break;
            }

            for(int i=0; i<K; i++){
                int curX = units[i].x;
                int curY = units[i].y;
                int nextX = units[i].x + dx[units[i].dir];
                int nextY = units[i].y + dy[units[i].dir];

                // 범위 안이면서 다음 칸이 흰색 또는 빨간색인 경우
                if(1 <= nextX && nextX <= N && 1 <= nextY && nextY <= N && (board[nextX][nextY] == 0 || board[nextX][nextY] == 1)){
                    // 흰색 칸
                    if (board[nextX][nextY] == 0){
                        // 이동하려는 칸에 말이 있다면
                        // 현재 말 위에 말이 있다면
                        goWite(curX, curY, nextX, nextY, i);
                    }

                    // 빨간색 칸
                    else {
                        // 이동하려는 칸에 말이 있다면
                        goRed(curX, curY, nextX, nextY, i);
                    }

                    if(boardState[nextX][nextY].size() >= 4){
                        System.out.println(turn);
                        return;
                    }
                }
                // 범위 밖 또는 파란색 칸
                else {
                    //방향을 반대로 바꾼 후에 이동하려는 칸이 파란색인 경우
                    switch (units[i].dir){
                        case 1:
                            units[i].dir = 2;
                            break;
                        case 2:
                            units[i].dir = 1;
                            break;
                        case 3:
                            units[i].dir = 4;
                            break;
                        case 4:
                            units[i].dir = 3;
                            break;
                    }

                    nextX = units[i].x + dx[units[i].dir];
                    nextY = units[i].y + dy[units[i].dir];

                    if(1 <= nextX && nextX <= N && 1 <= nextY && nextY <= N && board[nextX][nextY] != 2){
                        i--;
                        continue;
                    }
                }

            }


        }

        System.out.println(-1);


    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        boardState = new List[N+1][N+1];
        board = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                boardState[i][j] = new ArrayList<>();
            }
        }

        units = new Unit[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            units[i] = new Unit(x, y, dir);
            boardState[x][y].add(i);
        }

        solve();

    }
}

//        0 1 2 0 1 1
//        1 2 0 1 1 0
//        2 1 0 1 1 0
//        1 0 1 1 0 2
//        2 0 1 2 0 1
//        0 2 1 0 2 1