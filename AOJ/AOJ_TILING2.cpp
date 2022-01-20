#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

const int MOD = 1000000007;
int n;
int cache[100];


int tiling(int start){
    //기저 사례
    if(start == n)  return 1;
    if(start > n)   return 0;
    
    int& ret = cache[start];
    if(ret != -1)   return ret;
    
    return ret = (tiling(start + 1) + tiling(start + 2)) % MOD;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        memset(cache, -1, sizeof(cache));
        scanf("%d", &n);
        printf("%d\n", tiling(0));
    }
    
    return 0;
}
