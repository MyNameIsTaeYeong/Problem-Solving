#include <iostream>

using namespace std;

int main()
{
    // n = 5a + 3b
    int n, a, b, remain, result;
    cin >> n;
    
    a = n/5;
    remain = n % 5;
    
    result = -1;
    
    if( remain % 3 == 0 ){
        b = remain / 3;
        result = a+b;
    }else {
        while(a > 0){
            a--;
            remain += 5;
            if(remain % 3 == 0){
                b = remain / 3;
                result = a+b;
                break;
            }
        }
    }
    
    cout << result << '\n';
    
    return 0;
}
