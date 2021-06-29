#include <iostream>
#include <vector>
#include <queue>
using namespace std;

bool visited[2500][2500];

int main()
{
    int s;
    cin >> s;

    visited[1][0] = true;
    visited[1][1] = true;

    vector<int> start(3);
    start[0] = 1;
    start[1] = 1;
    start[2] = 1;
    
    queue< vector<int> > q;
    q.push(start);

    int ans = 0;

    while(!q.empty()){
        vector<int> node = q.front();
        q.pop();

        if(node[0] != node[1] && visited[node[0]][node[0]] == false){
            visited[node[0]][node[0]] = true;
            vector<int> next(3);
            next[0] = node[0];
            next[1] = node[0];
            next[2] = node[2] + 1;
            q.push(next);
        }

        if((node[0]+node[1]) < 2500 && visited[node[0]+node[1]][node[1]] == false){
            visited[node[0]+node[1]][node[1]] = true;
            vector<int> next(3);
            next[0] = node[0]+node[1];
            next[1] = node[1];
            next[2] = node[2] + 1;
            q.push(next);

            if(next[0] == s){
                ans = next[2];
                break;
            }
        }

        if(node[0] > 1 && visited[node[0]-1][node[1]] == false){
            visited[node[0]-1][node[1]] = true;
            vector<int> next(3);
            next[0] = node[0] - 1;
            next[1] = node[1];
            next[2] = node[2] + 1;
            q.push(next);

            if(next[0] == s){
                ans = next[2];
                break;
            }
        }

    }

    cout << ans;

    return 0;
}