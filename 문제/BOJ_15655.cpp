#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<int, bool>> num(10);
int ans[10];

void go(int index, int start, int n, int m)
{
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
        ans[index] = num[i].first;
        go(index+1, i+1, n, m);
    }
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int n, m;
    cin >> n >> m;
    
    
    
    for(int i=0; i<n; i++){
        cin >> num[i].first;
    }
    for(int i=0; i< 10-n; i++){
        num.pop_back();
    }
    sort(num.begin(), num.end());
    
    go(0, 0, n, m);
    
    
    return 0;
}
