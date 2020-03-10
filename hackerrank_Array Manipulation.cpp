#include<iostream>
using namespace std;

unsigned long long arr[10000001];
/*
 Prefix Sum(누적합) : 배열의 요소를 값으로 저장하는 방법이 아니라, 누적할 값으로 저장하는 방식. 예를 들면
 배열 1:   1 3 4  3 5  3  2 8 9
 배열 2:   1 2 1 -1 2 -2 -1 6 1 (1번요소부터 더해보면 배열1을 얻을 수 있다)
 */
int main()
{
    int n, m;
    cin >> n >> m;
    while(m--){
        int a, b, k;
        cin >> a >> b >> k;
        arr[a] += k;
        if(b < n)
            arr[b+1] -= k;
    }
    
    unsigned long long max = arr[1];
    
    for(int i=2; i<=n; i++){
        arr[i] = arr[i-1] + arr[i];
        if(arr[i] > max)
            max = arr[i];
    }
    
    cout << max << '\n';
    
    return 0;
}
