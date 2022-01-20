#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int memo[100000][2];

int main()
{
    int n;
    cin >> n;

    vector<int> numbers(n);

    for(int i=0; i<n; i++){
        cin >> numbers[i];
    }

    memo[0][0] = numbers[0];

    for(int i=1; i<n; i++){
        if(memo[i-1][0] > 0){
            memo[i][0] = memo[i-1][0] + numbers[i];
        }else{
            memo[i][0] = numbers[i];
        }
    }

    memo[n-1][1] = numbers[n-1];

    for(int i=n-2; i>=0; i--){
        if(memo[i+1][1] > 0){
            memo[i][1] = memo[i+1][1] + numbers[i];
        }else{
            memo[i][1] = numbers[i];
        }
    }

    int ans = -2000000000;

    for(int i=0; i<n; i++){
        int temp1 = memo[i][0] + memo[i][1] - numbers[i];

        int temp2 = memo[i][0] + memo[i][1] - 2*numbers[i] ;

        // temp2 == 0 이면, 수를 선택안하는 경우가 최대인 경우.
        if(temp2 == 0){
            ans = max(ans, temp1);
        }else{
            ans = max(ans, max(temp1, temp2));
        }

        
    }

    cout << ans;

    return 0;
}