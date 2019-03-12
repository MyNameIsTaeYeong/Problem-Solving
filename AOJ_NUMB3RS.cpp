#include<cstdio>
#include<algorithm>
#include<cstring>
using namespace std;

double cahce[101][51];
int connected[51][51];
int deg[51];
int n,d,p,q;

double probability(int day, int here){
    if(day == d)    return here==q ? 1.0 : 0.0;
    
    double& ret = cahce[day][here];
    if(ret > -0.5)  return ret;
    ret = 0.0;
    for(int i=0; i<n; i++){
        if(connected[here][i])
            ret += probability(day+1, i)/deg[here];
    }
    return ret;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        scanf("%d%d%d", &n, &d, &p);
        memset(deg, 0, sizeof(deg));
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                scanf("%d", &connected[i][j]);
                if(connected[i][j] == 1)
                    deg[i]++;
            }
        }
        
        int t;
        scanf("%d", &t);
        while(t--){
            scanf("%d", &q);
            fill_n(&cahce[0][0], 101*51, -1);
            printf("%.8lf\t", probability(0, p));
        }
        printf("\n");
    }
    return 0;
}
