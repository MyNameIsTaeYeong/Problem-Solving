#include <iostream>

using namespace std;

bool check[10];
int ans[10];

void go(int index, int m, int n){
    if(index == m){
        for(int i=0; i<m; i++){
            cout << ans[i] << " ";
        }
        cout << '\n';
        return;
    }
    
    for(int i=0; i<n; i++){
        if(check[i]){
            continue;
        }
        check[i] = true;
        ans[index] = i+1;
        go(index+1, m, n);
        check[i] = false;
    }
    
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n, m;
    cin >> n >> m;
    
    go(0, m, n);
    
    return 0;
}
