import java.util.*;


// board[x][y][0] , board[x][y][1]
// 설치


class Triple implements Comparable<Triple> {
    int x;
    int y;
    int a;
    public Triple(int x, int y, int a){
        this.x = x;
        this.y = y;
        this.a = a;
    }

    @Override
    public int compareTo(Triple t){
        if(this.y == t.y){
            if(this.x == t.x)
                return this.a - t.a;
            else
                return this.x - t.x;
        }else
            return this.y - t.y;
    }
}

class Solution {

    static boolean[][][] board;

    static boolean possible(int x, int y, int a, int n){
        // 0은 기둥, 1은 보
        if(a == 0){
            // 밑바닥
            if(x == 0)
                return true;

            // 보의 한 쪽 끝부분
            if((y > 0 && board[x][y-1][1]) || board[x][y][1])
                return true;

            // 또 다른 기둥 위
            if(x > 0 && board[x-1][y][0])
                return true;

        }
        // 보
        else{
            // 한쪽 끝 부분이 기둥 위
            if((x > 0 && board[x-1][y][0]) || (x > 0 && y + 1 <= n && board[x-1][y+1][0]))
                return true;

            if(y > 0 && y + 1 < n && board[x][y-1][1] && board[x][y+1][1])
                return true;
        }

        return false;
    }

    static void install(int x, int y, int a, int n){
        if(possible(x, y, a, n)){
            board[x][y][a] = true;
            //System.out.println(x + ", " + y + " 에 " + a + "설치");
        }

    }

    static void delete(int x, int y, int a, int n){

        boolean deletePossible = true;
        board[x][y][a] = false;

        if(a == 1){
            if(board[x][y][0] && !possible(x, y, 0, n))
                deletePossible = false;

            else if(board[x][y+1][0] && !possible(x, y+1, 0, n))
                deletePossible = false;

            else if(y > 0 && board[x][y-1][1] && !possible(x, y-1, 1, n))
                deletePossible = false;

            else if(y+1 < n && board[x][y+1][1] && !possible(x, y+1, 1, n))
                deletePossible = false;
        }
        else {
            if(x+1 < n && board[x+1][y][0] && !possible(x+1, y, 0, n))
                deletePossible = false;

            if(x+1 <= n && board[x+1][y][1] && !possible(x+1, y, 1, n))
                deletePossible = false;

            if(x+1 <= n && y > 0 && board[x+1][y-1][1] && !possible(x+1, y-1, 1, n))
                deletePossible = false;
        }

        if(!deletePossible){
            board[x][y][a] = true;
        }
    }

    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};

        board = new boolean[n+1][n+1][2];

        for(int[] build : build_frame){
            int y = build[0];
            int x = build[1];
            int a = build[2];
            int b = build[3];

            if(b == 0){
                //삭제
                delete(x, y, a, n);
            }else{
                //설치
                install(x, y, a, n);
            }
        }

        List<Triple> list = new ArrayList<>();

        for(int i=0; i<=n; i++){
            for(int j=0; j<=n; j++){
                if(board[i][j][0])
                    list.add(new Triple(i, j, 0));
                if(board[i][j][1])
                    list.add(new Triple(i, j, 1));
            }
        }

        Collections.sort(list);

        answer = new int[list.size()][3];

        for(int i=0; i<list.size(); i++){
            answer[i][0] = list.get(i).y;
            answer[i][1] = list.get(i).x;
            answer[i][2] = list.get(i).a;
        }

        return answer;
    }
}