#include <stdio.h>
#include <queue>
#include <vector>


using namespace std;

int n, m;
int map[100][100];
bool visited[100][100];

bool check(int x, int y){
    if(x == (n-1) && y == (m-1)){
        return true;
    }else{
        return false;
    }
}

int bfs(int x, int y)
{
    int rtn = 1;

    queue< vector<int> > q;
    vector<int> input;
    input.push_back(x);
    input.push_back(y);
    input.push_back(1);
    q.push(input);
    visited[x][y] = true;


    while(!q.empty()){
        int cur_x = q.front()[0];
        int cur_y = q.front()[1];
        int dist = q.front()[2];
        q.pop();

        //상 cur_x-1, cur_y
        if(cur_x > 0){
            if(map[cur_x-1][cur_y] == 1 && visited[cur_x-1][cur_y] == false){
                vector<int> temp_input;
                temp_input.push_back(cur_x-1);
                temp_input.push_back(cur_y);
                temp_input.push_back(dist+1);
                q.push(temp_input);
                visited[cur_x-1][cur_y] = true;
                if(check(cur_x-1, cur_y)){
                    rtn = dist+1;
                    break;
                }
            }
        }

        //하 cur_x+1, cur_y
        if((cur_x + 1) < n){
            if(map[cur_x+1][cur_y] == 1 && visited[cur_x+1][cur_y] == false){
                vector<int> temp_input;
                temp_input.push_back(cur_x+1);
                temp_input.push_back(cur_y);
                temp_input.push_back(dist+1);
                q.push(temp_input);
                visited[cur_x+1][cur_y] = true;
                if(check(cur_x+1, cur_y)){
                    rtn = dist+1;
                    break;
                }
            }
        }

        //좌 cur_x, cur_y-1
        if(cur_y > 0){
            if(map[cur_x][cur_y-1] == 1 && visited[cur_x][cur_y-1] == false){
                vector<int> temp_input;
                temp_input.push_back(cur_x);
                temp_input.push_back(cur_y-1);
                temp_input.push_back(dist+1);
                q.push(temp_input);
                visited[cur_x][cur_y-1] = true;
                if(check(cur_x, cur_y-1)){
                    rtn = dist+1;
                    break;
                }
            }
        }

        //우 cur_x, cur_y+1
        if(cur_y+1 < m){
            if(map[cur_x][cur_y+1] == 1 && visited[cur_x][cur_y+1] == false){
                vector<int> temp_input;
                temp_input.push_back(cur_x);
                temp_input.push_back(cur_y+1);
                temp_input.push_back(dist+1);
                q.push(temp_input);
                visited[cur_x][cur_y+1] = true;
                if(check(cur_x, cur_y+1)){
                    rtn = dist+1;
                    break;
                }
            }
        }

    }

    return rtn;
}

int main()
{
    scanf("%d %d", &n, &m);

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            scanf("%1d", &map[i][j]);
        }
    }

    int ans = bfs(0, 0);

    printf("%d", ans);

    return 0;
}