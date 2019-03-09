#include<cstdio>
#include<algorithm>
using namespace std;

const int M_MAX = 1000;
int n, m;
double cache[M_MAX+1][2*M_MAX+1];

double climb(int days, int climbed){
    if(days == m){
        if(climbed >= n)
            return 1.0;
        else
            return 0.0;
    }
    double& ret = cache[days][climbed];
    if(ret != -1.0)   return ret;
    
    return ret = 0.25*climb(days+1, climbed+1) + 0.75*climb(days+1, climbed+2);
}

double test(){
    return 0.0;
}

int main()
{
    
    int c;
    scanf("%d", &c);
    
    while(c--){
        fill_n(&cache[0][0], 1001*2001, -1.0);
        scanf("%d %d", &n, &m);
        
        double ans = climb(0, 0);
        
        printf("%.10lf\n", ans);
    }
    
    return 0;
}
