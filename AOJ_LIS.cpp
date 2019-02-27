#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

int n;
int s[500];
int cache[500];

int lis(int start){
    int& ret = cache[start];
    if(ret != -1) return ret;
    
    ret = 1;
    for(int i=start+1; i<n; i++){
        if(s[start] < s[i])
            ret = max(ret, lis(i) + 1);
    }
    return ret;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        memset(cache, -1, sizeof(cache));
        
        scanf("%d", &n);
        for(int i=0; i<n; i++)
            scanf("%d", &s[i]);
        
        int ans = lis(0);
        for(int i=1; i<n; i++){
            int temp = lis(i);
            if(ans < temp)
                ans = temp;
        }
        
        
        printf("%d\n", ans);
    }
    
    return 0;
}
