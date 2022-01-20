#include <iostream>

using namespace std;

const int MAX = 1000001;

int main()
{
    int prime[MAX];
    bool check[MAX];
    int pn = 0;
    
    int m, n;

    cin >> m >> n;
    
    for(int i=2; i<MAX; i++){
        if(check[i] == false){
            prime[pn++] = i;
        }
        for(int j=i*2; j<MAX; j+=i){
            check[j] = true;
        }
    }
    
   
    for(int i=0; i<pn; i++){
        if(prime[i] >= m && prime[i] <=n){
            cout << prime[i] << '\n';
        }
    }
    
  
    
    return 0;
}
