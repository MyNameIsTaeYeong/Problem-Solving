import java.util.*;

class Solution {

    static List<Character> menuList;
    static int[] maxCourseCnt = new int[11];
    static List<String>[] courseList = new List[11];

    static void check(StringBuilder curMenu, String[] orders){

        int cnt = 0;

        for(String order : orders){
            boolean isCnt = true;
            for(int i=0; i<curMenu.length(); i++){
                int menu = curMenu.charAt(i);
                boolean isIn = false;
                for(int j=0; j<order.length(); j++){
                    if(order.charAt(j) == menu){
                        isIn = true;
                        break;
                    }
                }
                if(!isIn){
                    isCnt = false;
                    break;
                }
            }

            if(isCnt)
                cnt++;
        }

        if(cnt < 2)
            return;

        if(maxCourseCnt[curMenu.length()] < cnt){
            maxCourseCnt[curMenu.length()] = cnt;
            courseList[curMenu.length()] = new ArrayList<>();
            courseList[curMenu.length()].add(curMenu.toString());
            return;
        }

        if(maxCourseCnt[curMenu.length()] == cnt){
            courseList[curMenu.length()].add(curMenu.toString());
            return;
        }

    }

    static void go(int index,
                   StringBuilder curMenu, int[] course, String[] orders){
        if(curMenu.length() > course[course.length - 1])
            return;

        for(int i=0; i<course.length; i++){
            if(curMenu.length() == course[i]){
                check(curMenu, orders);
            }
        }

        for(int i=index; i<menuList.size(); i++){
            curMenu.append(menuList.get(i));
            go(i+1, curMenu, course, orders);
            curMenu.deleteCharAt(curMenu.length() - 1);
        }
    }

    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};

        int[] menuCnt = new int[26];

        // 1. orders를 돌며 주문이 2개이상인 메뉴를 추린다.
        for(String order : orders){
            for(int i=0; i<order.length(); i++){
                menuCnt[order.charAt(i) - 'A']++;
            }
        }

        menuList = new ArrayList<>();

        for(int i=0; i<26; i++){
            if(menuCnt[i] >= 2)
                menuList.add((char)('A' + i));
        }

        // 2. 1에서 얻은 메뉴들로 조합을 만든다.

        go(0, new StringBuilder(""), course, orders);

        // 3. 각 조합에 대하여 손님들의 주문수를 체크한다.

        List<String> ans = new ArrayList<>();

        for(int i=0; i<=10; i++){
            if(courseList[i] == null)
                continue;

            for(int j=0; j<courseList[i].size(); j++){
                ans.add(courseList[i].get(j));
            }
        }

        Collections.sort(ans);

        answer = new String[ans.size()];

        for(int i=0; i<ans.size(); i++){
            answer[i] = ans.get(i);
        }


        return answer;
    }
}