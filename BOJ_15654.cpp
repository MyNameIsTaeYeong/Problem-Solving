#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<pair<int, bool>> num(10);
int ans[10];

void go(int index, int n, int m)
{
    if(index == m){
        for(int i=0; i<m; i++){
            cout << ans[i] << " ";
        }
        cout << '\n';
        return;
    }
    
    for(int i=0; i<n; i++){
        if(num[i].second){
            continue;
        }
        num[i].second = true;
        ans[index] = num[i].first;
        go(index+1, n, m);
        num[i].second = false;
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
    
    go(0, n, m);
    
    
    return 0;
}
