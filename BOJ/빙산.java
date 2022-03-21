package problem_solving_java;

import java.io.*;
import java.util.*;

public class 빙산 {

    static int N, M;
    static int[][] land;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void dfs(int x, int y, boolean[][] check){
        check[x][y] = true;

        for(int i=0; i<4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(land[nextX][nextY] != 0 && !check[nextX][nextY])
                dfs(nextX, nextY, check);
        }
    }

    static void go(){
        int[][] cnt = new int[N][M];

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(land[i][j] != 0){
                    for(int dir=0; dir<4; dir++){
                        if(land[i+dx[dir]][j+dy[dir]] == 0)
                            cnt[i][j]++;
                    }
                }
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(land[i][j] != 0){
                    if(land[i][j] < cnt[i][j])
                        land[i][j] = 0;
                    else
                        land[i][j] -= cnt[i][j];

                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        land = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                land[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (true){
            ans++;
            int cnt = 0;
            go();

            boolean[][] check = new boolean[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(land[i][j] != 0 && !check[i][j]){
                        dfs(i, j, check);
                        cnt++;
                    }
                }
            }

            if(cnt >= 2)
                break;
            if(cnt == 0){
                ans = 0;
                break;
            }
        }


        System.out.println(ans);
    }
}
