class Pair{
    int x;
    int y;
    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Solution {

    static Pair[] where;

    static void init(){
        where = new Pair[10];
        where[0] = new Pair(3, 1);
        where[1] = new Pair(0, 0);
        where[2] = new Pair(0, 1);
        where[3] = new Pair(0, 2);
        where[4] = new Pair(1, 0);
        where[5] = new Pair(1, 1);
        where[6] = new Pair(1, 2);
        where[7] = new Pair(2, 0);
        where[8] = new Pair(2, 1);
        where[9] = new Pair(2, 2);
    }

    public String solution(int[] numbers, String hand) {
        String answer = "";

        init();

        Pair left = new Pair(3, 0);
        Pair right = new Pair(3, 2);

        for(int number : numbers){
            if(number == 1 || number == 4 || number == 7){
                left = where[number];
                answer += "L";
            }else if(number == 3 || number == 6 || number == 9){
                right = where[number];
                answer += "R";
            }else{
                int leftDiff =
                        Math.abs(left.x - where[number].x) + Math.abs(left.y - where[number].y);
                int rightDiff =
                        Math.abs(right.x - where[number].x) + Math.abs(right.y - where[number].y);

                if(leftDiff < rightDiff){
                    left = where[number];
                    answer += "L";
                }else if(leftDiff > rightDiff){
                    right = where[number];
                    answer += "R";
                }else{
                    if(hand.equals("right")){
                        right = where[number];
                        answer += "R";
                    }else{
                        left = where[number];
                        answer += "L";
                    }
                }
            }
        }

        return answer;
    }
}