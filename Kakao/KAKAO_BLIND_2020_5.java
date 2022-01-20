import java.util.*;

class Solution {
    
    static int[][] gidoong;
    static int[][] bo;
    
    
    static boolean build_gidoong_check(int x, int y){
        if(y == 0 
           || (x > 0 && bo[x-1][y] == 1) 
           || bo[x][y] == 1 
           || (y > 0 && gidoong[x][y-1] == 1))
        {
            return true;
        }
        return false;
    }
    
    static boolean build_bo_check(int x, int y){
        if((y > 0 && gidoong[x][y-1] == 1) 
           || (y > 0 && x < bo.length && gidoong[x+1][y-1] == 1) 
           || (x > 0 && x < bo.length && bo[x-1][y] == 1 && bo[x+1][y] == 1))
        {
            return true;
        }
        
        return false;
    }
    
    static void delete_gidoong(int x, int y){
        gidoong[x][y] = 0;
        
        if(y < bo.length && gidoong[x][y+1] == 1 && !build_gidoong_check(x, y+1)){
            System.out.print("기둥1" + " ");
            gidoong[x][y] = 1;
            return;
        }
        
        if(y < bo.length && x > 0 && bo[x-1][y+1] == 1 && !build_bo_check(x-1, y+1)){
            System.out.print("기둥2" + " ");
            gidoong[x][y] = 1;
            return;
        }
        
        if(y < bo.length && bo[x][y+1] == 1 && !build_bo_check(x, y+1)){
            System.out.print("기둥3" + " ");
            gidoong[x][y] = 1;
            return;
        }
        
        System.out.print("기둥 삭제완료" + " ");
    }
    
    static void delete_bo(int x, int y){
        bo[x][y] = 0;
        
        if(x > 0 && bo[x-1][y] == 1 && !build_bo_check(x-1, y)){
            bo[x][y] = 1;
            return;
        }
        
        if(x < bo.length && bo[x+1][y] == 1 && !build_bo_check(x+1, y)){
            bo[x][y] = 1;
            return;
        }
        
        if(gidoong[x][y] == 1 && !build_gidoong_check(x,y)) {
            bo[x][y] = 1;
            return;
        }
        
        if(x < bo.length && gidoong[x+1][y] == 1 && !build_gidoong_check(x+1,y)) {
            bo[x][y] = 1;
            return;
        }
        
        if(y > 0 && gidoong[x][y-1] == 1 && !build_gidoong_check(x,y-1)){
            bo[x][y] = 1;
            return;
        }
        
        if(y > 0 && x < bo.length && gidoong[x+1][y-1] == 1 && !build_gidoong_check(x+1,y-1)){
            bo[x][y] = 1;
            return;
        }
        
        System.out.print("보 삭제완료" + " ");
    }
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        gidoong = new int[n+1][n+1];
        bo = new int[n+1][n+1];
        
        for(int[] frame : build_frame){
            int x = frame[0];
            int y = frame[1];
            int a = frame[2];
            int b = frame[3];
            
            // 설치
            if(b == 1){
                // 기둥
                if(a == 0){
                    if(build_gidoong_check(x, y)){
                        gidoong[x][y] = 1;
                        System.out.print("("+x + ", "+ y+")" + "기둥 설치" + " ");
                    }
                }
                // 보
                else{
                    if(build_bo_check(x, y)){
                        bo[x][y] = 1;
                        System.out.print("("+x + ", "+ y+")" + "보 설치" + " ");
                    }
                }
            }
            // 삭제
            else{
                // 기둥
                if(a == 0){
                    delete_gidoong(x, y);
                    System.out.print("("+x + ", "+ y+")" + "기둥 삭제" + " ");
                }
                // 보
                else{
                    delete_bo(x, y);
                    System.out.print("("+x + ", "+ y+")" + "보 삭제" + " ");
                }
            }
        }
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        for(int x=0; x<=n; x++){
            for(int y=0; y<=n; y++){
                if(gidoong[x][y] == 1){
                    ArrayList<Integer> input = new ArrayList<>();
                    input.add(x);
                    input.add(y);
                    input.add(0);
                    ans.add(input);
                }
                if(bo[x][y] == 1){
                     ArrayList<Integer> input = new ArrayList<>();
                    input.add(x);
                    input.add(y);
                    input.add(1);
                    ans.add(input);
                }
            }
        }
        
        answer = new int[ans.size()][3];
        
        for(int i=0; i<ans.size(); i++){
            answer[i][0] = ans.get(i).get(0);
            answer[i][1] = ans.get(i).get(1);
            answer[i][2] = ans.get(i).get(2);
        }
        
        return answer;
    }
}