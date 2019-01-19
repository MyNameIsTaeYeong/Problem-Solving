#include<cstdio>
#include<queue>
using namespace std;

bool check[100001];
int d[100001];

int main()
{
    int n, k;
    scanf("%d%d", &n, &k);
    queue<int> q;
    q.push(n);
    check[n] = true;
    
    while(!q.empty()){
        int node = q.front();
        q.pop();
        if(node-1 >= 0){
            if(check[node-1] == false){
                q.push(node-1);
                check[node-1] = true;
                d[node-1] = d[node] + 1;
            }
        }
        if(node+1 <= 100000){
            if(check[node+1] == false){
                q.push(node+1);
                check[node+1] = true;
                d[node+1] = d[node] + 1;
            }
        }
        
        if(2*node <= 100000){
            if(check[2*node] == false){
                q.push(2*node);
                check[2*node] = true;
                d[2*node] = d[node] + 1;
            }
        }
        
    }
    
    printf("%d\n", d[k]);
    
    return 0;
}

