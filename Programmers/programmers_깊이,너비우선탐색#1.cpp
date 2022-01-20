#include <string>
#include <vector>

using namespace std;

int solve(vector<int> &numbers, int target, int index, int cur_val)
{
    if(index == numbers.size()){
        if(cur_val == target){
            return 1;
        }else {
            return 0;
        }
    }
    
    return solve(numbers, target, index+1, cur_val+numbers[index]) + solve(numbers, target, index+1, cur_val-numbers[index]);
}

int solution(vector<int> numbers, int target) {
    int answer = 0;
    int cur_val = 0;
    answer += solve(numbers, target, 1, cur_val+numbers[0]);
    answer += solve(numbers, target, 1, cur_val-numbers[0]);
    
    return answer;
}
