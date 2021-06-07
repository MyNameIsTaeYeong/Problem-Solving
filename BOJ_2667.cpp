#include <stdio.h>
#include <queue>
#include <vector>
#include <algorithm>
#include <utility>

using namespace std;

int n;

int map[25][25];
bool check[25][25];

int bfs(int x, int y)
{
    int rtn = 1;

    queue< pair<int, int> > q;
    q.push(make_pair(x, y));
    check[x][y] = true;
    
    while(!q.empty()){
        int cur_x = q.front().first;
        int cur_y = q.front().second;
        q.pop();

        //상 x, y-1
        if(cur_y >= 1){
            if(map[cur_x][cur_y-1] == 1 && check[cur_x][cur_y-1] == false){
                check[cur_x][cur_y-1] = true;
                q.push(make_pair(cur_x, cur_y-1));
                rtn++;
            }
        }

        //하 x, y+1
        if(cur_y+1 < n){
            if(map[cur_x][cur_y+1] == 1 && check[cur_x][cur_y+1] == false){
                check[cur_x][cur_y+1] = true;
                q.push(make_pair(cur_x, cur_y+1));
                rtn++;
            }
        }

        //좌 x-1, y
        if(cur_x >= 1){
            if(map[cur_x-1][cur_y] == 1 && check[cur_x-1][cur_y] == false){
                check[cur_x-1][cur_y] = true;
                q.push(make_pair(cur_x-1, cur_y));
                rtn++;
            }
        }

        //우 x+1, y
        if(cur_x+1 < n){
            if(map[cur_x+1][cur_y] == 1 && check[cur_x+1][cur_y] == false){
                check[cur_x+1][cur_y] = true;
                q.push(make_pair(cur_x+1, cur_y));
                rtn++;
            }
        }
    }

    return rtn;
}

int main()
{
    scanf("%d", &n);

    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            scanf("%1d", &map[i][j]);
        }
    }

    vector<int> danzi_cnt;
    int danzi = 0;
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            if(map[i][j] == 1 && check[i][j] == false){
                int temp = bfs(i, j);
                danzi_cnt.push_back(temp);
                danzi++;
            }
        }
    }

    sort(danzi_cnt.begin(), danzi_cnt.end());

    printf("%d\n", danzi);
    for(int i=0; i<danzi; i++){
        printf("%d\n",danzi_cnt[i]);
    }

    return 0;
}