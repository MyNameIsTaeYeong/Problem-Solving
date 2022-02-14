import java.util.*;

class Trie{
    int cnt;
    Trie[] child;

    public Trie(){
        cnt = 0;
        child = new Trie[26];
    }

    public void insert(String s){
        cnt++;
        Trie cur = this;
        for(int i=0; i<s.length(); i++){
            if(cur.child[s.charAt(i) - 'a'] == null)
                cur.child[s.charAt(i) - 'a'] = new Trie();

            cur = cur.child[s.charAt(i) - 'a'];
            cur.cnt++;
        }
    }
}

class Solution {

    static Map<Integer, Trie> map = new HashMap<>();
    static Map<Integer, Trie> reverseMap = new HashMap<>();

    static String reverseString(String s){
        StringBuilder sb = new StringBuilder();

        for(int i=s.length()-1; i>=0; i--){
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    static void print(Trie t){
        System.out.print(t.cnt);
        for(int i=0; i<=25; i++){
            if(t.child[i] != null){
                System.out.print((char)(i+'a'));
                print(t.child[i]);
            }
        }
    }

    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];

        for(String word : words){
            if(map.get(word.length()) == null){
                map.put(word.length(), new Trie());
                reverseMap.put(word.length(), new Trie());
            }

            Trie trie = map.get(word.length());
            Trie reverseTrie = reverseMap.get(word.length());

            trie.insert(word);
            reverseTrie.insert(reverseString(word));
        }

        int idx = 0;
        // Trie t = new Trie();
        // t.insert("frodo");
        // print(t);

        for(String query : queries){
            if(map.get(query.length()) == null){
                answer[idx++] = 0;
                continue;
            }

            Trie trie = null;

            if(query.charAt(0) == '?'){
                trie = reverseMap.get(query.length());
                query = reverseString(query);
            }
            else
                trie = map.get(query.length());

            int cnt = 0;
            for(int i=0; i<query.length(); i++){
                if(query.charAt(i) == '?'){
                    cnt = trie.cnt;
                    break;
                }else{
                    if(trie.child[query.charAt(i) - 'a'] == null){
                        cnt = 0;
                        break;
                    }
                    trie = trie.child[query.charAt(i) - 'a'];
                    cnt = trie.cnt;
                }
            }


            answer[idx++] = cnt;
        }

        return answer;
    }
}