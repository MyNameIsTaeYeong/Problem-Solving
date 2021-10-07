#include <iostream>

using namespace std;

int main()
{
    int n;
    while(cin >> n){
        
        int before = 1 % n;
        int cnt = 1;
        
        while(before != 0){
            before = (before * 10 + 1) % n;
            cnt++;
        }
        
        cout << cnt << '\n';
        
    }
    
    return 0;
}
