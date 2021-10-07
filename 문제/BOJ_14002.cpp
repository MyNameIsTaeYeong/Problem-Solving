#include <iostream>
#include <stack>
using namespace std;

int memo[1001][3];

int main()
{
    int n;
    cin >> n;

    cin >> memo[1][0];
    memo[1][1] = 1;

    int max_len = 1;
    int last_index = 1;
    for(int i=2; i<=n; i++){
        cin >> memo[i][0];

        int temp_len = 1;
        for(int j=i-1; j>=1; j--){
            if(memo[j][0] < memo[i][0]){
                if(temp_len < memo[j][1]+1){
                    temp_len = memo[j][1] + 1;
                    memo[i][2] = j;
                }
            }
        }
        
        memo[i][1] = temp_len;

        if(memo[i][1] > max_len){
            last_index = i;
            max_len = memo[i][1];
        }
    }

    stack<int> ans;

    while(true)
    {
        ans.push(memo[last_index][0]);
        last_index = memo[last_index][2];
        if(last_index == 0){
            break;
        }
    }

    cout << max_len << '\n';

    while (!ans.empty())
    {
        cout << ans.top() << " ";
        ans.pop();
    }
    

    return 0;
}