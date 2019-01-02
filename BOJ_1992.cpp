#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

int a[64][64];

bool check(int x, int y, int count) {
	for (int i = x; i < x + count; i++) {
		for (int j = y; j < y + count; j++) {
			if (a[x][y] != a[i][j])
				return false;
		}
	}
	return true;
}

void solve(int x, int y, int count) {
	if (check(x, y, count)) {
		printf("%d", a[x][y]);
	}
	else {
		printf("(");
		count /= 2;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				solve(x + count*i, y + count*j, count);
			}
		}
		printf(")");
	}
}
	




int main()
{
	int n;
	scanf("%d", &n);
	for (int i = 0; i < n; i++)
		for (int j = 0; j < n; j++)
			scanf("%1d", &a[i][j]);

	solve(0, 0, n);
	return 0;
}