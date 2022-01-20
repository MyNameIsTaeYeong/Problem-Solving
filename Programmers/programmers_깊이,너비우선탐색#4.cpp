#include <string>
#include <vector>
#include <map>
#include <algorithm>


using namespace std;

struct Airport{
    string name;
    bool check;
};

bool cmp(Airport &a, Airport &b){
    return a.name < b.name;
}

bool solve(map<string, vector<Airport>> &m, vector<string> &answer, string start, unsigned long tickets_len)
{
    answer.push_back(start);
    
    if(answer.size() == tickets_len+1){
        return true;
    }
    
    
    vector<Airport> &dests = m[start];
    
    for(int i=0; i<dests.size(); i++){
        if(!dests[i].check){
            dests[i].check = true;
            if(solve(m, answer, dests[i].name, tickets_len)){
                return true;
            };
            dests[i].check = false;
        }
    }
    answer.pop_back();
    return  false;
}
vector<string> solution(vector<vector<string>> tickets) {
    vector<string> answer;
    
    map<string, vector<Airport>> m;
    
    unsigned long tickets_len = tickets.size();
    
    for(int i=0; i<tickets_len; i++){
        Airport input;
        input.name = tickets[i][1];
        input.check = false;
        m[tickets[i][0]].push_back(input);
    }
    
    for(auto it = m.begin(); it != m.end(); it++){
        sort(it->second.begin(), it->second.end(), cmp);
    }
    
    
    solve(m, answer, "ICN", tickets_len);
    
    return answer;
}
