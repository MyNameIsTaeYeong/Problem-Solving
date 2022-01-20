#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

int in[100000];
int post[100000];
//�ο����� root ��ġ
int in_position[100001];
/*
	��������		��Ʈ		L		R		2	1	3	
	�ο���		L		��Ʈ		R		1	2	3
	����Ʈ����	L		R		��Ʈ		1	3	2
*/

void solve(int in_start, int in_end, int post_start, int post_end) {
	// ��� Ż�� ����
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