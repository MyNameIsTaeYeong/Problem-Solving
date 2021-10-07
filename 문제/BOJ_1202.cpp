/*
 가장 비싼 보석을 가장 가벼운 가방부터 넣어본다.
 */

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

vector<pair<int, int>> jewelry;
vector<int> bag;

int main()
{
    int n, k;
    cin >> n >> k;
    
    for(int i=0; i<n; i++){
        int m, v;
        cin >> m >> v;
        jewelry.push_back(make_pair(m, v));
    }
    
    for(int i=0; i<k; i++){
        int weight;
        cin >> weight;
        bag.push_back(weight);
    }
    
    sort(jewelry.begin(), jewelry.end());
    sort(bag.begin(), bag.end());
    
    
    long long ans = 0;
    priority_queue<int> pq;
    
    for(int i=0, j=0; i<k; i++){
        while(j<n && bag[i] >= jewelry[j].first)
            pq.push(jewelry[j++].second);
        
        if(!pq.empty()){
            ans += pq.top();
            pq.pop();
        }
    }
    
    cout << ans;
    
    return 0;
}
