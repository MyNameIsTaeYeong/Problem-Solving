/*
 도시를 지날때마다 가장 싼 리터당 가격으로 이동한다.
 */
#include <stdio.h>
#include <vector>

using namespace std;

vector<int> cost;
vector<long long> distances;
const int billion = 1000000000;

int main()
{
    int n;
    scanf("%d", &n);
    
    for(int i=0; i<n-1; i++){
        int input;
        scanf("%d", &input);
        distances.push_back(input);
    }
    
    for(int i=0; i<n; i++){
        int input;
        scanf("%d", &input);
        cost.push_back(input);
    }
    
    long long ans = 0;
    long long p = 0;
    
    long long min_cost = cost[0];
    
    for(int i=0; i<n-1; i++){
        ans += min_cost * distances[i];
        
        if(ans >= billion){
            p += (ans/billion);
            ans %= billion;
        }
        
        if(cost[i+1] < min_cost)
            min_cost = cost[i+1];
    }
    
    if(p != 0){
        printf("%lld%09lld", p, ans);
    } else {
        printf("%lld", ans);
    }
    
    
    return 0;
}
