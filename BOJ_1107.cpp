#include<cstdio>
using namespace std;

bool broken[10];



int possible(int c){
    
    if(c==0){
        if(broken[0])
            return 0;
        else{
            return 1;
        }
    }
    
    int len = 0;
    
    while(c>0){
        if(broken[c%10])
            return 0;
        c/=10;
        len++;
    }
    return len;
}

int main()
{
    int n, m;
    scanf("%d%d", &n, &m);
    
    for(int i=0;i<m;i++){
        int b;
        scanf("%d", &b);
        broken[b] = true;
    }
    
    int ans = n-100;
    if(ans<0)
        ans = -ans;
    
    for(int i=0; i<1000000; i++){
        int c=i;
        if(int len = possible(c)){
            int press = n - c;
            if(press<0)
                press = -press;
            
            if(ans > len + press)
                ans = len + press;
        }
    }
    
    printf("%d\n", ans);
    
    return 0;
}

