#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int e,s,m;
    cin >> e >> s >> m;
    
    int e1, s1, m1, ans;
    e1 = s1 = m1 = ans = 1;
    
    while (true) {
        if(e == e1 && s == s1 && m == m1){
            break;
        }
        
        e1++;
        s1++;
        m1++;
        ans++;
        
        if(e1 == 16){
            e1 = 1;
        }
        if(s1 == 29){
            s1 = 1;
        }
        if(m1 == 20){
            m1 = 1;
        }
    }
    
    cout << ans;
    
    return 0;
}
