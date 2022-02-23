class Solution {

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static long startX, startY, endX, endY;

    static void go(int dir, int cnt, int n, int m){
        if(dir == 0){
            if(startY > 0 || endY <= 0)
                startY += cnt;

            endY += cnt;
            return;
        }

        if(dir == 1){
            if(startY >= m || endY < m)
                endY -= cnt;

            startY -= cnt;
            return;
        }

        if(dir == 2){
            if(startX > 0 || endX <= 0)
                startX += cnt;
            endX += cnt;
            return;
        }

        if(dir == 3){
            if(startX >= n || endX < n)
                endX -= cnt;
            startX -= cnt;
            return;
        }
    }

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = -1;

        startX = x;
        startY = y;
        endX = x+1;
        endY = y+1;

        int lastIdx = queries.length-1;

        for(int i=lastIdx; i>=0; i--){
            go(queries[i][0], queries[i][1], n, m);
            //System.out.println(startX + ", "+startY + " " + endX + ", "+endY);
        }

        if(startX >= n || startY >= m || endX <= 0 || endY <= 0)
            answer = 0;
        else{
            if(startX < 0)
                startX = 0;
            if(startY < 0)
                startY = 0;
            if(endX > n)
                endX = n;
            if(endY > m)
                endY = m;

            answer = (endX - startX) * (endY - startY);
        }


        return answer;
    }
}