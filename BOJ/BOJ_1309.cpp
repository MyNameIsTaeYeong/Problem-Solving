#include <iostream>

using namespace std;

int memo[100001][4];

const int MOD = 9901;

int main()
{
    int n;
    cin >> n;

    memo[1][0] = 1;
    memo[1][1] = 1;
    memo[1][2] = 1;
    memo[1][3] = 3;

    for(int i=2; i<=n; i++){
        memo[i][0] = memo[i-1][3];
        memo[i][1] %= MOD;
        memo[i][1] = memo[i-1][3] - memo[i-1][1];
        if(memo[i][1]<0){
            memo[i][1] += MOD;
        }
        memo[i][1] %= MOD;
        memo[i][2] = memo[i-1][3] - memo[i-1][2];
        if(memo[i][2]<0){
            memo[i][2] += MOD;
        }
        memo[i][2] %= MOD;
        memo[i][3] = memo[i][0] + memo[i][1] + memo[i][2];
        memo[i][3] %= MOD;
    }

    // % 9901
    cout << memo[n][3];
    return 0;
}