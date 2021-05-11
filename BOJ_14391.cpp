#include <stdio.h>

using namespace std;

int paper[4][4];

int main()
{
    int n, m;
    scanf("%d %d", &n, &m);

    for(int i=0; i<n; i++){
        for(int j=0; j<m; j++){
            scanf("%1d", &paper[i][j]);
        }
    }

    int ans = 0;

    for(int bit=0; bit<(1<<(n*m)); bit++){
        int sum = 0;

        for(int i=0; i<n; i++){
            int cur = 0;
            for(int j=0; j<m; j++){
                if((bit & 1<<(i*m+j)) == 0){
                    cur = cur*10 + paper[i][j];
                }else{
                    sum += cur;
                    cur = 0;
                }
            }
            sum += cur;
        }

        for(int j=0; j<m; j++){
            int cur = 0;
            for(int i=0; i<n; i++){
                if(bit & 1<<(i*m+j)){
                    cur = cur*10 + paper[i][j];
                }else{
                    sum += cur;
                    cur = 0;
                }
            }
            sum += cur;
        }

        if(sum > ans){
            ans = sum;
        }
    }

    printf("%d", ans);


    return 0;
}