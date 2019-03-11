#include<cstdio>
#include<cstring>
using namespace std;

const int MOD = 10*1000*1000;
int cache[101][101];
int n;

int poly(int size, int first){
    if(size == first)   return 1;
    
    int& ret = cache[size][first];
    if(ret != -1)   return ret;
    
    ret = 0;
    for(int second=1; second<=(size-first); second++){
        ret += (second+first-1) * poly(size-first, second) % MOD;
        ret %= MOD;
    }
    return ret;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        scanf("%d", &n);
        memset(cache, -1, sizeof(cache));
        
        int ans = 0;
        for(int i=1; i<=n; i++)
            ans += poly(n, i) % MOD;
        
        printf("%d\n", ans % MOD);
    }
    return 0;
}
