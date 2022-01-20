import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

class Solution {
    
    static int[] order_cnt = new int[26];
    static ArrayList<Integer> order = new ArrayList<>();
    static ArrayList<String> course_menu = new ArrayList<>();
    
    static boolean a_is_in_b(String a, String b){
        int a_idx = 0;
        for(int i=0; i<b.length(); i++){
            if(b.charAt(i) == a.charAt(a_idx)){
                a_idx++;
                if(a_idx == a.length()){
                    return true;
                }
            }
        }
        return false;
    }
    
    static ArrayList<Integer> check(String[] orders, int course_cnt) {
        ArrayList<Integer> index = new ArrayList<>();;
        int max_cnt = 1;
        for(int i=0; i<course_menu.size(); i++){
            if(course_menu.get(i).length() != course_cnt){
                continue;
            }
            
            int cnt = 0;
            for(int j=0; j<orders.length; j++){
                if(a_is_in_b(course_menu.get(i), orders[j])){
                    cnt++;
                }
                
            }
            
            if(cnt > max_cnt && cnt >= 2){
                max_cnt = cnt;
                index = new ArrayList<>();
                index.add(i);
            }else if( cnt == max_cnt && cnt >= 2){
                index.add(i);
            }
        }
        
        return index;
    }
    
    static void make_course(int[] course, int cnt, int index, StringBuilder menu)
    {
        for(int i=0; i<course.length-1; i++){
            if(cnt == course[i]){
                course_menu.add(menu.toString());
            }
        }
        
        if(cnt == course[course.length-1]){
            course_menu.add(menu.toString());
            return;
        }
        
        for(int i=index+1; i<order.size(); i++){
            
            menu.append((char)(65+order.get(i)));
            make_course(course, cnt+1, i, menu);
            menu.deleteCharAt(menu.length()-1);
        }
        
    }
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        for(int i=0; i<orders.length; i++){
            char[] charArr = orders[i].toCharArray();
            Arrays.sort(charArr);
            orders[i] = new String(charArr);
            for(int j=0; j<orders[i].length(); j++){
                order_cnt[orders[i].charAt(j) - 'A']++;
            }
        }
        
        // 두명 이상이 주문한 메뉴
        for(int i=0; i<order_cnt.length; i++){
            if(order_cnt[i] >= 2){
                order.add(i);
            }
            
        }
        
        // 위 메뉴에 대한 모든 조합
        for(int i=0; i<order.size(); i++){
            StringBuilder sb = new StringBuilder();
            sb.append((char)(order.get(i)+65));
            make_course(course, 1, i, sb);
        }
        
        
        ArrayList<Integer> ans_index = new ArrayList<>();
        for(int i=0; i<course.length; i++){
            ArrayList<Integer> temp = check(orders, course[i]);
            for(int j=0; j<temp.size(); j++){
                ans_index.add(temp.get(j));
            }
        }
        
        Collections.sort(ans_index);
        
        answer = new String[ans_index.size()];
        for(int i=0; i<ans_index.size(); i++){
            answer[i] = course_menu.get(ans_index.get(i));
        }
        
        return answer;
    }
}