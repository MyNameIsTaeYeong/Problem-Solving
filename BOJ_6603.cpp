#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;

int main()
{
    while(1){
        int k;
        scanf("%d", &k);
        
        if(k == 0)
            break;
        
        vector<int> p(k);
        
        for(int i=0; i<k; i++){
            scanf("%d", &p[i]);
        }
        
        vector<int> c;
        
        for(int i=0; i<k-6; i++){
            c.push_back(0);
        }
        
        for(int i=0; i<6; i++){
            c.push_back(1);
        }
        
        vector<vector<int> > ans;
        
        do{
            vector<int> go;
            
            for(int i=0; i<k; i++){
                if(c[i] == 1){
                    go.push_back(p[i]);
                }
            }
            
            ans.push_back(go);
            
        }while(next_permutation(c.begin(), c.end()));
        
        sort(ans.begin(), ans.end());
        
        for(auto &v : ans){
            for(int i=0; i<6; i++)
                printf("%d ", v[i]);
            printf("\n");
        }
        
        printf("\n");
        
    }
    
    
    
    return 0;
}

