#include <iostream>
#include <cstdlib>
#include <algorithm>

using namespace std;

int s[20][20];
int team[20];
int n;
int ans = 2000000000;

void solve(int index, int remain_team_cnt)
{
    if(remain_team_cnt == 0){
        
        for(int i=index; i<n; i++){
            team[i] = 0;
        }
        
        // 계산
        int team1=0;
        int team2=0;
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i==j){
                    continue;
                }
                if(team[i] == 1 && team[j] == 1){
                    team1 += s[i][j];
                }
                if(team[i] == 0 && team[j] == 0){
                    team2 += s[i][j];
                }
            }
        }
        
        ans = min(ans, abs(team1-team2));
        
        return;
    }
    
    if(n-index < remain_team_cnt){
        return;
    }
    
    team[index] = 1;
    solve(index+1, remain_team_cnt - 1);
    team[index] = 0;
    solve(index+1, remain_team_cnt);
    
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
   
    cin >> n;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> s[i][j];
        }
    }
    
    solve(0, n/2);
    
    cout << ans;
    
    
    return 0;
}
