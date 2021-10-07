#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n;
    cin >> n;
    
    int cnt=0;
    for(int i=n; i!=0; i/=10){
        cnt++;
    }
    
    
    int ans = 0;
    int q = 1;
    for(int i=1; i<cnt; i++){
        ans += 9 * q * i;
        q *= 10;
    }
    
    ans += (n-q+1)*cnt;
    
    cout << ans;
    
    
    return 0;
}
