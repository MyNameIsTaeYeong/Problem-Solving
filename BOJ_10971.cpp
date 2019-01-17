#include<cstdio>
#include<vector>
using namespace std;
int permu[10];

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
        permu[i] = i;
    }
    
    vector<int> v[n];
    
    for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
            int input=0;
            scanf("%d", &input);
            v[i].push_back(input);
        }
    }
    
    int min = 100000000;
    
    bool possible = true;
    
    do{
        int cost=0;
        for(int i=0; i<n-1; i++){
            if(v[permu[i]][permu[i+1]] == 0){
                possible = false;
                break;
            }
            cost += v[permu[i]][permu[i+1]];
        }
        
        
        
        if(possible && v[permu[n-1]][permu[0]]!=0){
            cost += v[permu[n-1]][permu[0]];
            if(cost < min)
                min = cost;
        }
        possible = true;
        
    }while(next_permutation(permu, n));
    
    printf("%d\n", min);
    
    return 0;
}

