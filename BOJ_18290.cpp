#include <iostream>

using namespace std;

int num[10][10];
int check[10][10];

int ans = -50000;
int n, m, k;

void solve(int cnt, int current, int row, int col)
{
    if(k == 1){
        ans = max(ans, current);
        return;
    }
    
    if(cnt == k){
        ans = max(ans, current);
        return;
    }

    
    for(int i=row; i<n; i++){
        for(int j=0; j<m; j++){
            if(i==row && j<=col){
                continue;
            }
            
            if(check[i][j] == 0){
                if(j+1 < m){
                    check[i][j+1] += 1;
                }
                if(i+1 < n){
                    check[i+1][j] += 1;
                }
                solve(cnt+1, current + num[i][j], i, j);
                //
                if(j+1 < m ){
                    check[i][j+1] -= 1;
                }
                if(i+1 < n){
                    check[i+1][j] -= 1;
                }
            }
        }
    }
    
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    
    cin >> n >> m >> k;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> num[i][j];
        }
    }
    
    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            if(j+1 < m){
                check[i][j+1] += 1;
            }
            if(i+1 < n){
                check[i+1][j] += 1;
            }
            solve(1, num[i][j], i, j);
            if(j+1 < m){
                check[i][j+1] -= 1;
            }
            if(i+1 < n){
                check[i+1][j] -= 1;
            }
            
        }
    }
    
    cout << ans;
     
    return 0;
}
