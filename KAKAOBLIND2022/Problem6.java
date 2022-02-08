class Solution {

    static int n, m;
    static int[] arr;

    public int solution(int[][] board, int[][] skills) {
        int answer = 0;

        n = board.length;
        m = board[0].length;

        arr = new int[n * m + 1];

        for(int[] skill : skills){
            int type = skill[0];
            int r1 = skill[1];
            int c1 = skill[2];
            int r2 = skill[3];
            int c2 = skill[4];
            int degree = skill[5];
            for(int x=r1; x<=r2; x++){
                // 공격
                if(type == 1){
                    arr[x * m + c1] -= degree;
                    arr[x * m + c2 + 1] += degree;
                }else{
                    arr[x * m + c1] += degree;
                    arr[x * m + c2 + 1] -= degree;
                }
            }

            // for(int i=0; i<= n * m; i++){
            //     System.out.print(arr[i] + " ");
            // }
            // System.out.println();
        }




        for(int i=1; i<= n * m; i++){
            arr[i] += arr[i-1];
        }


        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                board[i][j] += arr[i * n + j];
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j] > 0)
                    answer++;
            }
        }


        return answer;
    }
}