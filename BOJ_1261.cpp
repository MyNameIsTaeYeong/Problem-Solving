#include <stdio.h>
#include <deque>

using namespace std;

bool visited[100][100];
int weight[100][100];
int miro[100][100];

// (x+1, y), (x, y+1), (x-1, y), (x, y-1) 
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int main()
{
    int n, m;
    scanf("%d %d", &m, &n);

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            scanf("%1d", &miro[i][j]);
        }
    }

    deque< pair<int, int> > dq;
    dq.push_front(make_pair(0,0));
    visited[0][0] = true;

    while(!dq.empty()){
        int x = dq.front().first;
        int y = dq.front().second;
        dq.pop_front();

        // (x+1, y), (x, y+1), (x-1, y), (x, y-1) 
        for(int i=0; i<4; i++){
            if(0 <= x+dx[i] && x+dx[i] < n && 0 <= y+dy[i] && y+dy[i] < m){
                if(!visited[x+dx[i]][y+dy[i]]){
                    visited[x+dx[i]][y+dy[i]] = true;
                    if(miro[x+dx[i]][y+dy[i]] == 1){
                        dq.push_back(make_pair(x+dx[i], y+dy[i]));
                        weight[x+dx[i]][y+dy[i]] = weight[x][y] + 1;
                    }else{
                        dq.push_front(make_pair(x+dx[i], y+dy[i]));
                        weight[x+dx[i]][y+dy[i]] = weight[x][y];
                    }
                }
            }
        }
    }

    printf("%d", weight[n-1][m-1]);
    

    return 0;
}