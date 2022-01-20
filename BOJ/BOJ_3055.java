package 탈출;

import java.util.*;
import java.io.*;

// 1. 큐에서 꺼내옴
// 2. 목적지인가?
// 3. 연결된 곳을 순회한다.
// 4. 갈 수 있는가?
// 5. 체크인
// 6. 큐어 넣음

class Pair {
    public int x;
    public int y;
    public char which;

    public Pair(int x, int y, char which){
        this.x = x;
        this.y = y;
        this.which = which;
    }
}

public class BOJ_3055 {

    static int R, C;
    static char[][] map;
    static int[][] dist;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static void bfs(Queue<Pair> q){

        while(!q.isEmpty()){
            Pair p = q.poll();

            if(p.which == 'M' && map[p.x][p.y] == 'D'){
                System.out.println(dist[p.x][p.y]);
                return;
            }

            for(int i=0; i<4; i++){
                int nextX = p.x + dx[i];
                int nextY = p.y + dy[i];

                if(0 <= nextX && nextX < R && 0<= nextY && nextY < C){
                    if(p.which == '*'){
                        if(map[nextX][nextY] == '.'){
                            map[nextX][nextY] = '*';
                            q.add(new Pair(nextX, nextY, '*'));
                        }
                    }else{
                        if((map[nextX][nextY] == '.' || map[nextX][nextY] == 'D') && dist[nextX][nextY] > dist[p.x][p.y] + 1){
                            dist[nextX][nextY] = dist[p.x][p.y] + 1;
                            q.add(new Pair(nextX, nextY, 'M'));
                        }
                    }
                }

            }
        }

        System.out.println("KAKTUS");

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        dist = new int[R][C];

        for(int[] init : dist){
            Arrays.fill(init, 3000);
        }

        Queue<Pair> q = new LinkedList<>();
        Pair start = null;

        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                map[i][j] = line.charAt(j);
                if(map[i][j] == '*'){
                    q.add(new Pair(i, j, '*'));
                }

                if(map[i][j] == 'S'){
                    start = new Pair(i, j, 'M');
                    dist[i][j] = 0;
                }
            }
        }

        q.add(start);
        bfs(q);

    }
}
