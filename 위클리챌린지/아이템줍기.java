/* 생각하지 못한 점
 시작점 * 2, 끝점 * 2 + 1을 하여 1을 채우고 내부를 0으로 채우면 사각형 둘레를 배열로 표현할 수 있다.
*/
import java.util.*;

class Node {
    int x;
    int y;
    int dist;
    public Node(int x, int y, int dist){
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class Solution {

    static int[][] cordinate = new int[110][110];
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int total, patial;

    static int bfs(int startX, int startY, int destX, int destY){
        int rtn = 0;
        boolean[][] check = new boolean[110][110];
        check[startX][startY] = true;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0));

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == destX && cur.y == destY)
                return cur.dist;

            for(int i=0; i<4; i++){
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if(0 <= nextX && nextX <110 && 0 <= nextY && nextY < 110){
                    if(cordinate[nextX][nextY] == 1 && !check[nextX][nextY]){
                        check[nextX][nextY] = true;
                        q.add(new Node(nextX, nextY, cur.dist+1));
                    }
                }
            }
        }

        return rtn;
    }

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        for(int[] cordi : rectangle){
            int startR = cordi[1]* 2;
            int startC = cordi[0]* 2;
            int endR = cordi[3] * 2 + 1;
            int endC = cordi[2] * 2 + 1;

            for(int x=startR; x<endR; x++){
                for(int y=startC; y<endC; y++){
                    cordinate[x][y] = 1;
                }
            }
        }

        for(int[] cordi : rectangle){
            int startR = cordi[1]* 2;
            int startC = cordi[0]* 2;
            int endR = cordi[3] * 2 + 1;
            int endC = cordi[2] * 2 + 1;

            for(int x=startR+1; x<endR-1; x++){
                for(int y=startC+1; y<endC-1; y++){
                    cordinate[x][y] = 0;
                }
            }
        }


        answer = bfs(characterY * 2, characterX * 2, itemY * 2, itemX * 2) / 2;



        return answer;
    }
}