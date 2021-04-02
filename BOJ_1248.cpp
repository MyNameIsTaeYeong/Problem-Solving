#include <iostream>

using namespace std;

int n;
char s[10][10];
int ans[10];


bool check(int index)
{
    int sum = 0;
    for(int i=index; i>=0; i--){
        sum += ans[i];
        switch (s[i][index]) {
            case '+':
                if(sum <= 0){
                    return false;
                }
                break;
            case '-':
                if(sum >= 0){
                    return false;
                }
                break;
            case '0':
                if(sum != 0){
                    return false;
                }
                break;
        }
    }
    
    return true;
}

bool solve(int index)
{
    if(index == n){
        return check(index);
    }
    
    // 부호
    int buho = 0;
    switch (s[index][index]) {
        case '+':
            buho = 1;
            break;
        case '-':
            buho = -1;
            break;
        case '0':
            buho = 0;
            break;
    }
    
    if(buho == 0){
        ans[index] = 0;
        if(check(index) && solve(index+1)){
            return true;
        }
    }else {
        for(int i=1; i<=10; i++){
            ans[index] = buho * i;
            if(check(index) && solve(index+1)){
                return true;
            }
        }
    }

    return false;
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    cin >> n;
    
    for(int i=0; i<n; i++){
        for(int j=i; j<n; j++){
            cin >> s[i][j];
        }
    }
    
    if(solve(0)){
        for(int i=0; i<n; i++){
            cout << ans[i] << " ";
        }
    }
    
    return 0;
}
