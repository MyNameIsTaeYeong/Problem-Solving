#include<cstdio>
#include<algorithm>
using namespace std;

int h[20000];

int solve(int left, int right){
    if(left == right)
        return h[left];
    
    int mid = (left + right) / 2;
    // mid를 기준으로 왼쪽에서만 찾는 경우
    // mid를 기준으로 오른쪽에서만 찾는 경우
    int ret = max(solve(left, mid), solve(mid+1, right));
    
    // mid를 포함하여 왼쪽 오른쪽으로 찾는 경우
    int lo = mid;
    int hi = mid + 1;
    
    int height = min(h[lo], h[hi]);
    
    ret = max(ret, height*2);
    
    while(left < lo || hi < right){
        if(hi < right && (left == lo || (h[lo-1] < h[hi+1]))){
            hi++;
            height = min(height, h[hi]);
        }else{
            lo--;
            height = min(height, h[lo]);
        }
        ret = max(ret, height*(hi-lo+1));
    }
    return ret;
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        int n;
        scanf("%d", &n);
        
        for(int i=0; i<n; i++){
            scanf("%d", &h[i]);
        }
        
        int ans = solve(0, n-1);
        printf("%d\n", ans);
    }
    
    return 0;
}

