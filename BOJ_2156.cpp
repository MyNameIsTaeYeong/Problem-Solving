#include <iostream>
#include <algorithm>

using namespace std;

int memo[10001][3];
int wines[10001];

int main()
{
    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        cin >> wines[i];
    }

    memo[1][0] = 0;
    memo[1][1] = wines[1];
    memo[1][2] = wines[1];

    for(int i=2; i<=n; i++){
        memo[i][0] = max(memo[i-1][0], max(memo[i-1][1], memo[i-1][2]));
        memo[i][1] = memo[i-1][0] + wines[i];
        memo[i][2] = memo[i-1][1] + wines[i];
    }

    int ans = max(memo[n][0], max(memo[n][1], memo[n][2]));

    cout << ans;

    return 0;
}