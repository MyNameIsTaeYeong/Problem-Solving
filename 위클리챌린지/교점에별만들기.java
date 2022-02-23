import java.util.*;

class Pair{
    int x;
    int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {

    static char[][] picture;
    static List<Pair> list = new ArrayList<>();
    static int maxX = Integer.MIN_VALUE;
    static int maxY = Integer.MIN_VALUE;
    static int minX = Integer.MAX_VALUE;
    static int minY = Integer.MAX_VALUE;


    public String[] solution(int[][] line) {
        String[] answer = {};

        for(int i=0; i<line.length; i++){
            long A = line[i][0];
            long B = line[i][1];
            long E = line[i][2];
            for(int j=i-1; j>=0; j--){
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                if(A*D == B*C)
                    continue;

                if((B*F - E*D) % (A*D - B*C) == 0
                        && (E*C - A*F) % (A*D - B*C) == 0){
                    Long tempX = ((B*F - E*D) / (A*D - B*C));
                    Long tempY = ((E*C - A*F) / (A*D - B*C));
                    int x = tempX.intValue();
                    int y = tempY.intValue();
                    list.add(new Pair(x, y));
                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);
                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);

                    //System.out.println(x +", "+y);
                }

            }
        }
        int r = maxY - minY + 1;
        int c = maxX - minX + 1;
        picture = new char[r][c];

        //System.out.println(r +", "+c);

        for(int i=0; i<list.size(); i++){
            Pair cur = list.get(i);
            picture[maxY - cur.y][cur.x - minX] = '*';
            //System.out.println(cur.x +", "+cur.y);
        }

        answer = new String[r];

        for(int i=0; i<r; i++){
            StringBuilder sb = new StringBuilder("");
            for(int j=0; j<c; j++){
                if(picture[i][j] == '*')
                    sb.append('*');
                else
                    sb.append('.');
            }
            answer[i] = sb.toString();
            //System.out.println(answer[i]);
        }

        return answer;
    }
}