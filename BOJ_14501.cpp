#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

int t[16];
int p[16];
int n;
int cache[16];

//day번째 날 상담을 했을 때 최대 수익 리턴
int consulting(int day){
    if(day >= n+1)   return 0;
    
    int& ret = cache[day];
    if(ret != -1)   return ret;
    
    ret = 0;
    
    //오늘 일을 하는 경우
    if(day + t[day] <= n+1)
        ret = p[day] + consulting(day + t[day]);
    
    //오늘 일을 하지 않는 경우
    for(int i=1; day + i <= n; i++)
        ret = max(ret, consulting(day + i));
    
    return ret;
}

int main()
{
    memset(cache, -1, sizeof(cache));
    scanf("%d", &n);
    
    for(int i=1; i<=n; i++)
        scanf("%d%d", &t[i], &p[i]);
    
    int ans=0;
    for(int i=1; i<=n; i++)
        ans = max(ans, consulting(i));
    
    printf("%d\n", ans);
    
    return 0;
}
