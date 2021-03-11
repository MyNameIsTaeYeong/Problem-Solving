#include <iostream>
#include <vector>

using namespace std;

const int MAX = 1000001;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    vector<long long> f(MAX, 1);
    vector<long long> g(MAX, 0);
    
    for(int i=2; i<MAX; i++){
        for(int j=1; i*j<MAX; j++){
            f[i*j] += i;
        }
    }
    
    g[1] = f[1];
    
    for(int i=2; i<MAX; i++){
        g[i] = g[i-1] + f[i];
    }
    
    int t;
    cin >> t;
    
    while(t--){
        int n;
        cin >> n;
        
        cout << g[n] << '\n';
    }
    
    return 0;
}
