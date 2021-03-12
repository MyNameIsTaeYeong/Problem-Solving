#include <iostream>

using namespace std;

int main()
{
    int n;
    cin >> n;
    
    int ans = 0;
    while(n--){
        int input;
        cin >> input;
        
        if(input == 1){
            continue;
        }
        
        if(input == 2){
            ans++;
            continue;
        }
        
        bool check = true;
        
        for(int i=2; i*i<=input; i++){
            if(input % i == 0) {
                check = false;
                break;
            }
        }
        
        
        if(check){
            ans++;
        }
    }
    
    cout << ans;
    
    return 0;
}
