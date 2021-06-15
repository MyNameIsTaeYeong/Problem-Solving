#include <iostream>
#include <queue>
#include <stack>
#include <utility>

using namespace std;

bool visited[100001];
int from[100001];

int bfs(queue< pair<int, int> > q, int k)
{
    int rtn = -1;

    while(!q.empty()){
        int node = q.front().first;
        int sec = q.front().second;
        q.pop();

        if(node > 0 && visited[node-1] == false){
            visited[node-1] = true;
            from[node-1] = node;
            q.push(make_pair(node-1, sec+1));
            if(node-1 == k){
                rtn = sec+1;
                break;
            }
        }

        if(node < 100000 && visited[node+1] == false){
            visited[node+1] = true;
            from[node+1] = node;
            q.push(make_pair(node+1, sec+1));
            if(node+1 == k){
                rtn = sec+1;
                break;
            }
        }

        if(2*node <= 100000 && visited[2*node] == false){
            visited[2*node] = true;
            from[2*node] = node;
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
    from[n] = -1;


    if(n == k){
        cout << 0 << '\n';
        cout << n ;
    }else{
        cout << bfs(q, k) << '\n';
        
        stack<int> path;
        int node = k;
        
        
        while(node != -1){
            path.push(node);
            node = from[node];
        }

        while(!path.empty()){
            cout << path.top() << ' ';
            path.pop();
        }

    }

    return 0;
}