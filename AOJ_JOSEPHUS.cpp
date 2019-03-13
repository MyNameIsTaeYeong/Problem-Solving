#include<cstdio>
#include<list>
using namespace std;

void josephus(int n, int k){
    list<int> survivors;
    for(int i=1; i<=n; i++)
        survivors.push_back(i);
    
    list<int> :: iterator kill = survivors.begin();
    
    while(n > 2){
        kill = survivors.erase(kill);
        for(int i=1; i<k; i++){
            if(kill == survivors.end()) kill = survivors.begin();
            kill++;
        }
        if(kill == survivors.end()) kill = survivors.begin();
        n--;
    }
    printf("%d %d\n", survivors.front(), survivors.back());
}

int main()
{
    int c;
    scanf("%d", &c);
    while(c--){
        int n, k;
        scanf("%d%d", &n, &k);
        josephus(n, k);
    }
    return 0;
}
