#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

int in[100000];
int post[100000];
//인오더의 root 위치
int in_position[100001];
/*
	프리오더		루트		L		R		2	1	3	
	인오더		L		루트		R		1	2	3
	포스트오더	L		R		루트		1	3	2
*/

void solve(int in_start, int in_end, int post_start, int post_end) {
	// 재귀 탈출 조건
	if (in_start > in_end || post_start > post_end) return;

	int root = post[post_end];
	printf("%d", root);

	int left_count = in_position[root] - in_start;

	//L
	solve(in_start, in_position[root] - 1, post_start, post_start + left_count - 1);
	//R
	solve(in_position[root] + 1, in_end, post_start + left_count, post_end - 1);
}

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		scanf("%d", &in[i]);
		in_position[in[i]] = i;
	}
	for (int i = 0; i < n; i++) scanf("%d", &post[i]);
			
	solve(0, n - 1, 0, n - 1);

	return 0;
}