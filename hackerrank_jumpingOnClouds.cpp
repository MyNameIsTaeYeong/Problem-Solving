#include<iostream>
#include<algorithm>
using namespace std;

int cloud[101];
int memo[101];
int n;

int jumpingOnClouds(int index){
    //무한루프에 빠지지 않기위한 기저사례 2개가 필요하다.
    if( index == n )
        return 0;
    
    if( index == n+1)
        return 0;
    
    if( memo[index] != 0 )
        return memo[index];
    
    int temp1 = 1000000;
    if( cloud[index+1] == 0 )
        temp1 = jumpingOnClouds(index+1) + 1;
    
    int temp2 = 1000000;
    if( cloud[index+2] == 0 ){
        temp2 = jumpingOnClouds(index+2) + 1;
    }
    
    return memo[index] = min(temp1, temp2);
}

int main()
{
   
    cin >> n;
    
    for(int i=1; i <= n; i++)
        cin >> cloud[i];
    
    cout << jumpingOnClouds(1);
    
    
    return 0;
}
