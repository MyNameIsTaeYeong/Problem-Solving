#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int memo[500][500];

int solve(vector< vector<int> > &numbers, int row, int col, int n)
{
    if(row == (n-1)){
        return numbers[row][col];
    }

    if(memo[row][col] != 0){
        return memo[row][col];
    }

    memo[row][col] = numbers[row][col] + max(solve(numbers, row+1, col, n), solve(numbers, row+1, col+1, n));
    
    return memo[row][col];
}

int main()
{
    int n;
    cin >> n;

    vector< vector<int> > numbers(n);

    for(int i=0; i<n; i++){
        for(int j=0; j<=i; j++){
            int input;
            cin >> input;
            numbers[i].push_back(input);
        }
    }

    

    cout << solve(numbers, 0, 0, n);
    
    return 0;
}