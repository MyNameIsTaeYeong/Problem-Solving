#include <iostream>
#include <string>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int m;
    cin >> m;
    
    int s = 0;
    
    while (m--) {
        string mode;
        cin >> mode;
        
        if(mode == "add"){
            int x;
            cin >> x;
            s |= (1<<(x-1));
        }
        else if(mode == "remove"){
            int x;
            cin >> x;
            s &= ~(1<<(x-1));
        }
        else if(mode == "check"){
            int x;
            cin >> x;
            if(s&(1<<(x-1))){
                cout << 1 << '\n';
            }
            else{
                cout << 0 << '\n';
            }
        }
        else if(mode == "toggle"){
            int x;
            cin >> x;
            s ^= (1<<(x-1));
        }
        else if(mode == "all"){
            s = 1024 * 1024 - 1;
        }
        else if(mode == "empty"){
            s = 0;
        }
    }
    
    return 0;
}

