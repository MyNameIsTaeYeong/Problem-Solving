#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int n;
    cin >> n;
    
    vector<int> p(n+1);
    
    for(int i=1; i<=n; i++){
        cin >> p[i];
    }
    
    int memo[1001];
    memo[1] = p[1];
    
    for(int i=2; i<1001; i++){
        int temp = p[i];
        for(int j=1; j<i; j++){
            if(temp > p[j]+memo[i-j]){
                temp = p[j]+memo[i-j];
            }
        }
        memo[i] = temp;
    }
    
    cout << memo[n];
    
    return 0;
}
