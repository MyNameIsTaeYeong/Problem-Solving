#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;

int n;

vector<int> slice(const vector<int>& v, int begin, int end){
    return vector<int>(v.begin()+begin, v.begin()+end);
}

void postorder(const vector<int>& preorder, const vector<int>& inorder){
    if(preorder.empty())    return;
    
    int length = inorder.size();
    
    int root = preorder[0];
    int root_index = find(inorder.begin(), inorder.end(), root) - inorder.begin();
    
    //왼쪽
    postorder(slice(preorder, 1, root_index+1), slice(inorder, 0, root_index));
    //오른쪽
    postorder(slice(preorder, root_index+1, length), slice(inorder, root_index+1, length));
    
    printf("%d ", root);
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        scanf("%d", &n);
        
        vector<int> preorder, inorder;
        
        for(int i=0; i<n; i++){
            int x;
            scanf("%d", &x);
            preorder.push_back(x);
        }
        
        for(int i=0; i<n; i++){
            int x;
            scanf("%d", &x);
            inorder.push_back(x);
        }
        
        postorder(preorder, inorder);
        printf("\n");
    }
    return 0;
}
