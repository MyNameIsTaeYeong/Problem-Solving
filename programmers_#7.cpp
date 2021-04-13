#include <string>
#include <vector>
#include <algorithm>
#include <queue>
#include <iostream>

using namespace std;

bool check[50];

int solution(string begin, string target, vector<string> words) {
    int answer = 0;
    
    auto it = find(words.begin(), words.end(), target);
    if(it == words.end()){
        return 0;
    }
    
    queue<pair<string, int>> q;
    q.push(make_pair(begin, 0));
    
    unsigned long words_len = words.size();
    unsigned long word_len = begin.size();
    
    while (!q.empty()) {
        string node = q.front().first;
        int depth = q.front().second;
        q.pop();
        
        if(node == target){
            answer = depth;
            break;
        }
        
        for(int i=0; i<words_len; i++){
            if(!check[i]){
                int len = 0;
                for(int j=0; j<word_len; j++){
                    if(words[i][j] != node[j]){
                        len++;
                    }
                    if(len == 2){
                        break;
                    }
                }
                if(len == 1){
                    q.push(make_pair(words[i], depth+1));
                    check[i] = true;
                }
            }
        }
        
    }
    
    
    return answer;
}
