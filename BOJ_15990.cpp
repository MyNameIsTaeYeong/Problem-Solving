#include <iostream>

using namespace std;

const int MOD = 1000000009;


// memo[n][0] : n을 1,2,3의 합으로 나타내는 경우의 수
// memo[n][1] : 합이 +1로 끝나는 경우
// memo[n][2] : 합이 +2로 끝나는 경우
// memo[n][3] : 합이 +3로 끝나는 경우
int memo[100001][4];

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    memo[1][0] = 1;
    memo[1][1] = 1;
    memo[1][2] = 0;
    memo[1][3] = 0;
    
    memo[2][0] = 1;
    memo[2][1] = 0;
    memo[2][2] = 1;
    memo[2][3] = 0;
    
    memo[3][0] = 3;
    memo[3][1] = 1;
    memo[3][2] = 1;
    memo[3][3] = 1;
    
    for(int i=4; i<100001; i++){
        memo[i][1] = (memo[i-1][0] - memo[i-1][1]) % MOD;
        if(memo[i][1]<0){
            memo[i][1] += MOD;
        }
        memo[i][2] = (memo[i-2][0] - memo[i-2][2]) % MOD;
        if(memo[i][2]<0){
            memo[i][2] += MOD;
        }
        memo[i][3] = (memo[i-3][0] - memo[i-3][3]) % MOD;
        if(memo[i][3]<0){
            memo[i][3] += MOD;
        }
        memo[i][0] = ((memo[i][1] + memo[i][2]) % MOD + memo[i][3]) % MOD;
        
    }
    
    int t;
    cin >> t;
    
    while(t--)
    {
        int n;
        cin >> n;
        cout << memo[n][0] << '\n';
    }
    
    
    return 0;
}
