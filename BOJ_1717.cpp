#include<cstdio>
using namespace std;

int p[1000001];

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
    
    for(int i=0; i<=n; i++){
        p[i] = i;
    }
    
    for(int i=0; i<m; i++){
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        if(a==0){
            Union(b, c);
        }else{
            b = Find(b);
            c = Find(c);
            
            if(b == c)
                printf("YES\n");
            else
                printf("NO\n");
        }
    }
    
    return 0;
}

