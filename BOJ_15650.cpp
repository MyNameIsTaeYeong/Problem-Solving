#include <iostream>

using namespace std;

int ans[10];

void go(int index, int start, int m, int n){
    if(index == m){
        for(int i=0; i<m; i++){
            cout << ans[i] << " ";
        }
        cout << '\n';
        return;
    }
    
    if(start == n){
        return;
    }
    
    for(int i=start; i<n; i++){
        ans[index] = i+1;
        go(index+1, i+1, m, n);
    }
    
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n, m;
    cin >> n >> m;
    
    go(0, 0, m, n);
    
    return 0;
}
