#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    vector<int> height(9);
    
    int total = 0;
    
    for(int i=0; i<9; i++){
        cin >> height[i];
        total += height[i];
    }
    
    bool escape = false;
    
    for(int i=0; i<8; i++){
        for(int j=i+1; j<9; j++){
            if((total - height[i] - height[j]) == 100){
                height[i] = 0;
                height[j] = 0;
                escape = true;
                break;
            }
        }
        if(escape)
            break;
    }
    
    sort(height.begin(), height.end());
    
    for(int i=2; i<9; i++){
        cout << height[i] << '\n';
    }
    
    return 0;
}
