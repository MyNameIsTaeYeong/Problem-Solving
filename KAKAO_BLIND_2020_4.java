class Trie{
    int cnt;
    Trie[] child;
    
    public Trie(){
        cnt = 0;
        child = new Trie[26];
    }
    
    public void insert(String str){
        Trie node = this;
        
        for(char ch : str.toCharArray()){
            node.cnt++;
            if(node.child[ch-'a'] == null){
                node.child[ch-'a'] = new Trie();
            }
            node = node.child[ch-'a'];
        }
        node.cnt++;
    }
    
    public int search(String str){
        Trie node = this;
        
        for(char ch : str.toCharArray()){
            if(ch == '?'){
                return node.cnt;
            }
            node = node.child[ch-'a'];
            if(node == null){
                return 0;
            }
        }
        return node.cnt;
    }
    
}

class Solution {
    
    static Trie[] root;
    static Trie[] root_reverse;
    
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        
        root = new Trie[10001];
        root_reverse = new Trie[10001];
        
        for(String word : words){
            if(root[word.length()] == null){
                root[word.length()] = new Trie();
                root_reverse[word.length()] = new Trie();
            }
            
            StringBuilder sb = new StringBuilder(word);
            root[word.length()].insert(word);
            root_reverse[word.length()].insert(sb.reverse().toString());
        }
        
        int ans_idx = 0;
        for(String query : queries){
            if(query.charAt(0) == '?'){
                StringBuilder sb = new StringBuilder(query);
                if(root_reverse[query.length()] == null){
                    answer[ans_idx++] = 0;
                }else{
                    answer[ans_idx++] = root_reverse[query.length()].search(sb.reverse().toString());
                }
            }else{
                if(root[query.length()] == null){
                    answer[ans_idx++] = 0;
                }else{
                    answer[ans_idx++] = root[query.length()].search(query);
                }
            }
        }
        
        
        return answer;
    }
}