#include <iostream>
#include <deque>

using namespace std;

bool visited[200001];
int weight[200001];

int main()
{
    int n, k;
    cin >> n >> k;

    deque<int> dq;
    dq.push_front(n);
    visited[n] = true;

    while (!dq.empty())
    {
        int node = dq.front();
        dq.pop_front();

        if(node < 100001 && visited[2*node] == false){
            visited[2*node] = true;
            dq.push_front(2*node);
            weight[2*node] = weight[node];
        }

        if(node > 0 && visited[node-1] == false){
            visited[node-1] = true;
            dq.push_back(node-1);
            weight[node-1] = weight[node] + 1;
        }

        if(node < 200000 && visited[node+1] == false){
            visited[node+1] = true;
            dq.push_back(node+1);
            weight[node+1] = weight[node] + 1;
        }
    }
    
    cout << weight[k];

    return 0;
}