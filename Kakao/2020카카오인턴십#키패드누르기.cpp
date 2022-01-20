#include <string>
#include <vector>

using namespace std;

int dist[10][12];

void dist_cal()
{
    //2
    dist[2][0] = 3;
    dist[2][1] = 1;
    dist[2][2] = 0;
    dist[2][3] = 1;
    
    dist[2][4] = 2;
    dist[2][5] = 1;
    dist[2][6] = 2;
    
    dist[2][7] = 3;
    dist[2][8] = 2;
    dist[2][9] = 3;
    
    // * == 10, # == 11
    dist[2][10] = 4;
    dist[2][11] = 4;
    
    //5
    dist[5][0] = 2;
    dist[5][1] = 2;
    dist[5][2] = 1;
    dist[5][3] = 2;
    
    dist[5][4] = 1;
    dist[5][5] = 0;
    dist[5][6] = 1;
    
    dist[5][7] = 2;
    dist[5][8] = 1;
    dist[5][9] = 2;
    
    // * == 10, # == 11
    dist[5][10] = 3;
    dist[5][11] = 3;
    
    
    //8
    dist[8][0] = 1;
    dist[8][1] = 3;
    dist[8][2] = 2;
    dist[8][3] = 3;
    
    dist[8][4] = 2;
    dist[8][5] = 1;
    dist[8][6] = 2;
    
    dist[8][7] = 1;
    dist[8][8] = 0;
    dist[8][9] = 1;
    
    // * == 10, # == 11
    dist[8][10] = 2;
    dist[8][11] = 2;
    
    
    //0
    dist[0][0] = 0;
    dist[0][1] = 4;
    dist[0][2] = 3;
    dist[0][3] = 4;
    
    dist[0][4] = 3;
    dist[0][5] = 2;
    dist[0][6] = 3;
    
    dist[0][7] = 2;
    dist[0][8] = 1;
    dist[0][9] = 2;
    
    // * == 10, # == 11
    dist[0][10] = 1;
    dist[0][11] = 1;
}

string solution(vector<int> numbers, string hand) {
    string answer = "";
    
    dist_cal();
    
    // 10은 *
    int left = 10;
    // 11은 #
    int right = 11;
    
    for(int i=0; i<numbers.size(); i++){
        if(numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7){
            answer += "L";
            left = numbers[i];
        }else if(numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9){
            answer += "R";
            right = numbers[i];
        }else{
            int choice = dist[numbers[i]][left] - dist[numbers[i]][right];
            
            if(choice>0){
                answer += "R";
                right = numbers[i];
            }else if(choice<0){
                answer += "L";
                left = numbers[i];
            }else{
                if(hand.compare("right") == 0){
                    answer += "R";
                    right = numbers[i];
                }else {
                    answer += "L";
                    left = numbers[i];
                }
            }
        }
    }
    
    return answer;
}
