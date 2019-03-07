#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

int n;
int cache[100][100];
int cache2[100][100];
int triangle[100][100];

int path(int y, int x){
    if(y == n-1)  return cache[y][x] = triangle[y][x];
    
    int& ret = cache[y][x];
    if(ret != -1)  return cache[y][x];
    
    return ret = max(path(y+1, x), path(y+1, x+1)) + triangle[y][x];
}

int pathCNT(int y, int x){
    if(y == n-1)    return 1;
    
    int& ret = cache2[y][x];
    if(ret != -1)  return cache2[y][x];
    
    ret = 0;
    if(cache[y+1][x] >= cache[y+1][x+1]) ret += pathCNT(y+1, x);
    if(cache[y+1][x] <= cache[y+1][x+1]) ret += pathCNT(y+1, x+1);
    
    return ret;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        memset(cache, -1, sizeof(cache));
        memset(cache2, -1, sizeof(cache2));
        scanf("%d", &n);
        
        for(int i=0; i<n; i++)
            for(int j=0; j<=i; j++)
                scanf("%d", &triangle[i][j]);
        
        
        path(0, 0);
        printf("%d\n", pathCNT(0, 0));
    }
    
    return 0;
}
