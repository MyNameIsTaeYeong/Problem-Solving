#include <iostream>

using namespace std;

int memo[201][201];

int main()
{
    int n, k;
    cin >> n >> k;

    for(int i=1; i<=200; i++){
        memo[1][i] = i;
    }

    for(int i=1; i<=200; i++){
        memo[i][1] = 1;
        memo[0][i] = 1;
    }
    
    for(int i=2; i<=n; i++){
        for(int j=2; j<=200; j++){
            for(int t=0; t<=i; t++){
                memo[i][j] += memo[i-t][j-1];
                memo[i][j] %= 1000000000;
            }
        }
    }

    cout << memo[n][k];

    return 0;
}