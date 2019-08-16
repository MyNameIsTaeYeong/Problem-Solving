#include<iostream>
using namespace std;

int ar[101];

int main()
{
    int n;
    cin >> n;
    
    int color;
    
    for(int i=0; i<n; i++){
        cin >> color;
        ar[color]++;
    }
    int ans = 0;
    for(int i=1; i<101; i++){
        if(ar[i] != 0)
            ans += ar[i]/2;
    }
    
    cout << ans << "\n";
    
    return 0;
}


