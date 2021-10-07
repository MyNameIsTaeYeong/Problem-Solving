#include <iostream>
#include <vector>

using namespace std;

int memo[1001];

int main()
{
    int n;
    cin >> n;

    vector<int> numbers(n);

    for(int i=0; i<n; i++){
        cin >> numbers[i];
    }

    int ans = 0;

    for(int i=n-1; i>=0; i--){

        int temp = -1;
        for(int j=i+1; j<n; j++){
            if(numbers[i] > numbers[j]){
                if(memo[j] > temp){
                    temp = memo[j];
                }
            }
        }

        if(temp == -1){
            memo[i] = 1;
        }else{
            memo[i] = temp + 1;
        }

        if(memo[i] > ans){
            ans = memo[i];
        }
    }

    cout << ans;

    return 0;
}