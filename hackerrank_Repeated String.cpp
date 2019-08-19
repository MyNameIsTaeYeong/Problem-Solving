#include<iostream>
#include<string>
using namespace std;

int main()
{
    string str;
    long int n;
    
    cin >> str;
    cin >> n;
    

    long int count_a = 0;
    for(int i=0; i<str.size(); i++)
        if( str[i] == 'a' )
            count_a++;
    
    
    //몫
    long int quotient = n / str.size();
    //나머지
    long int remainder = n % str.size();
    
    long int ans = quotient * count_a;
    
    for(int i=0; i<remainder; i++)
        if( str[i] == 'a')
            ans++;
    
    cout << ans;
    
    return 0;
}
