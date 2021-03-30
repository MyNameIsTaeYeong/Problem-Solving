#include <iostream>
#include <algorithm>

using namespace std;

char alphabet[16];
char ans[16];

void solve(int l, int c, int index, int len)
{
    if(len == l){
        int ja = 0;
        int mo = 0;
        for(int i=0; i<l; i++){
            if(ans[i] == 'a' || ans[i] == 'e' || ans[i] == 'i' || ans[i] == 'o' || ans[i] == 'u'){
                mo++;
            }else{
                ja++;
            }
        }
        if(mo >= 1 && ja >= 2){
            cout << ans << '\n';
        }
        return;
    }
    
    if(index == c){
        return;
    }

    
    //선택한 경우
    ans[len] = alphabet[index];
    solve(l, c, index+1, len+1);
    
    //선택하지 않은 경우
    solve(l, c, index+1, len);
    
    
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    int l, c;
    cin >> l >> c;
    
    for(int i=0; i<c; i++){
        cin >> alphabet[i];
    }
    
    sort(alphabet, alphabet+c);
    
    solve(l, c, 0, 0);
    
    return 0;
}
