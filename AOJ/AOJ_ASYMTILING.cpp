#include<cstdio>
#include<cstring>
using namespace std;

const int MOD = 1000000007;
int n;
int cache[101];

int tiling2(int index){
    if(index == n)  return 1;
    if(index > n)   return 0;
    
    int& ret = cache[index];
    if(ret != -1)  return ret;
    
    return ret = (tiling2(index+1) + tiling2(index+2))%MOD;
}

int asymmetric(){
    if(n%2 == 1)
        return (cache[0] - cache[n/2+1] + MOD) % MOD;
    
    return (cache[0] - (cache[n/2] + cache[n/2 + 1])%MOD + MOD) % MOD;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        memset(cache, -1, sizeof(cache));
        scanf("%d", &n);
        
        if(n == 2)
            printf("%d\n", 0);
        else{
            tiling2(0);
            printf("%d\n", asymmetric());
        }
    }
    return 0;
}
