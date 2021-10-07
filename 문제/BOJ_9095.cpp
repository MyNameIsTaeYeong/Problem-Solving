#include <iostream>

using namespace std;

int memo[11];

int main()
{
    memo[1] = 1;
    memo[2] = 2;
    memo[3] = 4;
    
    for(int i=4; i<11; i++){
        memo[i] = memo[i-1] + memo[i-2] + memo[i-3];
    }
    
    int t;
    cin >> t;
    
    while (t--) {
        int n;
        cin >> n;
        cout << memo[n] << '\n';
    }
    
    return 0;
}
