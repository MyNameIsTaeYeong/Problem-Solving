class Solution {

    // 0, 1 : 가로,  2, 3 : 세로
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int[][][] d;
    static int minCost = Integer.MAX_VALUE;

    static int n;

    static void go(int x, int y, int[][] board, int cost, boolean[][] check, int dir){
        // 1. 체크인
        if(d[dir][x][y] != 0 && cost > d[dir][x][y]){
            return;
        }

        check[x][y] = true;
        d[dir][x][y] = cost;


        // 2. 목적지인가
        if(x == n-1 && y == n-1){
            if(cost < minCost){
                minCost = cost;
            }
            check[x][y] = false;
            return;
        }

        // 3. 연결되었는가
        for(int i=0; i<4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            // 4. 갈 수 있는가
            if(0 <= nextX && nextX < n && 0 <= nextY && nextY < n
                    && !check[nextX][nextY] && board[nextX][nextY] == 0){
                // 5. 간다.
                // 이전이 가로
                if(dir == 0){
                    if(i == 0 || i == 1){
                        go(nextX, nextY, board, cost + 100, check, 0);
                    }else{
                        go(nextX, nextY, board, cost + 600, check, 1);
                    }
                }
                // 이전이 세로
                else{
                    // 가로
                    if(i == 0 || i == 1){
                        go(nextX, nextY, board, cost + 600, check, 0);
                    }else{
                        go(nextX, nextY, board, cost + 100, check, 1);
                    }
                }
            }
        }


        // 6. 체크아웃
        check[x][y] = false;
    }

    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        d = new int[2][n][n];

        boolean[][] check = null;
        if(board[0][1] == 0){
            check = new boolean[n][n];
            check[0][0] = true;
            go(0, 1, board, 100, check, 0);
        }

        if(board[1][0] == 0){
            check = new boolean[n][n];
            check[0][0] = true;
            go(1, 0, board, 100, check, 1);
        }

        answer = minCost;

        return answer;
    }
}