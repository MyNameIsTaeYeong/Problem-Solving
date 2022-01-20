#include <iostream>

using namespace std;

/*
 첫번째 열부터 조사가 들어간다.
 이때, 한 점에서 오른쪽 대각선위, 오른쪽, 오른쪽 아래 순으로 갈 수 있는지 조사하고, 다음행으로 넘어간다.
 */

int r,c;
char map[10001][501];

int dx[3] = {1, 1, 1};
int dy[3] = {-1, 0, 1};

int cnt = 0;
int stop = -1;

void dfs(int y, int x){
    map[y][x] = 'o';
    if(x == c-1){
        cnt++;
        stop = 1;
        return;
    }
    
    for(int i=0; i<3; i++){
        int next_x = x + dx[i];
        int next_y = y + dy[i];
        
        if(next_y >= 0 && next_y < r && map[next_y][next_x] == '.')
            dfs(next_y, next_x);
        
        if(stop == 1)
            return;
    }
}

int main()
{
    
    cin >> r >> c;
    
    for(int i=0; i<r; i++){
        for(int j=0; j<c; j++){
            cin >> map[i][j];
        }
    }
    
    for(int i=0; i<r; i++){
        stop = -1;
        dfs(i, 0);
    }
    
    cout << cnt;
    
    return 0;
}
