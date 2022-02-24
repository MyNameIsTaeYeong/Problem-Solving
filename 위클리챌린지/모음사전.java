import java.util.*;

class Solution {

    static char[] alpha = {'A', 'E', 'I', 'O', 'U'};
    static List<String> list = new ArrayList<>();
    static Set<String> set = new HashSet<>();

    static void go(int index, StringBuilder cur){
        if(index == 5){
            if(cur.length() != 0)
                set.add(cur.toString());
            return;
        }

        go(index+1, cur);

        for(int i=0; i<5; i++){
            cur.append(alpha[i]);
            go(index + 1, cur);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public int solution(String word) {
        int answer = 0;

        go(0, new StringBuilder(""));

        Iterator<String> it = set.iterator();
        while(it.hasNext())
            list.add(it.next());


        Collections.sort(list);

        // for(int i=0; i<list.size(); i++){
        //     System.out.println(list.get(i));
        // }

        answer = list.indexOf(word) + 1;

        return answer;
    }
}