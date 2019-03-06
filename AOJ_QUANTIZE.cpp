#include<cstdio>
#include<cstring>
#include<algorithm>
using namespace std;

/*
        문제 : QUANTIZE
        1. a<b인 a의 양자화된 수열의 값은 b의 양자화된 수열의 값보다 작거나 같다.
            => sort를 해야하는 이유(인근 수열은 같은 양자화된 값을 가질 확률이 높다.
 
        2. quantize(from, parts) = from번째부터 parts개의 부분으로 나눈 오차제곱의 최소값
            => quantize(from, parts) = min[minError[from, from+size-1) + quantize(from+size, parts-1)]
            size는 1부터 n-from까지
 
        3. minError(from, from+size-1) = 구간에서 오차제곱의 최솟값
            => 오차제곱의 합 ( k는 a에서 b까지 (seq[k]-m)^2 은 m에 대한 이차식이며,
               미분계수가 0인 경우가 최솟값이므로 m = a에서 b까지 seq[k]의 합 / b-a+1)
 
        4. seq[]^2 과 seq[]의 합을 미리 계산해 놓으면, minError()를 상수시간에 해결할 수 있다.
 
 */
const int INF = 987654321;

int n, s;
int seq[100];
int cache[100][10];
int pSum[100], pSqSum[100];


void precalc(){
    sort(seq, seq+n);
    
    pSum[0] = seq[0];
    pSqSum[0] = seq[0] * seq[0];
    for(int i=1; i<n; i++){
        pSum[i] = pSum[i-1] + seq[i];
        pSqSum[i] = pSqSum[i-1] + seq[i] * seq[i];
    }
}

int minError(int from, int end){
    int sum = pSum[end] - (from == 0 ? 0 : pSum[from-1]);
    int sqSum = pSqSum[end] - (from == 0 ? 0 : pSqSum[from-1]);
    
    int m = (int)(0.5 + (double)sum / (end-from+1));
    
    return (end-from+1)*m*m - 2*m*sum + sqSum;
}

int quantize(int from, int parts){
    //기저사례
    if(from == n)
        return 0;
    
    if(parts == 0)
        return INF;
    
    int& ret = cache[from][parts];
    if(ret != -1)
        return ret;
    
    ret = INF;
    for(int size=1; size<=n-from; size++)
        ret = min(ret, minError(from, from+size-1) + quantize(from+size, parts-1));
    
    
    return ret;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        memset(cache, -1, sizeof(cache));
        
        scanf("%d %d", &n, &s);
        for(int i=0; i<n; i++)
            scanf("%d", &seq[i]);
        
        precalc();
        printf("%d\n", quantize(0, s));
    }
    return 0;
}
