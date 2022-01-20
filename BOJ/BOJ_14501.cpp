#include <iostream>
#include <algorithm>

using namespace std;

int consulting[15][2];
int ans = 0;

void solve(int n, int day, int pay)
{
    if(day == n){
        ans = max(ans, pay);
        return;
    }
    
    //상담 하는 경우
    if(day+consulting[day][0] <= n){
        solve(n, day+consulting[day][0], pay+consulting[day][1]);
    }

    //상담 하지 않는 경우
    solve(n, day+1, pay);
    
    return;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n;
    cin >> n;
    
    for(int i=0; i<n; i++){
        cin >> consulting[i][0] >> consulting[i][1];
    }
    
    solve(n, 0, 0);
    
    cout << ans;
    
    return 0;
}
