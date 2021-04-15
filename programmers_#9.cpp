#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> answers) {
    vector<int> answer;
    
    vector<int> supoja1 = {1,2,3,4,5};
    vector<int> supoja2 = {2, 1, 2, 3, 2, 4, 2, 5};
    vector<int> supoja3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    
    int cnt1 = 0;
    int cnt2 = 0;
    int cnt3 = 0;
    
    auto it1 = supoja1.begin();
    auto it2 = supoja2.begin();
    auto it3 = supoja3.begin();
    
    for(int i=0; i<answers.size(); i++){
        if(answers[i] == *it1){
            cnt1++;
        }
        
        if(answers[i] == *it2){
            cnt2++;
        }
        
        if(answers[i] == *it3){
            cnt3++;
        }
        
        it1++;
        it2++;
        it3++;
        
        if(it1 == supoja1.end()){
            it1 = supoja1.begin();
        }
        
        if(it2 == supoja2.end()){
            it2 = supoja2.begin();
        }
        
        if(it3 == supoja3.end()){
            it3 = supoja3.begin();
        }
    }
    
    if(cnt1 >= cnt2 && cnt1 >= cnt3){
        answer.push_back(1);
    }
    
    if(cnt2 >= cnt1 && cnt2 >= cnt3){
        answer.push_back(2);
    }
    
    if(cnt3 >= cnt1 && cnt3 >= cnt2){
        answer.push_back(3);
    }
    
    
    return answer;
}
