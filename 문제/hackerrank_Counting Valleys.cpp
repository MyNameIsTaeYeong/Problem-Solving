#include <iostream>
using namespace std;

char s[1000000];

int main()
{
    int n;
    cin >> n;
    
    int ans = 0;
    int unit = 0;
    int check = 0;
    
    for(int i=0; i<n; i++){
        cin >> s[i];
        
        if( s[i] == 'U' )
            unit++;
        else
            unit--;
        
        if( check == 1 && unit == 0 ){
            ans++;
            check = 0;
            continue;
        }
        
        if( unit == -1 && s[i] == 'D')
            check = 1;
        
    }
    
    cout << ans << '\n';
  
    
    return 0;
}
