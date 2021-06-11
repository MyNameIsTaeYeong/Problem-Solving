#include <vector>
#include <queue>
#include <iostream>
#include <string.h>

using namespace std;

int start_x, start_y, end_x, end_y;
int l;

bool visited[300][300];


int dx[8] = {-1, -2, -2, -1, 1, 2, 2, 1};
int dy[8] = {-2, -1, 1, 2, 2, 1, -1, -2};

bool check(int x, int y){
    if(x == end_x && y == end_y){
        return true;
    }else{
        return false;
    }
}

int bfs(queue< vector<int> > q){
    int rtn = 0;

    while(!q.empty()){
        int x = q.front()[0];
        int y = q.front()[1];
        int cnt = q.front()[2];
        q.pop();

        for(int i=0; i<8; i++){
            if(x+dx[i] < l && x+dx[i] >= 0 && y+dy[i] < l && y+dy[i] >= 0 && visited[x+dx[i]][y+dy[i]] == false){
                
                visited[x+dx[i]][y+dy[i]] = true;
                
                vector<int> temp;
                temp.push_back(x+dx[i]);
                temp.push_back(y+dy[i]);
                temp.push_back(cnt+1);
                q.push(temp);
                
                if(check(x+dx[i], y+dy[i])){
                    rtn = cnt+1;
                    break;
                }
            }
        }
        
        
    }

    return rtn;

}

int main()
{
    int t;
    cin >> t;

    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    while(t--){
        
        cin >> l;
        cin >> start_x >> start_y >> end_x >> end_y;

        memset(visited, false, sizeof(visited));

        queue< vector<int> > q;
        vector<int> start;
        start.push_back(start_x);
        start.push_back(start_y);
        start.push_back(0);
        q.push(start);
        
        if(check(start_x, start_y)){
            cout << 0 << '\n';
        }else{
            cout << bfs(q)<< '\n';
        }

        
    }

    return 0;
}