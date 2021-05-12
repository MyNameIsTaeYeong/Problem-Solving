#include <iostream>

using namespace std;

int memo[1001][2];

int main()
{
    int n;
    cin >> n;

    cin >> memo[1][0];
    memo[1][1] = 1;

    int ans = memo[1][1];

    for(int i=2; i<=n; i++){
        cin >> memo[i][0];

        int temp = 1;
        for(int j=i-1; j>=1; j--){
            if(memo[j][0] < memo[i][0]){
                if(temp < memo[j][1]+1){
                    temp = memo[j][1] + 1;
                }
            }
        }
    
        memo[i][1] = temp;
        
        if(memo[i][1] > ans){
            ans = memo[i][1];
        }
    }

    cout << ans;

    return 0;
}