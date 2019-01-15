#include<cstdio>
using namespace std;

int permu[8];

void swap(int *a, int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

bool next_permutation(int *a, int n){
    int i=n-1;
    
    //1. a[i-1]<a[i] 를 만족하는 가장 큰 i
    while(i>0 && a[i-1]>=a[i]) i--;
    if(i<=0) return false;
    
    int j = n-1;
    while(a[j]<=a[i-1]) j--;
    
    swap(&a[i-1], &a[j]);
    
    j = n-1;
    while(i<j){
        swap(&a[i], &a[j]);
        i++;    j--;
    }
    
    
    return true;
}

int main()
{
    int n;
    scanf("%d",&n);
    for(int i=0; i<n; i++)
        permu[i] = i+1;
    
    do{
        for(int i=0; i<n; i++)
            printf("%d ", permu[i]);
        puts("");
    }while(next_permutation(permu, n));
    return 0;
}

