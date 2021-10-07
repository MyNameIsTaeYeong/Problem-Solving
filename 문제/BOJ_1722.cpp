#include<cstdio>
using namespace std;

long long fac[21]={1};
int permu[20];
bool check[21];

int main()
{
    for(int i=1;i<=20;i++){
        fac[i] = fac[i-1]*i;
    }
    
    int n, q;
    scanf("%d%d", &n, &q);
    
    if(q==1){
        long long k;
        scanf("%lld", &k);
        
        for(int i=0; i<n; i++){
            for(int j=1; j<=n; j++){
                if(check[j] == true) continue;
                
                if(k > fac[n-i-1]){
                    k -= fac[n-i-1];
                }else{
                    permu[i] = j;
                    check[j] = true;
                    break;
                }
            }
        }
        
        for(int i=0; i<n; i++)
            printf("%d ", permu[i]);
        puts("");
        
    }else if(q==2){
        long long ans=0;
        for(int i=0;i<n;i++){
            scanf("%d", &permu[i]);
        }
        for(int i=0;i<n;i++){
            for(int j=1;j<permu[i];j++){
                if(check[j]==false)
                    ans += fac[n-i-1];
            }
            check[permu[i]] = true;
        }
        printf("%lld\n", ans+1);
    }
    
    
    
    
    return 0;
}

