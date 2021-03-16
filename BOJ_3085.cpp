#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int n;

int check(vector<string> &candy){
    int max_value = 0;
    
    for(int i=0; i<n; i++){
        
        int cnt = 1;
        
        for(int j=1; j<n; j++){
            if(candy[i][j-1] == candy[i][j]){
                cnt++;
            }else{
                cnt = 1;
            }
            if(max_value < cnt){
                max_value = cnt;
            }
        }
        
        cnt = 1;
        
        for(int j=1; j<n; j++){
            if(candy[j][i] == candy[j-1][i]){
                cnt++;
            }else{
                cnt = 1;
            }
            if(max_value < cnt){
                max_value = cnt;
            }
        }
    }
    
    return max_value;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
   
    cin >> n;
    
    vector<string> candy(n);
    
    for(int i=0; i<n; i++){
        cin >> candy[i];
    }
    
    int ans = 0;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            //오른쪽
            if(j != n-1){
                swap(candy[i][j], candy[i][j+1]);
                ans = max(ans, check(candy));
                swap(candy[i][j], candy[i][j+1]);
            }
            
            //아래
            if(i != n-1){
                swap(candy[i][j], candy[i+1][j]);
                ans = max(ans, check(candy));
                swap(candy[i][j], candy[i+1][j]);
            }
        }
    }
    
    cout << ans;
    
    return 0;
}
