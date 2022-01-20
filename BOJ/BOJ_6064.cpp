#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int t;
    cin >> t;
    
    while(t--){
        int m, n, x, y;
        cin >> m >> n >> x >> y;
        
        int ans = -1;
        for(int i=x-1 ;; i+=m){
            if(i%n == y-1){
                ans = i+1;
                break;
            }
            
            if( ((i%n+1)+(m-x)) % n == 0 ){
                break;
            }
            
        }
        
        cout << ans << '\n';
        
    }
    
    return 0;
}
