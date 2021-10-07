#include <string>
#include <vector>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;
    
    int row = -1;
    for(int col=1; col*col<=yellow; col++){
        if(yellow % col == 0){
            row = yellow / col;
            
            if((row*2 + col*2 + 4) == brown){
                answer.push_back(row+2);
                answer.push_back(col+2);
                break;
            }
        }
        
    }
    
    return answer;
}
