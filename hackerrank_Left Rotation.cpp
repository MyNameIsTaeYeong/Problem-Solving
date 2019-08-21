#include<iostream>
#include<vector>
using namespace std;

int main()
{
    vector<int> arr;
    int n, d;
    cin >> n >> d;
    
    for(int i=0; i<n; i++){
        int x;
        cin >> x;
        arr.push_back(x);
    }
    
    auto it = arr.begin();
    
    for(int i=0; i<d; i++){
        it++;
        if( it == arr.end() )
            it = arr.begin();
    }
    
    for(int i=0; i<n; i++){
        cout << *it << ' ';
        it++;
        if( it == arr.end() )
            it = arr.begin();
    }
    
    
    return 0;
}
