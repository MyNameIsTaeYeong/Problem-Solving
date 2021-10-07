#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;

int x[101], y[101], r[101];
int n;
int longest;

struct TreeNode{
    vector<TreeNode*> children;
};

int sqr(int x){
    return x*x;
}

int sqrdist(int parent, int child){
    return sqr(x[parent] - x[child]) + sqr(y[parent] - y[child]);
}

bool encloses(int parent, int child){
    return r[parent] > r[child] && sqrdist(parent, child) < sqr(r[parent] - r[child]);
}

bool isChild(int parent, int child){
    if(!encloses(parent, child))    return false;
    for(int i=0; i<n; i++)
        if(i != parent && i != child && encloses(parent, i) && encloses(i, child))
            return false;
    
    return true;
}

TreeNode* getTree(int root){
    TreeNode* ret = new TreeNode();
    for(int i=0; i<n; i++)
        if(isChild(root, i))
            ret->children.push_back(getTree(i));
    return ret;
}


int height(TreeNode* root){
    vector<int> heights;
    for(int i=0; i<root->children.size(); i++){
        heights.push_back(height(root->children[i]));
    }
    
    if(root->children.empty())  return 0;
    sort(heights.begin(), heights.end());
    
    if(heights.size() >= 2)
        longest = max(longest, 2 + heights[heights.size()-1] + heights[heights.size()-2]);
    
    
    return heights.back() + 1;
}

int solve(TreeNode* root){
    longest = 0;
    int h = height(root);
    return max(longest, h);
}

int main()
{
    int c;
    scanf("%d", &c);
    
    while(c--){
        scanf("%d", &n);
        
        for(int i=0; i<n; i++)
            scanf("%d%d%d", &x[i], &y[i], &r[i]);
        
        TreeNode* root = getTree(0);
        int ans = solve(root);
        printf("%d\n", ans);
    }
    
    
    
    return 0;
}
