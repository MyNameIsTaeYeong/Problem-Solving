#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int memo[1001][3];

int main()
{
    int n;
    cin >> n;

    vector< vector<int> > costs(n+1);
    
    for(int i=1; i<=n; i++){
        for(int j=0; j<3; j++){
            int temp;
            cin >> temp;
            costs[i].push_back(temp);
        }
    }

    memo[1][0] = costs[1][0];
    memo[1][1] = costs[1][1];
    memo[1][2] = costs[1][2];

    for(int i=2; i<=n; i++){
        memo[i][0] = min(memo[i-1][1]+costs[i][0], memo[i-1][2]+costs[i][0]);
        memo[i][1] = min(memo[i-1][0]+costs[i][1], memo[i-1][2]+costs[i][1]);
        memo[i][2] = min(memo[i-1][0]+costs[i][2], memo[i-1][1]+costs[i][2]);
    }

    int ans = min(min(memo[n][0], memo[n][1]), memo[n][2]);
    
    cout << ans;

    return 0;
}