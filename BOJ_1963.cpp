#include<cstdio>
#include<queue>
#include<cstring>
#include<string>
using namespace std;

bool prime[10000];
int d[10000];
bool c[10000];

int change(int front, int index, int value){
    if(index == 0 && value == 0) return -1;
    string s = to_string(front);
    s[index] = value + '0';
    return stoi(s);
}

int main()
{
    prime[0] = prime[1] = true;
    for(int i=2; i*i<10000; i++){
        if(prime[i] == false){
            for(int j=2*i; j<10000; j+=i){
                prime[j] = true;
            }
        }
    }
    
    
    int t;
    scanf("%d", &t);
    while(t--){
        int a, b;
        scanf("%d%d", &a, &b);
        memset(d,0,sizeof(d));
        memset(c,false,sizeof(c));
        queue<int> q;
        q.push(a);
        c[a] = true;
        
        while(!q.empty()){
            int front = q.front();
            q.pop();
            
            for(int i=0; i<4; i++){
                for(int j=0; j<10; j++){
                    int next = change(front, i, j);
                    if(next != -1){
                        if(prime[next]==false && c[next] ==false){
                            q.push(next);
                            c[next] = true;
                            d[next] = d[front] + 1;
                        }
                    }
                }
            }
        }
        printf("%d\n", d[b]);
    }
    return 0;
}

