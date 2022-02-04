import java.util.*;

class Solution {

    static Map<String, Set<String>> reportList;
    static Map<String, Integer> repoCnt;

    public int[] solution(String[] id_list, String[] reports, int k) {
        int[] answer = {};

        reportList = new HashMap<>();
        repoCnt = new HashMap<>();

        for(String id : id_list){
            reportList.put(id, new HashSet<>());
            repoCnt.put(id, 0);
        }

        for(String report : reports){
            StringTokenizer st = new StringTokenizer(report);
            String user1 = st.nextToken();
            String user2 = st.nextToken();

            if(!reportList.get(user1).contains(user2)){
                reportList.get(user1).add(user2);
                repoCnt.put(user2, repoCnt.get(user2) + 1);
            }
        }

        List<Integer> list = new ArrayList<>();

        for(String id : id_list){
            Iterator<String> it = reportList.get(id).iterator();
            int cnt = 0;
            while(it.hasNext()){
                String user = it.next();
                //System.out.println(id + " "+ user + " " + repoCnt.get(user));
                if(repoCnt.get(user) >= k){
                    cnt++;
                }
            }
            list.add(cnt);
        }

        answer = new int[list.size()];

        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }

        return answer;
    }
}