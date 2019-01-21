#include<iostream>
#include<algorithm>
#include<string>
using namespace std;

char alpha[35];
int l, c;

bool check(string passwd){
    int mo = 0;
    int ja = 0;
    
    for(int i=0; i<passwd.length(); i++){
        if(passwd[i] == 'a' || passwd[i] == 'e' || passwd[i] == 'i' || passwd[i] == 'o' || passwd[i] == 'u')
            mo++;
        else
            ja++;
    }
    
    if(mo>=1 && ja>=2)
        return true;
    else
        return false;
}
// index = alpha 배열의 순서, 
void go(char *a, int index, string passwd, int length){
    if(length == l){
        if(check(passwd)){
            cout << passwd << '\n';
            return;
        }
    }
    
    if(index >= c) return;
    
    go(a, index+1, passwd+alpha[index], length+1);
    go(a, index+1, passwd, length);
}

int main()
{
    cin >> l >> c;
    
    for(int i=0; i<c; i++){
        cin >> alpha[i];
    }
    
    sort(&alpha[0], &alpha[c]);
    
    go(alpha, 0, "", 0);
    
    return 0;
}

