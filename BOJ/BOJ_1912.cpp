#include <iostream>

using namespace std;

int memo[100001][2];

int main()
{
    int n;
    cin >> n;

    cin >> memo[1][0];
    memo[1][1] = memo[1][0];

    int ans = memo[1][1];
    for(int i=2; i<=n; i++){
        cin >> memo[i][0];
        if(memo[i-1][1] > 0){
            memo[i][1] = memo[i-1][1] + memo[i][0];
        }
        else{
            memo[i][1] = memo[i][0];
        }

        if(memo[i][1] > ans){
            ans = memo[i][1];
        }
    }

    cout << ans;

    return 0;
}