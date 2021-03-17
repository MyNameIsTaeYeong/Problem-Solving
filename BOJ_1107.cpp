#include <iostream>

using namespace std;

const int MAX = 1000000;

bool broken[10];

bool possible(int i){
    if(i==0){
        if(broken[i]){
            return false;
        }else{
            return true;
        }
    }
    
    while(i != 0){
        if(broken[i % 10]){
            return false;
        }
        i /= 10;
    }
    return true;
}

int main()
{
    int n, m;
    cin >> n >> m;

    for(int i=0; i<m; i++){
        int btn;
        cin >> btn;
        broken[btn] = true;
    }

    int ans = MAX;
    int chanel = -1;
    
    
    for(int i = 0; i <= MAX; i++){
        if(!possible(i)){
            continue;
        }
        if(ans > abs(n-i)){
            ans = abs(n-i);
            chanel = i;
        }
    }
    
    while(true){
        ans++;
        chanel /= 10;
        if(chanel == 0){
            break;
        }
    }
    
    ans = min(ans, abs(n-100));
    
    
    cout << ans;
    
    return 0;
}
