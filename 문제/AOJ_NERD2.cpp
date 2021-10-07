#include<cstdio>
#include<map>
using namespace std;

map<int, int> coords;

map<int, int> createMap(){
    map<int, int> m;
    return m;
}

bool isDominated(int x, int y){
    map<int, int>::iterator it = coords.lower_bound(x);
    if(it == coords.end())  return false;
    return y < it -> second;
}

void removeDominated(int x, int y){
    map<int, int>::iterator it = coords.lower_bound(x);
    if(it == coords.begin())    return;
    --it;
    
    while(true){
        if(it -> second > y)    break;
        
        if(it == coords.begin()){
            coords.erase(it);
            break;
        }else{
            map<int, int>::iterator jt = it;
            --jt;
            coords.erase(it);
            it = jt;
        }
    }
}

int registered(int x, int y){
    if(isDominated(x, y))   return coords.size();
    removeDominated(x, y);
    coords[x] = y;
    return coords.size();
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        int n;
        scanf("%d", &n);
        int ans = 0;
        while(n--){
            int p, q;
            scanf("%d%d", &p, &q);
            ans += registered(p, q);
        }
        printf("%d\n", ans);
        coords = createMap();
    }
    return 0;
}
