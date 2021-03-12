#include <iostream>

using namespace std;

int main()
{
    int a, b;
    
    cin >> a >> b;
    
    int ab = a * b;
    
    while( b != 0){
        int temp = a;
        a = b;
        b = temp % b;
    }
    
    int g = a;
    int l = ab / g;
    
    cout << g << '\n' << l;
    
    
    return 0;
}
