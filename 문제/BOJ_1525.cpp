#include<iostream>
#include<string>
#include<map>
#include<queue>
using namespace std;

int dx[4] = { -1, 1, 0, 0};
int dy[4] = { 0, 0, 1, -1};

int main()
{
    int n = 3;
    int start = 0;
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            int temp;
            cin>>temp;
            if(temp == 0)
                temp = 9;
            start = start*10 + temp;
        }
    }
    
    map<int, int> m;
    queue<int> q;
    q.push(start);
    m[start] = 0;
    
    while(!q.empty()){
        int now = q.front();
        q.pop();
        string s = to_string(now);
        int z = s.find('9');
        int x = z / 3;
        int y = z % 3;
        
        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(nx>=0 && nx < 3 && ny>=0 && ny < 3){
                string next = s;
                swap(next[3*x+y], next[3*nx+ny]);
                int key = stoi(next);
                if(m.count(key) == 0){
                    m[key] = m[now] + 1;
                    q.push(key);
                }
            }
        }
    }
    
    if(m.count(123456789) == 0)
        cout << -1 << '\n';
    else
        cout << m[123456789] << '\n';
    
    return 0;
}

