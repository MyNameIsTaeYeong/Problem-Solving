import java.util.*;

class Solution {

    static int[][] rotate(int[][] key){
        int m = key.length;

        int[][] newKey = new int[m][m];


        for(int y=0; y<m; y++){
            int k = 0;
            for(int x=m-1; x>=0; x--){
                newKey[y][k++] = key[x][y];
            }
        }

        return newKey;
    }

    static boolean fillLock(int[][] checkBoard, int[][] lock, int m, int n){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                checkBoard[m-1+i][m-1+j] += lock[i][j];
                if(checkBoard[m-1+i][m-1+j] != 1)
                    return false;
            }
        }

        return true;
    }

    static void fillKey(int[][] checkBoard, int[][] key, int x, int y, int m){
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                checkBoard[x+i][y+j] += key[i][j];
            }
        }
    }

    static boolean go(int[][] key, int[][] lock){
        int m = key.length;
        int n = lock.length;

        int k = n + 2 * m - 2;

        for(int i=0; i < m - 1 + n; i++){
            for(int j=0; j < m - 1 + n; j++){
                int[][] checkBoard = new int[k][k];
                fillKey(checkBoard, key, i, j, m);
                if(fillLock(checkBoard, lock, m, n))
                    return true;
            }
        }
        return false;
    }

    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        for(int i=0; i<4; i++){
            int[][] newKey = rotate(key);
            if(go(newKey, lock)){
                answer = true;
                break;
            }
            key = newKey;
        }


        return answer;
    }
}