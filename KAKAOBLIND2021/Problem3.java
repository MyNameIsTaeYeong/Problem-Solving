import java.util.*;

class Solution {

    static Map<String, List<Integer>> map;
    static Set<String> keys = new HashSet<>();

    static void go(String key, int score, StringBuilder sb, int index){
        if(sb.length() == 4){
            List<Integer> list = map.getOrDefault(sb.toString(), new ArrayList<>());
            list.add(score);
            map.put(sb.toString(), list);
            keys.add(sb.toString());
            return;
        }

        sb.append('-');
        go(key, score, sb, index + 1);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(key.charAt(index));
        go(key, score, sb, index + 1);
        sb.deleteCharAt(sb.length() - 1);
    }

    static int findIdx(List<Integer> list, int score){
        int left = 0;
        int right = list.size() - 1;

        int rtn = -1;

        while(left <= right){
            int mid = (left + right) / 2;
            if(list.get(mid) >= score){
                rtn = mid;
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }


        return rtn;
    }

    public int[] solution(String[] info, String[] query) {
        int[] answer = {};

        map = new HashMap<>();



        for(String str : info){
            StringTokenizer st = new StringTokenizer(str, " ");
            StringBuilder key = new StringBuilder("");
            key.append(st.nextToken().charAt(0));
            key.append(st.nextToken().charAt(0));
            key.append(st.nextToken().charAt(0));
            key.append(st.nextToken().charAt(0));
            int score = Integer.parseInt(st.nextToken());

            go(key.toString(), score, new StringBuilder(""), 0);
        }

        answer = new int[query.length];
        int ansIdx = 0;

        Iterator<String> it = keys.iterator();

        while(it.hasNext()){
            String key = it.next();
            List<Integer> list = map.get(key);
            Collections.sort(list);
            map.put(key, list);

        }

        for(String q : query){
            StringTokenizer st = new StringTokenizer(q, " ");
            StringBuilder key = new StringBuilder("");
            key.append(st.nextToken().charAt(0));
            st.nextToken();
            key.append(st.nextToken().charAt(0));
            st.nextToken();
            key.append(st.nextToken().charAt(0));
            st.nextToken();
            key.append(st.nextToken().charAt(0));


            int score = Integer.parseInt(st.nextToken());



            List<Integer> list = map.get(key.toString());

            if(list != null){
                int idx = findIdx(list, score);

                if(idx == -1){
                    answer[ansIdx++] = 0;
                }else{
                    answer[ansIdx++] = list.size() - idx;
                }
            }else{
                answer[ansIdx++] = 0;
            }
        }



        return answer;


    }



}