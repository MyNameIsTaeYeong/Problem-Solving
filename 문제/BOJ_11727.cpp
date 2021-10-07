#include <iostream>

using namespace std;

int memo[1001];

int main()
{
    memo[1] = 1;
    memo[2] = 3;
    
    for(int i=3; i<1001; i++){
        memo[i] = memo[i-1] + 2 * memo[i-2];
        memo[i] %= 10007;
    }
    
    int n;
    cin >> n;
    
    cout << memo[n];
    
    return 0;
}
