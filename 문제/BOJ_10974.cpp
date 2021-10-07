#include <iostream>
#include <vector>

using namespace std;

void swap(int* a, int* b){
    int temp = *a;
    *a = *b;
    *b = temp;
}

bool prev_permutation_ty(vector<int> &permutation)
{
    unsigned long n = permutation.size();
    unsigned long i = n-1;
    
    while(i>0 && permutation[i-1] <= permutation[i]){
        i--;
    }
    
    if(i==0){
        return false;
    }
    
    
    unsigned long j = n-1;
    
    while(permutation[i-1] <= permutation[j]){
        j--;
    }
    
    swap(&permutation[i-1], &permutation[j]);
    
    j = n-1;
    while(i<j){
        swap(&permutation[i], &permutation[j]);
        i++;
        j--;
    }
    
    return true;
}

bool next_permutation_ty(vector<int> &permutation)
{
    unsigned long n = permutation.size();
    unsigned long i = n-1;
    
    while(i>0 && permutation[i-1] >= permutation[i]){
        i--;
    }
    
    if(i==0){
        return false;
    }
    
    unsigned long j = n-1;
    
    while(permutation[i-1] >= permutation[j]){
        j--;
    }
    
    swap(&permutation[i-1], &permutation[j]);
    
    
    j = n-1;
    while(i<j){
        swap(&permutation[i], &permutation[j]);
        i++;
        j--;
    }
    
    return true;
}

int main()
{
    int n;
    cin >> n;
    
    vector<int> permutation(n);
    
    
    for(int i=0; i<n; i++){
        permutation[i] = i+1;
    }
    
    do{
        for(int i=0; i<permutation.size(); i++){
            cout << permutation[i] << ' ';
        }
        cout<<'\n';
    }while(next_permutation_ty(permutation));
    
    return 0;
}
