#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <functional>

using namespace std;

bool cmp(pair<int, int> &a, pair<int, int> &b){
    if(a.first == b.first){
        return a.second < b.second;
    }
    return a.first > b.first;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    
    map<string, int> total;
    map<string, vector<pair<int, int>>> genres_map;
    vector<pair<int, string>> total_vector;
    
    for(int i=0; i<genres.size(); i++){
        auto it = total.find(genres[i]);
        if(it == total.end()){
            total[genres[i]] = plays[i];
        }else{
            total[genres[i]] += plays[i];
        }
        genres_map[genres[i]].push_back(make_pair(plays[i], i));
    }
    
    for(auto it = total.begin(); it != total.end() ; it++){
        total_vector.push_back(make_pair(it->second, it->first));
        sort(genres_map[it->first].begin(), genres_map[it->first].end(), cmp);
    }
    
    sort(total_vector.begin(), total_vector.end(), greater<>());
    
    for(int i=0; i<total_vector.size(); i++){
        if(genres_map[total_vector[i].second].size() == 1){
            answer.push_back(genres_map[total_vector[i].second][0].second);
        }else {
            answer.push_back(genres_map[total_vector[i].second][0].second);
            answer.push_back(genres_map[total_vector[i].second][1].second);
        }
    }
    
    return answer;
}
