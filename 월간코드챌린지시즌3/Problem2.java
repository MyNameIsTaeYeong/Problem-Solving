import java.util.*;

class Solution {

    static boolean[][][] check;
    static int r, c;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    // 0 : 위, 1 : 아래, 2 : 왼쪽, 3 : 오른쪽
    static int nextDir(int dir, int ch){
        if(ch == 'S'){
            return dir;
        }

        if(ch == 'R'){
            if(dir == 0)
                return 3;

            if(dir == 1)
                return 2;

            if(dir == 2)
                return 0;

            if(dir == 3)
                return 1;
        }
        // 0 : 위, 1 : 아래, 2 : 왼쪽, 3 : 오른쪽
        if(ch == 'L'){
            if(dir == 0)
                return 2;

            if(dir == 1)
                return 3;

            if(dir == 2)
                return 1;

            if(dir == 3)
                return 0;
        }

        return -1;
    }

    static int go(int x, int y, int dir, String[] grid){
        int rtn = 0;

        // 0 : 위, 1 : 아래, 2 : 왼쪽, 3 : 오른쪽
        while(!check[x][y][dir]){
            check[x][y][dir] = true;
            char ch = grid[x].charAt(y);
            int nDir = nextDir(dir, ch);
            int nextX = x + dx[nDir];
            int nextY = y + dy[nDir];
            if(nextX < 0)
                nextX = r-1;
            else if(nextX == r)
                nextX = 0;

            if(nextY < 0)
                nextY = c-1;
            else if(nextY == c)
                nextY = 0;

            x = nextX;
            y = nextY;
            dir = nDir;
            rtn++;
        }

        return rtn;
    }

    public int[] solution(String[] grid) {
        int[] answer = {};

        r = grid.length;
        c = grid[0].length();

        check = new boolean[r][c][4];

        List<Integer> list = new ArrayList<>();

        for(int i=0; i<r; i++){
            for(int j=0; j<c; j++){
                for(int k=0; k<4; k++){
                    if(!check[i][j][k]){
                        int len = go(i, j, k, grid);
                        list.add(len);
                    }
                }
            }
        }

        Collections.sort(list);
        answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }

        return answer;
    }
}