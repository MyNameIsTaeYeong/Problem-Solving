#include <vector>
#include <queue>
#include <iostream>

using namespace std;

int m, n;
int will_be_red = 0;
int farm[1000][1000];
bool visited[1000][1000];
queue< vector<int> > q;

bool check()
{
    will_be_red--;
    if(will_be_red == 0){
        return true;
    }else{
        return false;
    }
}

int bfs(){
    int rtn = -1;

    while(!q.empty()){
        int x = q.front()[0];
        int y = q.front()[1];
        int cur_day = q.front()[2];
        q.pop();

        // 상 x-1, y
        if(x > 0){
            if(farm[x-1][y] == 0 && visited[x-1][y] == false){
                visited[x-1][y] = true;
                vector<int> temp;
                temp.push_back(x-1);
                temp.push_back(y);
                temp.push_back(cur_day+1);
                q.push(temp);

                if(check()){
                    rtn = temp[2];
                    break;
                }

            }
        }

        // 하 x+1, y
        if(x+1 < n){
            if(farm[x+1][y] == 0 && visited[x+1][y] == false){
                visited[x+1][y] = true;
                vector<int> temp;
                temp.push_back(x+1);
                temp.push_back(y);
                temp.push_back(cur_day+1);
                q.push(temp);

                if(check()){
                    rtn = temp[2];
                    break;
                }
            }
        }

        // 좌 x, y-1
        if(y > 0){
            if(farm[x][y-1] == 0 && visited[x][y-1] == false){
                visited[x][y-1] = true;
                vector<int> temp;
                temp.push_back(x);
                temp.push_back(y-1);
                temp.push_back(cur_day+1);
                q.push(temp);

                if(check()){
                    rtn = temp[2];
                    break;
                }
            }
        }

        // 우 x, y+1
        if(y+1 < m){
            if(farm[x][y+1] == 0 && visited[x][y+1] == false){
                visited[x][y+1] = true;
                vector<int> temp;
                temp.push_back(x);
                temp.push_back(y+1);
                temp.push_back(cur_day+1);
                q.push(temp);

                if(check()){
                    rtn = temp[2];
                    break;
                }
            }
        }


    }

    return rtn;

}

int main()
{
    cin >> m >> n;

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            cin >> farm[i][j];
            if(farm[i][j] == 0){
                will_be_red++;
            } 
            else if(farm[i][j] == 1){
                vector<int> first_day;
                first_day.push_back(i);
                first_day.push_back(j);
                first_day.push_back(0);
                q.push(first_day);
                visited[i][j] = true;
            }
        }
    }

    if(will_be_red == 0){
        cout << 0;
    }else{
        cout << bfs();
    }

    return 0;
}