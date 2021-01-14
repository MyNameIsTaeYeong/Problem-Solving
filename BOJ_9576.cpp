#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

bool compare_first(pair<int, int> a, pair<int, int> b){
    return a.first < b.first;
}

bool compare_second(pair<int, int> a, pair<int, int> b){
    return a.second < b.second;
}

int main()
{
    int t;
    cin >> t;
    
    while(t--){
        int n, m;
        cin >> n >> m;
        
        vector<int> book(n+1, 0);
        vector<pair<int, int>> want;
        
        for(int i=0; i<m; i++){
            int a, b;
            cin >> a >> b;
            want.push_back(make_pair(a, b));
        }
        
        sort(want.begin(), want.end(), compare_first);
        sort(want.begin(), want.end(), compare_second);
        
        int ans = 0;
        
        for(int i=0; i<m; i++){
            if(book[want[i].first] == 0){
                book[want[i].first] = 1;
                ans++;
            } else {
                for(int j = want[i].first+1;
                    j <= want[i].second;
                    j++){
                    if(book[j] == 0){
                        book[j] = 1;
                        ans++;
                        break;
                    }
                }
            }
        }
        
        cout << ans << '\n';
    }
    
    return 0;
}
