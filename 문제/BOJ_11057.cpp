#include <iostream>

using namespace std;

int memo[1001][10];

const int MOD = 10007;

int main()
{
    int n;
    cin >> n;

    memo[1][0] = 1;
    memo[1][1] = 1;
    memo[1][2] = 1;
    memo[1][3] = 1;
    memo[1][4] = 1;
    memo[1][5] = 1;
    memo[1][6] = 1;
    memo[1][7] = 1;
    memo[1][8] = 1;
    memo[1][9] = 1;

    for(int i=2; i<=n; i++){
        for(int j=0; j<10; j++){
            for(int k=0; k<=j; k++){
                memo[i][j] += memo[i-1][k];
                memo[i][j] %= MOD;
            }
        }
    }

    int ans = 0;

    for(int i=0; i<10; i++){
        ans += memo[n][i];
        ans %= MOD;
    }

    cout << ans;

    return 0;
}