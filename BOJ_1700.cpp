#include <iostream>
#include <vector>

using namespace std;

/*
 - 멀티탭 스케쥴링
 : 코드를 뽑아야하는 상황이 오면 꽂혀있는 코드중에 제일 나중에 나오는 코드를 먼저 뽑는다.
 */

int main()
{
    int n, k;
    vector<int> multitap, seq;
    
    cin >> n >> k;
  
    for(int i=0; i<k; i++){
        int input;
        cin >> input;
        seq.push_back(input);
    }
    
    // 멀티탭에 코드 꽂기 이때 이미 꽂혀있는 경우를 따져줘야한다.
    // 첫번째 사용할 코드를 꽂는다.
    multitap.push_back(seq[0]);
    
    if(multitap.size() != n){
        for(int i=1; i<k; i++){
            
            // 이미 꽂혀있는 경우를 나타내는 변수
            int already = 0;
            
            for(int j=0; j<multitap.size(); j++){
                if(multitap[j] == seq[i]){
                    already = 1;
                    break;;
                }
            }
            
            // 꽂혀있지 않다면 멀티탭에 코드를 꽂는다.
            if(already == 0){
                multitap.push_back(seq[i]);
            }
            
            if(multitap.size() == n)
                break;
        }
    }
    
    
    
    int count = 0;
    
    
    if(multitap.size() == n){
        for(int i=n; i<k; i++){
            
            // 이미 꽂혀있는 경우를 나타내는 변수
            int already = 0;
            
            
            for(int j=0; j<n; j++){
                // 이미 꽂혀있는 경우
                if(multitap[j] == seq[i]){
                    already = 1;
                    break;
                }
            }
            
            // 코드하나를 뽑아야 하는 경우
            if(already == 0){
                // 멀티탭에 꽂혀있는 플러그가 몇번째에서 다시 나오는지를 저장하는 변수.
                vector<int> index(n, 1000);
                
                for(int j=0; j<n; j++){
                    for(int z=i+1; z<k; z++){
                        if(multitap[j] == seq[z]){
                            index[j] = z - n;
                            break;
                        }
                    }
                }
                
                // 제일 나중에 나오는 코드가 몇번째 코드인지 찾는 부분
                int max_index = 0;
                int max_value = index[0];
                for(int j=1; j<n; j++){
                    if(max_value < index[j]){
                        max_value = index[j];
                        max_index = j;
                    }
                }
                
                
                
                multitap[max_index] = seq[i];
                count++;
            }
        }
    }
    
    
    cout << count << '\n';
    
    
    return 0;
}
