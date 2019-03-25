#include<cstdio>
#include<queue>
using namespace std;

struct RNG{
    unsigned seed;
    RNG() : seed(1983) {}
    unsigned next(){
        unsigned ret = seed;
        seed = ((seed * 214013u) + 2531011u);
        return ret % 10000 + 1;
    }
};

int countRange(int k, int n){
    int ret = 0, rangeSum = 0;
    RNG rng;
    queue<int> q;
    for(int i=0; i<n; i++){
        int newSignal = rng.next();
        rangeSum += newSignal;
        q.push(newSignal);
        
        while(rangeSum > k){
            rangeSum -= q.front();
            q.pop();
        }
        
        if(rangeSum == k)
            ret++;
    }
    return ret;
}

int main()
{
    int c;
    scanf("%d", &c);
    while(c--){
        int k, n;
        scanf("%d%d", &k, &n);
        printf("%d\n", countRange(k, n));
    }
    return 0;
}
