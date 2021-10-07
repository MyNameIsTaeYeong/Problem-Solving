#include <iostream>

using namespace std;

const int MAX = 1000001;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    
    bool check[MAX];
    int prime[MAX];
    int pn = 0;
    
    for(int i=2; i<MAX; i++){
        if(check[i] == false){
            prime[pn++] = i;
            for(int j=i*2; j<MAX; j+=i){
                check[j] = true;
            }
        }
    }
    
   
    
    while(true){
        int n;
        cin >> n;

        bool wrong = true;

        if(n == 0){
            break;
        }

        for(int i=1; i<pn; i++){
            if(check[n - prime[i]] == false){
                cout << n << " = " << prime[i] << " + " << n - prime[i] << '\n';
                wrong = false;
                break;
            }
        }

        if(wrong){
            cout << "Goldbach's conjecture is wrong." << '\n';
        }

    }
    
    
    return 0;
}
