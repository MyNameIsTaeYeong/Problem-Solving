#include <iostream>

using namespace std;

int paper[500][500];
int n, m;
int ans = 0;

void solve()
{
    int temp = 0;
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(j+3 < m){
                temp = paper[i][j] + paper[i][j+1] + paper[i][j+2] + paper[i][j+3];
                ans = max(ans, temp);
            }
            
            if(i+3 < n){
                temp = paper[i][j] + paper[i+1][j] + paper[i+2][j] + paper[i+3][j];
                ans = max(ans, temp);
            }
            
            if(j+1 < m && i+1 < n){
                temp = paper[i][j] + paper[i][j+1] + paper[i+1][j] + paper[i+1][j+1];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i][j] + paper[i+1][j] + paper[i+2][j] + paper[i+2][j+1];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i][j] + paper[i][j+1] + paper[i][j+2] + paper[i+1][j];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i][j] + paper[i][j+1] + paper[i+1][j+1] + paper[i+2][j+1];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i+1][j] + paper[i+1][j+1] + paper[i+1][j+2] + paper[i][j+2];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i+2][j] + paper[i+2][j+1] + paper[i+1][j+1] + paper[i][j+1];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i][j] + paper[i][j+1] + paper[i][j+2] + paper[i+1][j+2];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i][j] + paper[i+1][j] + paper[i+2][j] + paper[i][j+1];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i][j] + paper[i+1][j] + paper[i+1][j+1] + paper[i+1][j+2];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i][j] + paper[i+1][j] + paper[i+1][j+1] + paper[i+2][j+1];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i][j+1] + paper[i][j+2] + paper[i+1][j] + paper[i+1][j+1];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i][j+1] + paper[i+1][j+1] + paper[i+1][j] + paper[i+2][j];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i][j] + paper[i][j+1] + paper[i+1][j+1] + paper[i+1][j+2];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i][j] + paper[i+1][j] + paper[i+2][j] + paper[i+1][j+1];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i][j] + paper[i][j+1] + paper[i][j+2] + paper[i+1][j+1];
                ans = max(ans, temp);
            }
            
            if(i+2 < n && j+1 < m){
                temp =  paper[i][j+1] + paper[i+1][j+1] + paper[i+1][j] + paper[i+2][j+1];
                ans = max(ans, temp);
            }
            
            if(i+1 < n && j+2 < m){
                temp =  paper[i][j+1] + paper[i+1][j] + paper[i+1][j+1] + paper[i+1][j+2];
                ans = max(ans, temp);
            }
        }
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    cin >> n >> m;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> paper[i][j];
        }
    }
    
    
    solve();
    
    cout << ans;
    
    
    return 0;
}
