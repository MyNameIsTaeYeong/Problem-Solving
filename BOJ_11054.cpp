#include <iostream>
#include <vector>

using namespace std;

int memo[1000][2];

int main()
{
    int n;
    cin >> n;

    vector<int> numbers(n);

    for(int i=0; i<n; i++){
        cin >> numbers[i];
    }

    // 가장 긴 감소하는 부분수열 
    for(int i=n-1; i>=0; i--){

        int temp = -1;
        for(int j=i+1; j<n; j++){
            if(numbers[i] > numbers[j]){
                if(memo[j][0] > temp){
                    temp = memo[j][0];
                }
            }
        }

        if(temp == -1){
            memo[i][0] = 1;
        }else{
            memo[i][0] = temp + 1;
        }
    }


    // 가장 긴 증가하는 부분수열 
    for(int i=0; i<n; i++){

        int temp = -1;
        for(int j=i-1; j>=0; j--){
            if(numbers[j] < numbers[i]){
                if(memo[j][1] > temp){
                    temp = memo[j][1];
                }
            }
        }

        if(temp == -1){
            memo[i][1] = 1;
        }else{
            memo[i][1] = temp + 1;
        }
    }

    int ans = 0;

    // 가장 긴 바이토닉 부분수열
    for(int i=0; i<n; i++){ 
        int temp = memo[i][0] + memo[i][1] - 1;
        if(temp > ans){
            ans = temp;
        }
    }

    cout << ans;

    return 0;
}