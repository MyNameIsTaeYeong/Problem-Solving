#include <iostream>
#include <algorithm>

using namespace std;


int main()
{
    int memo[1000001];
    
    memo[1] = 0;
    memo[2] = 1;
    memo[3] = 1;
    
    for(int i=4; i<1000001; i++){
        memo[i] = 1000000;
        
        if(i % 3 == 0){
            memo[i] = min(memo[i], memo[i/3] + 1);
        }
        if(i % 2 == 0){
            memo[i] = min(memo[i], memo[i/2] + 1);
        }
        
        memo[i] = min(memo[i], memo[i-1] + 1);
    }
    
    int n;
    cin >> n;
    cout << memo[n];
    
    return 0;
}
