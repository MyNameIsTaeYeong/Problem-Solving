#include <iostream>

using namespace std;

int memo[100001];

int main()
{
    int n;
    cin >> n;

    for(int i=1; i<=n; i++){
        int temp = memo[i-1] + 1;
        for(int j=2; j*j<=i; j++){
            if(temp > (memo[i-j*j] + 1)){
                temp = memo[i-j*j] + 1;
            }
        }
        memo[i] = temp;
    }

    cout << memo[n];

    return 0;
}