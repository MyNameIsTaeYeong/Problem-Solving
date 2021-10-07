#include<cstdio>
using namespace std;

int p[101];

int Find(int x){
    if( x == p[x] ){
        return x;
    } else {
        return p[x] = Find(p[x]);
    }
}

void Union(int x, int y){
    x = Find(x);
    y = Find(y);
    if(x!=y){
        p[y] = x;
    }
}


int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    
    for(int i=1; i<=n; i++){
        p[i] = i;
    }
    
    for(int i=0; i<m; i++){
        int a, b;
        scanf("%d%d", &a, &b);
        Union(a, b);
    }
    
    int ans = 0;
    Find(1);
    for(int i=2; i<=n; i++){
        if( Find(i) == p[1])
            ans++;
    }
    
    printf("%d\n", ans);
    
    return 0;
}

