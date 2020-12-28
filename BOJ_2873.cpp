#include <iostream>

using namespace std;

/*
 
 총 나올 수 있는 경우의 수 3가지
 1. R이 홀수 또는 R,C가 홀수 인경우
 2. C가 홀수인 경우
 3. R,C가 짝수인 경우
    -> 체스판에 비유하면 시작점과 끝점이 흰색 또는 검은색, 즉 같은 색에 대응됨.
    -> 같은색에서 같은색으로 가기위해서는 홀수번 움직여야한다.
    -> 따라서 짝수,짝수인 경우에는 다른색 한칸을 버려야 한다.

*/

int main()
{
    int r, c, element;
    int min_x = 0;
    int min_y = 0;
    int min = 1000;
    
    cin >> r >> c;
    
    for(int i=0; i<r; i++){
        for(int j=0; j<c; j++){
            cin >> element;
            if((i+j) % 2 == 1 && min > element){
                min = element;
                min_x = j;
                min_y = i;
            }
        }
    }
    
    if(r%2 == 1){
        for(int i=0; i<r; i++){
            for(int j=0; j<c-1; j++){
                if(i%2 == 0)
                    printf("R");
                else
                    printf("L");
            }
            if(i == r-1)
                break;
            printf("D");
        }
    }else if(c%2 == 1){
        for(int i=0; i<c; i++){
            for(int j=0; j<r-1; j++){
                if(i%2 == 0)
                    printf("D");
                else
                    printf("U");
            }
            if(i == c-1)
                break;
            printf("R");
        }
    }else{
        int down_from_start, up_from_end, right_from_start, left_from_end;
        down_from_start = (min_y - 0) / 2;
        up_from_end = ((r-1) - min_y) / 2;
        
        right_from_start = (min_x - 0) / 2;
        left_from_end = ((c-1) - min_x) / 2;
        
        while(down_from_start--){
            for(int i=0; i<c-1; i++)
                printf("R");
            printf("D");
            for(int i=0; i<c-1; i++)
                printf("L");
            printf("D");
        }
        
        while(right_from_start--){
            printf("D");
            printf("R");
            printf("U");
            printf("R");
        }
        
        if(min_y % 2 == 0){
            printf("D");
            printf("R");
        }else{
            printf("R");
            printf("D");
        }
        
        while(left_from_end--){
            printf("R");
            printf("U");
            printf("R");
            printf("D");
        }
        
        while(up_from_end--){
            printf("D");
            for(int i=0; i<c-1; i++)
                printf("L");
            printf("D");
            for(int i=0; i<c-1; i++)
                printf("R");
        }
    }
    
    printf("\n");
    
    return 0;
}
