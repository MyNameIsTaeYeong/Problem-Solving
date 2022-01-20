#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

void solve(int n, int x, int y) {
	if (n == 0) return;
	solve(n - 1, x, 6 - x - y);
	printf("%d %d\n", x, y);
	solve(n - 1, 6 - x - y, y);
}

int main()
{
	/*
		1. 1~(n-1)개의 탑을 2번째로 옮긴다
		2. 나머지 1개의 탑을 1번째에서 3번째로 옮긴다.
		3. 1~(n-1)개의 탑을 2번째에서 3번째로 옮긴다.

		n계층 트리의 노드수 => 2^n-1
	*/
	int n;
	scanf("%d", &n);
	printf("%d\n", (1 << n) - 1);
	solve(n, 1, 3);

	return 0;
}