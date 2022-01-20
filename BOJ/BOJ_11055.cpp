#include <iostream>
#include <vector>

using namespace std;

int memo[1000];

int main()
{
    int n;
    cin >> n;

    vector<int> numbers(n);

    int ans = 0;

    for(int i=0; i<n; i++){
        cin >> numbers[i];

        int temp = -1;
        
        for(int j=i-1; j>=0; j--){
            if(numbers[j] < numbers[i]){
                if(memo[j] > temp){
                    temp = memo[j];
                }
            }
        }

        if(temp == -1){
            memo[i] = numbers[i];
        }else{
            memo[i] = temp + numbers[i];
        }

        if(memo[i] > ans){
            ans = memo[i];
        }
    }

    cout << ans;

    return 0;
}