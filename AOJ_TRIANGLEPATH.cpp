#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

int n;
int triangle[100][100];
int cache[100][100];

int path(int y, int x){
    if(y == n-1)
        return triangle[y][x];
    
    int& ret = cache[y][x];
    if(ret != -1)
        return ret;
    
    return ret = max(path(y+1, x), path(y+1, x+1)) + triangle[y][x];
}

int main()
{
    int c;
    scanf("%d", &c);
    while(c--){
        memset(cache, -1, sizeof(cache));
        
        scanf("%d", &n);
        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){
                scanf("%d", &triangle[i][j]);
            }
        }
        
        printf("%d\n", path(0,0));
        
    }
    return 0;
}

