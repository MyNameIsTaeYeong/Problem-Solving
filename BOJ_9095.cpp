#include <iostream>

using namespace std;

int solve(int n, int sum)
{
    if(sum > n){
        return 0;
    }
    
    if(sum == n){
        return 1;
    }
    
    int ans = 0;
    
    for(int i=1; i<=3; i++){
        ans += solve(n, sum+i);
    }
    
    return ans;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int t;
    cin >> t;
    
    while(t--){
        int n;
        cin >> n;
        
        cout << solve(n, 0) << '\n';
    }
    
    return 0;
}
