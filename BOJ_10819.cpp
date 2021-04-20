#include <iostream>
#include <vector>
#include <algorithm>
#include <stdlib.h>

using namespace std;

int ans = -1000000;

void swap(int* a, int* b){
    int temp = *a;
    *a = *b;
    *b = temp;
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

void sum_array(vector<int> &array, int n){
    int sum = 0;
    for(int i=0; i<n-1; i++){
        sum += abs(array[i] - array[i+1]);
    }
    
    if(ans < sum){
        ans = sum;
    }
}

int main()
{
    int n;
    cin >> n;
    
    vector<int> array(n);
    
    for(int i=0; i<n; i++){
        cin >> array[i];
    }
    
    sort(array.begin(), array.end());
    
    do{
        sum_array(array, n);
    }while(next_permutation_ty(array));
    
    cout << ans;
    
    return 0;
}
