#include <string>
#include <vector>

using namespace std;

const int MAX = 10000000;
bool not_prime[MAX];
bool duplication[MAX];
bool check[10];

int solve(vector<int> number, int index, int cur,unsigned long last_index)
{
    int rtn = 0;
    
    if(index == last_index){
        if(!not_prime[cur] && !duplication[cur]){
            rtn = 1;
            duplication[cur] = true;
            return rtn;
        }else{
            rtn = 0;
            return rtn;
        }
    }
    
    rtn += solve(number, index+1, cur, last_index);
    
    for(int i=0; i<last_index; i++){
        if(!check[i]){
            check[i] = true;
            rtn += solve(number, index+1, cur*10+number[i], last_index);
            check[i] = false;
        }
    }
    
    return rtn;
}


int solution(string numbers) {
    int answer = 0;
    
    not_prime[0] = true;
    not_prime[1] = true;
    for(int i=2; i*i<MAX; i++){
        if(!not_prime[i]){
            for(int j=i*2; j<MAX; j+=i){
                not_prime[j] = true;
            }
        }
    }
    
    vector<int> number;
    
    for(int i=0; i<numbers.size(); i++){
        number.push_back(numbers[i] - '0');
    }
    
    answer = solve(number, 0, 0, number.size());
    
    
    return answer;
}
