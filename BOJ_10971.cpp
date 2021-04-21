#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int w[10][10];
int ans = 100000000;

void solve(vector<int> &permutation)
{
    unsigned long size = permutation.size();
    
    int money = 0;
    for(int i=0; i<size-1; i++){
        if(w[permutation[i]][permutation[i+1]] == 0){
            return;
        }
        money += w[permutation[i]][permutation[i+1]];
    }
    
    if(w[permutation[size-1]][permutation[0]] == 0){
        return;
    }else {
        money += w[permutation[size-1]][permutation[0]];
    }
    
    if(money < ans){
        ans = money;
    }
}

int main()
{
    int n;
    cin >> n;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            cin >> w[i][j];
        }
    }
    
    vector<int> permutation;
    
    for(int i=0; i<n; i++){
        permutation.push_back(i);
    }
    
    
    do{
        solve(permutation);
    }while(next_permutation(permutation.begin(), permutation.end()));
    
    cout << ans;
    
    
    
    return 0;
}
