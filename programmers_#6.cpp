#include <string>
#include <vector>

using namespace std;

bool check[200];

void dfs(int n, vector<vector<int>> &computers, int index)
{
    check[index] = true;
    for(int i=0; i<n; i++){
        if(i == index){
            continue;
        }
        if(check[i] == false && computers[index][i] == 1){
            dfs(n, computers, i);
        }
    }
}

int solution(int n, vector<vector<int>> computers) {
    int answer = 0;
    
    for(int i=0; i<n; i++){
        if(check[i] == false){
            dfs(n, computers, i);
            answer++;
        }
        
    }
    
    return answer;
}
