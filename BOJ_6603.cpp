#include <iostream>
#include <vector>

using namespace std;

void solve(vector<int> &s, int index, int cnt, int k, vector<int> &ans)
{
    if(cnt == 6){
        for(int i=0; i<6; i++){
            cout << ans[i] << ' ';
        }
        cout << '\n';
        return;
    }
    
    if((6-cnt) > (k-index)){
        return;
    }
    
    for(int i=index; i<k; i++){
        ans.push_back(s[i]);
        solve(s, i+1, cnt+1, k, ans);
        ans.pop_back();
    }
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int k;
    while(true){
        cin >> k;
        if(k == 0){
            break;
        }
        
        vector<int> s(k);
        vector<int> ans;
        for(int i=0; i<k; i++){
            cin >> s[i];
        }
        
        solve(s, 0, 0, k, ans);
        cout << '\n';
        
    }
    
    return 0;
}
