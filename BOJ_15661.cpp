#include <iostream>
#include <cstdlib>
#include <algorithm>

using namespace std;

int n;
int s[20][20];

int team1[20];
int team2[20];

int ans = 2000000000;


void solve(int index, int team1_cnt, int team2_cnt)
{
    if(team1_cnt == 20){
        return;
    }
    
    if(team2_cnt == 20){
        return;
    }
    
    if(index == 20){
        int team1_score = 0;
        int team2_score = 0;
        
        for(int i=0; i<team1_cnt; i++){
            for(int j=0; j<team1_cnt; j++){
                if(i==j){
                    continue;
                }
                team1_score += s[team1[i]][team1[j]];
            }
        }
        
        for(int i=0; i<team2_cnt; i++){
            for(int j=0; j<team2_cnt; j++){
                if(i==j){
                    continue;
                }
                team2_score += s[team2[i]][team2[j]];
            }
        }
        
        ans = min(ans, abs(team1_score - team2_score));
        
        return;
    }
    
    team1[team1_cnt] = index;
    solve(index+1, team1_cnt+1, team2_cnt);
    
    team2[team2_cnt] = index;
    solve(index+1, team1_cnt, team2_cnt+1);
    
    return;
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
    
    solve(0, 0, 0);
    
    cout << ans;
    
    return 0;
}
