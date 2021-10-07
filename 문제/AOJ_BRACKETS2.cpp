#include<iostream>
#include<stack>
#include<string>
using namespace std;

int main()
{
    int c;
    cin >> c;
    
    while(c--){
        string s;
        cin >> s;
        
        stack<char> sta;
        
        bool ans = true;
        
        for(int i=0; i<s.length(); i++){
            if(s[i] == '[' || s[i] == '{' || s[i] == '(')
                sta.push(s[i]);
            else if(!sta.empty() && s[i] == ')' && sta.top() == '(')
                sta.pop();
            else if(!sta.empty() && s[i] == '}' && sta.top() == '{')
                sta.pop();
            else if(!sta.empty() && s[i] == ']' && sta.top() == '[')
                sta.pop();
            else{
                ans = false;
                break;
            }
        }
        
        if(sta.empty() && ans)
            cout << "YES" << '\n';
        else
            cout << "NO" << '\n';
        
    }
    return 0;
}
