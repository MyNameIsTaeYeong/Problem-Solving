#include <iostream>
#include <queue>

using namespace std;

bool visited[200001];
int weight[200001];

int main()
{
    int n, k;
    cin >> n >> k;

    visited[n] = true;

    queue<int> first_q, second_q;
    first_q.push(n);

    while(!first_q.empty()){
        int node = first_q.front();
        first_q.pop();

        if(node < 100001 && visited[2*node] == false){
            visited[2*node] = true;
            first_q.push(2*node);
            weight[2*node] = weight[node];
        }

        if(node > 0 && visited[node-1] == false){
            visited[node-1] = true;
            second_q.push(node-1);
            weight[node-1] = weight[node] + 1;
        }

        if(node < 200000 && visited[node+1] == false){
            visited[node+1] = true;
            second_q.push(node+1);
            weight[node+1] = weight[node] + 1;
        }

        if(first_q.empty()){
            first_q = second_q;
            second_q = queue<int>();
        }
    }

    cout << weight[k];

    return 0;
}