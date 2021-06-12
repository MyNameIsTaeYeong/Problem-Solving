#include <iostream>
#include <queue>
#include <utility>

using namespace std;

bool visited[100001];

int bfs(queue< pair<int, int> > q, int k)
{
    int rtn = -1;

    while(!q.empty()){
        int node = q.front().first;
        int sec = q.front().second;
        q.pop();

        if(node > 0 && visited[node-1] == false){
            visited[node-1] = true;
            q.push(make_pair(node-1, sec+1));
            if(node-1 == k){
                rtn = sec+1;
                break;
            }
        }

        if(node < 100000 && visited[node+1] == false){
            visited[node+1] = true;
            q.push(make_pair(node+1, sec+1));
            if(node+1 == k){
                rtn = sec+1;
                break;
            }
        }

        if(2*node <= 100000 && visited[2*node] == false){
            visited[2*node] = true;
            q.push(make_pair(2*node, sec+1));
            if(2*node == k){
                rtn = sec+1;
                break;
            }
        }


    }

    return rtn;

}

int main()
{
    int n, k;
    cin >> n >> k;

    visited[n] = true;

    queue< pair<int, int> > q;
    q.push(make_pair(n, 0));

    int ans = 0;

    if(n == k){
        cout << 0;
    }else{
        cout << bfs(q, k);
    }

    
    

    return 0;
}