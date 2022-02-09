import java.util.*;

// 1. board의 0이아닌 원소로 순열만들기.
// 2. 그 순열로 같은것이 있는 순열 만들기.
// 3. 2번에 대해 이동횟수 세기.
//    3.1 bfs로 최단이동횟수

class Card {
    int num;
    int x;
    int y;
    int dist;

    public Card(int num, int x, int y){
        this.num = num;
        this.x = x;
        this.y = y;
    }

    public Card(int num, int x, int y, int dist){
        this.num = num;
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class Solution {

    static List<Integer> cardNumList = new ArrayList<>();
    static List<Card>[] cardLocation = new List[7];
    static int[][] gBoard = new int[4][4];
    static int startX, startY;
    static int minCnt = Integer.MAX_VALUE;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};


    static void copyBoard(int[][] board){
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                board[i][j] = gBoard[i][j];
            }
        }
    }

    static int bfs(int x1, int y1, int x2, int y2, int[][] board){
        int rtn = 0;
        boolean[][] check = new boolean[4][4];
        check[x1][y1] = true;
        Queue<Card> q = new LinkedList<>();
        q.add(new Card(-1, x1, y1, 0));

        while(!q.isEmpty()){
            Card cur = q.poll();

            if(cur.x == x2 && cur.y == y2){
                return cur.dist + 1;
            }

            for(int i=0; i<4; i++){
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];

                if(0<=nextX && nextX<4 && 0<=nextY && nextY<4){
                    if(!check[nextX][nextY]){
                        check[nextX][nextY] = true;
                        q.add(new Card(-1, nextX, nextY, cur.dist+1));
                    }
                    // ctr + 방향키
                    while(board[nextX][nextY] == 0
                            && 0<=nextX+dx[i] && nextX+dx[i]<4
                            && 0<=nextY+dy[i] && nextY+dy[i]<4){
                        nextX += dx[i];
                        nextY += dy[i];
                    }
                    if(!check[nextX][nextY]){
                        check[nextX][nextY] = true;
                        q.add(new Card(-1, nextX, nextY, cur.dist+1));
                    }
                }




            }

        }

        return rtn;
    }

    static void makeDupPermutation(int index, List<Integer> list, List<Card> seq){
        if(index == cardNumList.size()){
            int[][] board = new int[4][4];
            copyBoard(board);
            int cnt = 0;
            int x1 = startX;
            int y1 = startY;

            for(int i=0; i<seq.size(); i++){
                Card next = seq.get(i);
                cnt += bfs(x1, y1, next.x, next.y, board);
                if(i % 2 == 1){
                    board[x1][y1] = 0;
                    board[next.x][next.y] = 0;
                }
                x1 = next.x;
                y1 = next.y;
            }

            minCnt = Math.min(minCnt, cnt);

            // bfs

            // for(int i=0; i<seq.size(); i++){
            //     System.out.print(seq.get(i).num + " " + seq.get(i).x+ " "+ seq.get(i).y+ " ");
            // }
            // System.out.println();
            return;
        }

        int num = list.get(index);
        seq.add(cardLocation[num].get(0));
        seq.add(cardLocation[num].get(1));
        makeDupPermutation(index + 1, list, seq);
        seq.remove(seq.size() - 1);
        seq.remove(seq.size() - 1);

        seq.add(cardLocation[num].get(1));
        seq.add(cardLocation[num].get(0));
        makeDupPermutation(index + 1, list, seq);
        seq.remove(seq.size() - 1);
        seq.remove(seq.size() - 1);
    }

    static void makePermutation(boolean[] check, List<Integer> list){
        if(list.size() == cardNumList.size()){
            // 해당 리스트로 중복순열 만들기
            makeDupPermutation(0, list, new ArrayList<>());
            // for(int i=0; i<list.size(); i++){
            //     System.out.print(list.get(i) + " ");
            // }
            // System.out.println();
            return;
        }

        for(int i=0; i<cardNumList.size(); i++){
            if(!check[i]){
                check[i] = true;
                list.add(cardNumList.get(i));
                makePermutation(check, list);
                list.remove(list.size() - 1);
                check[i] = false;
            }
        }
    }

    public int solution(int[][] board, int r, int c) {
        int answer = 0;

        startX = r;
        startY = c;

        boolean[] dupCheck = new boolean[7];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int num = board[i][j];
                gBoard[i][j] = num;
                if(num != 0){
                    if(!dupCheck[num]){
                        dupCheck[num] = true;
                        cardNumList.add(num);
                    }
                    if(cardLocation[num] == null)
                        cardLocation[num] = new ArrayList<>();
                    cardLocation[num].add(new Card(num, i, j));
                }
            }
        }

        // index, check, list, size
        makePermutation(new boolean[cardNumList.size()], new ArrayList<>());

        answer = minCnt;

        return answer;
    }
}