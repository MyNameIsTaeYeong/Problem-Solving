package problem_solving_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Fish{
    int x;
    int y;
    int dir;
    public Fish(int x, int y, int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class BOJ_23290 {

    static List<Fish>[][] grid = new List[5][5];
    static int[][] smell = new int[5][5];

    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    static int[] fishDx = {Integer.MAX_VALUE, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] fishDy = {Integer.MAX_VALUE, -1, -1, 0, 1, 1, 1, 0, -1};

    // 상은 1, 좌는 2, 하는 3, 우는 4
    static int[] sharkDx = {Integer.MAX_VALUE, -1, 0, 1, 0};
    static int[] shartDy = {Integer.MAX_VALUE, 0, -1, 0, 1};

    static int M, S, sharkX, sharkY;

    static List<List<Integer>> sharMoveMethod = new ArrayList<>();

    static void makeMethod(List<Integer> list){
        if(list.size() == 3){
            List<Integer> method = new ArrayList<>();
            for(int i=0; i<3; i++){
                method.add(list.get(i));
            }
            sharMoveMethod.add(method);
            return;
        }

        for(int i=1; i<=4; i++){
            list.add(i);
            makeMethod(list);
            list.remove(list.size()-1);
        }
    }

    static void smellDown(){
        for(int i=1; i<=4; i++){
            for(int j=1; j<=4; j++){
                if(smell[i][j] > 0)
                    smell[i][j]--;
            }
        }
    }

    static List<Fish>[][] backUp(){
        List<Fish>[][] rtn = new List[5][5];
        for(int i=1; i<=4; i++){
            for(int j=1; j<=4; j++){
                if(grid[i][j] != null){
                    rtn[i][j] = new ArrayList<>();
                    for(int k=0; k<grid[i][j].size(); k++){
                        Fish fish = grid[i][j].get(k);
                        rtn[i][j].add(new Fish(fish.x, fish.y, fish.dir));
                    }
                }
            }
        }

        return rtn;
    }

    static void copy(List<Fish>[][] copied){
        for(int i=1; i<=4; i++){
            for(int j=1; j<=4; j++){
                if(copied[i][j] != null){
                    if(grid[i][j] == null)
                        grid[i][j] = new ArrayList<>();

                    for(int k=0; k<copied[i][j].size(); k++){
                        Fish fish = copied[i][j].get(k);
                        grid[i][j].add(new Fish(fish.x, fish.y, fish.dir));
                    }
                }
            }
        }
    }
    //  상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자의 범위를 벗어나는 칸으로는 이동할 수 없다.
    static void fishMove(){
        List<Fish>[][] newGrid = new List[5][5];

        for(int i=1; i<=4; i++){
            for(int j=1; j<=4; j++){
                if(grid[i][j] != null){
                    for(int k = 0; k < grid[i][j].size(); k++){
                        Fish fish = grid[i][j].get(k);

                        for(int cnt=0; cnt<8; cnt++){
                            int nextX = fish.x + fishDx[fish.dir];
                            int nextY = fish.y + fishDy[fish.dir];
                            // 갈 수 있는 경우.
                            if(1<=nextX && nextX <=4 && 1<=nextY && nextY <=4){
                                if(nextX != sharkX || nextY != sharkY){
                                    if(smell[nextX][nextY] == 0){
                                        if(newGrid[nextX][nextY] == null)
                                            newGrid[nextX][nextY] = new ArrayList<>();
                                        newGrid[nextX][nextY].add(new Fish(nextX, nextY, fish.dir));
                                        break;
                                    }
                                }
                            }

                            fish.dir--;
                            if(fish.dir == 0)
                                fish.dir = 8;

                            // 갈 수 없는 경우.
                            if(cnt == 7){
                                if(newGrid[fish.x][fish.y] == null)
                                    newGrid[fish.x][fish.y] = new ArrayList<>();
                                newGrid[fish.x][fish.y].add(new Fish(fish.x, fish.y, fish.dir));
                            }
                        }

                    }
                }
            }
        }

        grid = newGrid;
    }

    static void sharkMove(){
        int methodNumber = -1;
        int maxFishCnt = -1;
        for(int i=0; i<64; i++){
            List<Integer> method = sharMoveMethod.get(i);
            boolean possible = true;
            int fishCnt = 0;
            int nextX = sharkX;
            int nextY = sharkY;
            boolean[][] eaten = new boolean[5][5];
            for(int j=0; j<3; j++){
                int dir = method.get(j);
                nextX += sharkDx[dir];
                nextY += shartDy[dir];
                if(1<=nextX && nextX <=4 && 1<=nextY && nextY <=4){
                    if(grid[nextX][nextY] != null && !eaten[nextX][nextY]){
                        fishCnt += grid[nextX][nextY].size();
                        eaten[nextX][nextY] = true;
                    }
                }else{
                    possible = false;
                    break;
                }
            }

            if(possible){
                if(fishCnt > maxFishCnt){
                    maxFishCnt = fishCnt;
                    methodNumber = i;
                }
            }
        }

        List<Integer> method = sharMoveMethod.get(methodNumber);

        for(int i=0; i<3; i++){
            int dir = method.get(i);
            sharkX += sharkDx[dir];
            sharkY += shartDy[dir];
            if(grid[sharkX][sharkY] != null){
                grid[sharkX][sharkY] = null;
                smell[sharkX][sharkY] = 2;
            }
        }

    }

    static void exercise(){
        List<Fish>[][] copied = backUp();
        fishMove();
        smellDown();
        sharkMove();
        copy(copied);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if(grid[x][y] == null)
                grid[x][y] = new ArrayList<>();
            grid[x][y].add(new Fish(x, y, dir));
        }

        st = new StringTokenizer(br.readLine());
        sharkX = Integer.parseInt(st.nextToken());
        sharkY = Integer.parseInt(st.nextToken());

        makeMethod(new ArrayList<>());

//        for(int i=0; i<sharMoveMethod.size(); i++){
//            for(int j=0; j<sharMoveMethod.get(i).size(); j++){
//                System.out.print(sharMoveMethod.get(i).get(j));
//
//            }
//            System.out.println();
//        }
//
//        System.out.println(sharMoveMethod.size());

        for(int i=0; i<S; i++){
            exercise();
        }

        int ans = 0;

        for(int i=1; i<=4; i++){
            for(int j=1; j<=4; j++){
                if(grid[i][j] != null)
                    ans += grid[i][j].size();
            }
        }

        System.out.println(ans);

    }
}
