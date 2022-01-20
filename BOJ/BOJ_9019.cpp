#include<iostream>
#include<cstring>
#include<string>
#include<queue>
#include<algorithm>
using namespace std;

int from[10001];
char command[10001];
bool check[10001];

int main()
{
    int t;
    cin>>t;

    while(t--){
        int a,b;
        cin>>a>>b;
        memset(command, 0, sizeof(command));
        memset(check, false, sizeof(check));
        memset(from, 0, sizeof(from));
        queue<int> q;
        q.push(a);
        check[a] = true;
        from[a] = -1;
        
        while(!q.empty()){
            int now = q.front();
            q.pop();
            
            int next = (now*2) % 10000;
            if(check[next] == false){
                q.push(next);
                check[next] = true;
                from[next] = now;
                command[next] = 'D';
            }
            
            next = now-1;
            if(next == -1)
                next = 9999;
            if(check[next] == false){
                q.push(next);
                check[next] = true;
                from[next] = now;
                command[next] = 'S';
            }
            
            //string temp = to_string(now);
            //next = (temp[1]-'0')*1000 + (temp[2] - '0')*100 + (temp[3] - '0')*10 + temp[0] - '0';
            next = (now%1000)*10 + now/1000;
            if(check[next] == false){
                q.push(next);
                check[next] = true;
                from[next] = now;
                command[next] = 'L';
            }
            
            //next = (temp[3]-'0')*1000 + (temp[0] - '0')*100 + (temp[1] - '0')*10 + temp[2] - '0';
            next = (now/10) + (now%10)*1000;
            if(check[next] == false){
                q.push(next);
                check[next] = true;
                from[next] = now;
                command[next] = 'R';
            }
        }
        
        string ans = "";
        while (b != a) {
            ans += command[b];
            b = from[b];
        }
        reverse(ans.begin(), ans.end());
        cout << ans << '\n';
    }
    return 0;
}

