#include<cstdio>
#include<cstring>
using namespace std;

int grid[100][100];

// 도착한 점에서 목적지까지 이동할 방법이 없는 경우 0을 저장, 있는 경우 1을 저장
int cache[100][100];

int n;

int go(int x, int y){
    if(x >= n || y >= n)
        return 0;
    
    if(x == n-1 && y == n-1)
        return 1;
    
    int& ret = cache[x][y];
    if(ret != -1) return ret;
    
    int movement = grid[x][y];
    
    if(x+movement >= n && y+movement >= n)
        ret = 0;
    else{
        // 오른쪽 또는 아래로 이동이 가능한 경우
        if(go(x, y+movement) || go(x+movement, y))
            ret = 1;
        else
            ret = 0;
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
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                scanf("%d", &grid[i][j]);
            }
        }
        
        if(go(0,0))
            printf("YES\n");
        else
            printf("NO\n");
        
    }
    return 0;
}

