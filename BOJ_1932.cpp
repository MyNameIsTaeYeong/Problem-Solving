#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

int n;
int cache[500][500];
int triangle[500][500];

int path(int x, int y){
    //기저사례
    if(x == n-1)
        return triangle[x][y];
    
    int& ret = cache[x][y];
    if(ret != -1)
        return ret;
    
    for(int i=0; i<2; i++)
        ret = max(ret, triangle[x][y] + path(x+1, y+i));
    
    return ret;
}

int main()
{
    memset(cache, -1, sizeof(cache));
    
    scanf("%d", &n);
    
    for(int i=0; i<n; i++){
        for(int j=0; j<=i; j++){
            scanf("%d", &triangle[i][j]);
        }
    }
    
    printf("%d\n", path(0, 0));
    
    return 0;
}
