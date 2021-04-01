#include <iostream>
#include <cstdlib>
#include <algorithm>

using namespace std;

int k;
char oper[10];
bool check[10];

long long int max_val = 0;
long long int min_val = 10000000000;

void solve(int index, long long int val, int prev)
{
    if(index == k){
        max_val = max(max_val, val);
        min_val = min(min_val, val);
        return;
    }
    
    for(int i=0; i<10; i++){
        if(oper[index] == '>'){
            if(!check[i] && prev > i){
                check[i] = true;
                solve(index+1, val*10 + i, i);
                check[i] = false;
            }
        }else{
            if(!check[i] && prev < i){
                check[i] = true;
                solve(index+1, val*10 + i, i);
                check[i] = false;
            }
        }
    }
    
    return;
    
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> k;
    for(int i=0; i<k; i++){
        cin >> oper[i];
    }
    
    for(int i=0; i<10; i++){
        check[i] = true;
        solve(0, i, i);
        check[i] = false;
    }
    
   
    cout.width(k+1);
    cout.fill('0');
    cout <<  max_val << '\n';
    cout.width(k+1);
    cout.fill('0');
    cout <<  min_val << '\n';
    return 0;
}
