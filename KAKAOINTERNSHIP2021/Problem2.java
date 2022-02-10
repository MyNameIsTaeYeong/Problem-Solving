import java.util.*;

class Solution {
    // ->, <-, bottom, up
    static int[] dx1 = {0, 0, 1, -1};
    static int[] dy1 = {1, -1, 0, 0};

    static int[] dx2 = {0, 0, 2, -2};
    static int[] dy2 = {2, -2, 0, 0};

    static int[] dx3 = {-1, -1, 1, 1};
    static int[] dy3 = {-1, 1, 1, -1};

    static boolean check1(String[] place, int x, int y){
        for(int i=0; i<4; i++){
            int nextX = x + dx1[i];
            int nextY = y + dy1[i];

            if(0<=nextX && nextX<5 && 0<=nextY && nextY<5){
                if(place[nextX].charAt(nextY) == 'P')
                    return true;
            }
        }

        return false;
    }

    static boolean check2(String[] place, int x, int y){
        for(int i=0; i<4; i++){
            int nextX = x + dx2[i];
            int nextY = y + dy2[i];

            if(0<=nextX && nextX<5 && 0<=nextY && nextY<5){
                if(place[nextX].charAt(nextY) == 'P'){
                    if(place[x + dx1[i]].charAt(y + dy1[i]) == 'O')
                        return true;
                }
            }
        }

        return false;
    }

    static boolean check3(String[] place, int x, int y){
        for(int i=0; i<4; i++){
            int nextX = x + dx3[i];
            int nextY = y + dy3[i];

            if(0<=nextX && nextX<5 && 0<=nextY && nextY<5){
                if(place[nextX].charAt(nextY) == 'P'){
                    if(i == 0
                            && (place[x-1].charAt(y) == 'O' || place[x].charAt(y-1) == 'O'))
                        return true;
                    else if(i == 1 && (place[x-1].charAt(y) == 'O' || place[x].charAt(y+1) == 'O'))
                        return true;
                    else if(i == 2 && (place[x].charAt(y+1) == 'O' || place[x+1].charAt(y) == 'O'))
                        return true;
                    else if(i == 3 && (place[x+1].charAt(y) == 'O' || place[x].charAt(y-1) == 'O'))
                        return true;
                }

            }
        }
        return false;
    }

    public int[] solution(String[][] places) {
        int[] answer = {};

        List<Integer> ans = new ArrayList<>();

        for(String[] place : places){
            boolean bad = false;

            for(int i=0; i<5; i++){
                for(int j=0; j<5; j++){
                    if(place[i].charAt(j) == 'P'){
                        if(check1(place, i, j)){
                            bad = true;
                            //System.out.print(1);
                            break;
                        }

                        if(check2(place, i, j)){
                            bad = true;
                            break;
                        }

                        if(check3(place, i, j)){
                            bad = true;
                            break;
                        }
                    }

                }
                if(bad)
                    break;
            }

            if(bad){
                ans.add(0);
            }else{
                ans.add(1);
            }


            //break;


        }

        answer = new int[ans.size()];
        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }
}