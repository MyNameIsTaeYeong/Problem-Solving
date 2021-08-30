class Solution {
    
    static int n, m, size;
    
    static boolean check(int x, int y, int[][] key, int[][] lock){
        int[][] board = new int[size][size];
        
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                board[x+i][y+j] = key[i][j];
            }
        }
        
         for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                board[m-1+i][m-1+j] += lock[i][j];
                if(board[m-1+i][m-1+j] != 1){
                    return false;
                }
            }
        }
        
        return true;
        
    }
    
    static boolean solve(int[][] key, int[][] lock){
       
        //60
        for(int x=0; x < (m + n - 1); x++){
            //60
            for(int y=0; y < (m + n - 1); y++){
                //400
                if(check(x, y, key, lock)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
    static int[][] rotate(int[][] key){
        int[][] new_key = new int[m][m];
        
        int x = m-1;
        int y = 0;
        
        for(int i=0; i<m; i++){
            for(int j=0; j<m; j++){
                new_key[i][j] = key[x][y];
                x--;
                if(x < 0){
                    x = m-1;
                    y++;
                }
            }
        }
        
        return new_key;
    }
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        
        n = lock.length;
        m = key.length;
        size = 2 * (m-1) + n;
        
       
        
        for(int i=0; i<4; i++){
            if(solve(key, lock)){
                answer = true;
                break;
            }
            key = rotate(key);
            
        }
        
        return answer;
    }
}