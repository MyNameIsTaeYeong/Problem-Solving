#include<cstdio>
#include<algorithm>
using namespace std;

int permu[8];

void swap(int *a, int *b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

bool next_permutation(int *a, int n)
{
    int i = n-1;
    while(i>0 && a[i-1]>=a[i]) i--;
    if(i<=0) return false;
    
    int j = n-1;
    while(a[i-1]>=a[j]) j--;
    
    swap(&a[i-1], &a[j]);
    
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
    
    for(int i=0; i<n; i++){
        scanf("%d", &permu[i]);
    }
    
    sort(&permu[0], &permu[n-1]);
    
    int ans = -100000;
    
    do{
        
        int k = 0;
        
        for(int i=0; i<n-1; i++){
            int abs = permu[i]-permu[i+1];
            if(abs<0)
                abs = -abs;
            
            k += abs;
        }
        
        if(ans < k)
            ans = k;
        
    }while(next_permutation(permu, n));
    
    printf("%d\n", ans);
    
    return 0;
}

