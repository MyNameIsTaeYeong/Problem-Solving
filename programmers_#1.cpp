#include <string>
#include <vector>
#include <algorithm>
using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    sort(participant.begin(), participant.end());
    sort(completion.begin(), completion.end());
    
    for(int i=0; i<completion.size(); i++){
        if(completion[i] != participant[i]){
            answer = participant[i];
            break;
        }
        if(i == completion.size()-1){
            answer = participant[i+1];
        }
    }
    return answer;
}
