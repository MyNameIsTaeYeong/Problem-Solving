#pragma warning(disable:4996)

#include<cstdio>
using namespace std;

char star[2200][2200];

void blank(int x, int y, int m) {
	for (int i = x; i < x + m; i++)
		for (int j = y; j < y + m; j++)
			star[i][j] = ' ';
}

void solve(int x, int y, int n) {
	if (n <= 0)
		return;
	else {
		int m = n / 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1)
					blank(x + i*m, y + j*m, m);
				else
					solve(x + i*m, y + j*m, m);
			}
		}
	}
}

int main()
{
	int n;
	scanf("%d", &n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			star[i][j] = '*';
		}
	}

	solve(0, 0, n);

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			printf("%c", star[i][j]);
		}
		printf("\n");
	}

	return 0;
}