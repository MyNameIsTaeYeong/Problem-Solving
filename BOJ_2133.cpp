#include <iostream>

using namespace std;

int memo[31];

int main()
{
    int n;
    cin >> n;

    memo[0] = 1;
    memo[2] = 3;

    for(int i=4; i<=n; i+=2){
        memo[i] = 3*memo[i-2];
        for(int j=i-4; j>=0; j-=2){
            memo[i] += 2*memo[j];
        }
    }

    cout << memo[n];
        
    

    return 0;
}