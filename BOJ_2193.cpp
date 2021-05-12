#include <iostream>

using namespace std;

long long int memo[91][2];

int main()
{
    memo[1][1] = 1;
    memo[1][0] = 0; 


    int n;
    cin >> n;

    for(int i=2; i<=n; i++){
        memo[i][0] = memo[i-1][0] + memo[i-1][1];
        memo[i][1] = memo[i-1][0];
    }


    cout << memo[n][0] + memo[n][1];

    return 0;
}