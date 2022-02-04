import java.util.*;

class Solution {

    static int maxDiff = -1;
    static List<Integer> ansList;

    static void copyList(List<Integer> oldList, List<Integer> newList){
        for(int i=0; i < oldList.size(); i++){
            newList.add(oldList.get(i));
        }
    }

    static boolean isFrot(List<Integer> a, List<Integer> b){
        for(int i = b.size()-1; i >= 0; i--){
            if(a.get(i) == b.get(i))
                continue;
            if(a.get(i) > b.get(i))
                return true;
            else{
                return false;
            }
        }
        return false;
    }

    static void go(int n, int[] info,
                   int cnt, int round, int ryan, int apeach, List<Integer> list){
        if(cnt > n)
            return;
        if(round == -1){
            if(cnt < n){
                int temp = list.get(10);
                list.remove(10);
                list.add(temp + n - cnt);
            }

            if(ryan > apeach){
                if((ryan - apeach) > maxDiff){
                    maxDiff = ryan - apeach;
                    ansList = new ArrayList<>();
                    copyList(list, ansList);
                    return;
                }else if((ryan - apeach) == maxDiff){
                    if(isFrot(list, ansList)){
                        ansList = new ArrayList<>();
                        copyList(list, ansList);
                    }
                    return;
                }

            }
            return;
        }

        // 쏜다.

        if(n - cnt > info[10-round]){
            list.add(info[10-round] + 1);
            go(n, info, cnt + info[10-round] + 1, round-1, ryan + round, apeach, list);
            list.remove(list.size() - 1);
        }

        // 안쏜다.
        list.add(0);
        if(info[10-round] > 0){
            go(n, info, cnt, round-1, ryan, apeach + round, list);
        }else{
            go(n, info, cnt, round-1, ryan, apeach, list);
        }
        list.remove(list.size() - 1);
    }

    public int[] solution(int n, int[] info) {
        int[] answer = {};

        go(n, info, 0, 10, 0, 0, new ArrayList<>());

        if(maxDiff == -1){
            answer = new int[1];
            answer[0] = -1;
        }else{
            answer = new int[11];

            for(int i=0; i<ansList.size(); i++){
                answer[i] = ansList.get(i);
            }
        }

        return answer;
    }
}