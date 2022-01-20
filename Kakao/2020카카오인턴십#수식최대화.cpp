#include <string>
#include <vector>
#include <algorithm>

#include <iostream>

using namespace std;

long long solve(string expression, int index, char first, char second, char third)
{
    for(int i=index; i>0; i--){
        if(expression[i] == third){
            if(third == '*'){
                return solve(expression.substr(0,i), i-1, first, second, third) * solve(expression.substr(i+1), index-i-1, first, second, third);
            }else if (third == '+'){
                return solve(expression.substr(0,i), i-1, first, second, third) + solve(expression.substr(i+1), index-i-1, first, second, third);
            }else{
                return solve(expression.substr(0,i), i-1, first, second, third) - solve(expression.substr(i+1), index-i-1, first, second, third);
            }
        }
    }
    
    for(int i=index; i>0; i--){
        if(expression[i] == second){
            if(second == '*'){
                return solve(expression.substr(0,i), i-1, first, second, third) * solve(expression.substr(i+1), index-i-1, first, second, third);
            }else if (second == '+'){
                return solve(expression.substr(0,i), i-1, first, second, third) + solve(expression.substr(i+1), index-i-1, first, second, third);
            }else{
                return solve(expression.substr(0,i), i-1, first, second, third) - solve(expression.substr(i+1), index-i-1, first, second, third);
            }
        }
    }
    
    for(int i=index; i>0; i--){
        if(expression[i] == first){
            if(first == '*'){
                return solve(expression.substr(0,i), i-1, first, second, third) * solve(expression.substr(i+1), index-i-1, first, second, third);
            }else if (first == '+'){
                return solve(expression.substr(0,i), i-1, first, second, third) + solve(expression.substr(i+1), index-i-1, first, second, third);
            }else{
                return solve(expression.substr(0,i), i-1, first, second, third) - solve(expression.substr(i+1), index-i-1, first, second, third);
            }
        }
    }
    
    return stoll(expression, nullptr, 10);
}

long long solution(string expression) {
    long long answer = 0;
    vector<char> operators = {'*', '+', '-'};
    sort(operators.begin(), operators.end());
    
    
    do{
        long long temp = solve(expression, expression.length()-1, operators[0], operators[1], operators[2]);
        if(temp<0){
            temp = -temp;
        }
        if(temp > answer){
            answer = temp;
        }
    }while(next_permutation(operators.begin(), operators.end()));
    
    return answer;
}
