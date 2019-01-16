#include<cstdio>
using namespace std;

int main()
{
    int e,s,m;
    scanf("%d%d%d", &e, &s, &m);
    int ans=1;
    int a=1, b=1, c=1;
    while(1){
        if(a==e && b==s && c==m)
            break;
       
        a++; b++; c++;
        ans++;
        if(a==16)
            a=1;
        if(b==29)
            b=1;
        if(c==20)
            c=1;
    }
    printf("%d\n", ans);
    return 0;
}

