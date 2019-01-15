#include<cstdio>
using namespace std;

int permu[10000];

void swap(int *a, int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

bool next_permutation(int *a, int n){
    //1. a[i-1]<a[i]중 가장 큰 i
    int i = n-1;
    while(i>0 && a[i-1]>=a[i]) i--;
    if(i<=0) return false; // 마지막 순열
    
    //2. i<=j중 a[i-1]<a[j]를 만족시키는 가장 큰 j
    int j = n-1;
    while(a[i-1]>=a[j]) j--;
    
    //3. a[i-1] 와 a[j] swap
    swap(&a[i-1], &a[j]);
    
    //4. a[i]부터 뒤집기
    j = n-1;
    while(i<j){
        swap(&a[i], &a[j]);
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
    
    if(next_permutation(permu, n)){
        for(int i=0; i<n; i++)
            printf("%d ", permu[i]);
    }else{
        printf("%d", -1);
    }
    puts("");
    return 0;
}

