#include <iostream>

using namespace std;
const int MAX = 1000000;

int main()
{
    int cnt;
    cin >> cnt;
    
    int min_div = MAX, max_div = 0;
    
    for(int i=0; i<cnt; i++){
        int temp;
        cin >> temp;
        if( temp < min_div )
            min_div = temp;
        if( temp > max_div )
            max_div = temp;
    }
    
    
    
    cout << min_div * max_div;
    
    return 0;
}

