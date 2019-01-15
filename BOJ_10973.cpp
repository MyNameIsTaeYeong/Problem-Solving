#include<cstdio>
using namespace std;

int permu[10000];

void swap(int *a, int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

bool prev_permutation(int *a, int n){
    //1. a[i] > a[i+1]중 가장 작은 i
    int i = 0;
    while(i<n-1 && a[i]<=a[i+1]) i++;
    if(i>=n-1) return false; // 마지막 순열
    
    //2. j<=i중 a[j] > a[i+1]를 만족시키는 가장 작은 j
    int j = 0;
    while(a[j] <= a[i+1]) j++;
    
    //3. a[i-1] 와 a[j] swap
    swap(&a[i+1], &a[j]);
    
    //4. a[i]부터 뒤집기
    j = n-1;
    while(i<j){
        swap(&a[i+1], &a[j]);
        i++; j--;
    }
    
    return true;
}

int main()
{
    int n;
    scanf("%d", &n);
    for(int i=0; i<n; i++)
        scanf("%d", &permu[i]);
    
    if(prev_permutation(permu, n)){
        for(int i=0; i<n; i++)
            printf("%d ", permu[i]);
    }else{
        printf("%d", -1);
    }
    puts("");
    return 0;
}
