#include <iostream>

using namespace std;

long long memo[1000001];
const int MOD = 1000000009;

int main()
{
    memo[1] = 1;
    memo[2] = 2;
    memo[3] = 4;
    for(int i=4; i<=1000000; i++){
        memo[i] = memo[i-1]%MOD + memo[i-2]%MOD + memo[i-3]%MOD;
        memo[i] %= MOD;
    }

    int t;
    cin >> t;
    while(t--){
        int n;
        cin >> n;
        cout << memo[n] << '\n';
    }
    return 0;
}