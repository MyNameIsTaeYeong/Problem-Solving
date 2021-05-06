#include <iostream>
#include <vector>

using namespace std;

int main()
{
    int n, s;
    cin >> n >> s;
    
    vector<int> numbers(n);
    
    for(int i=0; i<n; i++){
        cin >> numbers[i];
    }
    
    int ans = 0;
    for(int i=1; i<(1<<n); i++){
        int sum = 0;
        for(int j=0; j<n; j++){
            if(i&(1<<j)){
                sum += numbers[j];
            }
        }
        if(sum == s){
            ans++;
        }
    }
    
    cout << ans;
    
    return 0;
}

