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
		1. 1~(n-1)���� ž�� 2��°�� �ű��
		2. ������ 1���� ž�� 1��°���� 3��°�� �ű��.
		3. 1~(n-1)���� ž�� 2��°���� 3��°�� �ű��.

		n���� Ʈ���� ���� => 2^n-1
	*/
	int n;
	scanf("%d", &n);
	printf("%d\n", (1 << n) - 1);
	solve(n, 1, 3);

	return 0;
}